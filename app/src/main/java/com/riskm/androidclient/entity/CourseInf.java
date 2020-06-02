package com.riskm.androidclient.entity;

public class CourseInf {
    private Integer courseID;
    private String studentAccount;
    private String studentName;
    private String courseName;
    private Integer courseInfID;
    public CourseInf(Integer courseInfID, String studentAccount) {
        this.studentAccount = studentAccount;
        this.courseInfID = courseInfID;
    }

    public CourseInf(Integer courseID, String studentAccount, String studentName, String courseName, Integer courseInfID) {
        this.courseID = courseID;
        this.studentAccount = studentAccount;
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseInfID = courseInfID;
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
