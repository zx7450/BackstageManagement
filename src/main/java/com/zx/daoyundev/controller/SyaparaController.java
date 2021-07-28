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
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @GetMapping("")
    public Result getsyspara() {
        List<Syapara> syaparaList = syaparaService.queryAllpara();
        return Result.success().setData(syaparaList).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取所有系统参数成功！");
    }

    @ApiOperation(value = "新增系统参数")
    @RequiresPermissions("sysparam:edit")
    @PostMapping("/addpara")
    public Result addPara(@RequestBody Syapara syapara) {
        int i = syaparaService.addpara(syapara);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("添加参数成功！");
    }

    @ApiOperation(value = "根据keyword获取系统参数")
    @ApiParam(name = "keyword", type = "String")
    @GetMapping("/getpara/{keyword}")
    public Result getPara(@PathVariable String keyword) {
        Syapara syapara = syaparaService.getparaBykeyword(keyword);
        return Result.success().setData(syapara).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取系统参数成功！");
    }

    @ApiOperation(value = "修改系统参数")
    @RequiresPermissions("sysparam:edit")
    @PostMapping("/updatepara")
    public Result updatePara(@RequestBody Syapara syapara) {
        int j = syaparaService.updateparaBykeyword(syapara);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改参数成功！");
    }

    @ApiOperation(value = "根据参数id删除系统参数")
    @ApiParam(name = "sysparaid", type = "long")
    @RequiresPermissions("sysparam:edit")
    @PostMapping("/deletepara/{sysparaid}")
    public Result deletePara(@PathVariable long sysparaid) {
        int i = syaparaService.deleteparaById(sysparaid);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除参数成功！");
    }
}
