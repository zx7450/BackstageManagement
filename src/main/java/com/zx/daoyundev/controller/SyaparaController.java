package com.zx.daoyundev.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Syapara;
import com.zx.daoyundev.service.SyaparaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/syspara")
public class SyaparaController {
    @Autowired
    private SyaparaService syaparaService;
    @RequestMapping("")
    public Object getsyspara(){
        JSONArray jsonArray =new JSONArray();
        List<Syapara> syaparaList=syaparaService.queryAllpara();
        for (int i = 0; i < syaparaList.size(); i++) {
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("sysparaid",syaparaList.get(i).getSysparaid());
            jsonObject.put("hintname",syaparaList.get(i).getHintname());
            jsonObject.put("keyword",syaparaList.get(i).getKeyword());
            jsonObject.put("value",syaparaList.get(i).getValue());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping("/addpara")
    public Object addPara(@RequestBody Syapara syapara){
        int i = syaparaService.addpara(syapara);
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("success",true);
        jsonObject.put("message","添加参数成功");
        return jsonObject;
    }
    @RequestMapping("/getpara/{keyword}")
    public Object getPara(@PathVariable String keyword){
        Syapara syapara=syaparaService.getparaBykeyword(keyword);
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("sysparaid",syapara.getSysparaid());
        jsonObject.put("hintname",syapara.getHintname());
        jsonObject.put("keyword",syapara.getKeyword());
        jsonObject.put("value",syapara.getValue());
        return jsonObject;
    }
    @RequestMapping("/updatepara")
    public Object updatePara(@RequestBody Syapara syapara){
        //int i =syaparaService.getcountBykeyword(syapara.getKeyword());
        int j=syaparaService.updateparaBykeyword(syapara);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",true);
        jsonObject.put("message","修改参数成功");
//        if(i==1){
//            Syapara syaparatemp = syaparaService.getparaBykeyword(syapara.getKeyword());
//            if(syaparatemp.getSysparaid()==syapara.getSysparaid()){
//                int j=syaparaService.updateparaBykeyword(syapara);
//                jsonObject.put("success",true);
//                jsonObject.put("message","修改参数成功");
//            }else {
//                jsonObject.put("success",false);
//                jsonObject.put("message","关键字重复");
//            }
//        }else{
//            int j=syaparaService.updateparaBykeyword(syapara);
//            jsonObject.put("success",true);
//            jsonObject.put("message","修改参数成功");
//        }
        return jsonObject;
    }
    @RequestMapping("/deletepara/{sysparaid}")
    public Object deletePara(@PathVariable long sysparaid){
        int i = syaparaService.deleteparaById(sysparaid);
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("success",true);
        jsonObject.put("message","删除参数成功");
        return jsonObject;
    }
}
