package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int add(User user);
    User SelectByTel(String tel);
    User findOne(User user);
}

