package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.entity.UserDTO;
import com.zx.daoyundev.service.SendSmsService;
import com.zx.daoyundev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin//跨域支持
//@RequestMapping("/signup")
public class UserController {
    @Autowired
    private UserService userService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/signup")
    //public Object add(@RequestBody JSONObject jsonParam) {
    public Object add(@RequestBody UserDTO userDTO) {
//        String userName=jsonParam.get("userName").toString();
//        String userPassward=jsonParam.get("userPassward").toString();
//        String tel=jsonParam.get("tel").toString();
//        String code=jsonParam.get("code").toString();
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();

        // 根据手机号进行查询
        String redisCode = stringR.get(userDTO.getTel());
        if (userService.findByTel(userDTO.getTel()) != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success",false);
            jsonObject.put("message","手机号已被使用");
            return jsonObject;
        } else if (userDTO.getCode().equals(redisCode)) {
            User user=new User();
            user.setUserName(userDTO.getUserName());
            user.setUserPassward(userDTO.getUserPassward());
            user.setTel(userDTO.getTel());
            User user1;
            user1 = userService.add(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success", true);
            jsonObject.put("message", "注册成功");
            return jsonObject;

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
        }
    }
}
