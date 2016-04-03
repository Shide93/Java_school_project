package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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

    @Mock
    private FeatureDAO featureDAO;

    @Before
    public void setUp() throws Exception {
        featureId = 1;
        feature = new Feature();
        featureList = new ArrayList<>();

        MockitoAnnotations.initMocks(this);
        featureService = new FeatureServiceImpl();
        featureService.setFeatureDAO(featureDAO);
    }

    @Test
    public void addSuccess() throws Exception {
        featureService.add(feature);
        verify(featureDAO).create(feature);
    }

    @Test
    public void updateSuccess() throws Exception {
        featureService.update(feature);
        verify(featureDAO).update(feature);
    }

    @Test
    public void deleteSuccess() throws Exception {
        featureService.delete(featureId);
        verify(featureDAO).delete(featureId);
    }

    @Test
    public void getSuccess() throws Exception {
        feature.setId(featureId);
        when(featureDAO.getById(featureId)).thenReturn(feature);
        assertEquals(feature.getId(),
                featureService.get(featureId).getId());
    }

    @Test
    public void getAllSuccess() throws Exception {
        featureList.add(feature);
        when(featureDAO.getAll()).thenReturn(featureList);
        assertEquals(featureList,
                featureService.getAll());
    }
}