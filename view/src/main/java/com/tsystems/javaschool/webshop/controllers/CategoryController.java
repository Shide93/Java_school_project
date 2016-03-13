package com.tsystems.javaschool.webshop.controllers;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Shide on 13.03.2016.
 */
@Controller
public class CategoryController {

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
        model.addAttribute("categoryList", categoryService.getAllIdNames());
        return "sidebar";
    }

}
