package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Menu;
import com.zx.daoyundev.entity.RoleMenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/28 21:06
 */
@Mapper
@Repository
public interface MenuMapper {
    //根据角色id获取所有一级菜单
    List<Menu> getParentMenuByRoleId(Integer roleId);

    //获取所有菜单
    List<Menu> getAllMenu();

    //根据父id获取某菜单的子菜单
    List<Menu> getChildMenuByPid(Integer parentId);

    //根据父id和角色获取某菜单的子菜单
    List<Menu> getChildMenuByPidAndRoleId(Integer parentId, Integer roleId);

    //根据父id和角色id获取某菜单的子菜单数目
    int getChildMenuCountByPidAndRoleId(Integer parentId, Integer roleId);

    //根据父id获取某菜单的子菜单数目
    int getChildMenuCountByPid(Integer parentId);

    //新增菜单
    Integer addMenu(Menu menu);

    //修改菜单
    void updateMenuById(Menu menu);

    //根据菜单id删除一级菜单
    void deleteMenuById(Integer menuId);

    //根据父id删除某菜单的所有子菜单
    void deleteMenuByPid(Integer parentId);

    //根据角色id批量新增菜单
    void insertRoleMenu(RoleMenuDTO roleMenuDTO);

    //根据角色id和菜单id删除角色菜单
    void deleteRoleMenuById(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    //根据角色菜单id获取角色菜单数目
    int getRoleMenuCountById(Integer rolemenuId);
}
