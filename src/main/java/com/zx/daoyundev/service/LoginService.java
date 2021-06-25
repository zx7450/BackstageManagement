package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.User;

/**
 * @author zx
 * @date 2021/6/24 17:48
 */
public interface LoginService {
    String getToken(User user);
}
