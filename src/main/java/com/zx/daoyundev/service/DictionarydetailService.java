package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Dictionarydetail;
import com.zx.daoyundev.mapper.DictionarydetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionarydetailService {
    @Autowired
    DictionarydetailMapper dictionarydetailMapper;
    public int addDictionary(Dictionarydetail dictionarydetail){
        return dictionarydetailMapper.addDictionary(dictionarydetail);
    }
    public List<Dictionarydetail> SelectByCode(String dictionaryCode){
        return dictionarydetailMapper.SelectByCode(dictionaryCode);
    }
    public int deleteByDetailid(long dictionaryDetailId){
        return dictionarydetailMapper.deleteByDetailid(dictionaryDetailId);
    }
    public int updateByDetailid(Dictionarydetail dictionarydetail){
        return dictionarydetailMapper.updateByDetailid(dictionarydetail);
    }
}
