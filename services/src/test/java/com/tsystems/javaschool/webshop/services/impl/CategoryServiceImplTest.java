package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.Category;
import com.tsystems.javaschool.webshop.dao.entities.Product;
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
public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;
    private int categoryId;
    private Category category;
    private List<Category> categoryList;
    private Set<Product> products;
    @Mock
    private CategoryDAO categoryDAO;

    @Before
    public void setUp() {
        categoryId = 1;
        category = new Category();
        categoryList = new ArrayList<>();
        products = new HashSet<>();
        category.setProducts(products);

        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl();
        categoryService.setCategoryDAO(categoryDAO);
    }

    @Test
    public void addSuccess() {
        categoryService.add(category);
        verify(categoryDAO).create(category);
    }

    @Test
    public void updateSuccess() {
        category.setId(categoryId);
        when(categoryDAO.getById(categoryId))
                .thenReturn(category);

        categoryService.update(category);
        verify(categoryDAO).update(category);
    }

    @Test
    public void deleteSuccess() throws ServiceException {
        when(categoryDAO.getById(categoryId)).thenReturn(category);
        categoryService.delete(categoryId);
        verify(categoryDAO).delete(categoryId);
    }

    @Test(expected = ServiceException.class)
    public void deleteFail() throws ServiceException {
        products.add(new Product());
        when(categoryDAO.getById(categoryId)).thenReturn(category);
        categoryService.delete(categoryId);
    }

    @Test
    public void getSuccess() {
        category.setId(categoryId);
        when(categoryDAO.getById(categoryId)).thenReturn(category);
        assertEquals(category.getId(),
                categoryService.get(categoryId).getId());
    }

    @Test
    public void getAllSuccess() {
        categoryList.add(category);
        when(categoryDAO.getAll()).thenReturn(categoryList);
        assertEquals(categoryList,
                categoryService.getAll());
    }

    @Test
    public void getAllIdNamesSuccess() {
        categoryList.add(category);
        when(categoryDAO.getAllIdNames()).thenReturn(categoryList);
        assertEquals(categoryList,
                categoryService.getAllIdNames());
    }
}
