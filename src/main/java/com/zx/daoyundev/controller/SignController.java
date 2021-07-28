package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.*;
import com.zx.daoyundev.service.*;
import com.zx.daoyundev.util.DistanceUtil;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "签到接口")
@RestController
@CrossOrigin
public class SignController {
    @Autowired
    private InitsignService initsignService;
    @Autowired
    private StudentsignService studentsignService;
    @Autowired
    private CoursestudentService coursestudentService;
    @Autowired
    private SyaparaService syaparaService;
    @Autowired
    private DistanceUtil distanceUtil;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取已结束的签到，按开始时间降序")
    @GetMapping("/historysign/{courseId}/{endtime}")
    public Result gethistorysign(@PathVariable int courseId, @PathVariable long endtime) {
        Initsign initsign = new Initsign();
        initsign.setCourseId(courseId);
        initsign.setEndtime(endtime);
        List<Initsign> initsignList = initsignService.gethistory(initsign);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < initsignList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("teachersignId", initsignList.get(i).getTeachersignId());
            jsonObject.put("begtime", initsignList.get(i).getBegtime());
            jsonObject.put("endtime", initsignList.get(i).getEndtime());
            jsonObject.put("sightype", initsignList.get(i).getSightype());
            int issignnum = studentsignService.getissigncount(initsignList.get(i).getTeachersignId());
            int unsignnum = studentsignService.getunsigncount(initsignList.get(i).getTeachersignId());
            jsonObject.put("issignnum", issignnum);
            jsonObject.put("allstudentnum", issignnum + unsignnum);
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取历史签到列表成功!");
    }

