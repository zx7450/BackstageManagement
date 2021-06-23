package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class StudentsignDTO {
    private long teachersignId;
    private Integer studentId;
    private int courseId;
    private double longitude;
    private double latitude;
    private long signtype;
    private long signtime;
    private long signstatus;
    private long stugesture;//学生签到手势
}
