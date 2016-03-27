package com.tsystems.javaschool.webshop.dao.entities.dto;

import com.tsystems.javaschool.webshop.dao.entities.Order;
import com.tsystems.javaschool.webshop.dao.entities.User;

/**
 * The type User dto.
 */
public class UserDTO {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Last name.
     */
    private String lastName;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Orders count.
     */
    private long ordersCount;
    /**
     * The Order total.
     */
    private long orderTotal;

    /**
     * Instantiates a new User dto.
     *
     * @param user the user
     */
    public UserDTO(final User user) {
        name = user.getName();
        lastName = user.getLastName();
        email = user.getEmail();
        ordersCount = user.getOrders().size();
        orderTotal = calcOrderTotal(user);
    }

    /**
     * Calc order total for user.
     *
     * @param user the user
     * @return order total
     */
    private long calcOrderTotal(final User user) {
        long total = 0;
        for (Order order : user.getOrders()) {
            total += order.getTotal();
        }
        return total;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets orders count.
     *
     * @return the orders count
     */
    public long getOrdersCount() {
        return ordersCount;
    }

    /**
     * Sets orders count.
     *
     * @param ordersCount the orders count
     */
    public void setOrdersCount(final long ordersCount) {
        this.ordersCount = ordersCount;
    }

    /**
     * Gets order total.
     *
     * @return the order total
     */
    public long getOrderTotal() {
        return orderTotal;
    }

    /**
     * Sets order total.
     *
     * @param orderTotal the order total
     */
    public void setOrderTotal(final long orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(final String email) {
        this.email = email;
    }
}
