package com.tsystems.javaschool.webshop.servlets.backend;

import com.tsystems.javaschool.webshop.services.api.StatisticsService;
import com.tsystems.javaschool.webshop.services.api.ValidationService;
import com.tsystems.javaschool.webshop.services.impl.StatisticsServiceImpl;
import com.tsystems.javaschool.webshop.services.impl.ValidationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Statistics servlet.
 */
public class StatisticsServlet extends HttpServlet {
    /**
     * The Statistics service.
     */
    private final StatisticsService statisticsService;
    /**
     * The Validation service.
     */
    private final ValidationService validationService;

    /**
     * Instantiates a new Statistics servlet.
     */
    public StatisticsServlet() {
        statisticsService = new StatisticsServiceImpl();
        validationService = new ValidationServiceImpl();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("newOrders", statisticsService.newOrders());
        req.setAttribute("totalSales", statisticsService.totalSales());
        req.setAttribute("monthSales", statisticsService.monthSales());
        final int topCount = 3;
        req.setAttribute("topCustomers", statisticsService
                .topCustomers(topCount));
        req.setAttribute("topProducts", statisticsService
                .topProducts(topCount));
        req.getRequestDispatcher("statistics.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
