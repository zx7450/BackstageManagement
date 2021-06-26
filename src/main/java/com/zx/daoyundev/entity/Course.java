package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "班课实体")
public class Course {

  @ApiModelProperty(value = "班课id")
  private int courseId;
  @ApiModelProperty(value = "班课名")
  private String courseName;
  @ApiModelProperty(value = "班级")
  private String courseclass;
  @ApiModelProperty(value = "学期")
  private String term;
  //private long term;
  @ApiModelProperty(value = "创建老师名")
  private String teacherName;
  @ApiModelProperty(value = "是否允许加入,1代表不允许，2代表允许")
  private long joinable;
  @ApiModelProperty(value = "是否是学校课表班课，1代表是，2代表不是")
  private long isschoolclass;
  @ApiModelProperty(value = "学校")
  private long courseschool;
  @ApiModelProperty(value = "院系")
  private long coursemajor;
  @ApiModelProperty(value = "学习要求")
  private String learningrequire;
  @ApiModelProperty(value = "教学进度")
  private String teachprogess;
  @ApiModelProperty(value = "考试安排")
  private String examarrange;
  @ApiModelProperty(value = "班课图")
  private String image;
  @ApiModelProperty(value = "结束状态,1代表未结束，2代表已结束")
  private long coursestate;

  @Override
  public String toString() {
    return "Course{" +
            "courseId=" + courseId +
            ", courseName='" + courseName + '\'' +
            ", courseclass='" + courseclass + '\'' +
            ", term='" + term + '\'' +
            ", teacherName='" + teacherName + '\'' +
            ", joinable=" + joinable +
            ", isschoolclass=" + isschoolclass +
            ", courseschool=" + courseschool +
            ", coursemajor=" + coursemajor +
            ", learningrequire='" + learningrequire + '\'' +
            ", teachprogess='" + teachprogess + '\'' +
            ", examarrange='" + examarrange + '\'' +
            ", image='" + image + '\'' +
            ", coursestate=" + coursestate +
            '}';
  }

  public long getCoursestate() {
    return coursestate;
  }

  public void setCoursestate(long coursestate) {
    this.coursestate = coursestate;
  }

  public long getCourseschool() {
    return courseschool;
  }

  public void setCourseschool(long courseschool) {
    this.courseschool = courseschool;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public String getCourseclass() {
    return courseclass;
  }

  public void setCourseclass(String courseclass) {
    this.courseclass = courseclass;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }
//  public long getTerm() {
//    return term;
//  }
//
//  public void setTerm(long term) {
//    this.term = term;
//  }


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }


  public long getJoinable() {
    return joinable;
  }

  public void setJoinable(long joinable) {
    this.joinable = joinable;
  }


  public long getIsschoolclass() {
    return isschoolclass;
  }

  public void setIsschoolclass(long isschoolclass) {
    this.isschoolclass = isschoolclass;
  }


  public long getCoursemajor() {
    return coursemajor;
  }

  public void setCoursemajor(long coursemajor) {
    this.coursemajor = coursemajor;
  }


  public String getLearningrequire() {
    return learningrequire;
  }

  public void setLearningrequire(String learningrequire) {
    this.learningrequire = learningrequire;
  }


  public String getTeachprogess() {
    return teachprogess;
  }

  public void setTeachprogess(String teachprogess) {
    this.teachprogess = teachprogess;
  }


  public String getExamarrange() {
    return examarrange;
  }

  public void setExamarrange(String examarrange) {
    this.examarrange = examarrange;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
