package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

@ApiModel(value = "用户实体")
@NoArgsConstructor
public class User {

  @ApiModelProperty(value = "用户id")
  private Integer userId;
  @ApiModelProperty(value = "用户名")
  private String userName;
  @ApiModelProperty(value = "密码")
  private String userPassward;
  @ApiModelProperty(value = "手机号")
  private String tel;
  @ApiModelProperty(value = "性别")
  private int sex;
  @ApiModelProperty(value = "角色")
  private long role;
  @ApiModelProperty(value = "用户昵称")
  private String nickname;
  @ApiModelProperty(value = "出生年份")
  private String birthyear;
  @ApiModelProperty(value = "用户学校")
  private long userschool;
  @ApiModelProperty(value = "用户院系")
  private long depart;
  @ApiModelProperty(value = "学号/工号")
  private long perid;
  @ApiModelProperty(value = "头像")
  private String avatar;

  @Override
  public String toString() {
    return "User{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", userPassward='" + userPassward + '\'' +
            ", tel='" + tel + '\'' +
            ", sex=" + sex +
            ", role=" + role +
            ", nickname='" + nickname + '\'' +
            ", birthyear='" + birthyear + '\'' +
            ", userschool=" + userschool +
            ", depart=" + depart +
            ", perid=" + perid +
            ", avatar='" + avatar + '\'' +
            '}';
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public int getSex() {
    return sex;
  }

  public void setPerid(long perid) {
    this.perid = perid;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public long getPerid() {
    return perid;
  }

  public String getAvatar() {
    return avatar;
  }

  public long getUserschool() {
    return userschool;
  }

  public void setUserschool(long userschool) {
    this.userschool = userschool;
  }

  public Integer getUserId() {
    return userId;
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

  public long getRole() {
    return role;
  }

  public String getNickname() {
    return nickname;
  }

  public String getBirthyear() {
    return birthyear;
  }

  public long getDepart() {
    return depart;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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

  public void setRole(long role) {
    this.role = role;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setBirthyear(String birthyear) {
    this.birthyear = birthyear;
  }

  public void setDepart(long depart) {
    this.depart = depart;
  }
}
