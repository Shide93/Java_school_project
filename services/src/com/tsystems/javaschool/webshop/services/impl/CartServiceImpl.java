package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.CartEntity;
import com.tsystems.javaschool.webshop.dao.entities.CartProductEntity;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;
import com.tsystems.javaschool.webshop.dao.impl.CartDAOImpl;
import com.tsystems.javaschool.webshop.dao.impl.ProductDAOImpl;
import com.tsystems.javaschool.webshop.dao.utils.EntityManagerFactorySingleton;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import com.tsystems.javaschool.webshop.services.util.ServiceHelper;
import com.tsystems.javaschool.webshop.services.util.ServiceHelperImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Cart service.
 */
public class CartServiceImpl implements CartService {

    /**
     * The Cart dao.
     */
    private CartDAO cartDAO;
    /**
     * The Product dao.
     */
    private ProductDAO productDAO;
    /**
     * The Service helper.
     */
    private ServiceHelper serviceHelper;

    /**
     * Instantiates a new Cart service.
     */
    public CartServiceImpl() {
        cartDAO = new CartDAOImpl();
        productDAO = new ProductDAOImpl();
        serviceHelper = new ServiceHelperImpl();
    }

    @Override
    public final void add(final CartEntity cart) throws ServiceException {
        serviceHelper.executeInTransaction(manager -> {

            cartDAO.create(cart, manager);
        });
    }

    @Override
    public final void update(final CartEntity cart) throws ServiceException {
        serviceHelper.executeInTransaction(manager -> {

            cartDAO.update(cart, manager);
        });
    }

    @Override
    public final void delete(final CartEntity cart) throws ServiceException {
        serviceHelper.executeInTransaction(manager -> {

            cartDAO.delete(cart, manager);
        });
    }

    @Override
    public final CartEntity get(final int cartId) throws ServiceException {
        return serviceHelper.load(manager -> {

            return cartDAO.getById(cartId, manager);
        });
    }

    @Override
    public final List<CartEntity> getAll() throws ServiceException {
        return serviceHelper.loadInTransaction(manager -> {

            return cartDAO.getAll(manager);
        });
    }

    @Override
    public final CartEntity addToCart(final Integer productId,
                                      final Integer quantity,
                                      final Integer cartId)
            throws ServiceException {
        return serviceHelper.loadInTransaction(manager -> {
            CartEntity cart = cartDAO.getById(cartId, manager);
            ProductEntity product = productDAO.getById(productId, manager);
            CartProductEntity item= new CartProductEntity();

            item.setQuantity(quantity);
            item.setProduct(product);
            item.setCart(cart);
            item.setProductId(productId);
            item.setCartId(cart.getId());

            if (cart.getItems() == null) {
                //TODO: move to getItems? setItems? declaration? constructor?
                cart.setItems(new ArrayList<>());
            }
            cart.getItems().add(item);
            cart.setCount(cart.getCount() + quantity);
            cart.setSummary(cart.getSummary()
                    + (product.getPrice() * quantity));
            cartDAO.update(cart, manager);
            return cart;
        });
    }

    @Override
    public final CartEntity editCartProduct(final Integer productId,
                                            final Integer quantity,
                                            final Integer cartId)
            throws ServiceException {
        return serviceHelper.loadInTransaction(manager -> {
            CartEntity cart = cartDAO.getById(cartId, manager);

            for (CartProductEntity cartProduct : cart.getItems()) {
                if (productId.equals(cartProduct.getProductId())) {
                    cartProduct.setQuantity(quantity);
                    break;
                }
            }
            return cart;
        });
    }

    @Override
    public final CartEntity removeFromCart(final Integer productId,
                                           final Integer cartId)
            throws ServiceException {
        return serviceHelper.loadInTransaction(manager -> {
            CartEntity cart = cartDAO.getById(cartId, manager);

            Iterator<CartProductEntity> iterator = cart.getItems().iterator();
            while (iterator.hasNext()) {
                CartProductEntity cartProduct = iterator.next();
                if (productId.equals(cartProduct.getProductId())) {
                    iterator.remove();
                    break;
                }
            }
            //FIXME: deletes 2 rows exept one....
            // cartDAO.update(cart, manager);
            return cart;
        });
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ServiceException the service exception
     */
    public static void main(String[] args) throws ServiceException {

        CartService cartService = new CartServiceImpl();
        // cartService.editCartProduct(1, 5, 23);
        CartEntity cR = cartService.removeFromCart(3, 23);
        System.err.println(cR);
        EntityManagerFactorySingleton.closeFactory();

    }
}

