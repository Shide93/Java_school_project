package com.tsystems.javaschool.webshop.services.util;

import javax.persistence.EntityManager;

/**
 * Interface is user to perform update, delete and insert actions to DAO layer.
 */
@FunctionalInterface
public interface ServiceExecuteAction {
    /**
     * performs implemented actions.
     *
     * @param mgr used to work with DAO
     */
    void performAction(EntityManager mgr);
}
