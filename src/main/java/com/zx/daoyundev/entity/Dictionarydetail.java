package com.zx.daoyundev.entity;


public class Dictionarydetail {

  private long dictionaryDetailId;
  private long dictionaryId;
  private String dictionaryCode;
  private long itemKey;
  private String itemValue;
  private long isdefault;

  @Override
  public String toString() {
    return "Dictionarydetail{" +
            "dictionaryDetailId=" + dictionaryDetailId +
            ", dictionaryId=" + dictionaryId +
            ", dictionaryCode='" + dictionaryCode + '\'' +
            ", itemKey=" + itemKey +
            ", itemValue='" + itemValue + '\'' +
            ", isdefault=" + isdefault +
            '}';
  }

  public long getDictionaryDetailId() {
    return dictionaryDetailId;
  }

  public void setDictionaryDetailId(long dictionaryDetailId) {
    this.dictionaryDetailId = dictionaryDetailId;
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


  public long getItemKey() {
    return itemKey;
  }

  public void setItemKey(long itemKey) {
    this.itemKey = itemKey;
  }


  public String getItemValue() {
    return itemValue;
  }

  public void setItemValue(String itemValue) {
    this.itemValue = itemValue;
  }


  public long getIsdefault() {
    return isdefault;
  }

  public void setIsdefault(long isdefault) {
    this.isdefault = isdefault;
  }

}
