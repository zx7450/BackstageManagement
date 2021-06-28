package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Menu;
import com.zx.daoyundev.entity.RoleMenuDTO;
import com.zx.daoyundev.service.MenuService;
import com.zx.daoyundev.service.RoleService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/28 23:51
 */
@Api(tags = "菜单接口")
@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;

    @ApiOperation(value = "获取所有菜单")
    @GetMapping("/getAllMenu")
    public Result getAllMenu() {
        List<Menu> menuList = menuService.getAllMenu();
        return Result.success().setData(menuList).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取所有菜单成功");
    }

    @ApiOperation(value = "根据角色id层级获取菜单")
    @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "path", required = true)
    @GetMapping("/getMenuByRoleId/{roleId}")
    public Result getMenuByRoleId(@PathVariable Integer roleId) {
        List<Menu> menuList = menuService.getMenuWithChildByRoleId(roleId);
        return Result.success().setData(menuList).setCode(ResultCodeEnum.OK.getCode()).setMsg("根据角色id获取菜单成功");
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("/addMenu")
    public Result addMenu(@RequestBody Menu menu) {
        if (menu.getTitle() == null)
            return Result.failure(ResultCodeEnum.PARAMS_MISS).setMsg("菜单标题不能为空");
        else if (menu.getIcon() == null)
            return Result.failure(ResultCodeEnum.PARAMS_MISS).setMsg("菜单图标不能为空");
        else if (menu.getName() == null)
            return Result.failure(ResultCodeEnum.PARAMS_MISS).setMsg("菜单名称不能为空");
        Integer menuId = menuService.addMenu(menu);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("menuId", menuId);
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("新增菜单成功");
    }

    @ApiOperation(value = "修改菜单")
    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu) {
        menuService.updateMenuById(menu);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改菜单成功！");
    }

    @ApiOperation(value = "根据菜单id删除菜单（包括子菜单）")
    @ApiImplicitParam(name = "menuId", value = "菜单Id", paramType = "path", required = true)
    @PostMapping("/deleteMenu/{menuId}")
    public Result deleteMenu(@PathVariable Integer menuId) {
        menuService.deleteMenuWithChildById(menuId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除菜单成功！");
    }

    @ApiOperation(value = "根据角色id批量新增菜单")
    @PostMapping("/addRoleMenu")
    public Result addRoleMenu(@RequestBody RoleMenuDTO roleMenuDTO) {
        if (roleService.getRoleCountById(roleMenuDTO.getRoleId()) == 0)
            return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("未查询到该角色，无法新增菜单");
        menuService.insertRoleMenu(roleMenuDTO);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("为该角色新增菜单成功！");
    }

    @ApiOperation(value = "根据角色菜单id删除该角色的某一菜单")
    @ApiImplicitParam(name = "rolemenuId", value = "角色菜单Id", paramType = "path", required = true)
    @PostMapping("/deleteRoleMenu/{rolemenuId}")
    public Result deleteRoleMenu(@PathVariable Integer rolemenuId) {
        if (menuService.getRoleMenuCountById(rolemenuId) == 0)
            return Result.failure(ResultCodeEnum.PARAM_ERROR).setMsg("未查询到该角色菜单信息，无法删除菜单");
        menuService.deleteRoleMenuById(rolemenuId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("为该角色删除菜单成功！");
    }
}
