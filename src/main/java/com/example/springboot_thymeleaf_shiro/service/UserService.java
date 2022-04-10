package com.example.springboot_thymeleaf_shiro.service;

import com.example.springboot_thymeleaf_shiro.entity.User;

public interface UserService {
    void save(User user);

    User findUserByUserName(String username);
}
