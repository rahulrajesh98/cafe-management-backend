package com.cafe.inn.dao;

import com.cafe.inn.POJO.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {


    Product findByName(String name);

}
