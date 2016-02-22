package com.tsystems.javaschool.webshop.dao.impl;


import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.ProductEntity;

public class ProductDAOImpl extends AbstractGenericDAO<ProductEntity> implements ProductDAO {

    public ProductDAOImpl() {
        super(ProductEntity.class);
    }

}
