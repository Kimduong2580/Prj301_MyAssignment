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
        Attendance att = attendanceDB.getAttendanceBySessionIdAndStudentId("s79", "HE171819");
        System.out.println(att.getIsPresent());
//        attendanceDB.update("s33", "HE171819", false, "abcd");
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

    public Attendance getAttendanceBySessionIdAndStudentId(String sessionId, String studentId) {
        String sql = "select * from Attendance a where a.sessionId = ? and studentId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sessionId);
            stm.setString(2, studentId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                Session session = new Session();
                session.setId(sessionId);
                att.setSession(session);

                Student student = new Student();
                student.setId(rs.getString("studentId"));
                att.setStudent(student);
                Object object = rs.getObject("isPresent");
                Boolean isPresent = (Boolean) object;
                att.setIsPresent(isPresent);
                att.setDescription(rs.getString("description"));
                return att;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Attendance> getAttendancesBySessionId(String sessionId) {
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
                
                Object object = rs.getObject("isPresent");
                Boolean isPresent = (Boolean) object;
                att.setIsPresent(isPresent);
                att.setDescription(rs.getString("description"));
                list.add(att);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void update(String sessionId, String studentId, String subjectId, String semesterId, Boolean isPresent, String desciption) {
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
            Attendance att = getAttendanceBySessionIdAndStudentId(sessionId, studentId);
            System.out.println("att: " + att);
            if (att != null) {
                RegistrationDBContext regis = new RegistrationDBContext();
                if (att.getIsPresent() == false && isPresent) {
                    System.out.println("vao 1");
                    int totalAbsent = regis.getTotalAbsent(studentId, semesterId, subjectId);
                    regis.updateTotalAbsent(studentId, semesterId, subjectId, totalAbsent - 1);
                }
                if (att.getIsPresent() == true && isPresent == false) {
                    System.out.println("subject: " + subjectId);
                    System.out.println("vao 2");
                    int totalAbsent = regis.getTotalAbsent(studentId, semesterId, subjectId);
                    System.out.println("total 1:" + totalAbsent);
                    regis.updateTotalAbsent(studentId, semesterId, subjectId, totalAbsent + 1);
                }
            }
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
