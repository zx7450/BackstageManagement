package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "字典详细DTO", description="传参用，加了一个flag表示修改标记")
@Data
public class DictionarydetailDTO {
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
    @ApiModelProperty(value = "修改标记，0表示无修改，1表示新增，2表示修改，3表示删除")
    private int updateflag;
}
