package com.cafe.inn.restImpl;

import com.cafe.inn.constants.CafeConstants;
import com.cafe.inn.dao.UserDao;
import com.cafe.inn.rest.UserRest;
import com.cafe.inn.service.UserService;
import com.cafe.inn.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {

        return userService.signUp(requestMap);


    }
}
