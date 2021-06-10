package com.zx.daoyundev.entity;


public class Dictionary {

  private long dictionaryId;
  private String dictionaryCode;
  private String dictionaryDescribe;
//  private String creater;
//  private java.sql.Time createDate;


  @Override
  public String toString() {
    return "Dictionary{" +
            "dictionaryId=" + dictionaryId +
            ", dictionaryCode='" + dictionaryCode + '\'' +
            ", dictionaryDescribe='" + dictionaryDescribe + '\'' +
            '}';
  }

  public long getDictionaryId() {
    return dictionaryId;
  }

  public void setDictionaryId(long dictionaryId) {
    this.dictionaryId = dictionaryId;
  }


  public String getDictionaryCode() {
    return dictionaryCode;
  }

  public void setDictionaryCode(String dictionaryCode) {
    this.dictionaryCode = dictionaryCode;
  }


  public String getDictionaryDescribe() {
    return dictionaryDescribe;
  }

  public void setDictionaryDescribe(String dictionaryDescribe) {
    this.dictionaryDescribe = dictionaryDescribe;
  }


//  public String getCreater() {
//    return creater;
//  }
//
//  public void setCreater(String creater) {
//    this.creater = creater;
//  }
//
//
//  public java.sql.Time getCreateDate() {
//    return createDate;
//  }
//
//  public void setCreateDate(java.sql.Time createDate) {
//    this.createDate = createDate;
//  }

}
