package cn.violetgarden.blog.service;

import java.util.List;

import javax.crypto.SecretKey;

import cn.violetgarden.blog.controller.request_body.LoginRequestBody;
import cn.violetgarden.blog.dao.User;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import io.jsonwebtoken.security.Keys;

public interface UserService{
    SecretKey key = Keys.secretKeyFor(HS256);
    // 接口中的成员变量是 public static final 的常量，会被实现类继承（可通过实现类访问），但不能被重写

    public List<User> selectAll();

    //登录验证
    public String login(LoginRequestBody loginRequestBody);

}