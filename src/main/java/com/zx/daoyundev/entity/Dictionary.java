package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "数据字典实体", description = "左表")
public class Dictionary {

    @ApiModelProperty(value = "字典id")
    private long dictionaryId;
    @ApiModelProperty(value = "字典code")
    private String dictionaryCode;
    @ApiModelProperty(value = "字典描述")
    private String dictionaryDescribe;
    @ApiModelProperty(value = "父字典id，若没有则为0")
    private long pId;
//  private String creater;
//  private java.sql.Time createDate;


    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionaryId=" + dictionaryId +
                ", dictionaryCode='" + dictionaryCode + '\'' +
                ", dictionaryDescribe='" + dictionaryDescribe + '\'' +
                ", pId=" + pId +
                '}';
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
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
