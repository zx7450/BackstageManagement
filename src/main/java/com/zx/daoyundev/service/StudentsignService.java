package com.zx.daoyundev.service;

import com.zx.daoyundev.entity.Studentsign;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/24 16:36
 */
public interface StudentsignService {
    List<Studentsign> geistudentsignById(Studentsign studentsign);

    List<Studentsign> getissignstudentsignById(Studentsign studentsign);

    List<Studentsign> getissign(int teachersignId);

    List<Studentsign> getunsign(int teachersignId);

    int getissigncount(int teachersignId);

    int getunsigncount(int teachersignId);

    int addsign(Studentsign studentsign);

    int updatesign(Studentsign studentsign);

    int updateexp(Studentsign studentsign);
}
