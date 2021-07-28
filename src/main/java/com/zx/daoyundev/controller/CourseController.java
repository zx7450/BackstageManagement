package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.Course;
import com.zx.daoyundev.entity.Coursestudent;
import com.zx.daoyundev.entity.CoursestudentDTO;
import com.zx.daoyundev.entity.User;
import com.zx.daoyundev.service.CourseService;
import com.zx.daoyundev.service.CoursestudentService;
import com.zx.daoyundev.service.UserService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/29 17:56
 */
@Api(tags = "班课接口")
@RestController
@CrossOrigin//跨域支持

public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CoursestudentService coursestudentService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据userId查找我创建的班课")
    @ApiParam(name = "userId", type = "Integer")
    @RequiresPermissions("class:created")
    @GetMapping("/createlist/{userId}")
    public Result createlist(@PathVariable Integer userId) {
        JSONArray jsonArray = new JSONArray();
        User user = userService.findByUserid(userId);
        List<Course> courseList = courseService.getcoursebyTeachername(user.getUserName());
        return Result.success().setData(courseList).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取创建的班课成功!");
    }

    @ApiOperation(value = "根据userId查找我加入的班课")
    @ApiParam(name = "userId", type = "Integer")
    @GetMapping("/joinlist/{userId}")
    public Result joinlist(@PathVariable Integer userId) {
        List<Coursestudent> coursestudentList = coursestudentService.getcoursestudentbystudentId(userId);
        if (coursestudentList.size() == 0) {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("没有加入的班课！");
        }
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < coursestudentList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            Course course = courseService.getcoursebyId(coursestudentList.get(i).getCourseId());
            jsonObject.put("courseId", course.getCourseId());
            jsonObject.put("courseName", course.getCourseName());
            jsonObject.put("courseclass", course.getCourseclass());
            jsonObject.put("term", course.getTerm());
            jsonObject.put("teacherName", course.getTeacherName());
            jsonObject.put("image", course.getImage());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取加入的班课成功!");
    }

    @ApiOperation(value = "查找所有班课")
    @RequiresPermissions("class:all")
    @GetMapping("/getallcourse")
    public Result getAllcourse() {
        List<Course> courseList = courseService.findAllcourse();
        return Result.success().setData(courseList).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取所有班课成功!");
    }

    @ApiOperation(value = "根据courseId获取加入班课的学生列表")
    @ApiParam(name = "courseId", type = "int")
    @GetMapping("/memberList/{courseId}")
    public Result memberList(@PathVariable int courseId) {
        List<Coursestudent> coursestudentList = coursestudentService.getcoursestudentbycourseId(courseId);
        if (coursestudentList.size() == 0) {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("本班课没有学生加入！");
        } else {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < coursestudentList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                User user = userService.findByUserid(coursestudentList.get(i).getStudentId());
                jsonObject.put("studentId", coursestudentList.get(i).getStudentId());
                jsonObject.put("studentname", coursestudentList.get(i).getStudentname());
                jsonObject.put("perid", user.getPerid());
                jsonObject.put("totalExp", coursestudentList.get(i).getTotalExp());
                jsonObject.put("avatar", user.getAvatar());
                jsonArray.add(jsonObject);
            }
            return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取加入班课的学生列表成功!");
        }
    }

    @ApiOperation(value = "创建班课")
    @RequiresPermissions("class:creating")
    @PostMapping("/creatclass")
    public Result creatclass(@RequestBody Course course) {
        int courseId = courseService.addcourse(course);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("创建班课成功！");

    }


    @ApiOperation(value = "修改班课信息")
    @PostMapping("/updateclass")
    public Result updateclass(@RequestBody Course course) {
        int i = courseService.updatecoursebyId(course);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改班课信息成功！");
    }


    @ApiOperation(value = "根据courseId删除班课")
    @ApiParam(name = "courseId", type = "int")
    @PostMapping("/deleteclass/{courseId}")
    public Result deleteclass(@PathVariable int courseId) {
        Course course = courseService.getcoursebyId(courseId);
        int i = courseService.deletecoursebyId(courseId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除班课成功！");
    }

    @ApiOperation(value = "加入班课")
    @PostMapping("/joinclass")
    public Result joinclass(@RequestBody CoursestudentDTO coursestudentDTO) {
        if (courseService.getcountbyId(coursestudentDTO.getCourseId()) == 0) {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("班课不存在！");
        }
        Course course = courseService.getcoursebyId(coursestudentDTO.getCourseId());
        //jsonObject.put("success",true);
        Coursestudent coursestudent = new Coursestudent();
        coursestudent.setCourseId(coursestudentDTO.getCourseId());
        coursestudent.setStudentId(coursestudentDTO.getStudentId());
        coursestudent.setStudentname(" ");
        coursestudent.setTotalExp(0);
        //System.out.println(course.getCourseId());
        //JSONObject jsonObject =new JSONObject();
        if (course.getJoinable() == 1) {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("班课不允许学生加入！");
        } else if (course.getCoursestate() == 2) {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("该班课已结束！");
        } else if (coursestudentService.testisjoin(coursestudent) != 0) {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("已经加入该班课！");
        } else {
            coursestudent.setTotalExp(0);
            User user = userService.findByUserid(coursestudentDTO.getStudentId());
            coursestudent.setStudentname(user.getUserName());
            int i = coursestudentService.addcoursestudent(coursestudent);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("成功加入该班课！");
        }
    }

    @ApiOperation(value = "根据courseId获取班课详细信息")
    @ApiParam(name = "courseId", type = "int")
    @GetMapping("/getcoursedetail/{courseId}")
    public Result getcoursedetail(@PathVariable int courseId) {
        Course course = courseService.getcoursebyId(courseId);
        return Result.success().setData(course).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取班课详细信息成功!");
    }

    @ApiOperation(value = "根据班课Id和学生id退出班课")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "courseId", value = "班课id"),
            @ApiImplicitParam(required = true, name = "studentId", value = "学生id")
    })
    @PostMapping("/exitCourse/{courseId}/{studentId}")
    public Result exitCourse(@PathVariable int courseId, @PathVariable Integer studentId) {
        Coursestudent coursestudent = new Coursestudent();
        coursestudent.setCourseId(courseId);
        coursestudent.setStudentId(studentId);
        coursestudentService.deletecoursestudentbyId(coursestudent);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("退出班课成功！");
    }

    @ApiOperation(value = "根据创建老师获取班课列表")
    @ApiImplicitParam(required = true, name = "teacherName", value = "教师名")
    @GetMapping("/findCourseByTeacherName/{teacherName}")
    public Result findCourseByTeacherName(@PathVariable String teacherName) {
        List<Course> courseList = courseService.getcoursebyTeachername(teacherName);
        return Result.success().setData(courseList).setCode(ResultCodeEnum.OK.getCode()).setMsg("根据教师名获取班课列表成功!");
    }

    @ApiOperation(value = "根据班课名称获取班课列表")
    @ApiImplicitParam(required = true, name = "courseName", value = "班课名")
    @GetMapping("/findCourseByCoueseName/{courseName}")
    public Result findCourseByCoueseName(@PathVariable String courseName) {
        List<Course> courseList = courseService.getcoursebyCoursename(courseName);
        return Result.success().setData(courseList).setCode(ResultCodeEnum.OK.getCode()).setMsg("根据班课名获取班课列表成功!");
    }
}
