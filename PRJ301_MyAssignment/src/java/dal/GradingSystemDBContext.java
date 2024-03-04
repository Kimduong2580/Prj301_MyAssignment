/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assessment;
import model.Exam;
import model.Grade;
import model.GradingSystem;
import model.Student;
import model.Subject;

/**
 *
 * @author Nguyen Kim Duong
 */
public class GradingSystemDBContext extends DBContext<GradingSystem> {
    public static void main(String[] args) {
        GradingSystemDBContext db = new GradingSystemDBContext();
        ArrayList<GradingSystem> gss = db.getGradingSystemsBysidAndSubidAndSeId("HE171819", "fa23", "DBI202");
        System.out.println(gss.size());
    }

    public ArrayList<GradingSystem> getGradingSystemsBysidAndSubidAndSeId(String studentId, String semesterId, String subjectId) {
        ArrayList<GradingSystem> gss = new ArrayList<>();
        String sql = " select g.gdid, g.examId, g.studentId, g.semesterId, g.score,\n"
                + " e.assessmentId, e.criteria, e.dateBegin, e.eid, e.time,\n"
                + " ass.assName, ass.category, ass.subjectId, ass.weight, \n"
                + " r.groupId\n"
                + " from Grade g JOIN Exam e ON g.examId = e.eid \n"
                + " JOIN Assessment ass ON ass.assid = e.assessmentId \n"
                + " JOIN Student s ON s.sid = g.studentId\n"
                + " JOIN Registration r ON r.studentId = s.sid AND r.semesterId = g.semesterId and r.subjectId = ass.subjectId\n"
                + " where ass.subjectId = ? and g.studentId = ? and g.semesterId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectId);
            stm.setString(2, studentId);
            stm.setString(3, semesterId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                GradingSystem gs = new GradingSystem();
                Assessment ass = new Assessment();
                ass.setId(rs.getString("assessmentId"));
                ass.setName(rs.getString("assName"));
                ass.setCategory(rs.getString("category"));
                ass.setWeight(rs.getInt("weight"));
                Subject sub = new Subject();
                sub.setId(rs.getString("subjectId"));
                ass.setSubject(sub);
                gs.setAssessment(ass);
                
                Exam e = new Exam();
                e.setAssessment(ass);
                e.setCriteria(rs.getInt("criteria"));
                e.setDateBegin(rs.getDate("dateBegin"));
                e.setTime(rs.getString("time"));
                gs.setExam(e);
                
                Grade g = new Grade();
                g.setExam(e);
                g.setScore(rs.getDouble("score"));
                g.setId(rs.getString("gdid"));
                Student s = new Student();
                s.setId(rs.getString("studentId"));
                g.setStudent(s);
                gs.setGrade(g);
                
                gss.add(gs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradingSystemDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return gss;
    }

    @Override
    public ArrayList<GradingSystem> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(GradingSystem entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(GradingSystem entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(GradingSystem entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GradingSystem getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
