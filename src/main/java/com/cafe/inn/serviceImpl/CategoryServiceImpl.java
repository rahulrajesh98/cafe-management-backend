package com.cafe.inn.serviceImpl;

import com.cafe.inn.JWT.JwtFilter;
import com.cafe.inn.POJO.Category;
import com.cafe.inn.dao.CategoryDao;
import com.cafe.inn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        if (jwtFilter.isAdmin()) {
            if (validateCategoryMap(requestMap, false)) {
                categoryDao.save(getCategoryFromMap(requestMap, false));
                return new ResponseEntity<>("Category added successfully ", HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>("You are not an Admin", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>("something went wrong while creating new category", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateCategoryMap(Map<String, String> requestMap, Boolean isUpdate) {
        if (requestMap.containsKey("name") && !isUpdate)
            return true;
        else if (isUpdate) {
            return (requestMap.containsKey("name") && requestMap.containsKey("id"));
        }
        return false;
    }

    private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isUpdate) {
        Category category = new Category();
        if (!isUpdate) {
            category.setName(requestMap.get("name"));
            return category;
        } else {
            category.setName(requestMap.get("name"));
            category.setId(Integer.parseInt(requestMap.get("id")));
            return category;

        }

    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        if (!ObjectUtils.isEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
            return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
        }
        return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        if (jwtFilter.isAdmin()) {
            if (validateCategoryMap(requestMap, true)) {
                Optional<Category> optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!ObjectUtils.isEmpty(optional)) {
                    categoryDao.save(getCategoryFromMap(requestMap, true));
                    return new ResponseEntity<>("updated successfully", HttpStatus.OK);
                } else
                    return new ResponseEntity<>("Category id not found", HttpStatus.BAD_REQUEST);
            }
            else {
                return new ResponseEntity<>("Invalid inputs", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("You are not admin", HttpStatus.UNAUTHORIZED);


    }


}
