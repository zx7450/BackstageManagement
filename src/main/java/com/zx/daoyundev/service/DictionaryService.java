package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Dictionary;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/24 16:26
 */
public interface DictionaryService {
    List<Dictionary> queryAllDic();

    int addDict(Dictionary dictionary);

    int getcountbypId(long pId);

    List<Dictionary> querybypId(long pId);
}
