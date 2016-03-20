package com.tsystems.javaschool.webshop.controllers.backend;

import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import com.tsystems.javaschool.webshop.services.api.CategoryService;
import com.tsystems.javaschool.webshop.services.api.FeatureService;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Shide on 18.03.2016.
 */
@Controller
public class BackendProductController {
    /**
     * The Product service.
     */
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
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
        List<Category> categories = categoryService.getAllIdNames();

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
        model.addAttribute("categoryList", categories);
        return "backend/products";
    }

    @RequestMapping(path = "/backend/products",
            params = "action=save", method = RequestMethod.POST)
    public final String saveProduct(@ModelAttribute Product product,
                                    final BindingResult bindingResult,
                                    final Model model) {
        productService.update(product);
        //TODO: accept features
        return "redirect:/backend/products?productId=" + product.getId();
    }

    public final String createProduct() {
        return "";
    }

    public final String removeProduct() {
        return "";
    }

//    @RequestMapping(path = "/backend/products",
//            params = "action=save", method = RequestMethod.POST)
//    public final String saveProduct(final HttpServletRequest req) {
//
//        String idStr = req.getParameter("id");
//        String name = req.getParameter("name");
//        String description = req.getParameter("description");
//        String priceStr = req.getParameter("price");
//        String stockStr = req.getParameter("stock");
//        String categoryIdStr = req.getParameter("category");
//        String[] featureIds = req.getParameterValues("prod_features[id]");
//        String[] featureValues =
//                req.getParameterValues("prod_features[value]");
//
//        Integer id = Integer.parseInt(idStr);
//        Integer price = Integer.parseInt(priceStr);
//        Integer stock = Integer.parseInt(stockStr);
//        Integer categoryId = Integer.parseInt(categoryIdStr);
//        Product product = new Product();
//        product.setId(id);
//        product.setName(name);
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setStock(stock);
//        Category cat = new Category();
//        cat.setId(categoryId);
//        product.setCategory(cat);
//        setFeaturesToProduct(featureIds, featureValues, product);
//        productService.update(product);
//        return "redirect:/backend/products?productId=" + product.getId();
//
//
//    }
//    @RequestMapping(path = "/backend/products",
//            params = "action=add", method = RequestMethod.POST)
//    public final String createProduct(final HttpServletRequest req) {
//
//        String name = req.getParameter("name");
//        String description = req.getParameter("description");
//        String priceStr = req.getParameter("price");
//        String stockStr = req.getParameter("stock");
//        String categoryIdStr = req.getParameter("category");
//        String[] featureIds = req.getParameterValues("prod_features[id]");
//        String[] featureValues =
//                req.getParameterValues("prod_features[value]");
//
//        Integer price = Integer.parseInt(priceStr);
//        Integer stock = Integer.parseInt(stockStr);
//        Integer categoryId = Integer.parseInt(categoryIdStr);
//        Product product = new Product();
//        product.setName(name);
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setStock(stock);
//        Category cat = new Category();
//        cat.setId(categoryId);
//        product.setCategory(cat);
//        productService.add(product);
//        if (featureIds != null) {
//            setFeaturesToProduct(featureIds, featureValues, product);
//            productService.update(product);
//        }
//
//        return "redirect:/backend/products?productId=" + product.getId();
//    }
//    @RequestMapping(path = "/backend/products",
//            params = "action=remove", method = RequestMethod.POST)
//    public final String removeProduct(final HttpServletRequest req) {
//
//        String idStr = req.getParameter("id");
//        Integer id = Integer.parseInt(idStr);
//        try {
//            productService.delete(id);
//        } catch (Exception e) {
//            LOGGER.warn("Can't remove product", e);
//            req.setAttribute("cantRemove",
//                    "Can't remove product: it is already assigned to order");
//            return "/backend/cantRemove";
//        }
//        return "redirect:/backend/products";
//    }
//
//    /**
//     * Sets features to product.
//     *
//     * @param featureIds    the feature ids
//     * @param featureValues the feature values
//     * @param product       the product
//     */
//    private void setFeaturesToProduct(final String[] featureIds,
//                                      final String[] featureValues,
//                                      final Product product) {
//        for (int i = 0; i < featureIds.length; i++) {
//            Integer fId = Integer.parseInt(featureIds[i]);
//            ProductFeature feature = new ProductFeature();
//            feature.setProductId(product.getId());
//            feature.setProduct(product);
//            feature.setFeatureId(fId);
//            feature.setValue(featureValues[i]);
//            product.getFeatures().add(feature);
//        }
//    }
}
