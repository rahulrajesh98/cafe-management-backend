package com.cafe.inn.serviceImpl;

import com.cafe.inn.JWT.CustomerUsersDetailsService;
import com.cafe.inn.JWT.JwtFilter;
import com.cafe.inn.JWT.JwtUtil;
import com.cafe.inn.POJO.User;
import com.cafe.inn.constants.CafeConstants;
import com.cafe.inn.dao.UserDao;
import com.cafe.inn.service.UserService;
import com.cafe.inn.utils.CafeUtils;
import com.cafe.inn.wrapper.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServceImpl implements UserService {

    @Autowired UserDao userDao;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired AuthenticationManager authenticationManager;

    @Autowired JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

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

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        System.out.println("Inside Login");

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))
        );
        if(auth.isAuthenticated()){
            if(customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                return new ResponseEntity<String>("token : "+jwtUtil.generateToken(
                        customerUsersDetailsService.getUserDetail().getEmail(),
                        customerUsersDetailsService.getUserDetail().getRole()),HttpStatus.OK);
            }
            else
                return new ResponseEntity<String>("Wait for Admin Approval",HttpStatus.BAD_REQUEST );
        }
        return null;
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUser() {
        if(jwtFilter.isAdmin()){
            return new ResponseEntity<>(userDao.getAllUsers(),HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        }

         //return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        if(jwtFilter.isAdmin()){
            Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
            if(!optional.isEmpty()){
                userDao.updateStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
                return new ResponseEntity<>("User status updated Successfully",HttpStatus.OK);

            }
            else
                return new ResponseEntity<>("Id does not Exist",HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Unauthorized Access",HttpStatus.UNAUTHORIZED);
    }


}
