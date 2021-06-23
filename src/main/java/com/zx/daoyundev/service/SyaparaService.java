package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Syapara;
import com.zx.daoyundev.mapper.SyaparaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyaparaService {
    @Autowired
    SyaparaMapper syaparaMapper;
    public List<Syapara> queryAllpara(){
        return syaparaMapper.queryAllpara();
    }
//    public int getcountBykeyword(String keyword){
//        return syaparaMapper.getcountBykeyword(keyword);
//    }
    public int addpara(Syapara syapara){ return syaparaMapper.addpara(syapara);}
    public Syapara getparaBykeyword(String keyword){return syaparaMapper.getparaBykeyword(keyword);}
    public int updateparaBykeyword(Syapara syapara){return syaparaMapper.updateparaBykeyword(syapara);}
    public int deleteparaById(long sysparaid){return syaparaMapper.deleteparaById(sysparaid);}
}
