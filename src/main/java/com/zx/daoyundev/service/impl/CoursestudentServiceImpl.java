package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Coursestudent;
import com.zx.daoyundev.mapper.CoursestudentMapper;
import com.zx.daoyundev.service.CoursestudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursestudentServiceImpl implements CoursestudentService {
    @Autowired
    CoursestudentMapper coursestudentMapper;

    @Override
    public List<Coursestudent> getcoursestudentbycourseId(int courseId) {
        return coursestudentMapper.getcoursestudentbycourseId(courseId);
    }

    @Override
    public int getcoursestudentCountbycourseId(int courseId) {
        return coursestudentMapper.getcoursestudentCountbycourseId(courseId);
    }

    @Override
    public List<Coursestudent> getcoursestudentbystudentId(Integer studentId) {
        return coursestudentMapper.getcoursestudentbystudentId(studentId);
    }

    @Override
    public Coursestudent getcoursestudentbystudentIdandcourseId(Coursestudent coursestudent) {
        return coursestudentMapper.getcoursestudentbystudentIdandcourseId(coursestudent);
    }

    @Override
    public int addcoursestudent(Coursestudent coursestudent) {
        return coursestudentMapper.addcoursestudent(coursestudent);
    }

    @Override
    public int deletecoursestudentbyId(long id) {
        return coursestudentMapper.deletecoursestudentbyId(id);
    }

    @Override
    public int testisjoin(Coursestudent coursestudent) {
        return coursestudentMapper.testisjoin(coursestudent);
    }

    @Override
    public int updatetotalExp(Coursestudent coursestudent) {
        return coursestudentMapper.updatetotalExp(coursestudent);
    }
}
