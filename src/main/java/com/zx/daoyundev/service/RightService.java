package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Righ;
import com.zx.daoyundev.entity.Roleright;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/27 0:19
 */
public interface RightService {
    //查询所有权限
    List<Righ> queryAllRigh();

    //根据权限id查找权限
    Righ getRightById(Integer rightId);

    //根据权限id删除权限，给所有角色分配的该权限也级联删除
    void deleteRightById(Integer rightId);

    //修改权限信息
    void updateRight(Righ righ);

    //新增权限
    Integer addRight(Righ righ);

    //获取所有角色权限信息
    List<Roleright> queryAllRoleRight();

    //根据角色权限id查询角色权限信息
    Roleright getRoleRightById(Integer rolerightId);

    //根据角色id查看该角色所拥有的所有权限
    List<Roleright> getRoleRightByRoleId(Integer roleId);

    //根据权限id查看该权限为那些角色所有
    List<Roleright> getRoleRightByRightId(Integer rightId);

    //根据角色权限id删除角色权限（删除某角色的某一权限）
    void deleteRoleRightById(Integer rolerightId);

    //新增角色权限
    Integer addRoleRight(Roleright roleright);

    //根据权限id获取权限数
    int geyRightCountByRightId(Integer rightId);
}
