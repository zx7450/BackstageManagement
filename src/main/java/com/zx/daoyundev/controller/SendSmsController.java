package com.zx.daoyundev.controller;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.zx.daoyundev.entity.SendSms;
import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.service.SendSmsService;
import com.zx.daoyundev.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Api(tags = "验证码接口")
@RestController
@CrossOrigin//跨域支持
public class SendSmsController {
    @Autowired
    private SendSmsService sendSmsService;
    // 注入redis操作模板
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserService userService;



    //@GetMapping("/signupsendSms/{tel}")
    @ApiOperation(value = "注册时根据电话号码发送验证码")
    @ApiParam(name = "tel", type = "String")
    @RequestMapping("/signupsendSms/{tel}")
    //@PostMapping("/signupsendSms")
    //public Object signupsendSms(@RequestParam("tel") String tel) {
    public Object signupsendSms(@PathVariable String tel) {
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();

        // 根据手机号进行查询
        String phone = stringR.get(tel);
        System.out.println(tel);

        // 如果手机号在redis中不存在的话才进行发送验证码操作
        if (StringUtils.isEmpty(phone)) {
            // 生成6位随机数
            String code = String.valueOf(Math.random()).substring(3, 9);
            // 调用业务层接口 发送验证码
            boolean sendSmsFlag = sendSmsService.sendSms(tel, code);
            if (sendSmsFlag) {
                // 发送成功之后往redis中存入该手机号以及验证码 并设置超时时间 2 分钟
                stringR.set(tel, code, 65, TimeUnit.SECONDS);
            }
            //return "发送验证码到：" + tel + "成功! " + "Message:" + sendSmsFlag;
            String message="发送验证码到：" + tel + "成功! " + "Message:" + sendSmsFlag;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",true);
            jsonObject.put("message",message);
            return jsonObject;
        } else {
            String message="该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",false);
            jsonObject.put("message",message);
            return jsonObject;
            //return "该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
        }
    }
    //@GetMapping("/loginsendSms")
    //@PostMapping("/loginsendSms")
    @ApiOperation(value = "登录时根据电话号码发送验证码")
    @ApiParam(name = "tel", type = "String")
    @RequestMapping("/loginsendSms/{tel}")
    //public Object loginsendSms(@RequestParam("tel") String tel) {
    public Object loginsendSms(@PathVariable String tel) {
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();

        // 根据手机号进行查询
        String phone = stringR.get(tel);
        System.out.println(tel);

        // 如果手机号在redis中不存在的话才进行发送验证码操作
        if (StringUtils.isEmpty(phone)) {
            // 生成6位随机数
            String code = String.valueOf(Math.random()).substring(3, 9);
            // 调用业务层接口 发送验证码
            boolean sendSmsFlag = sendSmsService.sendSms(tel, code);
            if (sendSmsFlag) {
                // 发送成功之后往redis中存入该手机号以及验证码 并设置超时时间 2 分钟
                stringR.set(tel, code, 65, TimeUnit.SECONDS);
            }
            //return "发送验证码到：" + tel + "成功! " + "Message:" + sendSmsFlag;
            String message="发送验证码到：" + tel + "成功! " + "Message:" + sendSmsFlag;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",true);
            jsonObject.put("message",message);
            return jsonObject;
        } else {
            String message="该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",false);
            jsonObject.put("message",message);
            return jsonObject;
            //return "该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
        }
    }

    //@GetMapping("/loginbytel/{tel}/{code}")
    @ApiOperation(value = "验证码登录功能")
    @PostMapping  ("/loginbytel")
    //public Object checkCode(@PathVariable("tel") String tel, @PathVariable("code") String code) {
    public Object checkCode(@RequestBody SendSms sendSms) {
        User userInDataBase = userService.findByTel(sendSms.getTel());
        if (userInDataBase == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",false);
            jsonObject.put("message", "用户不存在");
        }
        // 获取到操作String的对象

        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据Key进行查询
        //String redisCode = stringR.get(tel);
        String redisCode = stringR.get(sendSms.getTel());
        if (sendSms.getCode().equals(redisCode)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",true);
            jsonObject.put("message","成功");
            return jsonObject;
            //return "成功";
        } else {
            if(redisCode==null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("success",false);
                jsonObject.put("message","请先获取验证码在进行校验！");
                return jsonObject;
            }
            else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("success",false);
                jsonObject.put("message","验证码错误！");
                return jsonObject;
            }
            //return redisCode == null?"请先获取验证码在进行校验！" : "错误";
        }
    }

//    @PostMapping(name = "获取验证码",value = "/getCode")
//    public Object getValidationCode(String phoneNumber) {
//        return userService.getValidationCode(phoneNumber);
//    }

}
