package com.example.springboot_thymeleaf_shiro.controller;

import com.example.springboot_thymeleaf_shiro.entity.User;
import com.example.springboot_thymeleaf_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/private";
        } catch (UnknownAccountException e){
            System.out.println("用户名错误");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            e.printStackTrace();
        }
        return "redirect:/returnlogin";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/returnlogin";
    }

    @RequestMapping("/registered")
    public String registered(User user){
        try {
            userService.save(user);
            return "redirect:/returnlogin";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/registered";
    }
}
