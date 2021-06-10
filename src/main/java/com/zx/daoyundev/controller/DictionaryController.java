package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Dictionary;
import com.zx.daoyundev.entity.Dictionarydetail;
import com.zx.daoyundev.entity.DictionarydetailDTO;
import com.zx.daoyundev.service.DictionaryService;
import com.zx.daoyundev.service.DictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin//跨域支持
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionarydetailService dictionarydetailService;
    @RequestMapping("")
    public Object getdictionary(){
        JSONArray jsonArray =new JSONArray();
        List<Dictionary> dictionaryList=dictionaryService.queryAllDic();
        for (int i=0;i<dictionaryList.size();i++){
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("dictionaryId",dictionaryList.get(i).getDictionaryId());
            jsonObject.put("dictionaryCode",dictionaryList.get(i).getDictionaryCode());
            jsonObject.put("dictionaryDescribe",dictionaryList.get(i).getDictionaryDescribe());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @RequestMapping("/adddic")
    public Object addDic(@RequestBody Dictionary dictionary){
        int i =dictionaryService.addDict(dictionary);
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("success",true);
        jsonObject.put("message","添加字典成功");
        return jsonObject;
    }
    @RequestMapping("/dictionarydetail/{dictionaryCode}")
    public Object getdictionarydetail(@PathVariable String dictionaryCode){
        JSONArray jsonArray =new JSONArray();
        List<Dictionarydetail> dictionarydetailList=dictionarydetailService.SelectByCode(dictionaryCode);
        for (int i = 0; i < dictionarydetailList.size(); i++) {
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("dictionaryDetailId",dictionarydetailList.get(i).getDictionaryDetailId());
            jsonObject.put("dictionaryId",dictionarydetailList.get(i).getDictionaryId());
            jsonObject.put("dictionaryCode",dictionarydetailList.get(i).getDictionaryCode());
            jsonObject.put("itemKey",dictionarydetailList.get(i).getItemKey());
            jsonObject.put("itemValue",dictionarydetailList.get(i).getItemValue());
            jsonObject.put("isdefault",dictionarydetailList.get(i).getIsdefault());
            jsonObject.put("updateflag",0);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping("/updatedictionary")
    public Object updateDictionary(@RequestBody List<DictionarydetailDTO> dictionarydetailDTOList){
        for (int i = 0; i < dictionarydetailDTOList.size(); i++) {
            if(dictionarydetailDTOList.get(i).getUpdateflag()==0){
                continue;
            }
            else if(dictionarydetailDTOList.get(i).getUpdateflag()==3){
                int j = dictionarydetailService.deleteByDetailid(dictionarydetailDTOList.get(i).getDictionaryDetailId());
//                Dictionarydetail dictionarydetail=new Dictionarydetail();
//                DictionarydetailDTO dictionarydetailDTO=dictionarydetailDTOList.get(i);
//                dictionarydetail.setDictionaryId(dictionarydetailDTO.getDictionaryId());
//                dictionarydetail.setDictionaryCode(dictionarydetailDTO.getDictionaryCode());
//                dictionarydetail.setItemKey(dictionarydetailDTO.getItemKey());
//                dictionarydetail.setItemValue(dictionarydetailDTO.getItemValue());
//                dictionarydetail.setIsdefault(dictionarydetailDTO.getIsdefault());
//                int j= dictionarydetailService.addDictionary(dictionarydetail);
            }
            else{
                Dictionarydetail dictionarydetail=new Dictionarydetail();
                DictionarydetailDTO dictionarydetailDTO=dictionarydetailDTOList.get(i);
                dictionarydetail.setDictionaryId(dictionarydetailDTO.getDictionaryId());
                dictionarydetail.setDictionaryCode(dictionarydetailDTO.getDictionaryCode());
                dictionarydetail.setItemKey(dictionarydetailDTO.getItemKey());
                dictionarydetail.setItemValue(dictionarydetailDTO.getItemValue());
                dictionarydetail.setIsdefault(dictionarydetailDTO.getIsdefault());
                if(dictionarydetailDTO.getUpdateflag()==1){
                    int j= dictionarydetailService.addDictionary(dictionarydetail);
                }
                else{
                    dictionarydetail.setDictionaryDetailId(dictionarydetailDTO.getDictionaryDetailId());
                    int j =dictionarydetailService.updateByDetailid(dictionarydetail);
                }
            }
        }
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("success",true);
        jsonObject.put("message","修改字典成功");
        return jsonObject;
    }
//    @RequestMapping("/adddictionary")
//    public Object addDictionary(@RequestBody Dictionarydetail dictionarydetail){
//        int i =dictionarydetailService.addDictionary(dictionarydetail);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","添加字典成功");
//        return jsonObject;
//    }
//    @RequestMapping("/updatedictionary")
//    public Object updateDictionary(@RequestBody Dictionarydetail dictionarydetail){
//        int i =dictionarydetailService.updateByDetailid(dictionarydetail);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","修改字典成功");
//        return jsonObject;
//    }
//    @RequestMapping("/deletedictionary/{dictionaryDetailId}")
//    public Object deleteDictionary(@PathVariable long dictionaryDetailId){
//        int i=dictionarydetailService.deleteByDetailid(dictionaryDetailId);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","删除字典成功");
//        return jsonObject;
//    }

}
