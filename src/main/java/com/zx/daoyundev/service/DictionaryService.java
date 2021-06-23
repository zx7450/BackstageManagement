package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Dictionary;
import com.zx.daoyundev.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {
    @Autowired
    DictionaryMapper dictionaryMapper;
    public List<Dictionary> queryAllDic(){
        return dictionaryMapper.queryAllDic();
    }
    public int addDict(Dictionary dictionary){ return dictionaryMapper.addDict(dictionary); }
    public int getcountbypId(long pId){ return dictionaryMapper.getcountbypId(pId);}
    public List<Dictionary> querybypId(long pId){ return dictionaryMapper.querybypId(pId);}
}
