package com.example.gradle.gradledemo.services.impl;

import com.example.gradle.gradledemo.data.User;
import com.example.gradle.gradledemo.data.UserMapper;
import com.example.gradle.gradledemo.services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
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

    @Override
    public Integer save(User user) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.save(user);
        return user.getId();
    }
}