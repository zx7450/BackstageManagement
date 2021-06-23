package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Initsign;
import com.zx.daoyundev.mapper.InitsignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitsignService {
    @Autowired
    InitsignMapper initsignMapper;
    public List<Initsign> gethistory(Initsign initsign){ return initsignMapper.gethistorybyCourseid(initsign);}
    public Initsign getinitsignbyteachersignId(int teachersignId){ return initsignMapper.getinitsignbyteachersignId(teachersignId);}
    public Initsign getsigningbyCourseid(Initsign initsign){ return initsignMapper.getsigningbyCourseid(initsign);}
    public int getsigningCountbyCourseid(Initsign initsign){ return initsignMapper.getsigningCountbyCourseid(initsign);}
    public int judgetimesign(Initsign initsign){ return initsignMapper.judgetimesign(initsign);}
    public int initAnsign(Initsign initsign){
        initsignMapper.initAnsign(initsign);
        return initsign.getTeachersignId();
    }
    public int updatesigntime(Initsign initsign){ return initsignMapper.updatesigntime(initsign);}
    public int endsigh(Initsign initsign){ return initsignMapper.endsigh(initsign);}
    public int deletesighbyId(int teachersignId){ return initsignMapper.deletesighbyId(teachersignId);}

}
