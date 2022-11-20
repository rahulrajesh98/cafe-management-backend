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

@Slf4j
@Service
public class UserServceImpl implements UserService {

    @Autowired UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        if(validateRequestMap(requestMap)){

            User user = userDao.findByEmailId(requestMap.get("email"));

        }
        else {
            return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
        return null;

    }
    private boolean validateRequestMap(Map<String ,String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber") &&
        requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        else {
            return false;
        }

    }
}
