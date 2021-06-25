package com.zx.daoyundev.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Syapara;
import com.zx.daoyundev.service.SyaparaService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统参数接口")
@RestController
@CrossOrigin
@RequestMapping("/syspara")
public class SyaparaController {
    @Autowired
    private SyaparaService syaparaService;

    @ApiOperation(value = "获取所有系统参数")
//    @RequestMapping("")
    @GetMapping("")
    public Result getsyspara(){
        List<Syapara> syaparaList=syaparaService.queryAllpara();
        return Result.success().setData(syaparaList).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取所有系统参数成功！");
    }
//    @ApiOperation(value = "获取所有系统参数")
//    @RequestMapping("")
//    public Object getsyspara(){
//        JSONArray jsonArray =new JSONArray();
//        List<Syapara> syaparaList=syaparaService.queryAllpara();
//        for (int i = 0; i < syaparaList.size(); i++) {
//            JSONObject jsonObject =new JSONObject();
//            jsonObject.put("sysparaid",syaparaList.get(i).getSysparaid());
//            jsonObject.put("hintname",syaparaList.get(i).getHintname());
//            jsonObject.put("keyword",syaparaList.get(i).getKeyword());
//            jsonObject.put("value",syaparaList.get(i).getValue());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
    @ApiOperation(value = "新增系统参数")
//    @RequestMapping("/addpara")
    @PostMapping("/addpara")
    public Result addPara(@RequestBody Syapara syapara){
        int i = syaparaService.addpara(syapara);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("添加参数成功！");
    }
//    @ApiOperation(value = "新增系统参数")
//    @RequestMapping("/addpara")
//    public Object addPara(@RequestBody Syapara syapara){
//        int i = syaparaService.addpara(syapara);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","添加参数成功");
//        return jsonObject;
//    }
    @ApiOperation(value = "根据keyword获取系统参数")
    @ApiParam(name = "keyword", type = "String")
//    @RequestMapping("/getpara/{keyword}")
    @GetMapping("/getpara/{keyword}")
    public Result getPara(@PathVariable String keyword){
        Syapara syapara=syaparaService.getparaBykeyword(keyword);
        return Result.success().setData(syapara).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取系统参数成功！");
    }
//    @ApiOperation(value = "根据keyword获取系统参数")
//    @ApiParam(name = "keyword", type = "String")
//    @RequestMapping("/getpara/{keyword}")
//    public Object getPara(@PathVariable String keyword){
//        Syapara syapara=syaparaService.getparaBykeyword(keyword);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("sysparaid",syapara.getSysparaid());
//        jsonObject.put("hintname",syapara.getHintname());
//        jsonObject.put("keyword",syapara.getKeyword());
//        jsonObject.put("value",syapara.getValue());
//        return jsonObject;
//    }
    @ApiOperation(value = "修改系统参数")
//    @RequestMapping("/updatepara")
    @PostMapping("/updatepara")
    public Result updatePara(@RequestBody Syapara syapara){
        //int i =syaparaService.getcountBykeyword(syapara.getKeyword());
        int j=syaparaService.updateparaBykeyword(syapara);
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
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改参数成功！");
    }
//    @ApiOperation(value = "修改系统参数")
//    @RequestMapping("/updatepara")
//    public Object updatePara(@RequestBody Syapara syapara){
//        //int i =syaparaService.getcountBykeyword(syapara.getKeyword());
//        int j=syaparaService.updateparaBykeyword(syapara);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","修改参数成功");
////        if(i==1){
////            Syapara syaparatemp = syaparaService.getparaBykeyword(syapara.getKeyword());
////            if(syaparatemp.getSysparaid()==syapara.getSysparaid()){
////                int j=syaparaService.updateparaBykeyword(syapara);
////                jsonObject.put("success",true);
////                jsonObject.put("message","修改参数成功");
////            }else {
////                jsonObject.put("success",false);
////                jsonObject.put("message","关键字重复");
////            }
////        }else{
////            int j=syaparaService.updateparaBykeyword(syapara);
////            jsonObject.put("success",true);
////            jsonObject.put("message","修改参数成功");
////        }
//        return jsonObject;
//    }
    @ApiOperation(value = "根据参数id删除系统参数")
    @ApiParam(name = "sysparaid", type = "long")
//    @RequestMapping("/deletepara/{sysparaid}")
    @PostMapping("/deletepara/{sysparaid}")
    public Result deletePara(@PathVariable long sysparaid){
        int i = syaparaService.deleteparaById(sysparaid);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除参数成功！");
    }
//    @ApiOperation(value = "根据参数id删除系统参数")
//    @ApiParam(name = "sysparaid", type = "long")
//    @RequestMapping("/deletepara/{sysparaid}")
//    public Object deletePara(@PathVariable long sysparaid){
//        int i = syaparaService.deleteparaById(sysparaid);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","删除参数成功");
//        return jsonObject;
//    }
}
