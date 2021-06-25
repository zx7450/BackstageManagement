package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Dictionarydetail;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/24 16:23
 */
public interface DictionarydetailService {
    int addDictionary(Dictionarydetail dictionarydetail);

    List<Dictionarydetail> SelectByCode(String dictionaryCode);

    List<Dictionarydetail> SelectById(long dictionaryId);

    List<Dictionarydetail> getchild(long detailpId);

    int deleteByDetailid(long dictionaryDetailId);

    int updateByDetailid(Dictionarydetail dictionarydetail);
}
