package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Coursestudent;
import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.entity.UserDTO;
import com.zx.daoyundev.service.CoursestudentService;
import com.zx.daoyundev.service.UserService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Api(tags = "用户接口")
@RestController
@CrossOrigin//跨域支持
//@RequestMapping("/signup")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CoursestudentService coursestudentService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "用户登录")
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
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("注册成功！");
        } else {
            if(redisCode==null){
                return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("请先获取验证码在进行校验！");
            }
            else {
                return Result.failure(ResultCodeEnum.LoginError).setMsg("验证码错误！");
            }
        }
    }
//    @ApiOperation(value = "用户登录")
//    @PostMapping("/signup")
//    //public Object add(@RequestBody JSONObject jsonParam) {
//    public Object add(@RequestBody UserDTO userDTO) {
////        String userName=jsonParam.get("userName").toString();
////        String userPassward=jsonParam.get("userPassward").toString();
////        String tel=jsonParam.get("tel").toString();
////        String code=jsonParam.get("code").toString();
//        // 获取到操作String的对象
//        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
//
//        // 根据手机号进行查询
//        String redisCode = stringR.get(userDTO.getTel());
//        if (userService.findByTel(userDTO.getTel()) != null) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("success",false);
//            jsonObject.put("message","手机号已被使用");
//            return jsonObject;
//        } else if (userDTO.getCode().equals(redisCode)) {
//            User user=new User();
//            user.setUserName(userDTO.getUserName());
//            user.setUserPassward(userDTO.getUserPassward());
//            user.setTel(userDTO.getTel());
//            User user1;
//            user1 = userService.add(user);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("success", true);
//            jsonObject.put("message", "注册成功");
//            return jsonObject;
//
//        } else {
//            if(redisCode==null){
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("success",false);
//                jsonObject.put("message","请先获取验证码在进行校验！");
//                return jsonObject;
//            }
//            else {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("success",false);
//                jsonObject.put("message","验证码错误！");
//                return jsonObject;
//            }
//        }
//    }
    @ApiOperation(value = "根据电话号码获取用户信息")
    @ApiParam(name = "tel", type = "String")
//    @RequestMapping("/getusermessage/{tel}")
    @GetMapping("/getusermessage/{tel}")
    public Result getusermessage(@PathVariable String tel){
        User user=userService.findByTel(tel);
        List<Coursestudent> coursestudentList=coursestudentService.getcoursestudentbystudentId(user.getUserId());
        long totalexp=0;
        for (int i = 0; i < coursestudentList.size(); i++) {
            totalexp+=coursestudentList.get(i).getTotalExp();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",user.getUserId());
        jsonObject.put("userName",user.getUserName());
        jsonObject.put("tel",user.getTel());
        jsonObject.put("role",user.getRole());
        jsonObject.put("sex",user.getSex());
        jsonObject.put("nickname",user.getNickname());
        jsonObject.put("birthyear",user.getBirthyear());
        jsonObject.put("userschool",user.getUserschool());
        jsonObject.put("depart",user.getDepart());
        jsonObject.put("perid",user.getPerid());
        jsonObject.put("totalexp",totalexp);
        jsonObject.put("avatar",user.getAvatar());
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取用户信息成功！");
    }
//    @ApiOperation(value = "根据电话号码获取用户信息")
//    @ApiParam(name = "tel", type = "String")
//    @RequestMapping("/getusermessage/{tel}")
//    public Object getusermessage(@PathVariable String tel){
//        User user=userService.findByTel(tel);
//        List<Coursestudent> coursestudentList=coursestudentService.getcoursestudentbystudentId(user.getUserId());
//        long totalexp=0;
//        for (int i = 0; i < coursestudentList.size(); i++) {
//            totalexp+=coursestudentList.get(i).getTotalExp();
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("userId",user.getUserId());
//        jsonObject.put("userName",user.getUserName());
//        jsonObject.put("tel",user.getTel());
//        jsonObject.put("role",user.getRole());
//        jsonObject.put("sex",user.getSex());
//        jsonObject.put("nickname",user.getNickname());
//        jsonObject.put("birthyear",user.getBirthyear());
//        jsonObject.put("userschool",user.getUserschool());
//        jsonObject.put("depart",user.getDepart());
//        jsonObject.put("perid",user.getPerid());
//        jsonObject.put("totalexp",totalexp);
//        return jsonObject;
//    }
    @ApiOperation(value = "修改用户信息")
//    @RequestMapping("/updateusermessage")
    @PostMapping("/updateusermessage")
    public Result updateusermessage(@RequestBody User user){
        User user1=userService.updateuser(user);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改用户信息成功！");
    }
//    @ApiOperation(value = "修改用户信息")
//    @RequestMapping("/updateusermessage")
//    public Object updateusermessage(@RequestBody User user){
//        User user1=userService.updateuser(user);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("success", true);
//        jsonObject.put("message", "修改用户信息成功");
//        return jsonObject;
//    }
    @ApiOperation(value = "获取所有用户")
//    @RequestMapping("/getalluser")
    @GetMapping("/getalluser")
    public Object getalluser(){
        List<User> userList=userService.findAlluser();
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < userList.size(); i++) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("userId",userList.get(i).getUserId());
            jsonObject.put("userName",userList.get(i).getUserName());
            jsonObject.put("tel",userList.get(i).getTel());
            jsonObject.put("role",userList.get(i).getRole());
            jsonObject.put("nickname",userList.get(i).getNickname());
            jsonObject.put("birthyear",userList.get(i).getBirthyear());
            jsonObject.put("userschool",userList.get(i).getUserschool());
            jsonObject.put("depart",userList.get(i).getDepart());
            jsonObject.put("perid",userList.get(i).getPerid());
            jsonObject.put("avatar",userList.get(i).getAvatar());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取所有用户成功！");
    }
//    @ApiOperation(value = "获取所有用户")
//    @RequestMapping("/getalluser")
//    public Object getalluser(){
//        List<User> userList=userService.findAlluser();
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < userList.size(); i++) {
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("userId",userList.get(i).getUserId());
//            jsonObject.put("userName",userList.get(i).getUserName());
//            jsonObject.put("tel",userList.get(i).getTel());
//            jsonObject.put("role",userList.get(i).getRole());
//            jsonObject.put("nickname",userList.get(i).getNickname());
//            jsonObject.put("birthyear",userList.get(i).getBirthyear());
//            jsonObject.put("userschool",userList.get(i).getUserschool());
//            jsonObject.put("depart",userList.get(i).getDepart());
//            jsonObject.put("perid",userList.get(i).getPerid());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
    @ApiOperation(value = "根据手机号注销用户")
    @ApiImplicitParam(name = "tel",value = "手机号", paramType = "path",required = true)
    @PostMapping("/logoutuserById/{tel}")
    public Result logoutuserById(@PathVariable String tel){
        userService.deleteuser(tel);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("注销用户成功！");
    }

}
