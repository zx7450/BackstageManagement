package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "菜单实体")
@Data
public class Menu {

  @ApiModelProperty(value = "菜单id")
  private Integer menuId;
  @ApiModelProperty(value = "所属父菜单id，若无父菜单则为0")
  private Integer parentId;
  @ApiModelProperty(value = "菜单标题（用于展示的）")
  private String title;
  @ApiModelProperty(value = "菜单名（英文）")
  private String name;
  @ApiModelProperty(value = "菜单图标")
  private String icon;
  @ApiModelProperty(value = "菜单所指路径")
  private String path;
  @ApiModelProperty(value = "菜单顺序")
  private Integer sort;
  @ApiModelProperty(value = "是否显示。0为显示，1为不显示")
  private Integer hidden;
  @ApiModelProperty(value = "创建时间")
  private java.sql.Timestamp createTime;
  @ApiModelProperty(value = "子菜单数组")
  private List<Menu> childmenus;
  @ApiModelProperty(value = "菜单类型")
  private Integer menutype;
}
