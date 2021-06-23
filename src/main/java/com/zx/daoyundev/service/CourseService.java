package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Course;
import com.zx.daoyundev.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;
    public List<Course> findAllcourse(){return courseMapper.findAllcourse();}
    public List<Course> getcoursebyTeachername(String teacherName){return courseMapper.getcoursebyTeachername(teacherName);}
    public Course getcoursebyId(int courseId){ return courseMapper.getcoursebyId(courseId);}
    public int addcourse(Course course){
        courseMapper.addcourse(course);
        return course.getCourseId();
    }
    public int deletecoursebyId(int courseId){ return courseMapper.deletecoursebyId(courseId);}
    public int updatecoursebyId(Course course){ return courseMapper.updatecoursebyId(course);}
    public int getcountbyId(int courseId){ return courseMapper.getcountbyId(courseId);}
}
