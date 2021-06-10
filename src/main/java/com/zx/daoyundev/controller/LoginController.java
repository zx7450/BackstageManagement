package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.entity.UserDTO;
import com.zx.daoyundev.service.LoginService;
import com.zx.daoyundev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin//跨域支持
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;
    private UserService userService;

    @Autowired
    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }
    @PostMapping("")
    //public Object login(@RequestBody User user) {
    public Object login(@RequestBody UserDTO userDTO) {
        User userInDataBase = userService.findByTel(userDTO.getTel());
        JSONObject jsonObject = new JSONObject();
        if (userInDataBase == null) {
            jsonObject.put("success",false);
            jsonObject.put("message", "用户不存在");
        } else if (!userService.comparePassword(userDTO.getUserPassward(), userInDataBase)) {
            jsonObject.put("success",false);
            jsonObject.put("message", "密码不正确");
        } else {
            String token = loginService.getToken(userInDataBase);
            jsonObject.put("token", token);
            jsonObject.put("success",true);
            jsonObject.put("message","登录成功");
            //jsonObject.put("user", userInDataBase);
        }
        return jsonObject;
    }
}
