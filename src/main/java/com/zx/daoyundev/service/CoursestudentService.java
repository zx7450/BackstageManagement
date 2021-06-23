package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Coursestudent;
import com.zx.daoyundev.mapper.CoursestudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursestudentService {
    @Autowired
    CoursestudentMapper coursestudentMapper;
    public List<Coursestudent> getcoursestudentbycourseId(int courseId){ return coursestudentMapper.getcoursestudentbycourseId(courseId);}
    public int getcoursestudentCountbycourseId(int courseId){ return coursestudentMapper.getcoursestudentCountbycourseId(courseId);}
    public List<Coursestudent> getcoursestudentbystudentId(Integer studentId){ return coursestudentMapper.getcoursestudentbystudentId(studentId);}
    public Coursestudent getcoursestudentbystudentIdandcourseId(Coursestudent coursestudent){ return coursestudentMapper.getcoursestudentbystudentIdandcourseId(coursestudent);}
    public int addcoursestudent(Coursestudent coursestudent){ return  coursestudentMapper.addcoursestudent(coursestudent);}
    public int deletecoursestudentbyId(long id){ return coursestudentMapper.deletecoursestudentbyId(id);}
    public int testisjoin(Coursestudent coursestudent){ return coursestudentMapper.testisjoin(coursestudent);}
    public int updatetotalExp(Coursestudent coursestudent){ return coursestudentMapper.updatetotalExp(coursestudent);}
}
