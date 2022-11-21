package com.cafe.inn.serviceImpl;

import com.cafe.inn.POJO.User;
import com.cafe.inn.constants.CafeConstants;
import com.cafe.inn.dao.UserDao;
import com.cafe.inn.service.UserService;
import com.cafe.inn.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        if (validateRequestMap(requestMap)) {

            User user = userDao.findByEmailId(requestMap.get("email"));
            if(Objects.isNull(user)){
                userDao.save(getUserFromMap(requestMap));
                return new ResponseEntity<String>("Successfully Registered",HttpStatus.OK);
            }

            else {
                return new ResponseEntity<String>("Email Already Exists",HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("Invalid Inputs",HttpStatus.BAD_REQUEST);
        }

    }

    private boolean validateRequestMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        } else {
            return false;
        }
    }

    private User getUserFromMap(Map<String, String> requestMap) {

        User user = new User();
        user.setName(requestMap.get("name"));

        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
