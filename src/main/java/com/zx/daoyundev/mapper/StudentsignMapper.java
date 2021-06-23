package com.zx.daoyundev.mapper;

import com.zx.daoyundev.entity.Studentsign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentsignMapper {
    List<Studentsign> geistudentsignById(Studentsign studentsign);
    List<Studentsign> getissignstudentsignById(Studentsign studentsign);
    //List<Studentsign> geistudentsignByteacherId(int teachersignId);
    List<Studentsign> getissign(int teachersignId);
    List<Studentsign> getunsign(int teachersignId);
    int getissigncount(int teachersignId);
    int getunsigncount(int teachersignId);
    int addsign(Studentsign studentsign);
    int updatesign(Studentsign studentsign);
    int updateexp(Studentsign studentsign);
    //int deletesignByteachersignId(long teachersignId);无需单独设置删除接口，数据库已设置外键级联删除
}
