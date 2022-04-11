package com.example.springboot_thymeleaf_shiro.dao;

import com.example.springboot_thymeleaf_shiro.entity.Pers;
import com.example.springboot_thymeleaf_shiro.entity.Role;
import com.example.springboot_thymeleaf_shiro.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    @Insert("insert into t_user(id,username,password,salt) values(#{id},#{username},#{password},#{salt})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void save(User user);

    @Select("select * from t_user where username = #{username}")
    User findUserByUserName(String username);

    @Select("select r.ID,r.NAME from T_USER u left join T_USER_ROLE ur on u.ID = ur.USERID\n" +
            "left join T_ROLE r on ur.ROLEID = r.ID\n" +
            "where u.USERNAME = #{username}")
    List<Role> findRoleByUserName(String username);

    @Select("select p.ID,p.NAME from T_ROLE r\n" +
            "left join T_ROLE_PERMS rp on r.ID = rp.ROLEID\n" +
            "left join T_PERS p on rp.PERMISID = p.ID\n" +
            "where r.ID = #{id}")
    List<Pers> findPersByRoleId(String id);
}
