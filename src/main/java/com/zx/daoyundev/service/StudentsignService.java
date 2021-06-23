package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Studentsign;
import com.zx.daoyundev.mapper.StudentsignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsignService {
    @Autowired
    StudentsignMapper studentsignMapper;
    public List<Studentsign> geistudentsignById(Studentsign studentsign){ return studentsignMapper.geistudentsignById(studentsign);}
    public List<Studentsign> getissignstudentsignById(Studentsign studentsign){return studentsignMapper.getissignstudentsignById(studentsign);}
    public List<Studentsign> getissign(int teachersignId){ return  studentsignMapper.getissign(teachersignId);}
    public List<Studentsign> getunsign(int teachersignId){ return studentsignMapper.getunsign(teachersignId);}
    public int getissigncount(int teachersignId){ return studentsignMapper.getissigncount(teachersignId);}
    public int getunsigncount(int teachersignId){ return studentsignMapper.getunsigncount(teachersignId);   }
    public int addsign(Studentsign studentsign){ return studentsignMapper.addsign(studentsign);}
    public int updatesign(Studentsign studentsign){ return studentsignMapper.updatesign(studentsign);}
    public int updateexp(Studentsign studentsign){ return studentsignMapper.updateexp(studentsign);}
    //public int deletesignByteachersignId(long teachersignId){ return studentsignMapper.deletesignByteachersignId(teachersignId);}
}