    @ApiOperation(value = "老师发起签到")
    @PostMapping("/initsign")
    public Result initsign(@RequestBody Initsign initsign) {
        //System.out.println(initsign);
        if (coursestudentService.getcoursestudentCountbycourseId(initsign.getCourseId()) == 0) {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("当前班课没有学生！");
        }
        if (initsignService.judgetimesign(initsign) > 0) {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("当前存在进行中的签到！");
        } else {
            int i = initsignService.initAnsign(initsign);
            List<Coursestudent> coursestudentList = coursestudentService.getcoursestudentbycourseId(initsign.getCourseId());
            Studentsign studentsign = new Studentsign();
            studentsign.setTeachersignId(initsign.getTeachersignId());
            studentsign.setCourseId(initsign.getCourseId());
            studentsign.setSigntype(initsign.getSightype());
            studentsign.setSignstatus(1);
            for (int i1 = 0; i1 < coursestudentList.size(); i1++) {
                studentsign.setStudentId(coursestudentList.get(i1).getStudentId());
                studentsignService.addsign(studentsign);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("teachersignId", i);
            return Result.success().setData(jsonObject).setCode(ResultCodeEnum.OK.getCode()).setMsg("发起签到成功!");
        }
    }

    @ApiOperation(value = "修改签到结束时间")
    @PostMapping("/updateendtime")
    public Result updateendtime(@RequestBody Initsign initsign) {
        int i = initsignService.updatesigntime(initsign);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改结束时间成功！");
    }

    @ApiOperation(value = "签到结束根据签到id查看已签到学生")
    @ApiParam(name = "teachersignId", type = "int")
    @GetMapping("/getissign/{teachersignId}")
    public Result getissign(@PathVariable int teachersignId) {
        List<Studentsign> studentsignList = studentsignService.getissign(teachersignId);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < studentsignList.size(); i++) {
            User user = userService.findByUserid(studentsignList.get(i).getStudentId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", user.getUserId());
            jsonObject.put("userName", user.getUserName());
            jsonObject.put("signstatus", studentsignList.get(i).getSignstatus());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("查看已签到学生成功!");
    }

    @ApiOperation(value = "签到结束根据签到id查看未签到学生")
    @ApiParam(name = "teachersignId", type = "int")
    @GetMapping("/getunsign/{teachersignId}")
    public Result getunsign(@PathVariable int teachersignId) {
        List<Studentsign> studentsignList = studentsignService.getunsign(teachersignId);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < studentsignList.size(); i++) {
            User user = userService.findByUserid(studentsignList.get(i).getStudentId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", user.getUserId());
            jsonObject.put("userName", user.getUserName());
            jsonObject.put("signstatus", studentsignList.get(i).getSignstatus());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("查看未签到学生成功!");
    }

    @ApiOperation(value = "根据签到id放弃签到")
    @ApiParam(name = "teachersignId", type = "int")
    @PostMapping("/giveupsign/{teachersignId}")
    public Result giveupsign(@PathVariable int teachersignId) {
        int i = initsignService.deletesighbyId(teachersignId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("放弃签到成功！");
    }

    @ApiOperation(value = "删除签到，并扣除本次已签到学生的经验值")
    @PostMapping("/deletesign")
    public Result deletesign(@RequestBody Initsign initsign) {//删除签到,需要扣去已签到学生的经验值
        int courseId = initsign.getCourseId();
        List<Studentsign> studentsignList = studentsignService.getissign(initsign.getTeachersignId());
        for (int i = 0; i < studentsignList.size(); i++) {
            long signexp = studentsignList.get(i).getGetexp();
            Coursestudent coursestudent = new Coursestudent();
            coursestudent.setCourseId(courseId);
            coursestudent.setStudentId(studentsignList.get(i).getStudentId());
            Coursestudent coursestudent1 = coursestudentService.getcoursestudentbystudentIdandcourseId(coursestudent);
            long exp = coursestudent1.getTotalExp();
            exp = exp - signexp;
            coursestudent1.setTotalExp(exp);
            int j = coursestudentService.updatetotalExp(coursestudent1);
        }
        int i = initsignService.deletesighbyId(initsign.getTeachersignId());
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除签到成功！");
    }

    @ApiOperation(value = "结束签到")
    @PostMapping("/endsign")
    public Result endsign(@RequestBody Initsign initsign) {

        if (initsign.getSightype() == 1) {//结束限时签到，则修改结束时间
            //System.out.println("原结束时间" + initsignService.getinitsignbyteachersignId(initsign.getTeachersignId()).getEndtime());
            //System.out.println("申请修改的时间" + initsign.getEndtime());
            int i = initsignService.updatesigntime(initsign);

            //System.out.println("修改时间成功");
        } else {
            int i = initsignService.endsigh(initsign);
        }
        List<Studentsign> studentsignList = studentsignService.getissign(initsign.getTeachersignId());
        Coursestudent coursestudent = new Coursestudent();
        coursestudent.setCourseId(initsign.getCourseId());
        long signexp = syaparaService.getparaBykeyword("experience").getValue();
        for (int i = 0; i < studentsignList.size(); i++) {
            studentsignList.get(i).setGetexp(signexp);
            int j = studentsignService.updateexp(studentsignList.get(i));
            coursestudent.setStudentId(studentsignList.get(i).getStudentId());
            Coursestudent coursestudent1 = coursestudentService.getcoursestudentbystudentIdandcourseId(coursestudent);
            long nowexp = coursestudent1.getTotalExp();
            nowexp = nowexp + signexp;
            coursestudent1.setTotalExp(nowexp);
            int k = coursestudentService.updatetotalExp(coursestudent1);
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("签到已结束！");
    }

    @ApiOperation(value = "学生进入签到")
    @PostMapping("/intosign")
    public Result intosign(@RequestBody Initsign initsign) {
        if (initsignService.getsigningCountbyCourseid(initsign) > 0) {
            Initsign initsign1 = initsignService.getsigningbyCourseid(initsign);
            return Result.success().setData(initsign1).setCode(ResultCodeEnum.OK.getCode()).setMsg("进入下一个页面");
        } else {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("没有进行中的签到或签到已结束！");
        }
    }

    @ApiOperation(value = "学生签到")
    @PostMapping("/stusign")//学生签到
    public Result stusign(@RequestBody Studentsign studentsign) {
        Initsign initsign = initsignService.getinitsignbyteachersignId(studentsign.getTeachersignId());
        if (initsign.getSightype() == 1) {
            if (initsign.getEndtime() < studentsign.getSigntime()) {
                return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("已超时！");
            } else {
                double teachlongitude = Double.parseDouble(initsign.getTeachlongitude());
                double teachlatitude = Double.parseDouble(initsign.getTeachlatitude());
                double stulongitude = Double.parseDouble(studentsign.getLongitude());
                double stulatitude = Double.parseDouble(studentsign.getLatitude());
                double distance = distanceUtil.getDistance(teachlongitude, teachlatitude, stulongitude, stulatitude);
                double distancereq = (double) syaparaService.getparaBykeyword("distance").getValue();
                if (distance > distancereq) {
                    return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("超出签到范围！");
                } else {
                    studentsign.setSignstatus(2);//表示签到成功
                    int i = studentsignService.updatesign(studentsign);
                    return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("签到成功！");
                }
            }
        } else {
            if (initsign.getIsEnd() == 2) {
                return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("一键签到已结束！");
            } else {
                double teachlongitude = Double.parseDouble(initsign.getTeachlongitude());
                double teachlatitude = Double.parseDouble(initsign.getTeachlatitude());
                double stulongitude = Double.parseDouble(studentsign.getLongitude());
                double stulatitude = Double.parseDouble(studentsign.getLatitude());
                double distance = distanceUtil.getDistance(teachlongitude, teachlatitude, stulongitude, stulatitude);
                double distancereq = (double) syaparaService.getparaBykeyword("distance").getValue();
                if (distance > distancereq) {
                    return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("超出签到范围！");
                } else {
                    studentsign.setSignstatus(2);//表示签到成功
                    int i = studentsignService.updatesign(studentsign);
                    return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("签到成功！");
                }
            }
        }
    }

    @ApiOperation(value = "查看班课内某学生详细签到信息")
    @GetMapping("/stusigndetail")
    public Result stusigndetail(@RequestBody Studentsign studentsign) {
        List<Studentsign> studentsignList = studentsignService.getissignstudentsignById(studentsign);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < studentsignList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("signtime", studentsignList.get(i).getSigntime());
            jsonObject.put("getexp", studentsignList.get(i).getGetexp());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("查看详细签到信息成功!");
    }

    @ApiOperation(value = "统计签到数据")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "courseId", value = "班课id"),
            @ApiImplicitParam(required = true, name = "endtime", value = "结束时间（当前时间的时间戳）")
    })
    @GetMapping("/statistSignData/{courseId}/{endtime}")
    public Result findCourseByCoueseName(@PathVariable int courseId, @PathVariable long endtime) {
        JSONArray jsonArray = new JSONArray();
        Initsign initsign = new Initsign();
        initsign.setCourseId(courseId);
        initsign.setEndtime(endtime);
        //System.out.println(initsign);
        int totalCount = initsignService.gethistoryCountbyCourseid(initsign);
        List<Coursestudent> coursestudentList = coursestudentService.getcoursestudentbycourseId(courseId);
        for (int i = 0; i < coursestudentList.size(); i++) {
            User user = userService.findByUserid(coursestudentList.get(i).getStudentId());
            Studentsign studentsign = new Studentsign();
            studentsign.setCourseId(courseId);
            studentsign.setStudentId(user.getUserId());
            int issignnum = studentsignService.getissignCountstudentsignById(studentsign);
            int unsignnum = studentsignService.getunsignCountstudentsignById(studentsign);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", user.getUserName());
            jsonObject.put("perid", user.getPerid());
            jsonObject.put("avatar", user.getAvatar());
            jsonObject.put("issignnum", issignnum);
            jsonObject.put("unsignnum", unsignnum);
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setCount(totalCount).setMsg("统计签到数据成功");
    }
}
