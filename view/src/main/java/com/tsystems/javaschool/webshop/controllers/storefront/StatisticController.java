package com.tsystems.javaschool.webshop.controllers.storefront;

import com.tsystems.javaschool.webshop.services.api.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shide on 14.03.2016.
 */
@Controller
public class StatisticController {

    /**
     * The Statistics service.
     */
    @Autowired
    private StatisticsService statisticsService;

    /**
     * Gets statistics page.
     *
     * @param model the model
     * @return statistics page
     */
    @RequestMapping("/backend/statistics")
    public final String getStatistics(final Model model) {

        model.addAttribute("newOrders", statisticsService.newOrders());
        model.addAttribute("totalSales", statisticsService.totalSales());
        model.addAttribute("monthSales", statisticsService.monthSales());
        //TODO: count to settings
        final int topCount = 3;
        model.addAttribute("topCustomers", statisticsService
                .topCustomers(topCount));
        model.addAttribute("topProducts", statisticsService
                .topProducts(topCount));

        return "backend/statistics";
    }
}
