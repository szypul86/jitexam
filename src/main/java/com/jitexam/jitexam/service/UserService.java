package com.jitexam.jitexam.service;



import com.jitexam.jitexam.dto.UserDto;
import com.jitexam.jitexam.entity.User;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(Long id);
    void deleteAll();
    User findOne(String username);
    User findById(Long id);
    UserDto update(UserDto userDto);
}
