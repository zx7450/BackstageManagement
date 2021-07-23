package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/28 23:01
 */
@ApiModel(value = "角色菜单DTO，用于传参")
@Data
public class RoleMenuDTO {
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    @ApiModelProperty(value = "菜单id数组")
    private List<Integer> menuIdList;
}
