package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Initsign;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InitsignMapper {
    List<Initsign> gethistorybyCourseid(Initsign initsign);
    Initsign getinitsignbyteachersignId(int teachersignId);
    Initsign getsigningbyCourseid(Initsign initsign);
    int getsigningCountbyCourseid(Initsign initsign);
    int judgetimesign(Initsign initsign);
    int initAnsign(Initsign initsign);
    int updatesigntime(Initsign initsign);
    int endsigh(Initsign initsign);//结束签到后要再已签到的学生经验加上相关经验值，若是放弃签到要删除学生签到表内的记录（经验值不管）
    int deletesighbyId(int teachersignId);//删除签到要再学生签到表里同步删除，并且扣去经验值
}
