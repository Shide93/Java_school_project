package com.tsystems.javaschool.webshop.dao.impl;

import com.tsystems.javaschool.webshop.dao.api.OrderDAO;
import com.tsystems.javaschool.webshop.dao.entities.OrderEntity;
import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
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
public class OrderDAOImpl extends AbstractGenericDAO<OrderEntity>
        implements OrderDAO {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(OrderDAOImpl.class);

    @Override
    public final List<OrderEntity> getByUser(final int userId) {
        TypedQuery<OrderEntity> q =
            manager.createNamedQuery("OrderEntity.getByUser",
                    OrderEntity.class);
        q.setParameter("id", userId);
        return q.getResultList();
    }

    @Override
    public final int newOrders() {
        Query query = manager.createNamedQuery("OrderEntity.getWithStatus");
        query.setParameter("orderStatus", OrderStatus.NEW);
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
    public final long monthSales() {
        TypedQuery<Long> query =
                manager.createNamedQuery("OrderEntity.periodSales",
                        Long.class);
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.MONTH, -1);
        Date date = c.getTime();
        query.setParameter("date", date);
        return query.getSingleResult();
    }

    @Override
    public final List<UserEntity> topCustomers(final int count) {
        TypedQuery<UserEntity> query =
                manager.createNamedQuery("OrderEntity.getTopCustomers",
                        UserEntity.class);
        return query.setMaxResults(count).getResultList();
    }
}
