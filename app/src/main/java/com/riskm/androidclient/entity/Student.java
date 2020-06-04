package com.riskm.androidclient.entity;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Student {
    private String studentAccount;
    private String studentName;


    public Student(String studentAccount) {
        this.studentAccount = studentAccount;
    }

    public Student(String studentAccount, String studentName) {
        this.studentAccount = studentAccount;
        this.studentName = studentName;
    }

    public String getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount(String studentAccount) {
        this.studentAccount = studentAccount;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
