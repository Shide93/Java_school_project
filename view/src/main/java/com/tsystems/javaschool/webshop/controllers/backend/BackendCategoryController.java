package com.tsystems.javaschool.webshop.controllers.backend;

import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.servlets.SaveProfileServlet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

/**
 * Category CRUD management in backend.
 */
@Controller
public class BackendCategoryController {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SaveProfileServlet.class);
    /**
     * The Category service.
     */
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categoryList")
    public final List<Category> getFeatureValuesList() {
        return this.categoryService.getAllIdNames();
        //TODO: make that list will be fetched once
    }

    @RequestMapping(path = "backend/categories", method= RequestMethod.GET)
    public final String getBackendCategory(@RequestParam(defaultValue = "-1")
                                           final int categoryId,
                                           @ModelAttribute
                                           final List<Category> categories,
                                           final Model model) {
        if (categoryId == -1) {        //when enter page without params
            if (categories != null && categories.size() > 0) {
                model.addAttribute("selectedCategory",
                        categoryService.get(categories.get(0).getId()));
            }
        } else {
            if (categoryId > 0) {                       //when choose category in sidebar
                model.addAttribute("selectedCategory",
                        categoryService.get(categoryId));
            } else {
                model.addAttribute("selectedCategory",
                        new Category());
            }
        }
        return "backend/categories";
    }

    @RequestMapping(path = "/backend/categories",
            params = "action=add", method = RequestMethod.POST)
    public final String addProduct(@ModelAttribute(value = "selectedCategory")
                                   @Valid final Category category,
                                   final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "/backend/categories";
        }
        categoryService.add(category);
        return "redirect:/backend/categories?categoryId="+ category.getId();
    }

    @RequestMapping(path = "/backend/categories",
            params = "action=save", method = RequestMethod.POST)
    public final String saveProduct(@ModelAttribute(value = "selectedCategory")
                                    @Valid final Category category,
                                    final BindingResult bindingResult,
                                    final Model model) {
        if (bindingResult.hasErrors()) {
            return "/backend/categories";
        }
        categoryService.update(category);
        return "redirect:/backend/categories?categoryId="+ category.getId();
    }

    @RequestMapping(path = "/backend/categories",
            params = "action=remove", method = RequestMethod.POST)
    public final String removeProduct(@RequestParam final int id,
                                            final Model model) {
        RedirectView redirectView = new RedirectView();
        try {
            categoryService.delete(id);

        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            model.addAttribute("cantRemove",
                    "Can't remove category: Category has products");
            //TODO: add message in jsp.
        }

        return "redirect:/backend/categories";
    }
}
