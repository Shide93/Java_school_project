package com.tsystems.javaschool.webshop.controllers.storefront;

import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.servlets.SaveProfileServlet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

/**
 * Shows category in storefront.
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

        Category category = categoryService.get(categoryId);
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
}
