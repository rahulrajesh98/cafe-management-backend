package com.cafe.inn.serviceImpl;

import com.cafe.inn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServceImpl implements UserService {
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        return null;
    }
}
