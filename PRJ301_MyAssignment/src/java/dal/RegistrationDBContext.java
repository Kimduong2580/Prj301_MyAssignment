/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Lecturer;
import model.Registration;
import model.Semester;
import model.Student;

/**
 *
 * @author Nguyen Kim Duong
 */
public class RegistrationDBContext extends DBContext<Registration> {

    public static void main(String[] args) {
        RegistrationDBContext reDB = new RegistrationDBContext();
        ArrayList<Registration> list = reDB.getRegistrationByStudentIdAndSemesterId("HE171819", "fa23");
        System.out.println(list.size());
    }

    public ArrayList<Registration> getRegistrationByStudentIdAndSemesterId(String studentId, String semesterId) {
        ArrayList<Registration> registrations = new ArrayList<>();
        String sql = "select * from Registration r JOIN Semester s ON r.semesterId = s.seId \n"
                + "JOIN Subject sub ON sub.subid = r.subjectId JOIN [Group] g ON g.gid = r.groupId \n"
                + "Where r.studentId = ? and r.semesterId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            stm.setString(2, semesterId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration regis = new Registration();
                
                Subject sub = new Subject();
                sub.setName(rs.getString("subname"));
                sub.setId(rs.getString("subjectId"));
                sub.setCredit(rs.getInt("credit"));
                regis.setSubject(sub);

                Semester se = new Semester();
                se.setId(rs.getString("semesterId"));
                se.setName(rs.getString("sename"));
                se.setYear(rs.getInt("year"));
                regis.setSemester(se);
                
                Group g = new Group();
                g.setId(rs.getString("groupId"));
                g.setName(rs.getString("gname"));
                
                registrations.add(regis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return registrations;
    }
    
    public ArrayList<Registration> getRegistrationByStudentId(String studentId) {
        ArrayList<Registration> registrations = new ArrayList<>();
        String sql = "select * from Registration r \n"
                + "JOIN Subject sub ON r.subjectId = sub.subid \n"
                + "JOIN Semester se ON se.seId = r.semesterId\n"
                + "JOIN Student s ON s.sid = r.studentId JOIN [Group] g ON g.gid = r.groupId\n"
                + "where r.studentId = ?\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration regis = new Registration();
                
                Subject sub = new Subject();
                sub.setName(rs.getString("subname"));
                sub.setId(rs.getString("subjectId"));
                sub.setCredit(rs.getInt("credit"));
                regis.setSubject(sub);

                Semester se = new Semester();
                se.setId(rs.getString("semesterId"));
                se.setName(rs.getString("sename"));
                se.setYear(rs.getInt("year"));
                regis.setSemester(se);

                Student s = new Student();
                s.setId(rs.getString("sid"));
                s.setName(rs.getString("name"));
                s.setSex(rs.getBoolean("sex"));
                regis.setStudent(s);
                
                Group g = new Group();
                g.setId(rs.getString("groupId"));
                g.setName(rs.getString("gname"));
                
                Lecturer l = new Lecturer();
                l.setId(rs.getString("lid"));
                g.setLecturer(l);
                regis.setGroup(g);
                regis.setId(rs.getString("reid"));
                
                registrations.add(regis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return registrations;
    }

    @Override
    public void insert(Registration entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Registration entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Registration entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Registration> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Registration getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
