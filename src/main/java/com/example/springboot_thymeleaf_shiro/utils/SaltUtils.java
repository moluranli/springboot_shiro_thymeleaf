package com.example.springboot_thymeleaf_shiro.utils;

import java.util.Random;

public class SaltUtils {
    /**
     * 根据存入的int参数生成随机的salt
     * @return
     */
    public static String getSalt(int num){
        char[] salts = "12345abcde#*%^!".toCharArray();
        StringBuffer saltres = new StringBuffer();
        for (int i = 0; i < num; i++) {
            char c = salts[new Random().nextInt(salts.length)];
            saltres.append(c);
        }
        return String.valueOf(saltres);
    }

    public static void main(String[] args) {
        System.out.println(SaltUtils.getSalt(10));
    }
}
