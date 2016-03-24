package com.tsystems.javaschool.webshop.controllers.storefront;

import com.fasterxml.jackson.annotation.JsonView;
import com.tsystems.javaschool.webshop.dao.entities.Cart;
import com.tsystems.javaschool.webshop.dao.entities.CartProduct;
import com.tsystems.javaschool.webshop.services.api.CartService;
import com.tsystems.javaschool.webshop.servlets.utils.ServletUtils;
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


    @ModelAttribute
    public final Cart createCartIfNone(final Cart cart,
                                       final HttpServletResponse resp) {

        //check if cart exists in DB or create it
        if (cart.getId() != 0) {
            return cart;
        }
        cartService.add(cart);
        resp.addCookie(ServletUtils.createCookie("cartID",
                "" + cart.getId()));

        return cart;
    }

    @ModelAttribute
    public final CartProduct getItem(final CartProduct cartItem,
                                     @ModelAttribute final Cart cart) {
        cartItem.setCartId(cart.getId());
        cartItem.setCart(cart);
        return cartItem;
    }

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
     */
    @RequestMapping(value = "/cart", params = "action=add",
            method = RequestMethod.POST)
    @ResponseBody
    @JsonView(Cart.JSONCart.class)
    public final Cart addToCart(@ModelAttribute("cart") final Cart cart,
                                  @ModelAttribute final CartProduct cartItem,
                                  final Model model) {

        //FIXME: if add to cart double times - exception
        return cartService.addToCart(cartItem);
    }

    @RequestMapping(value = "/cart", params = "action=edit",
            method = RequestMethod.POST)
    @ResponseBody
    @JsonView(Cart.JSONCart.class)
    public final Cart editCartItem(@ModelAttribute("cart") final Cart cart,
                                   @ModelAttribute final CartProduct cartItem,
                                     final Model model) {
        return cartService.editCartProduct(cartItem);
    }

    @RequestMapping(value = "/cart", params = "action=remove",
            method = RequestMethod.POST)
    @ResponseBody
    @JsonView(Cart.JSONCart.class)
    public final Cart removeFromCart(@ModelAttribute("cart") final Cart cart,
                                     @ModelAttribute final CartProduct cartItem,
                                        final Model model) {
        return cartService.removeFromCart(cartItem);
    }
}
