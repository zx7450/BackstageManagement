package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户DTO", description="登录传参用")
@Data
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
    private Integer role;
    @ApiModelProperty(value = "旧密码（修改密码时使用）")
    private String oldpassword;

}
