package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.User;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/24 17:49
 */
public interface UserService {
    User add(User user);

    User findById(Integer id);

    User findByUserid(Integer userId);

    User findByTel(String tel);

    String passwordToHash(String password);

    boolean comparePassword(String userPassward, User userInDataBase);

    User updateuser(User user);

    int deleteuser(String tel);

    List<User> findAlluser();

    void updatePasswordByTel(User user);
}
