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
import java.util.Date;
import java.util.List;

/**
 * Order dao implementation.
 */
@Repository
public class OrderDAOImpl extends AbstractGenericDAO<Order>
        implements OrderDAO {
    @Override
    public final List<Order> getByUser(final int userId) {
        TypedQuery<Order> q =
            manager.createNamedQuery("OrderEntity.getByUser",
                    Order.class);
        q.setParameter("id", userId);
        return q.getResultList();
    }

    @Override
    public final int getOrderCountByStatus(final OrderStatus status,
                                           final Date dateFrom) {
        Query query = manager.createNamedQuery("OrderEntity.getWithStatus");
        query.setParameter("orderStatus", status);
        query.setParameter("dateFrom", dateFrom);
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
    public final long periodSales(final Date dateFrom) {
        TypedQuery<Long> query =
                manager.createNamedQuery("OrderEntity.periodSales",
                        Long.class);
        query.setParameter("dateFrom", dateFrom);
        Long result =  query.getSingleResult();
        if (result != null) {
            return result;
        } else {
            return 0L;
        }
    }

    @Override
    public final List<User> topCustomers(final int count,
                                         final Date dateFrom) {
        TypedQuery<User> query =
                manager.createNamedQuery("OrderEntity.getTopCustomers",
                        User.class);
        query.setParameter("dateFrom", dateFrom);
        return query.setMaxResults(count).getResultList();
    }

    @Override
    public final long totalOrders() {
        TypedQuery<Long> query =
                manager.createNamedQuery("OrderEntity.getOrderTotal",
                        Long.class);
        return query.getSingleResult();
    }
}
