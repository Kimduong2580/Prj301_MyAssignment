/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.AcademicRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assessment;
import model.Exam;
import model.Grade;
import model.Registration;
import model.Student;

/**
 *
 * @author Nguyen Kim Duong
 */
public class AcademicRecordDBContext extends DBContext<AcademicRecord> {
    public static void main(String[] args) {
        AcademicRecordDBContext acaDB = new AcademicRecordDBContext();
        ArrayList<AcademicRecord> list = acaDB.getAcademicRecordByStudentId("HE171819");
        for (AcademicRecord a : list) {
            Assessment a1 = a.getGrade().getExam().getAssessment();
            System.out.println("reid: " + a.getRegistration().getId() + " Subject: " + a.getRegistration().getId() + " assname: " + a1.getName() + " weight: " + a1.getWeight() + " score: " + a.getGrade().getScore());
        }
    }

    public ArrayList<AcademicRecord> getAcademicRecordByStudentId(String studentId) {
        ArrayList<AcademicRecord> academicRecords = new ArrayList<>();
        String sql = "select *\n"
                + "from Assessment ass JOIN\n"
                + "Exam e ON e.assessmentId = ass.assid \n"
                + "JOIN Grade g ON g.examId = e.eid \n"
                + "JOIN Student s ON g.studentId = s.sid \n"
                + "JOIN Registration r ON r.studentId = s.sid and r.subjectId = ass.subjectId\n"
                + "WHERE s.sid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                AcademicRecord aca = new AcademicRecord();
                
                Registration r = new Registration();
                r.setId(rs.getString("reid"));
                aca.setRegistration(r);
                
                Grade grade = new Grade();
                grade.setId(rs.getString("gdid"));
                grade.setScore(rs.getDouble("score"));
                Student s = new Student();
                s.setId(rs.getString("studentId"));
                s.setName(rs.getString("name"));
                s.setSex(rs.getBoolean("sex"));
                grade.setStudent(s);
                
                Exam e = new Exam();
                e.setCriteria(rs.getInt("criteria"));
                Assessment ass = new Assessment();
                ass.setId(rs.getString("assid"));
                ass.setName(rs.getString("assname"));
                ass.setWeight(rs.getInt("weight"));
                ass.setCategory(rs.getString("category"));
                e.setAssessment(ass);
                grade.setExam(e);
                
                aca.setGrade(grade);
                academicRecords.add(aca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcademicRecordDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return academicRecords;
    }

    @Override
    public ArrayList<AcademicRecord> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(AcademicRecord entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(AcademicRecord entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(AcademicRecord entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AcademicRecord getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
