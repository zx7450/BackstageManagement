package com.zx.daoyundev.controller;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.zx.daoyundev.entity.SendSms;
import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.entity.UserDTO;
import com.zx.daoyundev.security.jwt.JWTUtil;
import com.zx.daoyundev.service.SendSmsService;
import com.zx.daoyundev.service.UserService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
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
@RequestMapping("/sendSms")
public class SendSmsController {
    @Autowired
    private SendSmsService sendSmsService;
    // 注入redis操作模板
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册时根据电话号码发送验证码")
    @ApiParam(name = "tel", type = "String")
    @PostMapping("/signupsendSms/{tel}")
    public Result signupsendSms(@PathVariable String tel) {
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据手机号进行查询
        String phone = stringR.get(tel);
        //System.out.println(tel);
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
            String message = "发送验证码到：" + tel + "成功! " + "Message:" + sendSmsFlag;
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg(message);
        } else {
            String message = "该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg(message);
            //return "该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
        }
    }

    @ApiOperation(value = "登录时根据电话号码发送验证码")
    @ApiParam(name = "tel", type = "String")
    @PostMapping("/loginsendSms/{tel}")
    public Result loginsendSms(@PathVariable String tel) {
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据手机号进行查询
        String phone = stringR.get(tel);
        //System.out.println(tel);
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
            String message = "发送验证码到：" + tel + "成功! " + "Message:" + sendSmsFlag;
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg(message);
        } else {
            String message = "该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg(message);
            //return "该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
        }
    }

    @ApiOperation(value = "忘记密码时根据电话号码发送验证码")
    @ApiParam(name = "tel", type = "String")
    @PostMapping("/forgetPasswordsendSms/{tel}")
    public Result forgetPasswordsendSms(@PathVariable String tel) {
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据手机号进行查询
        String phone = stringR.get(tel);
        //System.out.println(tel);
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
            String message = "发送验证码到：" + tel + "成功! " + "Message:" + sendSmsFlag;
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg(message);
        } else {
            String message = "该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg(message);
            //return "该手机号：" + tel + " 剩余：" + redisTemplate.getExpire(tel) + "秒后可再次进行发送！";
        }
    }

    @ApiOperation(value = "验证码登录功能")
    @PostMapping("/loginbytel")
    //public Object checkCode(@PathVariable("tel") String tel, @PathVariable("code") String code) {
    public Result checkCode(@RequestBody SendSms sendSms) {
        User userInDataBase = userService.findByTel(sendSms.getTel());
        if (userInDataBase == null) {
            return Result.failure(ResultCodeEnum.LoginError).setMsg("用户不存在！");
        }
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据Key进行查询
        //String redisCode = stringR.get(tel);
        String redisCode = stringR.get(sendSms.getTel());
        if (sendSms.getCode().equals(redisCode)) {
            String token = JWTUtil.sign(userInDataBase.getTel(), userInDataBase.getUserPassward());
            //System.out.println(token);
            userInDataBase.setUserPassward(null);
            //return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("登陆成功！");
            //userInDataBase.setUserPassward(null);
            return Result.success().setToken(token).setData(userInDataBase).setCode(ResultCodeEnum.OK.getCode()).setMsg("登录成功！");
            //return "成功";
        } else {
            if (redisCode == null) {
                return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("请先获取验证码在进行校验！");
            } else {
                return Result.failure(ResultCodeEnum.LoginError).setMsg("验证码错误！");
            }
            //return redisCode == null?"请先获取验证码在进行校验！" : "错误";
        }
    }

    @ApiOperation(value = "忘记密码功能")
    @PostMapping("/forgetPassword")
    public Result forgetPassword(@RequestBody UserDTO userDTO) {
        User user = userService.findByTel(userDTO.getTel());
        if (user == null) {
            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg("用户不存在！");
        }
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据Key进行查询
        //String redisCode = stringR.get(tel);
        String redisCode = stringR.get(userDTO.getTel());
        if (userDTO.getCode().equals(redisCode)) {
            User user1 = new User();
            user1.setTel(userDTO.getTel());
            user1.setUserPassward(userDTO.getUserPassward());
            userService.updatePasswordByTel(user1);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改密码成功！");
            //return "成功";
        } else {
            if (redisCode == null) {
                return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("请先获取验证码在进行校验！");
            } else {
                return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("验证码错误！");
            }
            //return redisCode == null?"请先获取验证码在进行校验！" : "错误";
        }
    }
}
