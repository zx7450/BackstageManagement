package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Initsign;
import com.zx.daoyundev.mapper.InitsignMapper;
import com.zx.daoyundev.service.InitsignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitsignServiceImpl implements InitsignService {
    @Autowired
    InitsignMapper initsignMapper;

    @Override
    public List<Initsign> gethistory(Initsign initsign) {
        return initsignMapper.gethistorybyCourseid(initsign);
    }

    @Override
    public int gethistoryCountbyCourseid(Initsign initsign){
        return initsignMapper.gethistoryCountbyCourseid(initsign);
    }

    @Override
    public Initsign getinitsignbyteachersignId(int teachersignId) {
        return initsignMapper.getinitsignbyteachersignId(teachersignId);
    }

    @Override
    public Initsign getsigningbyCourseid(Initsign initsign) {
        return initsignMapper.getsigningbyCourseid(initsign);
    }

    @Override
    public int getsigningCountbyCourseid(Initsign initsign) {
        return initsignMapper.getsigningCountbyCourseid(initsign);
    }

    @Override
    public int judgetimesign(Initsign initsign) {
        return initsignMapper.judgetimesign(initsign);
    }

    @Override
    public int initAnsign(Initsign initsign) {
        initsignMapper.initAnsign(initsign);
        return initsign.getTeachersignId();
    }

    @Override
    public int updatesigntime(Initsign initsign) {
        return initsignMapper.updatesigntime(initsign);
    }

    @Override
    public int endsigh(Initsign initsign) {
        return initsignMapper.endsigh(initsign);
    }

    @Override
    public int deletesighbyId(int teachersignId) {
        return initsignMapper.deletesighbyId(teachersignId);
    }

}
