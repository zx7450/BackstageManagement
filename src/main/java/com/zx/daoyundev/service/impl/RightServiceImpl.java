package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Righ;
import com.zx.daoyundev.entity.Roleright;
import com.zx.daoyundev.exception.UserFriendException;
import com.zx.daoyundev.mapper.RighMapper;
import com.zx.daoyundev.mapper.RolerightMapper;
import com.zx.daoyundev.service.RightService;
import com.zx.daoyundev.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/27 0:20
 */
@Service
public class RightServiceImpl implements RightService {
    @Autowired
    RighMapper righMapper;

    @Autowired
    RolerightMapper rolerightMapper;

    @Override
    public List<Righ> queryAllRigh() {
        return righMapper.queryAllRigh();
    }

    @Override
    public Righ getRightById(Integer rightId) {
        Righ righ = righMapper.getRightById(rightId);
        if (righ == null)
            throw new UserFriendException("未查询到该权限", ResultCodeEnum.INQUIRE_FAILED.getCode());
        return righ;
    }

    @Override
    public void deleteRightById(Integer rightId) {
        Righ righ = righMapper.getRightById(rightId);
        if (righ == null)
            throw new UserFriendException("未查询到该权限，无法删除", ResultCodeEnum.INQUIRE_FAILED.getCode());
        righMapper.deleteRightById(rightId);
    }

    @Override
    public void updateRight(Righ righ) {
        if (righ.getRightId() == null)
            throw new UserFriendException("权限id不能为空", ResultCodeEnum.PARAM_ERROR.getCode());
        else if (righ.getRightName() == null)
            throw new UserFriendException("权限名称不能为空", ResultCodeEnum.PARAM_ERROR.getCode());
        else if (righMapper.getRightById(righ.getRightId()) == null)
            throw new UserFriendException("未查询到该权限，无法修改", ResultCodeEnum.UPDATE_FAILED.getCode());
        else
            righMapper.updateRight(righ);
    }

    @Override
    public Integer addRight(Righ righ) {
        righMapper.addRight(righ);
        return righ.getRightId();
    }

    @Override
    public List<Roleright> queryAllRoleRight() {
        return rolerightMapper.queryAllRoleRight();
    }

    @Override
    public Roleright getRoleRightById(Integer rolerightId) {
        Roleright roleright = rolerightMapper.getRoleRightById(rolerightId);
        if (roleright == null)
            throw new UserFriendException("未查询到该角色权限信息", ResultCodeEnum.INQUIRE_FAILED.getCode());
        return roleright;
    }

    @Override
    public List<Roleright> getRoleRightByRoleId(Integer roleId) {
        return rolerightMapper.getRoleRightByRoleId(roleId);
    }

    @Override
    public List<Roleright> getRoleRightByRightId(Integer rightId) {
        return rolerightMapper.getRoleRightByRightId(rightId);
    }

    @Override
    public void deleteRoleRightById(Integer rolerightId) {
        Roleright roleright = rolerightMapper.getRoleRightById(rolerightId);
        if (roleright == null)
            throw new UserFriendException("未查询到该角色权限信息，无法删除", ResultCodeEnum.INQUIRE_FAILED.getCode());
        rolerightMapper.deleteRoleRightById(rolerightId);
    }

    @Override
    public Integer addRoleRight(Roleright roleright) {
        rolerightMapper.addRoleRight(roleright);
        return roleright.getRolerightId();
    }

    @Override
    public int geyRightCountByRightId(Integer rightId){
        return righMapper.geyRightCountByRightId(rightId);
    }
}
