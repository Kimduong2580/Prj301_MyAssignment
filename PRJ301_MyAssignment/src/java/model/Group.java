/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Nguyen Kim Duong
 */
public class Group {
    private String id;
    private String name;
    private Lecturer lecturer;
    private Subject subject;
    private ArrayList<Student> students;

    public Group() {
    }

    public Group(String id, String name, Lecturer lecturer, Subject subject, ArrayList<Student> students) {
        this.id = id;
        this.name = name;
        this.lecturer = lecturer;
        this.subject = subject;
        this.students = students;
    }

   

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    
}
