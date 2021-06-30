package com.zx.daoyundev.security.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zx
 * @date 2021/6/29 17:05
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}