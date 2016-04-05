package com.tsystems.javaschool.webshop.controllers.backend;

import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

import static com.tsystems.javaschool.webshop.controllers.utils.StringConstants.*;

/**
 * Category CRUD management in backend.
 */
@Controller
public class BackendCategoryController {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(BackendCategoryController.class);
    private static final String BACKEND_CATEGORY_PAGE = "/backend/categories";
    private static final String SELECTED_CATEGORY = "selectedCategory";


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

    @RequestMapping(path = BACKEND_CATEGORY_PAGE, method= RequestMethod.GET)
    public final String getBackendCategory(@RequestParam(defaultValue = "-1")
                                           final int categoryId,
                                           @ModelAttribute
                                           final List<Category> categories,
                                           final Model model) {
        if (categoryId == -1) {        //when enter page without params
            if (categories != null && categories.size() > 0) {
                model.addAttribute(SELECTED_CATEGORY,
                        categoryService.get(categories.get(0).getId()));
            }
        } else {
            if (categoryId > 0) {                       //when choose category in sidebar
                model.addAttribute(SELECTED_CATEGORY,
                        categoryService.get(categoryId));
            } else {
                model.addAttribute(SELECTED_CATEGORY,
                        new Category());
            }
        }
        return BACKEND_CATEGORY_PAGE;
    }

    @RequestMapping(path = BACKEND_CATEGORY_PAGE,
            params = ADD_ACTION, method = RequestMethod.POST)
    public final String addProduct(@ModelAttribute(value = SELECTED_CATEGORY)
                                   @Valid final Category category,
                                   final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return BACKEND_CATEGORY_PAGE;
        }
        categoryService.add(category);
        return "redirect:" + BACKEND_CATEGORY_PAGE + "?categoryId="+ category.getId();
    }

    @RequestMapping(path = BACKEND_CATEGORY_PAGE,
            params = SAVE_ACTION, method = RequestMethod.POST)
    public final String saveProduct(@ModelAttribute(value = SELECTED_CATEGORY)
                                    @Valid final Category category,
                                    final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BACKEND_CATEGORY_PAGE;
        }
        categoryService.update(category);
        return "redirect:" + BACKEND_CATEGORY_PAGE + "?categoryId="+ category.getId();
    }

    @RequestMapping(path = BACKEND_CATEGORY_PAGE,
            params = REMOVE_ACTION, method = RequestMethod.POST)
    public final String removeCategory(@RequestParam final int id,
                                            final RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);

        } catch (ServiceException e) {
            LOGGER.warn(e.getMessage(), e);
            redirectAttributes.addAttribute(REMOVE_FAILED,
                    "Can't remove category: Category already has products");
                return "redirect:" + BACKEND_CATEGORY_PAGE + "?categoryId=" + id;
        }

        return "redirect:" + BACKEND_CATEGORY_PAGE;
    }
}
