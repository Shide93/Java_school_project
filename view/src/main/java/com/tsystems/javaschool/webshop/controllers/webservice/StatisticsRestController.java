package com.tsystems.javaschool.webshop.controllers.webservice;

import com.tsystems.javaschool.webshop.dao.entities.dto.StatisticsDTO;
import com.tsystems.javaschool.webshop.services.api.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Shide on 23.03.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class StatisticsRestController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public final String restTest() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("month", "" + statisticsService.periodSales(Calendar.MONTH));
        map.put("all", "" + statisticsService.totalSales());
        map.put("orders", "" + statisticsService.newOrders());

        return "bla";
        //return "hello, you are RESTful";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public final StatisticsDTO getRestReport(@RequestParam
                                             final String period,
                                             @RequestParam
                                             final Integer topProductsCount,
                                             @RequestParam
                                             final Integer topUsersCount,
                                             @RequestParam
                                             final Integer minStock) {
        return statisticsService.getShopReport(period,
                    topProductsCount,
                    topUsersCount,
                    minStock);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public final String handleBadRequest(final HttpServletRequest req,
                                           final Exception e) {
        return "Error: " + e.getMessage();
    }
}