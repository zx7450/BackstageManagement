package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Roleright;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/27 0:30
 */
@Mapper
@Repository
public interface RolerightMapper {
    //获取所有角色权限信息
    List<Roleright> queryAllRoleRight();

    //根据角色权限id查询角色权限信息
    Roleright getRoleRightById(Integer rolerightId);

    //根据角色id查看该角色所拥有的所有权限
    List<Roleright> getRoleRightByRoleId(Integer roleId);

    //根据权限id查看该权限为那些角色所有
    List<Roleright> getRoleRightByRightId(Integer rightId);

    //根据角色id和权限id获取角色权限数目
    int getRoleRightCountById(Roleright roleright);

    //根据角色权限id删除角色权限（删除某角色的某一权限）
    void deleteRoleRightById(Roleright roleright);

    //新增角色权限
    Integer addRoleRight(Roleright roleright);
}
