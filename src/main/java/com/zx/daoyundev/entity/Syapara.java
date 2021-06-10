package com.zx.daoyundev.entity;


public class Syapara {

  private long sysparaid;
  private String hintname;
  private String keyword;
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
