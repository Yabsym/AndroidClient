package com.riskm.androidclient.entity;

public class Course {
    private Integer courseID;
    private String courseName;

    public Course() {
    }

    public Course(Integer courseID) {
        this.courseID = courseID;
    }

    public Course(Integer courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
