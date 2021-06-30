package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.mapper.UserMapper;
import com.zx.daoyundev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User add(User user) {
        userMapper.add(user);
        return findByTel(user.getTel());
    }

    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setUserId(id);
        return userMapper.findOne(user);
    }

    @Override
    public User findByUserid(Integer userId) {
        return userMapper.SelectByid(userId);
    }

    @Override
    public User findByTel(String tel) {
        return userMapper.SelectByTel(tel);
    }





    @Override
    public User updateuser(User user) {
        userMapper.updatebyuserId(user);
        return findByTel(user.getTel());
    }

    @Override
    public int deleteuser(String tel) {
        return userMapper.deletebyTel(tel);
    }

    @Override
    public List<User> findAlluser() {
        return userMapper.findAlluser();
    }

    @Override
    public void updatePasswordByTel(User user){
        userMapper.updatePasswordByTel(user);
    }
}
