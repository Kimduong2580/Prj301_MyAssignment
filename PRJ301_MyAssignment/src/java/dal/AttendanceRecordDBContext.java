/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.AttendanceRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Building;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Student;
import model.Time_slot;
import java.sql.Date;
import model.Subject;

/**
 *
 * @author Nguyen Kim Duong
 */
public class AttendanceRecordDBContext extends DBContext<AttendanceRecord> {

    public static void main(String[] args) {
        AttendanceRecordDBContext db = new AttendanceRecordDBContext();
        ArrayList<AttendanceRecord> list = db.getAttendanceRecordsBysIdAndsubIdAndseId("HE171819", "PRJ301", "sp24");
        System.out.println(list.get(14).getAttendance().getIsPresent());
    }

    public ArrayList<AttendanceRecord> getAttendanceRecordsBysIdAndsubIdAndseId(String studentId, String subjectId, String semesterId) {
        ArrayList<AttendanceRecord> attrs = new ArrayList<>();
        String sql = " select s.sesid, s.groupId, s.lecturerId, s.timeId, s.roomId, s.date, s.isTaken,\n"
                + " g.gname, g.subjectId,\n"
                + " e.sid,\n"
                + " r.semesterId,\n"
                + " ts.timeBegin, ts.timeEnd, \n"
                + " rm.buildingId, rm.number,\n"
                + " a.dateTime, a.description, a.isPresent, \n"
                + " r.status\n"
                + " from\n"
                + " Session s LEFT JOIN [Group] g ON s.groupId = g.gid \n"
                + " LEFT JOIN Enrollment e ON e.gid = g.gid\n"
                + " JOIN Registration r ON r.studentId = e.sid and r.subjectId = g.subjectId and r.groupId = g.gid\n"
                + " LEFT JOIN Attendance a ON a.sessionId = s.sesid and a.studentId = e.sid\n"
                + " JOIN Time_slot ts ON s.timeId = ts.tid\n"
                + " JOIN Room rm ON rm.rid = s.roomId\n"
                + " where g.subjectId = ? and e.sid = ? and semesterId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectId);
            stm.setString(2, studentId);
            stm.setString(3, semesterId);
            ResultSet rs = stm.executeQuery();
            Boolean status = null;
            int totalAbsent = 0;
            while (rs.next()) {
                AttendanceRecord attr = new AttendanceRecord();

                Session ses = new Session();
                ses.setId(rs.getString("sesid"));

                Room rm = new Room();
                rm.setId(rs.getString("timeId"));
                Building b = new Building();
                b.setId(rs.getString("buildingId"));
                rm.setBuilding(b);
                rm.setNumber(rs.getInt("number"));
                ses.setRoom(rm);

                Time_slot ts = new Time_slot();
                ts.setId(rs.getString("timeId"));
                ts.setTimeBegin(rs.getString("timeBegin"));
                ts.setTimeEnd(rs.getString("timeEnd"));
                ses.setTime_slot(ts);

                Lecturer l = new Lecturer();
                l.setId(rs.getString("lecturerId"));
                ses.setLecturer(l);

                Group g = new Group();
                g.setId(rs.getString("groupId"));
                g.setName(rs.getString("gname"));
                ses.setGroup(g);

                ses.setDate(rs.getDate("date"));
                ses.setIsTaken(rs.getBoolean("isTaken"));

                Attendance att = new Attendance();
                Student s = new Student();
                s.setId(rs.getString("sid"));
                att.setStudent(s);
                att.setSession(ses);
                att.setDescription(rs.getString("description"));
                Object isStatus = rs.getObject("status");
                status = (Boolean) isStatus;
                Object present = rs.getObject("isPresent");
                Boolean isPresent = (Boolean) present;
                if (isPresent != null && !isPresent) {
                    totalAbsent += 1;
                }
                att.setIsPresent(isPresent);
                attr.setSession(ses);
                attr.setAttendance(att);
                attrs.add(attr);
            }
            if (status == null) {
                RegistrationDBContext regis = new RegistrationDBContext();
                regis.updateTotalAbsent(studentId, semesterId, subjectId, totalAbsent);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AttendanceRecordDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return attrs;
    }

    public ArrayList<AttendanceRecord> getAttendanceRecordsBysIdAndFromDateAndToDate(String studentId, Date fromDate, Date toDate) {
        ArrayList<AttendanceRecord> attrs = new ArrayList<>();
        String sql = " select ses.sesid, ses.groupId, ses.lecturerId, ses.roomId, ses.timeId, ses.lecturerId, ses.isTaken, ses.date,\n"
                + "g.gname, g.subjectId, \n"
                + "r.number, r.buildingId,\n"
                + "ts.timeBegin, ts.timeEnd,\n"
                + "t.isPresent,\n"
                + "t.studentID\n"
                + "from Session ses \n"
                + "JOIN Room r ON ses.roomId = r.rid \n"
                + "JOIN Time_slot ts ON ts.tid = ses.timeId\n"
                + "JOIN [Group] g on ses.groupId = g.gid \n"
                + "JOIN Enrollment e ON e.gid = ses.groupId\n"
                + "LEFT JOIN (select * from Attendance a where a.studentId = ?) as t on t.sessionId = ses.sesid\n"
                + "where date >= ? and date <= ? and e.sid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            stm.setDate(2, fromDate);
            stm.setDate(3, toDate);
            stm.setString(4, studentId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AttendanceRecord attr = new AttendanceRecord();

                Session ses = new Session();
                ses.setId(rs.getString("sesid"));

                Room rm = new Room();
                rm.setId(rs.getString("timeId"));
                Building b = new Building();
                b.setId(rs.getString("buildingId"));
                rm.setBuilding(b);
                rm.setNumber(rs.getInt("number"));
                ses.setRoom(rm);

                Time_slot ts = new Time_slot();
                ts.setId(rs.getString("timeId"));
                ts.setTimeBegin(rs.getString("timeBegin"));
                ts.setTimeEnd(rs.getString("timeEnd"));
                ses.setTime_slot(ts);

                Lecturer l = new Lecturer();
                l.setId(rs.getString("lecturerId"));
                ses.setLecturer(l);

                Group g = new Group();
                g.setId(rs.getString("groupId"));
                g.setName(rs.getString("gname"));
                Subject sub = new Subject();
                sub.setId(rs.getString("subjectId"));
                g.setSubject(sub);
                ses.setGroup(g);

                ses.setDate(rs.getDate("date"));
                ses.setIsTaken(rs.getBoolean("isTaken"));

                Attendance att = new Attendance();
                Student s = new Student();
                s.setId(rs.getString("studentId"));
                att.setStudent(s);
                att.setSession(ses);
                
                Object present = rs.getObject("isPresent");
                Boolean isPresent = (Boolean) present;
                att.setIsPresent(isPresent);
                attr.setAttendance(att);
                attr.setSession(ses);
                attr.setAttendance(att);
                attrs.add(attr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceRecordDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return attrs;
    }

    @Override
    public ArrayList<AttendanceRecord> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(AttendanceRecord entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(AttendanceRecord entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(AttendanceRecord entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AttendanceRecord getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
