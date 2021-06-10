package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.GithubUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GithubUserMapper {
    int  InsertUser(GithubUser githubUser);
}
