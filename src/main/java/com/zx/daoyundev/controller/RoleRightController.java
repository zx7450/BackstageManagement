package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Role;
import com.zx.daoyundev.exception.UserFriendException;
import com.zx.daoyundev.service.RoleService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/26 23:02
 */
@Api(tags = "角色接口")
@RestController
@CrossOrigin
@RequestMapping("/roleright")
public class RoleRightController {
    @Autowired
    RoleService roleService;

    @ApiOperation(value = "获取所有角色权限信息")
    @GetMapping("/roleList")
    public Result getRoleList() {
        List<Role> roleList = roleService.queryAllRole();
        return Result.success().setData(roleList).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @ApiOperation(value = "根据角色id获取角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "path", required = true)
    @GetMapping("/getRoleById/{roleId}")
    public Result getRoleById(@PathVariable Integer roleId) {
        try {
            roleService.getRoleById(roleId);
        } catch (UserFriendException u) {
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg(u.getMsg());
        }
        Role role = roleService.getRoleById(roleId);
        //List<Roleright> rolerightList = rightService.getRoleRightByRoleId(roleId);
        //role.setRolerights(rolerightList);
        return Result.success().setData(role).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @ApiOperation(value = "根据角色id删除角色")
    @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "path", required = true)
    @RequiresRoles("admin")
    @PostMapping("/deleteRoleById/{roleId}")
    public Result deleteRoleById(@PathVariable Integer roleId) {
        try {
            roleService.deleteRoleById(roleId);
        } catch (UserFriendException u) {
            return Result.failure(ResultCodeEnum.DELETE_FAILED).setMsg(u.getMsg());
        }
        return Result.success().setCode(ResultCodeEnum.DELETED.getCode());
    }

    @ApiOperation(value = "修改角色信息（包括权限）")
    @RequiresRoles("admin")
    @PostMapping("/updateRoleById")
    public Result updateRoleById(@RequestBody Role role) {
        if (role.getRoleId() == null)
            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg(("角色id不能为空"));
        else if (role.getRoleName() == null)
            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg(("角色名称不能为空"));
        try {
            roleService.getRoleById(role.getRoleId());
        } catch (UserFriendException u) {
            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg("未查询到该角色,无法修改");
        }
        roleService.updateRole(role);
        return Result.success().setCode(ResultCodeEnum.UPDATED.getCode()).setMsg("修改角色信息成功");
    }

    @ApiOperation(value = "新增角色")
    @RequiresRoles("admin")
    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role) {
        if (role.getRoleName() == null)
            return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("角色名称不能为空");
        Integer roleId = roleService.addRole(role);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("roleId", roleId);
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("新增角色成功");
    }
}
