package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Menu;
import com.zx.daoyundev.entity.RoleMenuDTO;
import com.zx.daoyundev.mapper.MenuMapper;
import com.zx.daoyundev.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/28 22:12
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuWithChildByRoleId(Integer roleId) {
        List<Menu> menuList = menuMapper.getParentMenuByRoleId(roleId);
        for (int i = 0; i < menuList.size(); i++) {
            List<Menu> childMenu = this.getChildMenuByPidAndRoleId(menuList.get(i).getMenuId(), roleId);
            menuList.get(i).setChildmenus(childMenu);
        }
        return menuList;
    }

    @Override
    public List<Menu> getChildMenuByPidAndRoleId(Integer parentId, Integer roleId) {
        List<Menu> menuList = menuMapper.getChildMenuByPidAndRoleId(parentId, roleId);
        for (int i = 0; i < menuList.size(); i++) {
            if (menuMapper.getChildMenuCountByPidAndRoleId(menuList.get(i).getMenuId(), roleId) != 0)
                menuList.get(i).setChildmenus(this.getChildMenuByPidAndRoleId(menuList.get(i).getMenuId(), roleId));
        }
        return menuList;
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> menuList = menuMapper.getAllMenu();
        for (int i = 0; i < menuList.size(); i++) {
            List<Menu> childMenu = this.getChildMenuByPid(menuList.get(i).getMenuId());
            menuList.get(i).setChildmenus(childMenu);
        }
        return menuList;
    }

    @Override
    public List<Menu> getChildMenuByPid(Integer parentId) {
        List<Menu> menuList = menuMapper.getChildMenuByPid(parentId);
        for (int i = 0; i < menuList.size(); i++) {
            if (menuMapper.getChildMenuCountByPid(menuList.get(i).getMenuId()) != 0)
                menuList.get(i).setChildmenus(this.getChildMenuByPid(menuList.get(i).getMenuId()));
        }
        return menuList;
    }

    @Override
    public Integer addMenu(Menu menu) {
        menuMapper.addMenu(menu);
        return menu.getMenuId();
    }

    @Override
    public void updateMenuById(Menu menu) {
        menuMapper.updateMenuById(menu);
    }

    @Override
    public void deleteMenuWithChildById(Integer menuId) {
        menuMapper.deleteMenuByPid(menuId);
        menuMapper.deleteMenuById(menuId);
    }

    @Override

    public void insertRoleMenu(RoleMenuDTO roleMenuDTO) {
        menuMapper.insertRoleMenu(roleMenuDTO);
    }

    @Override
    public void deleteRoleMenuById(RoleMenuDTO roleMenuDTO) {
        for (int i = 0; i < roleMenuDTO.getMenuIdList().size(); i++) {
            menuMapper.deleteRoleMenuById(roleMenuDTO.getRoleId(), roleMenuDTO.getMenuIdList().get(i));
        }
    }

    @Override
    public int getRoleMenuCountById(Integer rolemenuId) {
        return menuMapper.getRoleMenuCountById(rolemenuId);
    }
}
