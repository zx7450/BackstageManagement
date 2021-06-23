package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "系统参数实体")
public class Syapara {

  @ApiModelProperty(value = "参数id")
  private long sysparaid;
  @ApiModelProperty(value = "提示名称")
  private String hintname;
  @ApiModelProperty(value = "参数关键字")
  private String keyword;
  @ApiModelProperty(value = "参数值")
  private long value;

  @Override
  public String toString() {
    return "Syapara{" +
            "sysparaid=" + sysparaid +
            ", hintname='" + hintname + '\'' +
            ", keyword='" + keyword + '\'' +
            ", value=" + value +
            '}';
  }

  public long getSysparaid() {
    return sysparaid;
  }

  public void setSysparaid(long sysparaid) {
    this.sysparaid = sysparaid;
  }


  public String getHintname() {
    return hintname;
  }

  public void setHintname(String hintname) {
    this.hintname = hintname;
  }


  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }


  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

}
