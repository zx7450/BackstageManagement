package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "班课学生DTO", description="用于传参，只有班课id和学生id两项")
@Data
public class CoursestudentDTO {
    @ApiModelProperty(value = "班课id")
    private int courseId;
    @ApiModelProperty(value = "学生id")
    private Integer studentId;
}
