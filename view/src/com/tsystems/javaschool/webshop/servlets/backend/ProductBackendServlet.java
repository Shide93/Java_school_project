package com.tsystems.javaschool.webshop.servlets.backend;

import com.tsystems.javaschool.webshop.dao.entities.CategoryEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.services.api.ProductService;
import com.tsystems.javaschool.webshop.services.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Shide on 28.02.2016.
 */
public class ProductBackendServlet extends HttpServlet {
    /**
     * The Product service.
     */
    private ProductService productService;

    /**
     * Instantiates a new Product backend servlet.
     */
    public ProductBackendServlet() {
        productService = new ProductServiceImpl();
    }
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        String productIdStr = req.getParameter("productId");
        //get all products
        List<ProductEntity> products = productService.getAll();

        if (productIdStr == null) {        //when enter page without params
            req.setAttribute("selectedProduct", products.get(0));
        } else {
            Integer productId = Integer.parseInt(productIdStr);
            if (productId > 0) {                            //when choose product in sidebar
                req.setAttribute("selectedProduct",         //0 is for new product
                        productService.get(productId));
            }
        }
        req.setAttribute("products", products);
        req.getRequestDispatcher("/backend/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");
        String categoryIdStr = req.getParameter("category");

        if (action.equals("add")) {
            Integer price = Integer.parseInt(priceStr);
            Integer stock = Integer.parseInt(stockStr);
            Integer categoryId = Integer.parseInt(categoryIdStr);
            ProductEntity product= new ProductEntity();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            //TODO: may be change signature of method instead of this?
            CategoryEntity cat = new CategoryEntity();
            cat.setId(categoryId);
            product.setCategory(cat);

            productService.add(product);
            resp.sendRedirect(resp.encodeRedirectURL(
                    "/backend/products?productId="
                            + product.getId()));
        } else if (action.equals("save")) {
            Integer id = Integer.parseInt(idStr);
            Integer price = Integer.parseInt(priceStr);
            Integer stock = Integer.parseInt(stockStr);
            Integer categoryId = Integer.parseInt(categoryIdStr);
            ProductEntity product = new ProductEntity();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            //TODO: may be change signature of method instead of this?
            CategoryEntity cat = new CategoryEntity();
            cat.setId(categoryId);
            product.setCategory(cat);
            productService.update(product);
            resp.sendRedirect(resp.encodeRedirectURL(
                    "/backend/products?productId="
                            + product.getId()));
        } else if (action.equals("remove")) {
            Integer id = Integer.parseInt(idStr);

            productService.delete(id);
            resp.sendRedirect(resp.encodeRedirectURL("/backend/products"));
        }
    }
}
