package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Dictionary;
import com.zx.daoyundev.entity.Dictionarydetail;
import com.zx.daoyundev.entity.DictionarydetailDTO;
import com.zx.daoyundev.service.DictionaryService;
import com.zx.daoyundev.service.DictionarydetailService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "数据字典接口")
@RestController
@CrossOrigin//跨域支持
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionarydetailService dictionarydetailService;
    @ApiOperation(value = "获取字典表")
    @GetMapping("")
    public Result getdictionary(){
        return Result.success().setData(getchild(0)).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取数据字典表成功!");
//        JSONArray jsonArray =new JSONArray();
//        List<Dictionary> dictionaryList=dictionaryService.querybypId(0);
//        for (int i=0;i<dictionaryList.size();i++){
//            JSONObject jsonObject =new JSONObject();
//            jsonObject.put("dictionaryId",dictionaryList.get(i).getDictionaryId());
//            jsonObject.put("dictionaryCode",dictionaryList.get(i).getDictionaryCode());
//            jsonObject.put("dictionaryDescribe",dictionaryList.get(i).getDictionaryDescribe());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;

    }

    public Object getchild(long pId){
        JSONArray jsonArray =new JSONArray();
        List<Dictionary> dictionaryList=dictionaryService.querybypId(pId);
        for (int i=0;i<dictionaryList.size();i++){
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("dictionaryId",dictionaryList.get(i).getDictionaryId());
            jsonObject.put("dictionaryCode",dictionaryList.get(i).getDictionaryCode());
            jsonObject.put("dictionaryDescribe",dictionaryList.get(i).getDictionaryDescribe());
            jsonObject.put("pId",dictionaryList.get(i).getpId());
            if (dictionaryService.getcountbypId(dictionaryList.get(i).getDictionaryId())!=0){
                jsonObject.put("child",getchild(dictionaryList.get(i).getDictionaryId()));
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @ApiOperation(value = "新增数据字典")
    @PostMapping("/adddic")
    public Result addDic(@RequestBody Dictionary dictionary){
        int i =dictionaryService.addDict(dictionary);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("新增数据字典成功！");
    }

    @ApiOperation(value = "根据dictionaryCode获取字典详细表")
    @ApiParam(name = "dictionaryCode", type = "String")
    @GetMapping("/dictionarydetailbycode/{dictionaryCode}")
    public Result getdictionarydetailbycode(@PathVariable String dictionaryCode){
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
            jsonObject.put("detailpId",dictionarydetailList.get(i).getDetailpId());
            jsonObject.put("updateflag",0);
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取数据字典表成功!");
    }

    @ApiOperation(value = "根据dictionaryId获取字典详细表")
    @ApiParam(name = "dictionaryId", type = "long")
    @GetMapping("/dictionarydetailbyid/{dictionaryId}")
    public Result getdictionarydetailbyid(@PathVariable long dictionaryId){
        JSONArray jsonArray =new JSONArray();
        List<Dictionarydetail> dictionarydetailList=dictionarydetailService.SelectById(dictionaryId);
        for (int i = 0; i < dictionarydetailList.size(); i++) {
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("dictionaryDetailId",dictionarydetailList.get(i).getDictionaryDetailId());
            jsonObject.put("dictionaryId",dictionarydetailList.get(i).getDictionaryId());
            jsonObject.put("dictionaryCode",dictionarydetailList.get(i).getDictionaryCode());
            jsonObject.put("itemKey",dictionarydetailList.get(i).getItemKey());
            jsonObject.put("itemValue",dictionarydetailList.get(i).getItemValue());
            jsonObject.put("isdefault",dictionarydetailList.get(i).getIsdefault());
            jsonObject.put("detailpId",dictionarydetailList.get(i).getDetailpId());
            jsonObject.put("updateflag",0);
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取数据字典表成功!");
    }

    @ApiOperation(value = "根据detailpId获取字典详细表")
    @ApiParam(name = "detailpId", type = "long")
    @GetMapping("/dictionarydetailbypid/{detailpId}")
    public Result getdictionarydetailbypid(@PathVariable long detailpId){
        JSONArray jsonArray =new JSONArray();
        List<Dictionarydetail> dictionarydetailList=dictionarydetailService.getchild(detailpId);
        for (int i = 0; i < dictionarydetailList.size(); i++) {
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("dictionaryDetailId",dictionarydetailList.get(i).getDictionaryDetailId());
            jsonObject.put("dictionaryId",dictionarydetailList.get(i).getDictionaryId());
            jsonObject.put("dictionaryCode",dictionarydetailList.get(i).getDictionaryCode());
            jsonObject.put("itemKey",dictionarydetailList.get(i).getItemKey());
            jsonObject.put("itemValue",dictionarydetailList.get(i).getItemValue());
            jsonObject.put("isdefault",dictionarydetailList.get(i).getIsdefault());
            jsonObject.put("detailpId",dictionarydetailList.get(i).getDetailpId());
            jsonObject.put("updateflag",0);
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取数据字典表成功!");
    }

    @ApiOperation(value = "批量增删查改字典详细表")
    @PostMapping("/updatedictionary")
    public Result updateDictionary(@RequestBody List<DictionarydetailDTO> dictionarydetailDTOList){
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
                dictionarydetail.setDetailpId(dictionarydetailDTO.getDetailpId());
                if(dictionarydetailDTO.getUpdateflag()==1){
                    int j= dictionarydetailService.addDictionary(dictionarydetail);
                }
                else{
                    dictionarydetail.setDictionaryDetailId(dictionarydetailDTO.getDictionaryDetailId());
                    int j =dictionarydetailService.updateByDetailid(dictionarydetail);
                }
            }
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("批量增删查改字典详细表成功！");
    }

}
