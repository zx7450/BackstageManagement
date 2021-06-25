package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Initsign;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/24 16:31
 */
public interface InitsignService {
    List<Initsign> gethistory(Initsign initsign);

    Initsign getinitsignbyteachersignId(int teachersignId);

    Initsign getsigningbyCourseid(Initsign initsign);

    int getsigningCountbyCourseid(Initsign initsign);

    int judgetimesign(Initsign initsign);

    int initAnsign(Initsign initsign);

    int updatesigntime(Initsign initsign);

    int endsigh(Initsign initsign);

    int deletesighbyId(int teachersignId);
}
