package com.cafe.inn.serviceImpl;

import com.cafe.inn.JWT.JwtFilter;
import com.cafe.inn.POJO.Category;
import com.cafe.inn.POJO.Product;
import com.cafe.inn.dao.ProductDao;
import com.cafe.inn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        if(jwtFilter.isAdmin()){
            if(validateRequestMap(requestMap,false)){
                Product product = productDao.findByName(requestMap.get("name"));
                if(ObjectUtils.isEmpty(product)){
                    productDao.save(getProductFromMap(requestMap,false));
                    return new ResponseEntity<>("product added successfully",HttpStatus.OK);
                }
                else
                    return new ResponseEntity<>("Product exists",HttpStatus.BAD_REQUEST);
            }
            else
                return new ResponseEntity<>("Invalid Inputs",HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Not admin", HttpStatus.UNAUTHORIZED);

    }



    private boolean validateRequestMap(Map<String, String> requestMap,Boolean isUpdate) {
        if(requestMap.containsKey("name")){
            if(requestMap.containsKey("id") && isUpdate){
                return true;
            }
            else
                return true;
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap,Boolean isUpdate) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));
        Product product = new Product();
        if(isUpdate){
            product.setId(Integer.parseInt(requestMap.get("id")));
        }else
            product.setStatus("true");
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }
}
