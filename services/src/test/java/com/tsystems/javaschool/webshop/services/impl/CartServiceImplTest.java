package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CartDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.services.exceptions.ExistsInCartException;
import com.tsystems.javaschool.webshop.services.exceptions.OutOfStockException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Shide on 02.04.2016.
 */
public class CartServiceImplTest {


    private CartServiceImpl cartService;
    private int cartId;
    private int productId;
    private Product product;
    private Cart cart;
    private CartProduct item;

    @Mock
    private CartDAO cartDAO;

    @Mock
    private ProductDAO productDAO;

    @Before
    public void setUp() {
        cartId = 1;
        productId = 2;
        product = new Product();
        cart = new Cart();

        item = new CartProduct();
        item.setProductId(productId);
        item.setCartId(cartId);
        item.setCart(cart);
        item.setQuantity(10);

        MockitoAnnotations.initMocks(this);
        cartService = new CartServiceImpl();
        cartService.setCartDAO(cartDAO);
        cartService.setProductDAO(productDAO);
    }

    @Test
    public void addSuccess() {
        cartService.add(cart);
        verify(cartDAO).create(cart);
    }


    @Test
    public void getSuccess() {
        cart.setId(cartId);
        when(cartDAO.getById(cartId)).thenReturn(cart);
        assertEquals(cart.getId(),
                cartService.get(cartId).getId());
    }

    @Test
    public void addToCartSuccess() throws ExistsInCartException, OutOfStockException {


        product.setStock(10);

        when(productDAO.getById(productId)).thenReturn(product);

        Cart resultCart = cartService.addToCart(item);

        cart.getItems().add(item);
        verify(cartDAO).update(cart);
        assertEquals(cart.getItems().size(),
                resultCart.getItems().size());

    }

    @Test(expected = OutOfStockException.class)
    public void addToCartOutOfStockFail() throws ExistsInCartException, OutOfStockException {

        when(productDAO.getById(productId)).thenReturn(product);

        cartService.addToCart(item);
    }

    @Test(expected = ExistsInCartException.class)
    public void addToCartExistsInCartFail() throws ExistsInCartException, OutOfStockException {

        product.setStock(10);
        cart.getItems().add(item);

        when(productDAO.getById(productId)).thenReturn(product);

        cartService.addToCart(item);
    }

    @Test
    public void editCartProductSuccess() throws OutOfStockException {

        product.setStock(10);
        item.setProduct(product);
        cart.getItems().add(item);          //added item with 10 quantity

        CartProduct newItem = new CartProduct();        //new item with 3
        newItem.setProductId(productId);
        newItem.setCartId(cartId);
        newItem.setCart(cart);
        newItem.setQuantity(3);

        when(productDAO.getById(productId)).thenReturn(product);

        cartService.editCartProduct(newItem);
        verify(cartDAO).update(cart);
        assertEquals(item.getQuantity(), newItem.getQuantity());
    }

    @Test(expected = OutOfStockException.class)
    public void editCartProductOutOfStockFail() throws OutOfStockException {

        product.setStock(2);
        item.setProduct(product);
        cart.getItems().add(item);          //added item with 10 quantity

        CartProduct newItem = new CartProduct();        //new item with 3
        newItem.setProductId(productId);
        newItem.setCartId(cartId);
        newItem.setCart(cart);
        newItem.setQuantity(3);

        when(productDAO.getById(productId)).thenReturn(product);

        cartService.editCartProduct(newItem);
    }

    @Test
    public void removeFromCartSuccess() {

        product.setStock(2);
        cart.getItems().add(item);          //added item with 10 quantity

        when(productDAO.getById(productId)).thenReturn(product);

        cartService.removeFromCart(item);
        verify(cartDAO).update(cart);
        assertTrue(cart.getItems().isEmpty());

    }
}
