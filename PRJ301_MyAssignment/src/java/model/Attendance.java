/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Nguyen Kim Duong
 */
public class Attendance {
    private Session session;
    private Student student;
    private Boolean isPresent;
    private String description;
    private Date datetime;

    public Attendance() {
    }

    public Attendance(Session session, Student student, Boolean isPresent, String description, Date datetime) {
        this.session = session;
        this.student = student;
        this.isPresent = isPresent;
        this.description = description;
        this.datetime = datetime;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    
}
