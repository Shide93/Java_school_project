package com.tsystems.javaschool.webshop.controllers.backend;

import com.fasterxml.jackson.annotation.JsonView;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.tsystems.javaschool.webshop.controllers.utils.StringConstants.*;

/**
 * Created by Shide on 30.03.2016.
 */
@Controller
public class BackendFeaturesController {

    /**
     * The constant BACKEND_FEATURES_PAGE.
     */
    private static final String BACKEND_FEATURES_PAGE = "/backend/features";

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(BackendFeaturesController.class);
    /**
     * The Feature service.
     */
    @Autowired
    private FeatureService featureService;

    /**
     * Gets features page.
     *
     * @param model the model
     * @return the features page
     */
    @RequestMapping(value = BACKEND_FEATURES_PAGE, method = RequestMethod.GET)
    public String getFeaturesPage(final Model model) {

        model.addAttribute("features", featureService.getAll());
        return BACKEND_FEATURES_PAGE;
    }

    /**
     * Add feature feature.
     *
     * @param name  the name
     * @param model the model
     * @return the feature
     */
    @RequestMapping(value = BACKEND_FEATURES_PAGE, params = ADD_ACTION,
            method = RequestMethod.POST)
    @ResponseBody
    @JsonView(Feature.class)
    public Feature addFeature(@RequestParam final String name,
                              final Model model) {
        Feature feature = new Feature();
        feature.setName(name);
        featureService.add(feature);
        return feature;
    }

    /**
     * Edit feature feature.
     *
     * @param id    the id
     * @param name  the name
     * @param model the model
     * @return the feature
     */
    @RequestMapping(value = BACKEND_FEATURES_PAGE, params = SAVE_ACTION,
            method = RequestMethod.POST)
    @ResponseBody
    @JsonView(Feature.class)
    public Feature editFeature(@RequestParam final int id,
                               @RequestParam final String name,
                              final Model model) {
        Feature feature = new Feature();
        feature.setId(id);
        feature.setName(name);
        featureService.update(feature);
        return feature;
    }

    /**
     * Remove feature string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = BACKEND_FEATURES_PAGE, params = REMOVE_ACTION,
            method = RequestMethod.POST)
    @ResponseBody
    public String removeFeature(@RequestParam final int id,
                                final Model model) {

        try {
            featureService.delete(id);
        } catch (ServiceException e) {
            return "{ \"removeFailed\" : \"Can't remove feature: it is already assigned to product.\" }";
        }
        return "{ \"id\" : \"" + id + "\" }";
    }
}
