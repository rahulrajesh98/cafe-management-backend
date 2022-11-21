package com.cafe.inn.rest;

import com.cafe.inn.wrapper.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
public interface UserRest {

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Map<String,String> requestMap);

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String,String> requestMap);

    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAllUsers();

    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestBody Map<String,String> requestMap);

    @GetMapping("/checkToken")
    public  ResponseEntity<String> checkToken();

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap);
}
