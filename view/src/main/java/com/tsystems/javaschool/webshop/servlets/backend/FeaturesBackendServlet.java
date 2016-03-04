package com.tsystems.javaschool.webshop.servlets.backend;

import com.tsystems.javaschool.webshop.dao.entities.FeatureEntity;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.impl.FeatureServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;
import com.tsystems.javaschool.webshop.servlets.SaveProfileServlet;
import flexjson.JSONSerializer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Shide on 28.02.2016.
 */
public class FeaturesBackendServlet extends HttpServlet {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(SaveProfileServlet.class);
    /**
     * The Feature service.
     */
    private final FeatureService featureService;
    /**
     * The Json serializer.
     */
    private final JSONSerializer jsonSerializer;
    /**
     * The Validation service.
     */
    private final ValidationService validationService;
    /**
     * Instantiates a new Features backend servlet.
     */
    public FeaturesBackendServlet() {
        featureService = new FeatureServiceImpl();
        jsonSerializer = new JSONSerializer();
        validationService = new ValidationServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        //get all features
        req.setAttribute("features", featureService.getAll());
       // req.setAttribute("feature_types", FeatureType.values());
        req.getRequestDispatcher("/backend/features.jsp").forward(req, resp);
    }

    @Override
    protected final void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        String name = req.getParameter("name");

        if (action.equals("add")) {
            FeatureEntity feature = new FeatureEntity();
            feature.setName(name);
            featureService.add(feature);
            resp.getWriter().println(jsonSerializer.serialize(feature));
        } else if (action.equals("save")) {
            Integer id = Integer.parseInt(idStr);
            FeatureEntity feature = new FeatureEntity();
            feature.setId(id);
            feature.setName(name);
            featureService.update(feature);
            resp.getWriter().println(jsonSerializer.serialize(feature));

        } else if (action.equals("remove")) {
            Integer id = Integer.parseInt(idStr);
            try {
                featureService.delete(id);
            } catch (Exception e) {
                LOGGER.warn("Can't remove feature", e);
                resp.getWriter().println(
                        "{\"cantRemove\": \"Can't remove feature: "
                                + "it is already assigned to product\"}");

                return;
            }
            resp.getWriter().println(jsonSerializer.serialize(id));

        }
    }
}
