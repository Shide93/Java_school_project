package com.tsystems.javaschool.webshop.controllers.backend;

import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.tsystems.javaschool.webshop.controllers.utils.StringConstants.*;

/**
 * Created by Shide on 18.03.2016.
 */
@Controller
public class BackendProductController {

    /**
     * The constant BACKEND_PRODUCT_PAGE.
     */
    public static final String BACKEND_PRODUCT_PAGE = "/backend/products";
    /**
     * The constant SELECTED_PRODUCT.
     */
    public static final String SELECTED_PRODUCT = "selectedProduct";

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(BackendProductController.class);

    /**
     * The Product service.
     */
    @Autowired
    private ProductService productService;

    /**
     * The Category service.
     */
    @Autowired
    private CategoryService categoryService;
    /**
     * The Feature service.
     */
    @Autowired
    private FeatureService featureService;

    /**
     * Populate model.
     *
     * @param model the model
     */
    @ModelAttribute
    public final void populateModel(final Model model) {
        List<Product> products = productService.getAll();
        List<Feature> features = featureService.getAll();
        List<Category> categories = categoryService.getAllIdNames();
        ProductFeature productFeature = new ProductFeature();


        model.addAttribute("products", products);
        model.addAttribute("features", features);
        model.addAttribute("categoryList", categories);
        model.addAttribute("productFeature", productFeature);
    }

    /**
     * Gets product edit page.
     *
     * @param productId the product id
     * @param products  the products
     * @param model     the model
     * @return the product edit page
     */
    @RequestMapping(path = BACKEND_PRODUCT_PAGE, method = RequestMethod.GET)
    public final String getProductEditPage(@RequestParam(defaultValue = "0")
                                           final int productId,
                                           @ModelAttribute(value = "products")
                                           final List<Product> products,
                                           final Model model) {


        if (productId == 0
                && products != null
                && products.size() > 0) {         //when enter page without params
            model.addAttribute(SELECTED_PRODUCT, products.get(0));
        } else if (productId > 0) {         //when choose product in sidebar
            model.addAttribute(SELECTED_PRODUCT,
                        productService.get(productId));
        }
        return BACKEND_PRODUCT_PAGE;
    }

    /**
     * Create product string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(path = BACKEND_PRODUCT_PAGE,
            params = ADD_ACTION, method = RequestMethod.POST)
    public final String createProduct(final Model model) {
        Product product = new Product();
        product.setName("New product");
        product.setPrice(0);
        productService.add(product);
        return "redirect:" + BACKEND_PRODUCT_PAGE + "?productId="
                + product.getId();

    }

    /**
     * Save product string.
     *
     * @param product            the product
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping(path = BACKEND_PRODUCT_PAGE,
            params = SAVE_ACTION, method = RequestMethod.POST)
    public final String saveProduct(@ModelAttribute(value = "product")
                                        final Product product,
                                    final RedirectAttributes redirectAttributes) {
        productService.update(product);
        return "redirect:" + BACKEND_PRODUCT_PAGE
                + "?productId=" + product.getId();
    }

    /**
     * Remove product string.
     *
     * @param id                 the id
     * @param redirectAttributes the redirect attributes
     * @param model              the model
     * @return the string
     */
    @RequestMapping(path = BACKEND_PRODUCT_PAGE,
            params = REMOVE_ACTION, method = RequestMethod.POST)
    public final String removeProduct(@RequestParam final int id,
                                      final RedirectAttributes redirectAttributes,
                                      final Model model) {
        try {
            productService.delete(id);
        } catch (ServiceException e) {
            LOGGER.warn("Can't remove product" + id, e);
            redirectAttributes.addAttribute(REMOVE_FAILED, "Can't remove product.");

            return "redirect:" + BACKEND_PRODUCT_PAGE + "?productId=" + id;
        }
        return "redirect:" + BACKEND_PRODUCT_PAGE;
    }

    /**
     * Add feature string.
     *
     * @param productFeature     the product feature
     * @param redirectAttributes the redirect attributes
     * @param model              the model
     * @return the string
     */
    @RequestMapping(path = BACKEND_PRODUCT_PAGE,
            params = ADD_FEATURE_ACTION, method = RequestMethod.POST)
    public final String addFeature(@ModelAttribute(value = "productFeature")
                                       final ProductFeature productFeature,
                                   final RedirectAttributes redirectAttributes,
                                   final Model model) {
        Product product = null;
        try {
            product = productService.addNewProductFeature(productFeature);
            model.addAttribute(SELECTED_PRODUCT, product);
        } catch (ServiceException e) {
            LOGGER.warn("Feature creation failed", e);
            redirectAttributes.addAttribute(ADD_FEATURE_FAILED, "Cannot add feature: "
                    + e.getMessage());

        }
        return "redirect:" + BACKEND_PRODUCT_PAGE + "?productId="
                + productFeature.getProductId();
    }
}
