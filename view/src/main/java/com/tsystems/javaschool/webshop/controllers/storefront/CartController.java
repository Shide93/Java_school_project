package com.tsystems.javaschool.webshop.controllers.storefront;

import com.fasterxml.jackson.annotation.JsonView;
import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.services.exceptions.ExistsInCartException;
import com.tsystems.javaschool.webshop.services.exceptions.OutOfStockException;
import com.tsystems.javaschool.webshop.controllers.utils.ControllerUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Shide on 20.03.2016.
 */
@Controller
@SessionAttributes("cart")
public class CartController {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CartController.class);

    /**
     * The Cart service.
     */
    @Autowired
    private CartService cartService;


    /**
     * Create cart if none cart.
     *
     * @param cart the cart
     * @param resp the resp
     * @return the cart
     */
    @ModelAttribute
    public final Cart createCartIfNone(final Cart cart,
                                       final HttpServletResponse resp) {

        //check if cart exists in DB or create it
        if (cart.getId() != 0) {
            return cart;
        }
        cartService.add(cart);
        resp.addCookie(ControllerUtils.createCookie("cartID",
                "" + cart.getId()));

        return cart;
    }

    /**
     * Gets item.
     *
     * @param cartItem the cart item
     * @param cart     the cart
     * @return the item
     */
    @ModelAttribute
    public final CartProduct getItem(final CartProduct cartItem,
                                     @ModelAttribute final Cart cart) {
        cartItem.setCartId(cart.getId());
        cartItem.setCart(cart);
        return cartItem;
    }

    /**
     * Gets cart page.
     *
     * @param cart  the cart
     * @param model the model
     * @return the cart page
     */
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public final String getCartPage(@ModelAttribute("cart") final Cart cart,
                                    final Model model) {
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
                model.addAttribute("isCartEmpty", true);
        }
        return "cart";
    }

    /**
     * Adds product to cart via AJAX.
     *
     * @param cart     the cart
     * @param cartItem the cart item with Cart encapsulated
     * @param model    the model
     * @return the string
     * @throws OutOfStockException   the out of stock exception
     * @throws ExistsInCartException the exists in cart exception
     */
    @RequestMapping(value = "/cart", params = "action=add",
            method = RequestMethod.POST)
    @ResponseBody
    @JsonView(Cart.JSONCart.class)
    public final Cart addToCart(@ModelAttribute("cart") final Cart cart,
                                  @ModelAttribute final CartProduct cartItem,
                                  final Model model)
            throws OutOfStockException, ExistsInCartException {
        return cartService.addToCart(cartItem);
    }

    /**
     * Edit cart item cart.
     *
     * @param cart     the cart
     * @param cartItem the cart item
     * @param model    the model
     * @return the cart
     * @throws OutOfStockException the out of stock exception
     */
    @RequestMapping(value = "/cart", params = "action=edit",
            method = RequestMethod.POST)
    @ResponseBody
    @JsonView(Cart.JSONCart.class)
    public final Cart editCartItem(@ModelAttribute("cart") final Cart cart,
                                   @ModelAttribute final CartProduct cartItem,
                                     final Model model) throws OutOfStockException {
        return cartService.editCartProduct(cartItem);
    }

    /**
     * Remove from cart cart.
     *
     * @param cart     the cart
     * @param cartItem the cart item
     * @param model    the model
     * @return the cart
     */
    @RequestMapping(value = "/cart", params = "action=remove",
            method = RequestMethod.POST)
    @ResponseBody
    @JsonView(Cart.JSONCart.class)
    public final Cart removeFromCart(@ModelAttribute("cart") final Cart cart,
                                     @ModelAttribute final CartProduct cartItem,
                                        final Model model) {
        return cartService.removeFromCart(cartItem);
    }

    /**
     * Handle out of stock exception out of stock exception.
     *
     * @param e the e
     * @return the out of stock exception
     */
    @ExceptionHandler(OutOfStockException.class)
    @ResponseBody
    @JsonView(OutOfStockException.class)
    public final OutOfStockException handleOutOfStockException(
            final OutOfStockException e) {
        LOGGER.warn(e.getMessage(), e);
        return e;
    }

    /**
     * Handle exists in cart exception exists in cart exception.
     *
     * @param e the e
     * @return the exists in cart exception
     */
    @ExceptionHandler(ExistsInCartException.class)
    @ResponseBody
    @JsonView(ExistsInCartException.class)
    public final ExistsInCartException handleExistsInCartException(
            final ExistsInCartException e) {
        LOGGER.warn(e.getMessage(), e);
        return e;
    }
}
