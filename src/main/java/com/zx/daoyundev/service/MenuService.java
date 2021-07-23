package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Menu;
import com.zx.daoyundev.entity.RoleMenuDTO;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/28 22:12
 */
public interface MenuService {
    //根据角色id获取所有菜单(父子结构)
    List<Menu> getMenuWithChildByRoleId(Integer roleId);

    //获取所有菜单
    List<Menu> getAllMenu();

    //根据父id获取某菜单的子菜单
    List<Menu> getChildMenuByPid(Integer parentId);

    //根据父id和角色获取某菜单的子菜单
    List<Menu> getChildMenuByPidAndRoleId(Integer parentId, Integer roleId);

    //新增菜单
    Integer addMenu(Menu menu);

    //修改菜单
    void updateMenuById(Menu menu);

    //根据菜单id删除菜单及其子菜单
    void deleteMenuWithChildById(Integer menuId);

    //根据角色id批量新增菜单
    void insertRoleMenu(RoleMenuDTO roleMenuDTO);

    //根据角色id和菜单id批量删除角色菜单
    void deleteRoleMenuById(RoleMenuDTO roleMenuDTO);

    //根据角色菜单id获取角色菜单数目
    int getRoleMenuCountById(Integer rolemenuId);
}
