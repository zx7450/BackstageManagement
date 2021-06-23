package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "学生签到实体")
public class Studentsign {

  @ApiModelProperty(value = "学生签到id")
  private long studentsignid;
  @ApiModelProperty(value = "发起签到id")
  private int teachersignId;
  @ApiModelProperty(value = "学生id")
  private Integer studentId;
  @ApiModelProperty(value = "课程id")
  private int courseId;
  @ApiModelProperty(value = "签到经度")
  private String longitude;
  @ApiModelProperty(value = "签到纬度")
  private String latitude;
  @ApiModelProperty(value = "签到类型，1代表限时签到，2代表一件签到")
  private long signtype;
  @ApiModelProperty(value = "签到时间")
  private long signtime;
  @ApiModelProperty(value = "签到状态，1代表未签到，2代表已签到")
  private long signstatus;
  @ApiModelProperty(value = "签到获得的经验值")
  private long getexp;

  @Override
  public String toString() {
    return "Studentsign{" +
            "studentsignid=" + studentsignid +
            ", teachersignId=" + teachersignId +
            ", studentId=" + studentId +
            ", courseId=" + courseId +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", signtype=" + signtype +
            ", signtime=" + signtime +
            ", signstatus=" + signstatus +
            ", getexp=" + getexp +
            '}';
  }

  public void setGetexp(long getexp) {
    this.getexp = getexp;
  }

  public long getGetexp() {
    return getexp;
  }

  public long getStudentsignid() {
    return studentsignid;
  }

  public void setStudentsignid(long studentsignid) {
    this.studentsignid = studentsignid;
  }


  public int getTeachersignId() {
    return teachersignId;
  }

  public void setTeachersignId(int teachersignId) {
    this.teachersignId = teachersignId;
  }


  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }


  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }


  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }


  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }


  public long getSigntype() {
    return signtype;
  }

  public void setSigntype(long signtype) {
    this.signtype = signtype;
  }


  public long getSigntime() {
    return signtime;
  }

  public void setSigntime(long signtime) {
    this.signtime = signtime;
  }


  public long getSignstatus() {
    return signstatus;
  }

  public void setSignstatus(long signstatus) {
    this.signstatus = signstatus;
  }

}
