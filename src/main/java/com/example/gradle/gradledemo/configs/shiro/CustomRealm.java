package com.example.gradle.gradledemo.configs.shiro;

import com.example.gradle.gradledemo.data.Permissions;
import com.example.gradle.gradledemo.data.Role;
import com.example.gradle.gradledemo.data.User;
import com.example.gradle.gradledemo.services.UserService;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService loginService;

    @SneakyThrows
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = loginService.getUserByName(name);
        System.out.println("user >");
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Permissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     *  认证配置类
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = null == authenticationToken.getPrincipal() ? "" : authenticationToken.getPrincipal().toString();
        if (StringUtils.isEmpty(principal)) {
            return null;
        }
        // 获取用户信息
        String name = Objects.requireNonNull(authenticationToken.getPrincipal()).toString();
        User user = loginService.getUserByName(name);
        System.out.println(user.getName());
        System.out.println(name);
        if (user == null) {
            // 这里返回后会报出对应异常
            return null;
        } else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(name, user.getName(), getName());
        }
    }
}