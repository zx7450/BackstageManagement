package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "角色菜单实体")
@Data
public class RoleMenu {

  @ApiModelProperty(value = "角色菜单id")
  private Integer rolemenuId;
  @ApiModelProperty(value = "角色id")
  private Integer roleId;
  @ApiModelProperty(value = "菜单id")
  private Integer menuId;
  @ApiModelProperty(value = "创建时间")
  private java.sql.Timestamp createTime;

}
