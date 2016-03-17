package com.tsystems.javaschool.webshop.controllers.backend;

import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.Product;
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

/**
 * Created by Shide on 18.03.2016.
 */
@Controller
public class BackendProduct {
    /**
     * The Product service.
     */
    @Autowired
    private ProductService productService;

    /**
     * The Feature service.
     */
    @Autowired
    private FeatureService featureService;

    @RequestMapping(path = "/backend/products", method = RequestMethod.GET)
    public final String getProductEditPage(@RequestParam(defaultValue = "-1")
                                           int productId,  Model model) {

        //get all products
        List<Product> products = productService.getAll();
        List<Feature> features = featureService.getAll();

        if (productId == -1) {         //when enter page without params
            if (products != null && products.size() > 0) {
                model.addAttribute("selectedProduct", products.get(0));
            }
        } else {                        //when choose product in sidebar
            if (productId > 0) {        //if productId == 0 - it's new product
                model.addAttribute("selectedProduct",
                        productService.get(productId));
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("features", features);
        return "backend/products";
    }

    @RequestMapping(path = "/backend/products",
            params = "action=save", method = RequestMethod.POST)
    public final String saveProduct(@ModelAttribute Product product,
                                    final Model model) {
        productService.update(product);
        //TODO: accept features
        return "";
    }

    public final String createProduct() {
        return "";
    }

    public final String removeProduct() {
        return "";
    }
}
