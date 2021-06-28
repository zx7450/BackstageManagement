package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GithubUser {
    //private String login;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "Githubid")
    private Long id;
    @ApiModelProperty(value = "Githubbio")
    private String bio;
    @ApiModelProperty(value = "Githubtoken")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    //    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getLogin() {
//        return login;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }
}
