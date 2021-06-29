package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Coursestudent;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/24 16:15
 */
public interface CoursestudentService {
    List<Coursestudent> getcoursestudentbycourseId(int courseId);

    int getcoursestudentCountbycourseId(int courseId);

    List<Coursestudent> getcoursestudentbystudentId(Integer studentId);

    Coursestudent getcoursestudentbystudentIdandcourseId(Coursestudent coursestudent);

    int addcoursestudent(Coursestudent coursestudent);

    int deletecoursestudentbyId(Coursestudent coursestudent);

    int testisjoin(Coursestudent coursestudent);

    int updatetotalExp(Coursestudent coursestudent);
}
