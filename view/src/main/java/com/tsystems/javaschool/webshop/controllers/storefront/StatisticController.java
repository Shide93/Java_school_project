package com.tsystems.javaschool.webshop.controllers.storefront;

import com.tsystems.javaschool.webshop.controllers.utils.ControllerUtils;
import com.tsystems.javaschool.webshop.controllers.webservice.StatisticsRestController;
import com.tsystems.javaschool.webshop.services.api.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Shide on 14.03.2016.
 */
@Controller
public class StatisticController {

    @Autowired
    private ServletContext servletContext;
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
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.MONTH, -1);
        model.addAttribute("monthSales",
                statisticsService.periodSales(c.getTime()));
        final int topCount = 3;
        model.addAttribute("topCustomers", statisticsService
                .topCustomers(topCount));
        model.addAttribute("topProducts", statisticsService
                .topProducts(topCount));
        return "backend/statistics";
    }

    @RequestMapping(value = "/backend/statistics",
            params = "action=getToken",
            method = RequestMethod.POST)
    @ResponseBody
    public final String getAccessToken(final Model model) {
        String token = statisticsService.generateAccessToken();
        ControllerUtils.setTokenToContext(token, servletContext);
        return "{ \"token\" : \"" + token + "\" }";
    }
}
