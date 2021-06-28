package com.zx.daoyundev.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "角色权限实体")
public class Roleright {

  @ApiModelProperty(value = "角色权限id")
  private Integer rolerightId;
  @ApiModelProperty(value = "角色id")
  private Integer roleId;
  @ApiModelProperty(value = "权限id")
  private Integer rightId;

}
