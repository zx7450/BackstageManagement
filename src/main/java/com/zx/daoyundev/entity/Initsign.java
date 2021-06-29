package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "发起签到实体")
@Data
public class Initsign {

  @ApiModelProperty(value = "发起签到id")
  private int teachersignId;
  @ApiModelProperty(value = "教师id")
  private long teacherId;
  @ApiModelProperty(value = "班课id")
  private int courseId;
  @ApiModelProperty(value = "开始时间")
  private long begtime;
  @ApiModelProperty(value = "结束时间")
  private long endtime;
  @ApiModelProperty(value = "教师经度")
  private String teachlongitude;
  @ApiModelProperty(value = "教师纬度")
  private String teachlatitude;
  @ApiModelProperty(value = "签到类型，1代表限时签到，2代表一件签到")
  private long sightype;
  @ApiModelProperty(value = "是否结束，1代表未结束，2代表已结束")
  private long isEnd;
  //private long gesture;

  @Override
  public String toString() {
    return "Initsign{" +
            "teachersignId=" + teachersignId +
            ", teacherId=" + teacherId +
            ", courseId=" + courseId +
            ", begtime=" + begtime +
            ", endtime=" + endtime +
            ", teachlongitude='" + teachlongitude + '\'' +
            ", teachlatitude='" + teachlatitude + '\'' +
            ", sightype=" + sightype +
            ", isEnd=" + isEnd +
            '}';
  }


//  public void setGesture(long gesture) {
//    this.gesture = gesture;
//  }
//
//  public long getGesture() {
//    return gesture;
//  }

//  public int getTeachersignId() {
//    return teachersignId;
//  }
//
//  public void setTeachersignId(int teachersignId) {
//    this.teachersignId = teachersignId;
//  }
//
//
//  public long getTeacherId() {
//    return teacherId;
//  }
//
//  public void setTeacherId(long teacherId) {
//    this.teacherId = teacherId;
//  }
//
//
//  public int getCourseId() {
//    return courseId;
//  }
//
//  public void setCourseId(int courseId) {
//    this.courseId = courseId;
//  }
//
//
//  public long getBegtime() {
//    return begtime;
//  }
//
//  public void setBegtime(long begtime) {
//    this.begtime = begtime;
//  }
//
//
//  public long getEndtime() {
//    return endtime;
//  }
//
//  public void setEndtime(long endtime) {
//    this.endtime = endtime;
//  }
//
//
//  public String getTeachlongitude() {
//    return teachlongitude;
//  }
//
//  public void setTeachlongitude(String teachlongitude) {
//    this.teachlongitude = teachlongitude;
//  }
//
//
//  public String getTeachlatitude() {
//    return teachlatitude;
//  }
//
//  public void setTeachlatitude(String teachlatitude) {
//    this.teachlatitude = teachlatitude;
//  }
//
//
//  public long getSightype() {
//    return sightype;
//  }
//
//  public void setSightype(long sightype) {
//    this.sightype = sightype;
//  }
//
//
//  public long getIsEnd() {
//    return isEnd;
//  }
//
//  public void setIsEnd(long isEnd) {
//    this.isEnd = isEnd;
//  }

}
