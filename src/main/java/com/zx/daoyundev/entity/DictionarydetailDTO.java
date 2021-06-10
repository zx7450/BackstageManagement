package com.zx.daoyundev.entity;

import lombok.Data;

@Data
public class DictionarydetailDTO {
    private long dictionaryDetailId;
    private long dictionaryId;
    private String dictionaryCode;
    private long itemKey;
    private String itemValue;
    private long isdefault;
    private int updateflag;//0表示无修改，1表示新增，2表示修改，3表示删除
}
