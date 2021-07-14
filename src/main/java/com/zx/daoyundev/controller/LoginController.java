package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.entity.UserDTO;
import com.zx.daoyundev.security.jwt.JWTUtil;
import com.zx.daoyundev.service.LoginService;
import com.zx.daoyundev.service.UserService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户登录")
@RestController
@CrossOrigin//跨域支持
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "用户注册")
    @PostMapping("/signup")
    //public Object add(@RequestBody JSONObject jsonParam) {
    public Result add(@RequestBody UserDTO userDTO) {
//        String userName=jsonParam.get("userName").toString();
//        String userPassward=jsonParam.get("userPassward").toString();
//        String tel=jsonParam.get("tel").toString();
//        String code=jsonParam.get("code").toString();
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();

        // 根据手机号进行查询
        String redisCode = stringR.get(userDTO.getTel());
        if (userService.findByTel(userDTO.getTel()) != null) {
            return Result.failure(ResultCodeEnum.RegisterAlreadyExist).setMsg("手机号已被使用！");
        } else if (userDTO.getCode().equals(redisCode)) {
            User user=new User();
            user.setUserName(userDTO.getUserName());
            user.setUserPassward(userDTO.getUserPassward());
            user.setTel(userDTO.getTel());
            User user1;
            user1 = userService.add(user);
            String token = JWTUtil.sign(user.getTel(), user.getUserPassward());
            user1.setUserPassward(null);
            return Result.success().setToken(token).setData(user1).setCode(ResultCodeEnum.OK.getCode()).setMsg("注册成功！");
            //return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("注册成功！");
        } else {
            if(redisCode==null){
                return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("请先获取验证码在进行校验！");
            }
            else {
                return Result.failure(ResultCodeEnum.LoginError).setMsg("验证码错误！");
            }
        }
    }

    @ApiOperation(value = "用户登录接口")
    @PostMapping("/login")
    //public Object login(@RequestBody User user) {
    public Result login(@RequestBody UserDTO userDTO) {
        String tel=userDTO.getTel();
        String password=userDTO.getUserPassward();
        User userInDataBase = userService.findByTel(tel);
        if (userInDataBase != null && userInDataBase.getUserPassward().equals(password)) {
            System.out.println("登录成功");
            String token = JWTUtil.sign(tel, password);
            //System.out.println(token);
            userInDataBase.setUserPassward(null);
            return Result.success().setToken(token).setData(userInDataBase).setCode(ResultCodeEnum.OK.getCode()).setMsg("登录成功！");
        }  else {
            return Result.failure(ResultCodeEnum.LoginError).setMsg("登陆失败！");
        }
    }
//    @ApiOperation(value = "用户登录接口")
//    @PostMapping("")
//    //public Object login(@RequestBody User user) {
//    public Object login(@RequestBody UserDTO userDTO) {
//        User userInDataBase = userService.findByTel(userDTO.getTel());
//        JSONObject jsonObject = new JSONObject();
//        if (userInDataBase == null) {
//            jsonObject.put("success",false);
//            jsonObject.put("message", "用户不存在");
//        } else if (!userService.comparePassword(userDTO.getUserPassward(), userInDataBase)) {
//            jsonObject.put("success",false);
//            jsonObject.put("message", "密码不正确");
//        } else {
//            String token = loginService.getToken(userInDataBase);
//            jsonObject.put("token", token);
//            jsonObject.put("success",true);
//            //jsonObject.put("role",userInDataBase.getRole());
//            jsonObject.put("message","登录成功");
//            //jsonObject.put("user", userInDataBase);
//        }
//        return jsonObject;
//    }
    @GetMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
    return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST);
}

}
