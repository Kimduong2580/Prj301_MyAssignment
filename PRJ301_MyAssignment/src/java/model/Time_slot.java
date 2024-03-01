/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Kim Duong
 */
public class Time_slot {
    private String id;
    private String timeBegin;
    private String timeEnd;

    public Time_slot(String id, String timeBegin, String timeEnd) {
        this.id = id;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }

    public Time_slot() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
    
     
}
