package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Course;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/24 16:10
 */
public interface CourseService {

    List<Course> findAllcourse();

    List<Course> getcoursebyTeachername(String teacherName);

    Course getcoursebyId(int courseId);

    int addcourse(Course course);

    int deletecoursebyId(int courseId);

    int updatecoursebyId(Course course);

    int getcountbyId(int courseId);
}
