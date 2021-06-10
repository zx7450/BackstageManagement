package com.zx.daoyundev.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class User {

  private Integer userId;
  private String userName;
  private String userPassward;
  private String tel;


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPassward() {
    return userPassward;
  }

  public void setUserPassward(String userPassward) {
    this.userPassward = userPassward;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

}
