/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Kim Duong
 */
public class GradingSystem {
    private Assessment assessment;
    private Exam exam;
    private Grade grade;

    public GradingSystem() {
    }

    public GradingSystem(Assessment assessment, Exam exam, Grade grade) {
        this.assessment = assessment;
        this.exam = exam;
        this.grade = grade;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    
    
}
