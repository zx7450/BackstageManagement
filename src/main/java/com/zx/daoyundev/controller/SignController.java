package com.zx.daoyundev.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zx.daoyundev.entity.*;
import com.zx.daoyundev.service.*;
import com.zx.daoyundev.util.DistanceUtil;
import com.zx.daoyundev.util.Result;
import com.zx.daoyundev.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
//    @RequestMapping("/historysign")
    @GetMapping("/historysign/{courseId}/{endtime}")
    public Result gethistorysign(@PathVariable int courseId,@PathVariable long endtime){
        Initsign initsign=new Initsign();
        initsign.setCourseId(courseId);
        initsign.setEndtime(endtime);
        List<Initsign> initsignList=initsignService.gethistory(initsign);
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < initsignList.size(); i++) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("teachersignId",initsignList.get(i).getTeachersignId());
            jsonObject.put("begtime",initsignList.get(i).getBegtime());
            jsonObject.put("endtime",initsignList.get(i).getEndtime());
            jsonObject.put("sightype",initsignList.get(i).getSightype());
            int issignnum=studentsignService.getissigncount(initsignList.get(i).getTeachersignId());
            int unsignnum=studentsignService.getunsigncount(initsignList.get(i).getTeachersignId());
            jsonObject.put("issignnum",issignnum);
            jsonObject.put("allstudentnum",issignnum+unsignnum);
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取历史签到列表成功!");
    }
