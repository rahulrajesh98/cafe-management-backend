package com.cafe.inn.dao;

import com.cafe.inn.POJO.User;
import com.cafe.inn.wrapper.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    public User findByEmailId( @Param("email") String email);

    List<UserDto> getAllUsers();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status,@Param("id") Integer id);

    List<String> getAllAdmin();

    User findByEmail(String email);



}
