package com.cafe.inn.dao;

import com.cafe.inn.POJO.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface CategoryDao extends JpaRepository<Category,Integer> {

    List<Category> getAllCategory();
}
