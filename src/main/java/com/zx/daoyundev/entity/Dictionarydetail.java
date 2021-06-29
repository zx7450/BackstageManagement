package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "字典详细实体", description = "右表")
public class Dictionarydetail {

    @ApiModelProperty(value = "字典详细id")
    private long dictionaryDetailId;
    @ApiModelProperty(value = "字典id")
    private long dictionaryId;
    @ApiModelProperty(value = "字典code")
    private String dictionaryCode;
    @ApiModelProperty(value = "字典key")
    private long itemKey;
    @ApiModelProperty(value = "key代表的值")
    private String itemValue;
    @ApiModelProperty(value = "是否默认")
    private long isdefault;
    @ApiModelProperty(value = "字典详细夫id，若无则设为0")
    private long detailpId;

    @Override
    public String toString() {
        return "Dictionarydetail{" +
                "dictionaryDetailId=" + dictionaryDetailId +
                ", dictionaryId=" + dictionaryId +
                ", dictionaryCode='" + dictionaryCode + '\'' +
                ", itemKey=" + itemKey +
                ", itemValue='" + itemValue + '\'' +
                ", isdefault=" + isdefault +
                ", detailpId=" + detailpId +
                '}';
    }

    public long getDetailpId() {
        return detailpId;
    }

    public void setDetailpId(long detailpId) {
        this.detailpId = detailpId;
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
