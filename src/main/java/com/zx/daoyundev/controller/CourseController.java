package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.*;
import com.zx.daoyundev.service.CourseService;
import com.zx.daoyundev.service.CoursestudentService;
import com.zx.daoyundev.service.UserService;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
//    @RequestMapping("/createlist/{userId}")//我创建的班课
    @GetMapping("/createlist/{userId}")//我创建的班课
    public Result createlist(@PathVariable Integer userId) {
        JSONArray jsonArray = new JSONArray();
        User user = userService.findByUserid(userId);
        List<Course> courseList = courseService.getcoursebyTeachername(user.getUserName());
//        for (int i = 0; i < courseList.size(); i++) {
//            JSONObject jsonObject =new JSONObject();
//            jsonObject.put("courseId",courseList.get(i).getCourseId());
//            jsonObject.put("courseName",courseList.get(i).getCourseName());
//            jsonObject.put("courseclass",courseList.get(i).getCourseclass());
//            jsonObject.put("term",courseList.get(i).getTerm());
//            jsonObject.put("image",courseList.get(i).getImage());
//            jsonObject.put("teacherName",courseList.get(i).getTeacherName());
//            jsonArray.add(jsonObject);
//        }
        return Result.success().setData(courseList).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取创建的班课成功!");
    }

    //    @ApiOperation(value = "根据userId查找我创建的班课")
//    @ApiParam(name = "userId", type = "Integer")
//    @RequestMapping("/createlist/{userId}")//我创建的班课
//    public Object createlist(@PathVariable Integer userId){
//        JSONArray jsonArray =new JSONArray();
//        User user=userService.findByUserid(userId);
//        List<Course> courseList=courseService.getcoursebyTeachername(user.getUserName());
//        for (int i = 0; i < courseList.size(); i++) {
//            JSONObject jsonObject =new JSONObject();
//            jsonObject.put("courseId",courseList.get(i).getCourseId());
//            jsonObject.put("courseName",courseList.get(i).getCourseName());
//            jsonObject.put("courseclass",courseList.get(i).getCourseclass());
//            jsonObject.put("term",courseList.get(i).getTerm());
//            jsonObject.put("image",courseList.get(i).getImage());
//            jsonObject.put("teacherName",courseList.get(i).getTeacherName());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
    @ApiOperation(value = "根据userId查找我加入的班课")
    @ApiParam(name = "userId", type = "Integer")
    //@RequestMapping("/joinlist/{userId}")//我加入的班课
    @GetMapping("/joinlist/{userId}")//我加入的班课
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

    //    @ApiOperation(value = "根据userId查找我加入的班课")
//    @ApiParam(name = "userId", type = "Integer")
//    @RequestMapping("/joinlist/{userId}")//我加入的班课
//    public Object joinlist(@PathVariable Integer userId){
//        JSONArray jsonArray =new JSONArray();
//        List<Coursestudent> coursestudentList=coursestudentService.getcoursestudentbystudentId(userId);
//        for (int i = 0; i < coursestudentList.size(); i++) {
//            JSONObject jsonObject =new JSONObject();
//            Course course=courseService.getcoursebyId(coursestudentList.get(i).getCourseId());
//            jsonObject.put("courseId",course.getCourseId());
//            jsonObject.put("courseName",course.getCourseName());
//            jsonObject.put("courseclass",course.getCourseclass());
//            jsonObject.put("term",course.getTerm());
//            jsonObject.put("teacherName",course.getTeacherName());
//            jsonObject.put("image",course.getImage());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
    @ApiOperation(value = "查找所有班课")
//    @RequestMapping("/getallcourse")
    @GetMapping("/getallcourse")
    public Result getAllcourse() {
        List<Course> courseList = courseService.findAllcourse();
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < courseList.size(); i++) {
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("courseId",courseList.get(i).getCourseId());
//            jsonObject.put("courseName",courseList.get(i).getCourseName());
//            jsonObject.put("courseclass",courseList.get(i).getCourseclass());
//            jsonObject.put("term",courseList.get(i).getTerm());
//            jsonObject.put("teacherName",courseList.get(i).getTeacherName());
//            jsonObject.put("joinable",courseList.get(i).getJoinable());
//            jsonObject.put("isschoolclass",courseList.get(i).getIsschoolclass());
//            jsonObject.put("courseschool",courseList.get(i).getCourseschool());
//            jsonObject.put("coursemajor",courseList.get(i).getCoursemajor());
//            jsonObject.put("learningrequire",courseList.get(i).getLearningrequire());
//            jsonObject.put("teachprogess",courseList.get(i).getTeachprogess());
//            jsonObject.put("examarrange",courseList.get(i).getExamarrange());
//            jsonObject.put("coursestate",courseList.get(i).getCoursestate());
//            jsonArray.add(jsonObject);
//        }
        return Result.success().setData(courseList).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取所有班课成功!");
    }

    //    @ApiOperation(value = "查找所有班课")
