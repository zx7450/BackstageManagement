package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Righ;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/27 0:09
 */
@Mapper
@Repository
public interface RighMapper {
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

    //根据权限id获取权限数
    int geyRightCountByRightId(Integer rightId);
}