//    @ApiOperation(value = "获取已结束的签到，按开始时间降序")
//    @RequestMapping("/historysign")
//    public Object gethistorysign(@RequestBody Initsign initsign){
//        List<Initsign> initsignList=initsignService.gethistory(initsign);
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < initsignList.size(); i++) {
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("teachersignId",initsignList.get(i).getTeachersignId());
//            jsonObject.put("begtime",initsignList.get(i).getBegtime());
//            jsonObject.put("endtime",initsignList.get(i).getEndtime());
//            jsonObject.put("sightype",initsignList.get(i).getSightype());
//            int issignnum=studentsignService.getissigncount(initsignList.get(i).getTeachersignId());
//            int unsignnum=studentsignService.getunsigncount(initsignList.get(i).getTeachersignId());
//            jsonObject.put("issignnum",issignnum);
//            jsonObject.put("allstudentnum",issignnum+unsignnum);
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
    @ApiOperation(value = "老师发起签到")
//    @RequestMapping("/initsign")
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
//    @ApiOperation(value = "老师发起签到")
//    @RequestMapping("/initsign")
//    public Object initsign(@RequestBody Initsign initsign){
//        JSONObject jsonObject=new JSONObject();
//        //System.out.println(initsign);
//        if(coursestudentService.getcoursestudentCountbycourseId(initsign.getCourseId())==0){
//            jsonObject.put("success",false);
//            jsonObject.put("message","当前班课没有学生");
//            return jsonObject;
//        }
//        if(initsignService.judgetimesign(initsign)>0){
//
//            jsonObject.put("success",false);
//            jsonObject.put("message","当前存在进行中的签到");
//            return jsonObject;
//        } else {
//            int i=initsignService.initAnsign(initsign);
//            List<Coursestudent> coursestudentList=coursestudentService.getcoursestudentbycourseId(initsign.getCourseId());
//            Studentsign studentsign=new Studentsign();
//            studentsign.setTeachersignId(initsign.getTeachersignId());
//            studentsign.setCourseId(initsign.getCourseId());
//            studentsign.setSigntype(initsign.getSightype());
//            studentsign.setSignstatus(1);
//            for (int i1 = 0; i1 < coursestudentList.size(); i1++) {
//                studentsign.setStudentId(coursestudentList.get(i1).getStudentId());
//                studentsignService.addsign(studentsign);
//            }
//            jsonObject.put("teachersignId",i);
//            jsonObject.put("success",true);
//            jsonObject.put("message","发起签到成功");
//            return jsonObject;}
//    }
    @ApiOperation(value = "修改签到结束时间")
//    @RequestMapping("/updateendtime")
    @PostMapping("/updateendtime")
    public Result updateendtime(@RequestBody Initsign initsign){
//        Initsign initsign1=initsignService.getinitsignbyteachersignId(initsign.getTeachersignId());
//        System.out.println("修改的签到号"+initsign.getTeachersignId());
//        System.out.println("修改前的时间"+initsign1.getEndtime());
//        System.out.println("申请修改的时间"+initsign.getEndtime());
        int i=initsignService.updatesigntime(initsign);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改结束时间成功！");
    }
//    @ApiOperation(value = "修改签到结束时间")
//    @RequestMapping("/updateendtime")
//    public Object updateendtime(@RequestBody Initsign initsign){
////        Initsign initsign1=initsignService.getinitsignbyteachersignId(initsign.getTeachersignId());
////        System.out.println("修改的签到号"+initsign.getTeachersignId());
////        System.out.println("修改前的时间"+initsign1.getEndtime());
////        System.out.println("申请修改的时间"+initsign.getEndtime());
//        int i=initsignService.updatesigntime(initsign);
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","修改结束时间成功");
//        return jsonObject;
//    }
    @ApiOperation(value = "签到结束根据签到id查看已签到学生")
    @ApiParam(name = "teachersignId", type = "int")
//    @RequestMapping("/getissign/{teachersignId}")
    @GetMapping("/getissign/{teachersignId}")
    public Result getissign(@PathVariable int teachersignId){
        List<Studentsign> studentsignList=studentsignService.getissign(teachersignId);
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < studentsignList.size(); i++) {
            User user=userService.findByUserid(studentsignList.get(i).getStudentId());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("userId",user.getUserId());
            jsonObject.put("userName",user.getUserName());
            jsonObject.put("signstatus",studentsignList.get(i).getSignstatus());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("查看已签到学生成功!");
    }
//    @ApiOperation(value = "签到结束根据签到id查看已签到学生")
//    @ApiParam(name = "teachersignId", type = "int")
//    @RequestMapping("/getissign/{teachersignId}")
//    public Object getissign(@PathVariable int teachersignId){
//        List<Studentsign> studentsignList=studentsignService.getissign(teachersignId);
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < studentsignList.size(); i++) {
//            User user=userService.findByUserid(studentsignList.get(i).getStudentId());
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("userId",user.getUserId());
//            jsonObject.put("userName",user.getUserName());
//            jsonObject.put("signstatus",studentsignList.get(i).getSignstatus());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
    @ApiOperation(value = "签到结束根据签到id查看未签到学生")
    @ApiParam(name = "teachersignId", type = "int")
//    @RequestMapping("/getunsign/{teachersignId}")
    @GetMapping("/getunsign/{teachersignId}")
    public Result getunsign(@PathVariable int teachersignId){
        List<Studentsign> studentsignList=studentsignService.getunsign(teachersignId);
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < studentsignList.size(); i++) {
            User user=userService.findByUserid(studentsignList.get(i).getStudentId());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("userId",user.getUserId());
            jsonObject.put("userName",user.getUserName());
            jsonObject.put("signstatus",studentsignList.get(i).getSignstatus());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("查看未签到学生成功!");
    }
//    @ApiOperation(value = "签到结束根据签到id查看未签到学生")
//    @ApiParam(name = "teachersignId", type = "int")
//    @RequestMapping("/getunsign/{teachersignId}")
//    public Object getunsign(@PathVariable int teachersignId){
//        List<Studentsign> studentsignList=studentsignService.getunsign(teachersignId);
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < studentsignList.size(); i++) {
//            User user=userService.findByUserid(studentsignList.get(i).getStudentId());
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("userId",user.getUserId());
//            jsonObject.put("userName",user.getUserName());
//            jsonObject.put("signstatus",studentsignList.get(i).getSignstatus());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
    @ApiOperation(value = "根据签到id放弃签到")
    @ApiParam(name = "teachersignId", type = "int")
//    @RequestMapping("/giveupsign/{teachersignId}")
    @PostMapping("/giveupsign/{teachersignId}")
    public Result giveupsign(@PathVariable int teachersignId){
        int i=initsignService.deletesighbyId(teachersignId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("放弃签到成功！");
    }
//    @ApiOperation(value = "根据签到id放弃签到")
//    @ApiParam(name = "teachersignId", type = "int")
//    @RequestMapping("/giveupsign/{teachersignId}")
//    public Object giveupsign(@PathVariable int teachersignId){
//        int i=initsignService.deletesighbyId(teachersignId);
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("success",true);
//        jsonObject.put("message","放弃签到成功");
//        return jsonObject;
//    }
    @ApiOperation(value = "删除签到，并扣除本次已签到学生的经验值")
//    @RequestMapping("/deletesign")
    @PostMapping("/deletesign")
    public Result deletesign(@RequestBody Initsign initsign) {//删除签到,需要扣去已签到学生的经验值
        int courseId = initsign.getCourseId();
        List<Studentsign> studentsignList = studentsignService.getissign(initsign.getTeachersignId());
        for (int i = 0; i < studentsignList.size(); i++) {
            long signexp=studentsignList.get(i).getGetexp();
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
//    @ApiOperation(value = "删除签到，并扣除本次已签到学生的经验值")
//    @RequestMapping("/deletesign")
//    public Object deletesign(@RequestBody Initsign initsign) {//删除签到,需要扣去已签到学生的经验值
//        int courseId = initsign.getCourseId();
//        List<Studentsign> studentsignList = studentsignService.getissign(initsign.getTeachersignId());
//        for (int i = 0; i < studentsignList.size(); i++) {
//            long signexp=studentsignList.get(i).getGetexp();
//            Coursestudent coursestudent = new Coursestudent();
//            coursestudent.setCourseId(courseId);
//            coursestudent.setStudentId(studentsignList.get(i).getStudentId());
//            Coursestudent coursestudent1 = coursestudentService.getcoursestudentbystudentIdandcourseId(coursestudent);
//            long exp = coursestudent1.getTotalExp();
//            exp = exp - signexp;
//            coursestudent1.setTotalExp(exp);
//            int j = coursestudentService.updatetotalExp(coursestudent1);
//        }
//        int i = initsignService.deletesighbyId(initsign.getTeachersignId());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("success", true);
//        jsonObject.put("message", "删除签到成功");
//        return jsonObject;
//    }
    @ApiOperation(value = "结束签到")
//    @RequestMapping("/endsign")
    @PostMapping("/endsign")
    public Result endsign(@RequestBody Initsign initsign){

        if(initsign.getSightype()==1){//结束限时签到，则修改结束时间
            System.out.println("原结束时间"+initsignService.getinitsignbyteachersignId(initsign.getTeachersignId()).getEndtime());
            System.out.println("申请修改的时间"+initsign.getEndtime());
            int i=initsignService.updatesigntime(initsign);

            System.out.println("修改时间成功");
        } else {
            int i=initsignService.endsigh(initsign);
        }
        List<Studentsign> studentsignList=studentsignService.getissign(initsign.getTeachersignId());
        Coursestudent coursestudent=new Coursestudent();
        coursestudent.setCourseId(initsign.getCourseId());
        long signexp=syaparaService.getparaBykeyword("experience").getValue();
        for (int i = 0; i < studentsignList.size(); i++) {
            studentsignList.get(i).setGetexp(signexp);
            int j =studentsignService.updateexp(studentsignList.get(i));
            coursestudent.setStudentId(studentsignList.get(i).getStudentId());
            Coursestudent coursestudent1=coursestudentService.getcoursestudentbystudentIdandcourseId(coursestudent);
            long nowexp=coursestudent1.getTotalExp();
            nowexp=nowexp+signexp;
            coursestudent1.setTotalExp(nowexp);
            int k = coursestudentService.updatetotalExp(coursestudent1);
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("签到已结束！");
    }
//    @ApiOperation(value = "结束签到")
//    @RequestMapping("/endsign")
//    public Object endsign(@RequestBody Initsign initsign){
//        JSONObject jsonObject = new JSONObject();
//
//        if(initsign.getSightype()==1){//结束限时签到，则修改结束时间
//            System.out.println("原结束时间"+initsignService.getinitsignbyteachersignId(initsign.getTeachersignId()).getEndtime());
//            System.out.println("申请修改的时间"+initsign.getEndtime());
//            int i=initsignService.updatesigntime(initsign);
//
//            System.out.println("修改时间成功");
//        } else {
//            int i=initsignService.endsigh(initsign);
//        }
//        List<Studentsign> studentsignList=studentsignService.getissign(initsign.getTeachersignId());
//        Coursestudent coursestudent=new Coursestudent();
//        coursestudent.setCourseId(initsign.getCourseId());
//        long signexp=syaparaService.getparaBykeyword("experience").getValue();
//        for (int i = 0; i < studentsignList.size(); i++) {
//            studentsignList.get(i).setGetexp(signexp);
//            int j =studentsignService.updateexp(studentsignList.get(i));
//            coursestudent.setStudentId(studentsignList.get(i).getStudentId());
//            Coursestudent coursestudent1=coursestudentService.getcoursestudentbystudentIdandcourseId(coursestudent);
//            long nowexp=coursestudent1.getTotalExp();
//            nowexp=nowexp+signexp;
//            coursestudent1.setTotalExp(nowexp);
//            int k = coursestudentService.updatetotalExp(coursestudent1);
//        }
//        jsonObject.put("success", true);
//        jsonObject.put("message", "签到已结束");
//        return jsonObject;
//    }
    @ApiOperation(value = "学生进入签到")
//    @RequestMapping("/intosign")
    @PostMapping("/intosign")
    public Result intosign(@RequestBody Initsign initsign){
        if(initsignService.getsigningCountbyCourseid(initsign)>0){
            Initsign initsign1=initsignService.getsigningbyCourseid(initsign);
            return Result.success().setData(initsign1).setCode(ResultCodeEnum.OK.getCode()).setMsg("进入下一个页面");
        } else {
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("没有进行中的签到或签到已结束！");
        }
    }
//    @ApiOperation(value = "学生进入签到")
//    @RequestMapping("/intosign")
//    public Object intosign(@RequestBody Initsign initsign){
//        JSONObject jsonObject = new JSONObject();
//        if(initsignService.getsigningCountbyCourseid(initsign)>0){
//            Initsign initsign1=initsignService.getsigningbyCourseid(initsign);
//            jsonObject.put("teachersignId",initsign1.getTeachersignId());
//            jsonObject.put("teacherId",initsign1.getTeacherId());
//            jsonObject.put("courseId",initsign1.getCourseId());
//            jsonObject.put("begtime",initsign1.getBegtime());
//            jsonObject.put("endtime",initsign1.getEndtime());
//            jsonObject.put("teachlongitude",initsign1.getTeachlongitude());
//            jsonObject.put("teachlatitude",initsign1.getTeachlatitude());
//            jsonObject.put("sightype",initsign1.getSightype());
//            jsonObject.put("isEnd",initsign1.getIsEnd());
//            jsonObject.put("success", true);
//            jsonObject.put("message", "进入下一个页面");
//
//        } else {
//            jsonObject.put("success", false);
//            jsonObject.put("message", "没有进行中的签到或签到已结束");
//        }
//        return jsonObject;
//    }
    @ApiOperation(value = "学生签到")
//    @RequestMapping("/stusign")//学生签到
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
//    @ApiOperation(value = "学生签到")
//    @RequestMapping("/stusign")//学生签到
//    public Object stusign(@RequestBody Studentsign studentsign) {
//        Initsign initsign = initsignService.getinitsignbyteachersignId(studentsign.getTeachersignId());
//        JSONObject jsonObject = new JSONObject();
//        if (initsign.getSightype() == 1) {
//            if (initsign.getEndtime() < studentsign.getSigntime()) {
//                jsonObject.put("success", false);
//                jsonObject.put("message", "已超时");
//            } else {
//                double teachlongitude = Double.parseDouble(initsign.getTeachlongitude());
//                double teachlatitude = Double.parseDouble(initsign.getTeachlatitude());
//                double stulongitude = Double.parseDouble(studentsign.getLongitude());
//                double stulatitude = Double.parseDouble(studentsign.getLatitude());
//                double distance = distanceUtil.getDistance(teachlongitude, teachlatitude, stulongitude, stulatitude);
//                double distancereq = (double) syaparaService.getparaBykeyword("distance").getValue();
//                if (distance > distancereq) {
//                    jsonObject.put("success", false);
//                    jsonObject.put("message", "超出签到范围");
//                } else {
//                    studentsign.setSignstatus(2);//表示签到成功
//                    int i = studentsignService.updatesign(studentsign);
//                    jsonObject.put("success", true);
//                    jsonObject.put("message", "签到成功");
//                }
//            }
//        } else {
//            if (initsign.getIsEnd() == 2) {
//                jsonObject.put("success", false);
//                jsonObject.put("message", "一键签到已结束");
//            } else {
//                double teachlongitude = Double.parseDouble(initsign.getTeachlongitude());
//                double teachlatitude = Double.parseDouble(initsign.getTeachlatitude());
//                double stulongitude = Double.parseDouble(studentsign.getLongitude());
//                double stulatitude = Double.parseDouble(studentsign.getLatitude());
//                double distance = distanceUtil.getDistance(teachlongitude, teachlatitude, stulongitude, stulatitude);
//                double distancereq = (double) syaparaService.getparaBykeyword("distance").getValue();
//                if (distance > distancereq) {
//                    jsonObject.put("success", false);
//                    jsonObject.put("message", "超出签到范围");
//                } else {
//                    studentsign.setSignstatus(2);//表示签到成功
//                    int i = studentsignService.updatesign(studentsign);
//                    jsonObject.put("success", true);
//                    jsonObject.put("message", "签到成功");
//                }
//            }
//        }
//        return jsonObject;
//    }
    @ApiOperation(value = "查看班课内某学生详细签到信息")
//    @RequestMapping("/stusigndetail")
    @GetMapping("/stusigndetail")
    public Result stusigndetail(@RequestBody Studentsign studentsign){
        List<Studentsign> studentsignList=studentsignService.getissignstudentsignById(studentsign);
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < studentsignList.size(); i++) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("signtime",studentsignList.get(i).getSigntime());
            jsonObject.put("getexp",studentsignList.get(i).getGetexp());
            jsonArray.add(jsonObject);
        }
        return Result.success().setData(jsonArray).setCode(ResultCodeEnum.OK.getCode()).setMsg("查看详细签到信息成功!");
    }
//    @ApiOperation(value = "查看班课内某学生详细签到信息")
//    @RequestMapping("/stusigndetail")
//    public Object stusigndetail(@RequestBody Studentsign studentsign){
//        List<Studentsign> studentsignList=studentsignService.getissignstudentsignById(studentsign);
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < studentsignList.size(); i++) {
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("signtime",studentsignList.get(i).getSigntime());
//            jsonObject.put("getexp",studentsignList.get(i).getGetexp());
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray;
//    }
}
