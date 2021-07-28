package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Role;
import com.zx.daoyundev.exception.UserFriendException;
import com.zx.daoyundev.mapper.RoleMapper;
import com.zx.daoyundev.service.RoleService;
import com.zx.daoyundev.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/26 22:23
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> queryAllRole() {
        return roleMapper.queryAllRole();
    }

    @Override
    public Role getRoleById(Integer roleId) {
        Role role = roleMapper.getRoleById(roleId);
        if (role == null)
            throw new UserFriendException("未查询到该角色", ResultCodeEnum.INQUIRE_FAILED.getCode());
        return role;
    }

    @Override
    public int getRoleCountById(Integer roleId) {
        return roleMapper.getRoleCountById(roleId);
    }

    @Override
    public void deleteRoleById(Integer roleId) {
        Role role = roleMapper.getRoleById(roleId);
        if (role == null)
            throw new UserFriendException("未查询到该角色，无法删除", ResultCodeEnum.DELETE_FAILED.getCode());
        roleMapper.deleteRoleById(roleId);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public Integer addRole(Role role) {
        roleMapper.addRole(role);
        return role.getRoleId();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleMapper.getRoleByName(roleName);
    }
}
