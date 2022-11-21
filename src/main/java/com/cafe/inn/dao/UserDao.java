package com.cafe.inn.dao;

import com.cafe.inn.POJO.User;
import com.cafe.inn.wrapper.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    public User findByEmailId( @Param("email") String email);

    List<UserDto> getAllUsers();


}
