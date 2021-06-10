package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DictionaryMapper {

    List<Dictionary> queryAllDic();
    int addDict(Dictionary dictionary);

}
