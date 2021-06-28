package com.zx.daoyundev.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/28 23:01
 */
@ApiModel(value = "角色菜单DTO，用于传参")
@Data
public class RoleMenuDTO {
    private Integer roleId;
    private List<Integer> menuIdList;
}
