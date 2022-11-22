package com.cafe.inn.restImpl;

import com.cafe.inn.rest.ProductRest;
import com.cafe.inn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductRestImpl implements ProductRest {

    @Autowired
    ProductService productService;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        return productService.addNewProduct(requestMap);
    }
}
