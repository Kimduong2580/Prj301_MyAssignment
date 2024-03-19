/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;

/**
 *
 * @author Nguyen Kim Duong
 */
public class Session {
    private String id;
    private Group group;
    private Lecturer lecturer;
    private Room room;
    private Time_slot time_slot;
    private Date date;
    private Boolean isTaken;
    private Semester semester;

    public Session(String id, Group group, Lecturer lecturer, Room room, Time_slot time_slot, Date date, Boolean isTaken) {
        this.id = id;
        this.group = group;
        this.lecturer = lecturer;
        this.room = room;
        this.time_slot = time_slot;
        this.date = date;
        this.isTaken = isTaken;
    }

    public Session() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Time_slot getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(Time_slot time_slot) {
        this.time_slot = time_slot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(Boolean isTaken) {
        this.isTaken = isTaken;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

   
    
    
}
