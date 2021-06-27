package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "权限实体")
public class Righ {

  @ApiModelProperty(value = "权限id")
  private Integer rightId;
  @ApiModelProperty(value = "权限名称")
  private String rightName;
  @ApiModelProperty(value = "权限描述")
  private String rightDescribe;

  @Override
  public String toString() {
    return "Righ{" +
            "rightId=" + rightId +
            ", rightName='" + rightName + '\'' +
            ", rightDescribe='" + rightDescribe + '\'' +
            '}';
  }

  public Integer getRightId() {
    return rightId;
  }

  public void setRightId(Integer rightId) {
    this.rightId = rightId;
  }


  public String getRightName() {
    return rightName;
  }

  public void setRightName(String rightName) {
    this.rightName = rightName;
  }


  public String getRightDescribe() {
    return rightDescribe;
  }

  public void setRightDescribe(String rightDescribe) {
    this.rightDescribe = rightDescribe;
  }

}
