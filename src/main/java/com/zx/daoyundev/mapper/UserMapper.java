package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> findAlluser();

    int add(User user);

    User SelectByTel(String tel);

    User SelectByid(Integer userId);

    User findOne(User user);

    int updatebyuserId(User user);

    int deletebyTel(String tel);

    void updatePasswordByTel(User user);
}

