package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Syapara;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/24 17:36
 */
public interface SyaparaService {
    List<Syapara> queryAllpara();

    int addpara(Syapara syapara);

    Syapara getparaBykeyword(String keyword);

    int updateparaBykeyword(Syapara syapara);

    int deleteparaById(long sysparaid);
}
