package com.example.springboot_thymeleaf_shiro.service;

import com.example.springboot_thymeleaf_shiro.dao.UserDao;
import com.example.springboot_thymeleaf_shiro.entity.Pers;
import com.example.springboot_thymeleaf_shiro.entity.Role;
import com.example.springboot_thymeleaf_shiro.entity.User;
import com.example.springboot_thymeleaf_shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public void save(User user) {
        //对用户输入的密码进行md5加密+salt+hash散列
        String salt = SaltUtils.getSalt(6);//根据写的SaltUtils里的getsalt静态方法产生salt
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());//将加密后的密码存入
        userDao.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public List<Role> findRolesByUserName(String username) {
        return userDao.findRoleByUserName(username);
    }

    @Override
    public List<Pers> findPersByPoleId(String id) {
        return userDao.findPersByRoleId(id);
    }
}
