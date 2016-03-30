package com.tsystems.javaschool.webshop.controllers.backend;

import com.fasterxml.jackson.annotation.JsonView;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Shide on 30.03.2016.
 */
@Controller
public class BackendFeaturesController {

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

    @RequestMapping(value = "/backend/features", method = RequestMethod.GET)
    public String getFeaturesPage(Model model) {

        model.addAttribute("features", featureService.getAll());
        return "backend/features";
    }

    @RequestMapping(value = "/backend/features", params = "action=add",
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

    @RequestMapping(value = "/backend/features", params = "action=save",
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

    @RequestMapping(value = "/backend/features", params = "action=remove",
            method = RequestMethod.POST)
    @ResponseBody
    public int removeFeature(@RequestParam final int id,
                                final Model model) {

        featureService.delete(id);
        //TODO: handle exception if already assigned to product
        return id;
    }
}
