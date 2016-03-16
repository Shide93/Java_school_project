package com.tsystems.javaschool.webshop.controllers;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.servlets.SaveProfileServlet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by Shide on 13.03.2016.
 */
@Controller
public class CategoryController {
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

    /**
     * Gets category by id in path.
     * <p/>
     * if category doesn't exist, assigns null to category param
     *
     * @param categoryId the category id
     * @param model      the model to assign category data
     * @return category page
     */
    @RequestMapping(path = "/category/{categoryId:[0-9]+}", method= RequestMethod.GET)
    public final String getCategory(@PathVariable final int categoryId,
                                    final Model model) {

        CategoryEntity category = categoryService.get(categoryId);
        model.addAttribute("category", category);
        return "category";
    }

    /**
     * Gets null category, if path without categoryId.
     *
     * @param model the model
     * @return category page
     */
    @RequestMapping(path = "/category/*", method= RequestMethod.GET)
    public final String getNullCategory(final Model model) {
        model.addAttribute("category", null);
        return "category";
    }

    /**
     * Gets category list for sidebar.
     *
     * @param model the model
     * @return sidebar jsp
     */
    @RequestMapping(path = "/sidebar", method = RequestMethod.GET)

    public final String getCategorySidebarList(final Model model) {
        model.addAttribute("categoryList", categoryService.getAllIdNames()); //TODO: move to appcontext
        return "sidebar";
    }

    /*********Backend**********/
    /**
     * Gets null category, if path without categoryId.
     *
     * @param categoryId the category id
     * @param model      the model
     * @return category page
     */
    @RequestMapping(path = "backend/categories", method= RequestMethod.GET)
    public final String getBackendCategory(@RequestParam(defaultValue = "-1")
                                            final int categoryId,
                                            final Model model) {
        List<CategoryEntity> categories = categoryService.getAllIdNames(); //TODO: take from appcontext
        model.addAttribute("categoryList", categories);
        if (categoryId == -1) {        //when enter page without params
            if (categories != null && categories.size() > 0) {
                model.addAttribute("selectedCategory",
                        categoryService.get(categories.get(0).getId()));
            }

        } else {
            if (categoryId > 0) {                       //when choose category in sidebar
                model.addAttribute("selectedCategory",
                        categoryService.get(categoryId));
            }
        }

        return "backend/categories";
    }

    @RequestMapping(path = "/backend/categories",
            params = "action=add", method = RequestMethod.POST)
    public final RedirectView addProduct(@ModelAttribute CategoryEntity category,
                                          final Model model) {
        categoryService.add(category);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/backend/categories?categoryId="+ category.getId());
        return redirectView;
    }

    @RequestMapping(path = "/backend/categories",
            params = "action=save", method = RequestMethod.POST)
    public final RedirectView saveProduct(@ModelAttribute CategoryEntity category,
                                          final Model model) {
        categoryService.update(category);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/backend/categories?categoryId="+ category.getId());
        return redirectView;
    }

    @RequestMapping(path = "/backend/categories",
            params = "action=remove", method = RequestMethod.POST)
    public final RedirectView removeProduct(@RequestParam int id,
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

        redirectView.setUrl("/backend/categories?categoryId="+ id);
        return redirectView;
    }
    //TODO: refresh category list in sidebar while add or remove
}
