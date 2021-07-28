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
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @ApiOperation(value = "根据电话号码获取用户信息")
    @ApiParam(name = "tel", type = "String")
    @GetMapping("/getusermessage/{tel}")
    public Result getusermessage(@PathVariable String tel) {
        User user = userService.findByTel(tel);
        List<Coursestudent> coursestudentList = coursestudentService.getcoursestudentbystudentId(user.getUserId());
        long totalexp = 0;
        for (int i = 0; i < coursestudentList.size(); i++) {
            totalexp += coursestudentList.get(i).getTotalExp();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getUserId());
        jsonObject.put("userName", user.getUserName());
        jsonObject.put("tel", user.getTel());
        jsonObject.put("role", user.getRole());
        jsonObject.put("sex", user.getSex());
        jsonObject.put("nickname", user.getNickname());
        jsonObject.put("birthyear", user.getBirthyear());
        jsonObject.put("userschool", user.getUserschool());
        jsonObject.put("depart", user.getDepart());
        jsonObject.put("perid", user.getPerid());
        jsonObject.put("totalexp", totalexp);
        jsonObject.put("avatar", user.getAvatar());
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取用户信息成功！");
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateusermessage")
    public Result updateusermessage(@RequestBody User user) {
        User user1 = userService.updateuser(user);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改用户信息成功！");
    }

    @ApiOperation(value = "获取所有用户")
    @RequiresPermissions("user:list")
    @GetMapping("/getalluser")
    public Object getalluser() {
        List<User> userList = userService.findAlluser();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < userList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userList.get(i).getUserId());
            jsonObject.put("userName", userList.get(i).getUserName());
            jsonObject.put("tel", userList.get(i).getTel());
            jsonObject.put("role", userList.get(i).getRole());
            jsonObject.put("nickname", userList.get(i).getNickname());
            jsonObject.put("birthyear", userList.get(i).getBirthyear());
            jsonObject.put("userschool", userList.get(i).getUserschool());
            jsonObject.put("depart", userList.get(i).getDepart());
            jsonObject.put("perid", userList.get(i).getPerid());
            jsonObject.put("avatar", userList.get(i).getAvatar());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取所有用户成功！");
    }

    @ApiOperation(value = "根据手机号注销用户")
    @ApiImplicitParam(name = "tel", value = "手机号", paramType = "path", required = true)
    @PostMapping("/logoutuserById/{tel}")
    public Result logoutuserById(@PathVariable String tel) {
        userService.deleteuser(tel);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("注销用户成功！");
    }

    @ApiOperation(value = "用户修改密码")
    @PostMapping("/updatepassword")
    public Result updatepassword(@RequestBody UserDTO userDTO) {
        User user = userService.findByTel(userDTO.getTel());
        if (user == null)
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("用户不存在！");
        if (user.getUserPassward().equals(userDTO.getOldpassword())) {
            user.setUserPassward(userDTO.getUserPassward());
            userService.updatePasswordByTel(user);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改密码成功！");
        } else {
            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg("旧密码输入错误！");
        }
    }
}
