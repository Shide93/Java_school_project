package com.tsystems.javaschool.webshop.dao.impl;


import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Iterator;
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
        StringBuilder queryString = new StringBuilder();
        queryString.append("select p from Product p ");
        queryString.append("inner join p.features f where f.id in ");
        queryString.append("(select pf.id from ProductFeature pf where ");

        Iterator<Map.Entry<Integer, List<String>>> entries =
                featureValues.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, List<String>> fValue = entries.next();

            queryString.append("(pf.featureId = ");
            queryString.append(fValue.getKey());
            queryString.append(" and (");
            List<String> values = fValue.getValue();
            for (int i = 0; i < values.size(); i++) {
                if (i != 0) {
                    queryString.append(" or ");
                }
                queryString.append("pf.value = '");
                queryString.append(values.get(i));
                queryString.append("'");
            }
            queryString.append(")) ");
            if (entries.hasNext()) {
                queryString.append("or ");
            }
        }
        queryString.append(") group by p.id having count(p) = ");
        queryString.append(featureValues.size());

        TypedQuery<Product> query =
            manager.createQuery(queryString.toString(), Product.class);
        return query.getResultList();
    }

    @Override
    public final List<Product> topProducts(final int count) {
        TypedQuery<Product> query =
                manager.createNamedQuery("OrderProductEntity.getTopProducts",
                        Product.class);
        return query.setMaxResults(count).getResultList();
    }

    @Override
    public final List<Product> getOutOfStockProducts(final int stockConstraint) {
        TypedQuery<Product> query =
                manager.createNamedQuery("Product.getOutOfStockProducts",
                        Product.class);
        query.setParameter("stockConstraint", stockConstraint);
        return query.getResultList();
    }
}
