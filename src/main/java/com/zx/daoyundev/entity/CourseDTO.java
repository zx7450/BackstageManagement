package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class CourseDTO {
    private long courseId;
    private String courseName;
    private String courseclass;
    private long term;
    private String teacherName;
}
