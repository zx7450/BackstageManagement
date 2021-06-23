package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User add(User user) {
        String passwordHash =  passwordToHash(user.getUserPassward());
        user.setUserPassward(passwordHash);
        userMapper.add(user);
        return findByTel(user.getTel());
    }

    public User findById(Integer id) {
        User user = new User();
        user.setUserId(id);
        return userMapper.findOne(user);
    }

    public User findByUserid(Integer userId){ return userMapper.SelectByid(userId);}
    public User findByTel(String tel) {
        return userMapper.SelectByTel(tel);
    }
    private String passwordToHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            byte[] src = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            // 字节数组转16进制字符串
            // https://my.oschina.net/u/347386/blog/182717
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }
    //public boolean comparePassword(User user, User userInDataBase) {
    public boolean comparePassword(String userPassward, User userInDataBase) {
        return passwordToHash(userPassward)      // 将用户提交的密码转换为 hash
                .equals(userInDataBase.getUserPassward()); // 数据库中的 password 已经是 hash，不用转换
    }
    public User updateuser(User user){
        userMapper.updatebyuserId(user);
        return findByTel(user.getTel());
    }
    public int deleteuser(String tel){
        return userMapper.deletebyTel(tel);
    }
    public List<User> findAlluser(){ return userMapper.findAlluser();}
}
