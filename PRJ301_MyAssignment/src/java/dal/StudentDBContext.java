/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author Nguyen Kim Duong
 */
public class StudentDBContext extends DBContext<Student>{
    public static void main(String[] args) {
        StudentDBContext db = new StudentDBContext();
        ArrayList<Student> list = db.listStudentBygId("g1");
        System.out.println(list.size());
    }
    
    public ArrayList<Student> listStudentBygId(String gId) {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "select s.sid, s.name, s.sex, e.gid from Enrollment e JOIN Student s ON e.sid = s.sid where gid = ?";
        try {
            PreparedStatement stm =  connection.prepareStatement(sql);
            stm.setString(1, gId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("sid"));
                s.setName(rs.getString("name"));
                s.setSex(rs.getBoolean("sex"));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return students;
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
