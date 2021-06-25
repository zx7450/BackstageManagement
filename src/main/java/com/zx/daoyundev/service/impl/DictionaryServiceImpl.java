package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Dictionary;
import com.zx.daoyundev.mapper.DictionaryMapper;
import com.zx.daoyundev.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> queryAllDic() {
        return dictionaryMapper.queryAllDic();
    }

    @Override
    public int addDict(Dictionary dictionary) {
        return dictionaryMapper.addDict(dictionary);
    }

    @Override
    public int getcountbypId(long pId) {
        return dictionaryMapper.getcountbypId(pId);
    }

    @Override
    public List<Dictionary> querybypId(long pId) {
        return dictionaryMapper.querybypId(pId);
    }
}
