package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Course;
import com.zx.daoyundev.mapper.CourseMapper;
import com.zx.daoyundev.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> findAllcourse() {
        return courseMapper.findAllcourse();
    }

    @Override
    public List<Course> getcoursebyTeachername(String teacherName) {
        return courseMapper.getcoursebyTeachername(teacherName);
    }

    @Override
    public Course getcoursebyId(int courseId) {
        return courseMapper.getcoursebyId(courseId);
    }

    @Override
    public int addcourse(Course course) {
        courseMapper.addcourse(course);
        return course.getCourseId();
    }

    @Override
    public int deletecoursebyId(int courseId) {
        return courseMapper.deletecoursebyId(courseId);
    }

    @Override
    public int updatecoursebyId(Course course) {
        return courseMapper.updatecoursebyId(course);
    }

    @Override
    public int getcountbyId(int courseId) {
        return courseMapper.getcountbyId(courseId);
    }
}
