package com.zx.daoyundev.service.impl;

import com.zx.daoyundev.entity.Studentsign;
import com.zx.daoyundev.mapper.StudentsignMapper;
import com.zx.daoyundev.service.StudentsignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsignServiceImpl implements StudentsignService {
    @Autowired
    StudentsignMapper studentsignMapper;

    @Override
    public List<Studentsign> geistudentsignById(Studentsign studentsign) {
        return studentsignMapper.geistudentsignById(studentsign);
    }

    @Override
    public List<Studentsign> getissignstudentsignById(Studentsign studentsign) {
        return studentsignMapper.getissignstudentsignById(studentsign);
    }

    @Override
    public List<Studentsign> getissign(int teachersignId) {
        return studentsignMapper.getissign(teachersignId);
    }

    @Override
    public List<Studentsign> getunsign(int teachersignId) {
        return studentsignMapper.getunsign(teachersignId);
    }

    @Override
    public int getissigncount(int teachersignId) {
        return studentsignMapper.getissigncount(teachersignId);
    }

    @Override
    public int getunsigncount(int teachersignId) {
        return studentsignMapper.getunsigncount(teachersignId);
    }

    @Override
    public int addsign(Studentsign studentsign) {
        return studentsignMapper.addsign(studentsign);
    }

    @Override
    public int updatesign(Studentsign studentsign) {
        return studentsignMapper.updatesign(studentsign);
    }

    @Override
    public int updateexp(Studentsign studentsign) {
        return studentsignMapper.updateexp(studentsign);
    }
    //public int deletesignByteachersignId(long teachersignId){ return studentsignMapper.deletesignByteachersignId(teachersignId);}
}
