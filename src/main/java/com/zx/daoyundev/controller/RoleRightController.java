package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Righ;
import com.zx.daoyundev.entity.Role;
import com.zx.daoyundev.entity.Roleright;
import com.zx.daoyundev.exception.UserFriendException;
import com.zx.daoyundev.service.RightService;
import com.zx.daoyundev.service.RoleService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @Autowired
    RightService rightService;

    @ApiOperation(value = "获取所有角色权限信息")
    @GetMapping("/roleList")
    public Result getRoleList() {
        List<Role> roleList = roleService.queryAllRole();
        for (int i = 0; i < roleList.size(); i++) {
            List<Roleright> rolerightList = rightService.getRoleRightByRoleId(roleList.get(i).getRoleId());
//            for (int i1 = 0; i1 < rolerightList.size(); i1++) {
//                rolerightList.get(i1).setUpdateflag(0);
//            }
            roleList.get(i).setRolerights(rolerightList);
        }
        return Result.success().setData(roleList).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @ApiOperation(value = "根据角色id获取角色权限信息")
    @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "path", required = true)
    @GetMapping("/getRoleById/{roleId}")
    public Result getRoleById(@PathVariable Integer roleId) {
        try {
            roleService.getRoleById(roleId);
        } catch (UserFriendException u) {
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg(u.getMsg());
        }
        Role role = roleService.getRoleById(roleId);
        List<Roleright> rolerightList = rightService.getRoleRightByRoleId(roleId);
        role.setRolerights(rolerightList);
        return Result.success().setData(role).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @ApiOperation(value = "根据角色id删除角色")
    @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "path", required = true)
    @PostMapping("/deleteRoleById/{roleId}")
    public Result deleteRoleById(@PathVariable Integer roleId) {
        try {
            roleService.deleteRoleById(roleId);
        } catch (UserFriendException u) {
            return Result.failure(ResultCodeEnum.DELETE_FAILED).setMsg(u.getMsg());
        }
        return Result.success().setCode(ResultCodeEnum.DELETED.getCode());
    }

    //    @ApiOperation(value = "修改角色信息")
//    @PostMapping("/updateRoleById")
//    public Result updateRoleById(@RequestBody Role role){
//        if (role.getRoleId() == null)
//            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg(("角色id不能为空"));
//        else if (role.getRoleName() == null)
//            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg(("角色名称不能为空"));
//        try {
//            roleService.getRoleById(role.getRoleId());
//        } catch (UserFriendException u){
//            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg("未查询到该角色,无法修改");
//        }
//        roleService.updateRole(role);
//        for (int i = 0; i < role.getRolerights().size(); i++) {
//            if (role.getRolerights().get(i).getUpdateflag()==0)
//                continue;
//            else if (role.getRolerights().get(i).getUpdateflag()==1){
//                if (rightService.geyRightCountByRightId(role.getRolerights().get(i).getRightId())==0)
//                    return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("未查询到该权限,无法为该角色新增权限");
//                rightService.addRoleRight(role.getRolerights().get(i));
//            } else {
//                if (rightService.geyRightCountByRightId(role.getRolerights().get(i).getRightId())==0)
//                    return Result.failure(ResultCodeEnum.DELETE_FAILED).setMsg("未查询到该权限,无法为该角色删除权限");
//                rightService.deleteRoleRightById(role.getRolerights().get(i).getRolerightId());
//            }
//        }
//        return Result.success().setCode(ResultCodeEnum.UPDATED.getCode());
//    }
    @ApiOperation(value = "新增角色权限信息")
    @PostMapping("/addRoleRightById")
    public Result addRoleRightById(@RequestBody Roleright roleright) {
        if (roleService.getRoleCountById(roleright.getRoleId()) == 0)
            return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("未查询到该角色，无法分配权限");
        else if (rightService.geyRightCountByRightId(roleright.getRightId()) == 0)
            return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("未查询到该权限，无法分配权限");
        else {
            Integer rolerightId = rightService.addRoleRight(roleright);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rolerightId", rolerightId);
            return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("新增角色权限成功");
        }
    }

    @ApiOperation(value = "删除角色权限信息")
    @PostMapping("/deleteRoleRightById")
    public Result deleteRoleRightById(@RequestBody Roleright roleright) {
        if (rightService.getRoleRightCountById(roleright) == 0)
            return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("未查询到该角色权限，无法分配权限");
        rightService.deleteRoleRightById(roleright);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除角色权限成功！");
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role) {
        if (role.getRoleName() == null)
            return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("角色名称不能为空");
        Integer roleId = roleService.addRole(role);
        if (role.getRolerights().size() != 0) {
            for (int i = 0; i < role.getRolerights().size(); i++) {
                if (rightService.geyRightCountByRightId(role.getRolerights().get(i).getRightId()) == 0)
                    return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("未查询到该权限,无法为该角色新增权限");
                role.getRolerights().get(i).setRoleId(roleId);
                rightService.addRoleRight(role.getRolerights().get(i));
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("roleId", roleId);
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("新增角色成功");
    }

    @ApiOperation(value = "查询所有权限")
    @GetMapping("/rightList")
    public Result rightList() {
        List<Righ> righList = rightService.queryAllRigh();
        return Result.success().setData(righList).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有权限成功");
    }
}
