package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "发送验证码实体")
@Data
public class SendSms {
    @ApiModelProperty(value = "手机号")
    private String tel;
    @ApiModelProperty(value = "验证码")
    private String code;
}
