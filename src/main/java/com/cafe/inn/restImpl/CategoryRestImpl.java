package com.cafe.inn.restImpl;

import com.cafe.inn.POJO.Category;
import com.cafe.inn.rest.CategoryRest;
import com.cafe.inn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryRestImpl implements CategoryRest {

    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        return categoryService.addNewCategory(requestMap);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        return categoryService.getAllCategory(filterValue);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        return categoryService.updateCategory(requestMap);
    }


}
