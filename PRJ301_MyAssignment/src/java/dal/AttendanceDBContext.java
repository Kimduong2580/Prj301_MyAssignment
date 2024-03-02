/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Attendance;
import model.Session;
import model.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Kim Duong
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public static void main(String[] args) {
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        ArrayList<Attendance> list = attendanceDB.getListByStudentIdAndSessionId("s33");
        System.out.println(list.get(0).getDescription());
        attendanceDB.update("s33", "HE171819", false, "abcd");
    }

    public void insert(String sessionId, String studentId, Boolean isPresent, String description) {
        String sql = "INSERT INTO [dbo].[Attendance]\n"
                + "           ([sessionId]\n"
                + "           ,[studentId]\n"
                + "           ,[isPresent]\n"
                + "           ,[description]\n"
                + "           ,[dateTime])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE())\n";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sessionId);
            stm.setString(2, studentId);
            stm.setBoolean(3, isPresent);
            stm.setString(4, description);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Attendance> getListByStudentIdAndSessionId(String sessionId) {
        ArrayList<Attendance> list = new ArrayList<>();
        String sql = "select * from Attendance a JOIN Session s ON a.sessionId = s.sesid where s.sesid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sessionId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                Session session = new Session();
                session.setId(sessionId);
                att.setSession(session);

                Student student = new Student();
                student.setId(rs.getString("studentId"));
                att.setStudent(student);
                if (rs.wasNull()) {
                    att.setIsPresent(null);
                } else {
                    Boolean isPresent = rs.getBoolean("isPresent");
                    att.setIsPresent(isPresent);
                }
                att.setDescription(rs.getString("description"));
                list.add(att);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void update(String sessionId, String studentId, Boolean isPresent, String desciption) {
        String sql = "UPDATE [dbo].[Attendance]\n"
                + "   SET \n"
                + "      [isPresent] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[dateTime] = GETDATE()\n"
                + " WHERE studentId = ? and sessionId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, isPresent);
            stm.setString(2, desciption);
            stm.setString(3, studentId);
            stm.setString(4, sessionId);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
