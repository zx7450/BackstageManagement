package com.zx.daoyundev.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author zx
 * @date 2021/6/29 16:49
 */
public class JWTUtil {

    // 过期时间2小时
    private static final long EXPIRE_TIME = 2*60*60*1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String tel, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("tel", tel)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户电话
     */
    public static String getUsertel(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            String tel = jwt.getClaim("tel").asString();
            return tel;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,2小时后过期
     * @param tel 用户手机号
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String tel, String secret) {
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);// 密码作为加密用的秘钥
        // 附带tel信息
        return JWT.create()
                .withClaim("tel", tel)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}
