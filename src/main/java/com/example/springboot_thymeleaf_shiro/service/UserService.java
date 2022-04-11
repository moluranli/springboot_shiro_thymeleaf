package com.example.springboot_thymeleaf_shiro.service;

import com.example.springboot_thymeleaf_shiro.entity.Role;
import com.example.springboot_thymeleaf_shiro.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findUserByUserName(String username);

    List<Role> findRolesByUserName(String username);
}
