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

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired JwtFilter jwtFilter;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        if(jwtFilter.isAdmin()){
            if(validateCategoryMap(requestMap)){
                categoryDao.save(getCategoryFromMap(requestMap));
                return new ResponseEntity<>("Category added successfully ", HttpStatus.OK);
            }
        }
        else
            return new ResponseEntity<>("You are not an Admin",HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>("something went wrong while creating new category",HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateCategoryMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name");
    }

    private Category getCategoryFromMap(Map<String, String> requestMap) {
        Category category =new Category();
        category.setName(requestMap.get("name"));
        return category;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        if(!ObjectUtils.isEmpty(filterValue) && filterValue.equalsIgnoreCase("true")){
            return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(),HttpStatus.OK);
        }
        return new ResponseEntity<>(categoryDao.findAll(),HttpStatus.OK);
    }

}
