package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.User;
import com.tsystems.javaschool.webshop.dao.entities.enums.OrderStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Order dao implementation.
 */
@Repository
public class OrderDAOImpl extends AbstractGenericDAO<Order>
        implements OrderDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(OrderDAOImpl.class);

    @Override
    public final List<Order> getByUser(final int userId) {
        TypedQuery<Order> q =
            manager.createNamedQuery("OrderEntity.getByUser",
                    Order.class);
        q.setParameter("id", userId);
        return q.getResultList();
    }

    @Override
    public final int getOrderCountByStatus(final OrderStatus status) {
        Query query = manager.createNamedQuery("OrderEntity.getWithStatus");
        query.setParameter("orderStatus", status);
        return query.getResultList().size();
    }

    @Override
    public final long totalSales() {
        TypedQuery<Long> query =
                manager.createNamedQuery("OrderEntity.totalSales",
                        Long.class);
        return query.getSingleResult();
    }

    @Override
    public final long periodSales(int period) {
        TypedQuery<Long> query =
                manager.createNamedQuery("OrderEntity.periodSales",
                        Long.class);
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(period, -1);
        Date date = c.getTime();
        query.setParameter("date", date);
        return query.getSingleResult();
    }

    @Override
    public final List<User> topCustomers(final int count) {
        TypedQuery<User> query =
                manager.createNamedQuery("OrderEntity.getTopCustomers",
                        User.class);
        return query.setMaxResults(count).getResultList();
    }
}
