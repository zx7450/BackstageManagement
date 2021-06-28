package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/26 21:39
 */
@Mapper
@Repository
public interface RoleMapper {
    //获取所有角色信息
    List<Role> queryAllRole();

    //根据角色id获取角色信息
    Role getRoleById(Integer roleId);

    //根据角色id获取角色数
    int getRoleCountById(Integer roleId);

    //根据角色id删除角色，对应的权限也级联删除
    void deleteRoleById(Integer roleId);

    //修改角色信息
    void updateRole(Role role);

    //新增角色
    Integer addRole(Role role);
}
