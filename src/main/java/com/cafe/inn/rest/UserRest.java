package com.cafe.inn.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/user")
public interface UserRest {

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Map<String,String> requestMap);
}
