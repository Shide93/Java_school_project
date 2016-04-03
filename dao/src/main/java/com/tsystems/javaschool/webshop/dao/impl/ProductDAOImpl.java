package com.tsystems.javaschool.webshop.dao.impl;


import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Product dao implementation.
 */
@Repository
public class ProductDAOImpl extends AbstractGenericDAO<Product>
        implements ProductDAO {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ProductDAOImpl.class);

    @Override
    public final List<Product> findByFeatures(
            final Map<Integer, List<String>> featureValues) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery =
                builder.createQuery(Product.class);
        Root<Product> product = criteriaQuery.from(Product.class);
        Path<ProductFeature> feature = product.join("features");
        CriteriaQuery<Product> select = criteriaQuery.select(product);

        Predicate featurePredicate = builder.disjunction();
        for (final Map.Entry<Integer, List<String>> fValue
                : featureValues.entrySet()) {
            Predicate equalFeatureId =
                    builder.equal(feature.get("featureId"),
                            fValue.getKey());
            List<String> values = fValue.getValue();
            Predicate equalsValues = builder.disjunction();
            for (String value : values) {
                Predicate equalFeatureVal =
                        builder.equal(feature.get("value"),
                                value);
                equalsValues = builder.or(equalsValues, equalFeatureVal);
            }

            featurePredicate = builder.or(featurePredicate,
                    builder.and(equalFeatureId, equalsValues));
        }
        select.where(featurePredicate);
        select.groupBy(product.get("id"));
        select.having(builder.equal(builder.count(product),
                featureValues.size()));

        TypedQuery<Product> query =
                manager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public final List<Product> topProducts(final int count,
                                           final Date dateFrom) {
        TypedQuery<Product> query =
                manager.createNamedQuery("OrderProductEntity.getTopProducts",
                        Product.class);
        query.setParameter("dateFrom", dateFrom);
        return query.setMaxResults(count).getResultList();
    }

    @Override
    public final List<Product> getOutOfStockProducts(final int stockLimit) {
        TypedQuery<Product> query =
                manager.createNamedQuery("Product.getOutOfStockProducts",
                        Product.class);
        query.setParameter("stockLimit", stockLimit);
        return query.getResultList();
    }

    @Override
    public final long getProductSales(final int productId,
                                      final Date dateFrom) {
        TypedQuery<Long> query =
                manager.createNamedQuery("OrderProductEntity.getProductTotal",
                        Long.class);
        query.setParameter("productId", productId);
        query.setParameter("dateFrom", dateFrom);
        return query.getSingleResult();
    }
}
