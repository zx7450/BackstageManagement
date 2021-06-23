package com.zx.daoyundev.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zx.daoyundev.entity.User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class LoginService {
    public String getToken(User user) {
        String token = "";
        token = JWT.create()
                .withAudience(user.getUserId().toString())          // 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getUserPassward()));   // 以 password 作为 token 的密钥
        return token;
    }
}
