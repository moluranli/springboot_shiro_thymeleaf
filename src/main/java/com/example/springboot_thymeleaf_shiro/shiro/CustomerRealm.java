package com.example.springboot_thymeleaf_shiro.shiro;

import com.example.springboot_thymeleaf_shiro.entity.Pers;
import com.example.springboot_thymeleaf_shiro.entity.Role;
import com.example.springboot_thymeleaf_shiro.entity.User;
import com.example.springboot_thymeleaf_shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//自定义Realm
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        List<Role> roles = userService.findRolesByUserName(primaryPrincipal);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (!CollectionUtils.isEmpty(roles)){
            roles.forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                List<Pers> pers = userService.findPersByPoleId(role.getId());
                if (!CollectionUtils.isEmpty(pers)){
                    pers.forEach(pers1 -> {
                        simpleAuthorizationInfo.addStringPermission(pers1.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        User user = userService.findUserByUserName(principal);
        if (!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}
