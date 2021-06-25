package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.AccessTokenDTO;

import com.zx.daoyundev.entity.GithubUser;
import com.zx.daoyundev.mapper.GithubUserMapper;
import com.zx.daoyundev.mapper.UserMapper;
import com.zx.daoyundev.provider.GitHubProvider;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "第三方授权登录接口")
@RestController
@CrossOrigin
public class AuthorizeController {
    @Autowired
    private GithubUserMapper githubUserMapper;
    @Autowired
    private GitHubProvider gitHubProvider;
    @ApiOperation(value = "第三方登录")
    @GetMapping ("/callback")
    public Result callback(@RequestParam(name="code") String code){
        System.out.println(code);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("60a86fba1827e43eb426");
        accessTokenDTO.setClient_secret("002775f835a26d2642cecb03bdd139950f56819d");
        accessTokenDTO.setCode(code);
        //accessTokenDTO.setRedirectUri("http://localhost:8887/callback");
        //进行doPost请求，获取access_token
        //gitHubProvider.getAccessToken(accessTokenDTO);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);//进行doPost请求，获取access_token
//        gitHubProvider.sendPost(accessTokenDTO);
//        GithubUser user = gitHubProvider.getUser(accessToken);//使用accesstok获得user信息
//        System.out.println(user.getName());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token",accessToken);
        jsonObject.put("success",true);
        GithubUser githubUser=new GithubUser();
        githubUser.setToken(accessToken);
        githubUser.setName("zx7450");
        int i = githubUserMapper.InsertUser(githubUser);
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("登录成功!");
    }
//    @ApiOperation(value = "第三方登录")
//    @GetMapping ("/callback")
//    public Object callback(@RequestParam(name="code") String code){
//        System.out.println(code);
//        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
//        accessTokenDTO.setClient_id("60a86fba1827e43eb426");
//        accessTokenDTO.setClient_secret("002775f835a26d2642cecb03bdd139950f56819d");
//        accessTokenDTO.setCode(code);
//        //accessTokenDTO.setRedirectUri("http://localhost:8887/callback");
//        //进行doPost请求，获取access_token
//        //gitHubProvider.getAccessToken(accessTokenDTO);
//        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);//进行doPost请求，获取access_token
////        gitHubProvider.sendPost(accessTokenDTO);
////        GithubUser user = gitHubProvider.getUser(accessToken);//使用accesstok获得user信息
////        System.out.println(user.getName());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("token",accessToken);
//        jsonObject.put("success",true);
//        GithubUser githubUser=new GithubUser();
//        githubUser.setToken(accessToken);
//        githubUser.setName("zx7450");
//        int i = githubUserMapper.InsertUser(githubUser);
//        return jsonObject;
//
//    }

}
