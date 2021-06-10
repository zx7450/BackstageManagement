package com.zx.daoyundev.entity;

public class UserDTO {
    private String userName;
    private String userPassward;
    private String tel;
    private String code;

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
                "userName='" + userName + '\'' +
                ", userPassward='" + userPassward + '\'' +
                ", tel='" + tel + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
