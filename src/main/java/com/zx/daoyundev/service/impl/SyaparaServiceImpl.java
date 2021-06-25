package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Syapara;
import com.zx.daoyundev.mapper.SyaparaMapper;
import com.zx.daoyundev.service.SyaparaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyaparaServiceImpl implements SyaparaService {
    @Autowired
    SyaparaMapper syaparaMapper;

    @Override
    public List<Syapara> queryAllpara() {
        return syaparaMapper.queryAllpara();
    }

    //    public int getcountBykeyword(String keyword){
//        return syaparaMapper.getcountBykeyword(keyword);
//    }
    @Override
    public int addpara(Syapara syapara) {
        return syaparaMapper.addpara(syapara);
    }

    @Override
    public Syapara getparaBykeyword(String keyword) {
        return syaparaMapper.getparaBykeyword(keyword);
    }

    @Override
    public int updateparaBykeyword(Syapara syapara) {
        return syaparaMapper.updateparaBykeyword(syapara);
    }

    @Override
    public int deleteparaById(long sysparaid) {
        return syaparaMapper.deleteparaById(sysparaid);
    }
}
