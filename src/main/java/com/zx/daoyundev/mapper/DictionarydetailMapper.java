package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Dictionarydetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DictionarydetailMapper {
    int addDictionary(Dictionarydetail dictionarydetail);

    List<Dictionarydetail> SelectByCode(String dictionaryCode);

    List<Dictionarydetail> SelectById(long dictionaryId);

    List<Dictionarydetail> getchild(long detailpId);

    int deleteByDetailid(long dictionaryDetailId);

    //int updateByDetailid(Dictionarydetail dictionarydetail);
    int updateByDetailid(Dictionarydetail dictionarydetail);
}
