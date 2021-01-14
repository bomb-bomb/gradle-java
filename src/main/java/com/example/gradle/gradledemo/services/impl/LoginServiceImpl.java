package com.example.gradle.gradledemo.services.impl;

import com.example.gradle.gradledemo.data.User;
import com.example.gradle.gradledemo.data.UserMapper;
import com.example.gradle.gradledemo.services.LoginService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    SqlSession sqlSession;

    @Override
    public User getUserByName(String getMapByName) {
        return getMapByName(getMapByName);
    }

    /**
     * 数据库查询
     *
     * @param userName 用户名
     * @return User
     */
    private User getMapByName(String userName) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.Sel(userName);
    }

    @Override
    public Set<User> getAll() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.all();
    }
}