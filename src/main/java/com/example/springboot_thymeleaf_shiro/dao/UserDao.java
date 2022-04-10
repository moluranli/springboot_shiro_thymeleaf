package com.example.springboot_thymeleaf_shiro.dao;

import com.example.springboot_thymeleaf_shiro.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    @Insert("insert into t_user(id,username,password,salt) values(#{id},#{username},#{password},#{salt})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void save(User user);

    @Select("select * from t_user where username = #{username}")
    User findUserByUserName(String username);
}
