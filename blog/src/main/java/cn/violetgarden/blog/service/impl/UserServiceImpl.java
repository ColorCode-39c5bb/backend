package cn.violetgarden.blog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.violetgarden.blog.controller.request_body.LoginRequestBody;
import cn.violetgarden.blog.dao.*;
import cn.violetgarden.blog.service.UserService;
import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectAll(){
        //验证是否为管理员
        
        return userDao.selectAll();
    }

    //登录验证

    @Override
    public String login(LoginRequestBody loginRequestBody) {
        String username = loginRequestBody.getUsername();
        String password = loginRequestBody.getPassword();
        User user = userDao.selectByUsername(username);
        if (user == null || !user.getPassword().equals(password)) return null;
        
        Map<String, Object> body = new HashMap<>();
        body.put("username", "violetgarden");

        return Jwts.builder()
            .signWith(key) // 无需再指定算法，密钥已包含算法信息
            .addClaims(body)
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .compact();
    }
}