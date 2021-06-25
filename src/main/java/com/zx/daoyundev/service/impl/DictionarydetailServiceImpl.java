package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Dictionarydetail;
import com.zx.daoyundev.mapper.DictionarydetailMapper;
import com.zx.daoyundev.service.DictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionarydetailServiceImpl implements DictionarydetailService {
    @Autowired
    DictionarydetailMapper dictionarydetailMapper;

    @Override
    public int addDictionary(Dictionarydetail dictionarydetail) {
        return dictionarydetailMapper.addDictionary(dictionarydetail);
    }

    @Override
    public List<Dictionarydetail> SelectByCode(String dictionaryCode) {
        return dictionarydetailMapper.SelectByCode(dictionaryCode);
    }

    @Override
    public List<Dictionarydetail> SelectById(long dictionaryId) {
        return dictionarydetailMapper.SelectById(dictionaryId);
    }

    @Override
    public List<Dictionarydetail> getchild(long detailpId) {
        return dictionarydetailMapper.getchild(detailpId);
    }

    @Override
    public int deleteByDetailid(long dictionaryDetailId) {
        return dictionarydetailMapper.deleteByDetailid(dictionaryDetailId);
    }

    @Override
    public int updateByDetailid(Dictionarydetail dictionarydetail) {
        return dictionarydetailMapper.updateByDetailid(dictionarydetail);
    }
}
