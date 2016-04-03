package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.api.FeatureDAO;
import com.tsystems.javaschool.webshop.dao.api.ProductDAO;
import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.dao.entities.Feature;
import com.tsystems.javaschool.webshop.dao.entities.Product;
import com.tsystems.javaschool.webshop.dao.entities.ProductFeature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Shide on 02.04.2016.
 */
public class ProductServiceImplTest {


    private ProductServiceImpl productService;
    private int productId;
    private int categoryId;
    private int featureId;
    private Product product;
    private Category category;
    private Feature feature;
    private List<Product> productList;
    private Map<Integer, List<String>> map;
    private String[] selectedFeatures;
    private ProductFeature productFeature;

    @Mock
    private ProductDAO productDAO;
    @Mock
    private CategoryDAO categoryDAO;
    @Mock
    private FeatureDAO featureDAO;

    @Before
    public void setUp() throws Exception {
        productId = 1;
        categoryId = 2;
        featureId = 3;
        product = new Product();
        category = new Category();
        feature = new Feature();
        productList = new ArrayList<>();
        map = new HashMap<>();
        productFeature = new ProductFeature();

        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl();
        productService.setProductDAO(productDAO);
        productService.setFeatureDAO(featureDAO);
        productService.setCategoryDAO(categoryDAO);
    }

    @Test
    public void addSuccess() throws Exception {
        productService.add(product);
        verify(productDAO).create(product);
    }

    @Test
    public void updateSuccess() throws Exception {
        product.setId(productId);
        category.setId(categoryId);
        product.setCategory(category);

        when(categoryDAO.getById(categoryId))
                .thenReturn(category);

        productService.update(product);
        verify(productDAO).update(product);
    }

    @Test
    public void deleteSuccess() throws Exception {
        productService.delete(productId);
        verify(productDAO).delete(productId);
    }

    @Test
    public void getSuccess() throws Exception {
        product.setId(productId);
        when(productDAO.getById(productId)).thenReturn(product);
        assertEquals(product.getId(),
                productService.get(productId).getId());
    }

    @Test
    public void getAllSuccess() throws Exception {
        productList.add(product);
        when(productDAO.getAll()).thenReturn(productList);
        assertEquals(productList,
                productService.getAll());
    }

    @Test
    public void searchByFeatureSuccess() throws Exception {
        selectedFeatures = new String[]{"12/test1", "13/test2", "13/test3", "12/test4"};
        map.put(12, Arrays.asList("test1", "test4"));
        map.put(13, Arrays.asList("test2", "test3"));
        productService.searchByFeature(selectedFeatures);
        verify(productDAO).findByFeatures(map);
    }

    @Test
    public void addNewProductFeatureSuccess() throws Exception {
        when(productDAO.getById(productId)).thenReturn(product);
        when(featureDAO.getById(featureId)).thenReturn(feature);
        productFeature.setProductId(productId);
        productFeature.setFeatureId(featureId);
        productFeature.setValue("value");

        Product resultProduct = productService.addNewProductFeature(productFeature);
        verify(productDAO).update(product);
        assertEquals(resultProduct, product);
    }
    //TODO: addNewProductFeature negative tests
}