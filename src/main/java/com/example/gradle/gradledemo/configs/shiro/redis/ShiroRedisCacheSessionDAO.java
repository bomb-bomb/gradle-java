package com.example.gradle.gradledemo.configs.shiro.redis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

public class ShiroRedisCacheSessionDAO extends EnterpriseCacheSessionDAO {
    private RedisTemplate<Object, Object> redisTemplate;

    public ShiroRedisCacheSessionDAO(RedisTemplate<Object, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        SimpleSession session1 = (SimpleSession) session;
        session1.setId(sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return (Session) redisTemplate.opsForValue().get(sessionId);
    }

    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession) {
            System.out.println(session.getTimeout());
            System.out.println(session.getId());
            if (((ValidatingSession) session).isValid()) {
                redisTemplate.opsForValue().set(session.getId(), session);
            } else {
                redisTemplate.delete(session.getId());
            }
        } else {
            redisTemplate.opsForValue().set(session.getId(), session);
        }
    }

    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(session.getId());
    }
}
