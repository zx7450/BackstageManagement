package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Syapara;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SyaparaMapper {
    List<Syapara> queryAllpara();

    //int getcountBykeyword(String keyword);

    int addpara(Syapara syapara);

    Syapara getparaBykeyword(String keyword);

    int updateparaBykeyword(Syapara syapara);

    int deleteparaById(long sysparaid);
}
