package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {
    List<Course> findAllcourse();

    List<Course> getcoursebyTeachername(String teacherName);

    List<Course> getcoursebyCoursename(String courseName);

    Course getcoursebyId(int courseId);

    int addcourse(Course course);

    int deletecoursebyId(int courseId);

    int updatecoursebyId(Course course);

    int getcountbyId(int courseId);
}
