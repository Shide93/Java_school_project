package com.tsystems.javaschool.webshop.controllers.storefront;

import com.tsystems.javaschool.webshop.dao.entities.*;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by Shide on 21.03.2016.
 */
@Controller
@SessionAttributes("cart")
public class CheckoutController {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CheckoutController.class);
    /**
     * The Checkout service.
     */
    @Autowired
    private OrderService checkoutService;
    /**
     * The Account service.
     */
    @Autowired
    private AccountService accountService;

    @ModelAttribute
    public final Cart getCart(final Cart cart) {
        return cart;
    }

    @ModelAttribute
    public final Order getOrder(final Order order,
                                final Principal principal) {
        if (order.getUser() != null) {
            User user = accountService.getUserByEmail(principal.getName());
            user.setName(order.getUser().getName());
            user.setLastName(order.getUser().getLastName());
            user.setPhone(order.getUser().getPhone());
            order.setUser(user);
        }
        return order;
    }
    @ModelAttribute
    public final void getTypes(final Model model) {
        List<Payment> paymentTypes = checkoutService.getPaymentTypes();
        List<Shipping> shippingTypes = checkoutService.getShippingTypes();

        model.addAttribute("paymentTypes", paymentTypes);
        model.addAttribute("shippingTypes", shippingTypes);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String getCheckoutPage(@ModelAttribute final Cart cart,
                                  @ModelAttribute final Order order,
                                  final Model model,
                                  final Principal principal) {
        if (cart == null || cart.getItems() == null
                || cart.getItems().isEmpty()) {
            return "redirect:cart";
        }
        User user = accountService.getUserByEmail(principal.getName());

        order.setUser(user);
        order.setAddress(user.getAddress());

        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute @Valid final Order order,
                              final BindingResult bindingResult,
                              @ModelAttribute final Cart cart,
                              final Model model,
                              final SessionStatus status) {
        if (bindingResult.hasErrors()) {
            return "checkout";
        }
        checkoutService.createOrder(order, cart);
        status.setComplete();
        return "order_created";
    }
}
