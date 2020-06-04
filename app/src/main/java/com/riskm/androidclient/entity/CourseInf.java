package com.riskm.androidclient.entity;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseInf {
    private Integer courseID;
    private String studentAccount;
    private String studentName;
    private String courseName;
    private Integer courseInfID;
    private String score;

    public static List<CourseInf> conventCourseInf(List<String> data){
        List<CourseInf> retValue = new ArrayList<>();
        for (String dat :
                data) {
            Map map = JSON.parseObject(dat);
            retValue.add(new CourseInf(Integer.parseInt(map.get("courseID").toString()),
                    map.get("studentAccount").toString(),
                    map.get("studentName").toString(),
                    map.get("courseName").toString(),
                    Integer.parseInt(map.get("courseInfID").toString()),map.get("score").toString()));
        }
        return retValue;
    }

    public CourseInf(Integer courseInfID, String studentAccount) {
        this.studentAccount = studentAccount;
        this.courseInfID = courseInfID;
    }

    public CourseInf(Integer courseID, String studentAccount, String studentName, String courseName, Integer courseInfID,String score) {
        this.courseID = courseID;
        this.studentAccount = studentAccount;
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseInfID = courseInfID;
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseInfID() {
        return courseInfID;
    }

    public void setCourseInfID(Integer courseInfID) {
        this.courseInfID = courseInfID;
    }
}
