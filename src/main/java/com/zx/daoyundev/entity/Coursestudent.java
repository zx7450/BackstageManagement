package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "班课学生实体")
public class Coursestudent {

  private long id;
  @ApiModelProperty(value = "班课id")
  private int courseId;
  @ApiModelProperty(value = "学生id")
  private Integer studentId;
  @ApiModelProperty(value = "学生名字")
  private String studentname;
  @ApiModelProperty(value = "总经验值")
  private long totalExp;

  @Override
  public String toString() {
    return "Coursestudent{" +
            "id=" + id +
            ", courseId=" + courseId +
            ", studentId=" + studentId +
            ", studentname='" + studentname + '\'' +
            ", totalExp=" + totalExp +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }


  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }


  public String getStudentname() {
    return studentname;
  }

  public void setStudentname(String studentname) {
    this.studentname = studentname;
  }


  public long getTotalExp() {
    return totalExp;
  }

  public void setTotalExp(long totalExp) {
    this.totalExp = totalExp;
  }

}
