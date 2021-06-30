package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "用户实体")
@NoArgsConstructor
@Data
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
  private Integer role;
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




}
