package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import com.tsystems.javaschool.webshop.services.exceptions.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Shide on 02.04.2016.
 */
public class FeatureServiceImplTest {

    private FeatureServiceImpl featureService;
    private int featureId;
    private Feature feature;
    private List<Feature> featureList;
    private Set<ProductFeature> products;

    @Mock
    private FeatureDAO featureDAO;

    @Before
    public void setUp() {
        featureId = 1;
        feature = new Feature();
        featureList = new ArrayList<>();
        products = new HashSet<>();
        feature.setProducts(products);

        MockitoAnnotations.initMocks(this);
        featureService = new FeatureServiceImpl();
        featureService.setFeatureDAO(featureDAO);
    }

    @Test
    public void addSuccess() {
        featureService.add(feature);
        verify(featureDAO).create(feature);
    }

    @Test
    public void updateSuccess() {
        featureService.update(feature);
        verify(featureDAO).update(feature);
    }

    @Test
    public void deleteSuccess()
            throws ServiceException {
        when(featureDAO.getById(featureId)).thenReturn(feature);
        featureService.delete(featureId);
        verify(featureDAO).delete(featureId);
    }

    @Test(expected = ServiceException.class)
    public void deleteFail()
            throws ServiceException {
        products.add(new ProductFeature());
        when(featureDAO.getById(featureId)).thenReturn(feature);
        featureService.delete(featureId);
    }

    @Test
    public void getSuccess() {
        feature.setId(featureId);
        when(featureDAO.getById(featureId)).thenReturn(feature);
        assertEquals(feature.getId(),
                featureService.get(featureId).getId());
    }

    @Test
    public void getAllSuccess() {
        featureList.add(feature);
        when(featureDAO.getAll()).thenReturn(featureList);
        assertEquals(featureList,
                featureService.getAll());
    }
}