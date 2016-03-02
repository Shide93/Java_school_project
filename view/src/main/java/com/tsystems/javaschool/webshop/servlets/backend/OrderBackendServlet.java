package com.tsystems.javaschool.webshop.servlets.backend;

import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import com.tsystems.javaschool.webshop.services.api.OrderService;
import com.tsystems.javaschool.webshop.services.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Shide on 29.02.2016.
 */
public class OrderBackendServlet extends HttpServlet {
    /**
     * The Order service.
     */
    private OrderService orderService;

    /**
     * Instantiates a new Order backend servlet.
     */
    public OrderBackendServlet() {
        orderService = new OrderServiceImpl();
    }

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        String orderIdStr = req.getParameter("orderId");
        //get all products
        List<OrderEntity> orders = orderService.getAll();

        if (orderIdStr == null) {        //when enter page without params
            req.setAttribute("selectedOrder", orders.get(0));
        } else {
            Integer orderId = Integer.parseInt(orderIdStr);
            if (orderId > 0) {                            //when choose order in sidebar
                req.setAttribute("selectedOrder",
                        orderService.get(orderId));
            }
        }
        req.setAttribute("orders", orders);
        req.setAttribute("orderStatuses", OrderStatus.values());
        req.getRequestDispatcher("/backend/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String idStr = req.getParameter("id");
        String statusStr = req.getParameter("status");

        if (action.equals("changeStatus")) {
            Integer id = Integer.parseInt(idStr);
            OrderStatus status = OrderStatus.valueOf(statusStr);
            orderService.changeStatus(id, status);
            resp.sendRedirect("/backend/orders?orderId=" + id);
        }
    }
}
