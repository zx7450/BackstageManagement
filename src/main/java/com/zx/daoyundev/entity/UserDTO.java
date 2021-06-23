package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户DTO", description="登录传参用")
public class UserDTO {
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String userPassward;
    @ApiModelProperty(value = "手机号")
    private String tel;
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "用户角色")
    private long role;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public long getRole() {
        return role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassward(String userPassward) {
        this.userPassward = userPassward;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassward() {
        return userPassward;
    }

    public String getTel() {
        return tel;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassward='" + userPassward + '\'' +
                ", tel='" + tel + '\'' +
                ", code='" + code + '\'' +
                ", role=" + role +
                '}';
    }
}