//    @RequestMapping("/getallcourse")
//    public Object getAllcourse(){
//        List<Course> courseList=courseService.findAllcourse();
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < courseList.size(); i++) {
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("courseId",courseList.get(i).getCourseId());
//            jsonObject.put("courseName",courseList.get(i).getCourseName());
//            jsonObject.put("courseclass",courseList.get(i).getCourseclass());
//            jsonObject.put("term",courseList.get(i).getTerm());
//            jsonObject.put("teacherName",courseList.get(i).getTeacherName());
//            jsonObject.put("joinable",courseList.get(i).getJoinable());
//            jsonObject.put("isschoolclass",courseList.get(i).getIsschoolclass());
//            jsonObject.put("courseschool",courseList.get(i).getCourseschool());
//            jsonObject.put("coursemajor",courseList.get(i).getCoursemajor());
//            jsonObject.put("learningrequire",courseList.get(i).getLearningrequire());
//            jsonObject.put("teachprogess",courseList.get(i).getTeachprogess());
//            jsonObject.put("examarrange",courseList.get(i).getExamarrange());
//            jsonObject.put("coursestate",courseList.get(i).getCoursestate());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
    @ApiOperation(value = "根据courseId获取加入班课的学生列表")
    @ApiParam(name = "courseId", type = "int")
//    @RequestMapping("/memberList/{courseId}")
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

    //    @ApiOperation(value = "根据courseId获取加入班课的学生列表")
//    @ApiParam(name = "courseId", type = "int")
//    @RequestMapping("/memberList/{courseId}")
//    public Object memberList(@PathVariable int courseId){
//        List<Coursestudent> coursestudentList=coursestudentService.getcoursestudentbycourseId(courseId);
//        if(coursestudentList.size()==0){
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("success",false);
//            jsonObject.put("message","本班课没有学生加入");
//            return jsonObject;
//        } else {
//            JSONArray jsonArray =new JSONArray();
//            for (int i = 0; i < coursestudentList.size(); i++) {
//                JSONObject jsonObject=new JSONObject();
//                User user =userService.findByUserid(coursestudentList.get(i).getStudentId());
//                jsonObject.put("studentId",coursestudentList.get(i).getStudentId());
//                jsonObject.put("studentname",coursestudentList.get(i).getStudentname());
//                jsonObject.put("perid",user.getPerid());
//                jsonObject.put("totalExp",coursestudentList.get(i).getTotalExp());
//                jsonArray.add(jsonObject);
//            }
//            return jsonArray;
//        }
//    }
    @ApiOperation(value = "创建班课")
//  @RequestMapping("/creatclass")
    @PostMapping("/creatclass")
    public Result creatclass(@RequestBody Course course) {
        int courseId = courseService.addcourse(course);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("创建班课成功！");

    }

    //    @ApiOperation(value = "创建班课")
//    @RequestMapping("/creatclass")
//    public Object creatclass(@RequestBody Course course){
//        int courseId = courseService.addcourse(course);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("courseId",courseId);
//        jsonObject.put("success",true);
//        jsonObject.put("message","创建班课成功");
//        return jsonObject;
//    }
    @ApiOperation(value = "修改班课信息")
//    @RequestMapping("/updateclass")
    @PostMapping("/updateclass")
    public Result updateclass(@RequestBody Course course) {
        int i = courseService.updatecoursebyId(course);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改班课信息成功！");
    }

    //    @ApiOperation(value = "修改班课信息")
//    @RequestMapping("/updateclass")
//    public Object updateclass(@RequestBody Course course){
//        int i = courseService.updatecoursebyId(course);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","修改班课信息成功");
//        return jsonObject;
//    }
    @ApiOperation(value = "根据courseId删除班课")
    @ApiParam(name = "courseId", type = "int")
//    @RequestMapping("/deleteclass/{courseId}")
    @PostMapping("/deleteclass/{courseId}")
    public Result deleteclass(@PathVariable int courseId) {
        Course course = courseService.getcoursebyId(courseId);
        int i = courseService.deletecoursebyId(courseId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除班课成功！");
    }

    //    @ApiOperation(value = "根据courseId删除班课")
//    @ApiParam(name = "courseId", type = "int")
//    @RequestMapping("/deleteclass/{courseId}")
//    public Object deleteclass(@PathVariable int courseId){
//        int i =courseService.deletecoursebyId(courseId);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","删除班课成功");
//        return jsonObject;
//    }
    @ApiOperation(value = "加入班课")
//    @RequestMapping("/joinclass")
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

    //    @ApiOperation(value = "加入班课")
//    @RequestMapping("/joinclass")
//    public Object joinclass(@RequestBody CoursestudentDTO coursestudentDTO){
//        JSONObject jsonObject =new JSONObject();
//        if(courseService.getcountbyId(coursestudentDTO.getCourseId())==0){
//            jsonObject.put("success",false);
//            jsonObject.put("message","班课不存在");
//            return jsonObject;
//        }
//        Course course=courseService.getcoursebyId(coursestudentDTO.getCourseId());
//        //jsonObject.put("success",true);
//        Coursestudent coursestudent=new Coursestudent();
//        coursestudent.setCourseId(coursestudentDTO.getCourseId());
//        coursestudent.setStudentId(coursestudentDTO.getStudentId());
//        coursestudent.setStudentname(" ");
//        coursestudent.setTotalExp(0);
//        //System.out.println(course.getCourseId());
//        //JSONObject jsonObject =new JSONObject();
//        if(course.getJoinable()==0){
//            jsonObject.put("success",false);
//            jsonObject.put("message","该班课不允许加入");
//        } else if (course.getCoursestate()==1){
//            jsonObject.put("success",false);
//            jsonObject.put("message","该班课已结束");
//        } else if (coursestudentService.testisjoin(coursestudent)!=0){
//            jsonObject.put("success",false);
//            jsonObject.put("message","已加入该班课");
//        } else {
//            coursestudent.setTotalExp(0);
//            User user=userService.findByUserid(coursestudentDTO.getStudentId());
//            coursestudent.setStudentname(user.getUserName());
//            int i =coursestudentService.addcoursestudent(coursestudent);
//            jsonObject.put("success",true);
//            jsonObject.put("message","成功加入该班课");
//        }
//        return jsonObject;
//    }
    @ApiOperation(value = "根据courseId获取班课详细信息")
    @ApiParam(name = "courseId", type = "int")
//    @RequestMapping("getcoursedetail/{courseId}")
    @GetMapping("getcoursedetail/{courseId}")
    public Result getcoursedetail(@PathVariable int courseId) {
        Course course = courseService.getcoursebyId(courseId);
        return Result.success().setData(course).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取班课详细信息成功!");
    }
//    @ApiOperation(value = "根据courseId获取班课详细信息")
//    @ApiParam(name = "courseId", type = "int")
//    @RequestMapping("getcoursedetail/{courseId}")
//    public Object getcoursedetail(@PathVariable int courseId){
//        Course course=courseService.getcoursebyId(courseId);
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("courseId",course.getCourseId());
//        jsonObject.put("courseName",course.getCourseName());
//        jsonObject.put("courseclass",course.getCourseclass());
//        jsonObject.put("term",course.getTerm());
//        jsonObject.put("teacherName",course.getTeacherName());
//        jsonObject.put("joinable",course.getJoinable());
//        jsonObject.put("isschoolclass",course.getIsschoolclass());
//        jsonObject.put("courseschool",course.getCourseschool());
//        jsonObject.put("coursemajor",course.getCoursemajor());
//        jsonObject.put("learningrequire",course.getLearningrequire());
//        jsonObject.put("teachprogess",course.getTeachprogess());
//        jsonObject.put("examarrange",course.getExamarrange());
//        jsonObject.put("image",course.getImage());
//        jsonObject.put("coursestate",course.getCoursestate());
//        return jsonObject;
//    }

    @ApiOperation(value = "根据班课Id和学生id退出班课")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "courseId", value = "班课id"),
            @ApiImplicitParam(required = true, name = "studentId", value = "学生id")
    })
    @PostMapping("exitCourse/{courseId}/{studentId}")
    public Result exitCourse(@PathVariable int courseId,@PathVariable Integer studentId){
        Coursestudent coursestudent=new Coursestudent();
        coursestudent.setCourseId(courseId);
        coursestudent.setStudentId(studentId);
        coursestudentService.deletecoursestudentbyId(coursestudent);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("退出班课成功！");
    }
}
