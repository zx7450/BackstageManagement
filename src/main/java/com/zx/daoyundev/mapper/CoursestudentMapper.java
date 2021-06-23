package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Course;
import com.zx.daoyundev.entity.Coursestudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CoursestudentMapper {
    List<Coursestudent> getcoursestudentbycourseId(int courseId);
    int getcoursestudentCountbycourseId(int courseId);
    List<Coursestudent> getcoursestudentbystudentId(Integer studentId);
    Coursestudent getcoursestudentbystudentIdandcourseId(Coursestudent coursestudent);
    int addcoursestudent(Coursestudent coursestudent);
    int deletecoursestudentbyId(long id);
    int testisjoin(Coursestudent coursestudent);
    int updatetotalExp(Coursestudent coursestudent);
}
