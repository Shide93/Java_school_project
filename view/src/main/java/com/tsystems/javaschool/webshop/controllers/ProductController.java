package com.tsystems.javaschool.webshop.controllers;

import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.services.api.ProductService;
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
public class ProductController {

    /**
     * The Product service.
     */
    @Autowired
    private ProductService productService;

    /**
     * Gets product by id in path.
     * <p/>
     * if product doesn't exist, assigns null to product param
     *
     * @param productId the product id
     * @param model     the model to assign product data
     * @return product page
     */
    @RequestMapping(path = "/product/{productId:[0-9]+}", method= RequestMethod.GET)
    public final String getProduct(@PathVariable final int productId,
                                    final Model model) {

        ProductEntity product = productService.get(productId);
        model.addAttribute("product", product);
        return "product";
    }

    /**
     * Gets null product, if path without productId.
     *
     * @param model the model
     * @return product page
     */
    @RequestMapping(path = "/product/*", method= RequestMethod.GET)
    public final String getNullProduct(final Model model) {
        model.addAttribute("product", null);
        return "product";
    }
}
