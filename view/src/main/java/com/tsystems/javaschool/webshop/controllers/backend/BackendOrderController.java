package com.tsystems.javaschool.webshop.controllers.backend;

import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Order managing in backend.
 */
@Controller
public class BackendOrderController {
    /**
     * The Order service.
     */
    @Autowired
    private OrderService orderService;

    @ModelAttribute(value = "orders")
    public final List<Order> getOrderList() {
        return orderService.getAll();
    }

    @RequestMapping(value = "/backend/orders", method = RequestMethod.GET)
    public final String getOrder(@RequestParam(defaultValue = "-1")
                                     final int orderId,
                                 @ModelAttribute(value = "orders")
                                 final List<Order> orders,
                                 final Model model) {
        if (orderId == -1) {       //when enter page without params
            if (orders != null && orders.size() > 0) {
                model.addAttribute("selectedOrder", orders.get(0));
            }
        } else {
            if (orderId > 0) {    //when choose order in sidebar
                model.addAttribute("selectedOrder",
                        orderService.get(orderId));
            } else {
                model.addAttribute("selectedOrder", new Order());
            }
        }
        model.addAttribute("orderStatuses", OrderStatus.values());
        return "backend/orders";
    }

    @RequestMapping(value = "/backend/orders", method = RequestMethod.POST)
    public final String getOrder(@RequestParam final int id,
                                 @RequestParam final OrderStatus status) {
        orderService.changeStatus(id, status);
        return "redirect:/backend/orders?orderId=" + id;
    }
}
