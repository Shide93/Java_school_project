package com.tsystems.javaschool.webshop.services.impl;

import com.tsystems.javaschool.webshop.dao.api.CategoryDAO;
import com.tsystems.javaschool.webshop.dao.entities.Category;
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
public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;
    private int categoryId;
    private Category category;
    private List<Category> categoryList;

    @Mock
    private CategoryDAO categoryDAO;

    @Before
    public void setUp() {
        categoryId = 1;
        category = new Category();
        categoryList = new ArrayList<>();

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
    public void deleteSuccess() {
        categoryService.delete(categoryId);
        verify(categoryDAO).delete(categoryId);
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
