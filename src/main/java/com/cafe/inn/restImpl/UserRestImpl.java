package com.cafe.inn.restImpl;

import com.cafe.inn.constants.CafeConstants;
import com.cafe.inn.dao.UserDao;
import com.cafe.inn.rest.UserRest;
import com.cafe.inn.service.UserService;
import com.cafe.inn.utils.CafeUtils;
import com.cafe.inn.wrapper.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {

        return userService.signUp(requestMap);


    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        return userService.login(requestMap);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return userService.getAllUser();
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        return userService.updateStatus(requestMap);
    }


}
