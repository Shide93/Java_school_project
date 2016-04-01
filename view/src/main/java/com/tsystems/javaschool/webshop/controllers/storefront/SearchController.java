package com.tsystems.javaschool.webshop.controllers.storefront;

import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Shide on 13.03.2016.
 */
@Controller
public class SearchController {
    /**
     * The Feature service.
     */
    @Autowired
    private FeatureService featureService;

    /**
     * The Product service.
     */
    @Autowired
    private ProductService productService;

    /**
     * Gets feature list.
     *
     * @return the feature list
     */
    @ModelAttribute("features")
    public final List<Feature> getFeatureList() {
        return this.featureService.getAll();
    }

    /**
     * Gets feature values list.
     *
     * @return the feature values list
     */
    @ModelAttribute("featureValues")
    public final List<ProductFeature> getFeatureValuesList() {
        return this.featureService.getAllCategoryValues();
    }

    /**
     * Returns search page.
     *
     * @return the search page
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public final String getSearchPage() {
        return "search";
    }

    /**
     * Performs search by specified features.
     *
     * @param model            the model
     * @param selectedFeatures the selected features
     * @return search result view
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public final String searchByFeatures(final Model model,
                                         @RequestParam(name = "features[]")
                                         final Map<String, List<String>> selectedFeatures) {

        //TODO: try to accept 2d array from form
       // List<Product> products =
            //    productService.searchByFeature(selectedFeatures);
      //  model.addAttribute("products", products);
        return "searchResult";
    }
   /* protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {
        String[] selectedFeatures = req.getParameterValues("features");
        List<ProductEntity> products =
                productService.searchByFeature(selectedFeatures);

        req.setAttribute("products", products);
        req.getRequestDispatcher("/searchResult.jsp").forward(req, resp);
    }*/
}
