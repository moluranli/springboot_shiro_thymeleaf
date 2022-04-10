package com.example.springboot_thymeleaf_shiro.config;


import com.example.springboot_thymeleaf_shiro.shiro.CustomerRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //配置ShiroFilter配置Shiro的过滤器
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager DefaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //添加受限资源
        Map<String,String> map = new HashMap<>();
        map.put("/private","authc");//authc代表认证和授权
        //添加没有进行认证和授权,会默认返回的页面
        shiroFilterFactoryBean.setLoginUrl("/returnlogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setSecurityManager(DefaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }

    //配置安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(realm);
        return defaultSecurityManager;
    }

    @Bean
    public Realm getRealm(){
        return new CustomerRealm();
    }
}