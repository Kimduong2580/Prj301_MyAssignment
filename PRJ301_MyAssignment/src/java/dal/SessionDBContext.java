/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Session;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Building;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Semester;
import model.Subject;
import model.Time_slot;

/**
 *
 * @author Nguyen Kim Duong
 */
public class SessionDBContext extends DBContext<Session> {

    public static void main(String[] args) {
        SessionDBContext db = new SessionDBContext();
        ArrayList<Session> list = db.getSessionByDateAndStudentId(Date.valueOf("2024-2-26"), Date.valueOf("2024-3-3"), "HE171819");
        System.out.println(list.size());
        int total = db.totalSessionBySubjectIdAndSemester("PRJ301", "sp24");
        System.out.println(total);
//        Session s = db.getSesionBysesId("s104");
//        System.out.println(s.getGroup().getId());
    }

    public ArrayList<Session> getSessionByDateAndStudentId(Date fromDate, Date toDate, String studentId) {
        ArrayList<Session> sessions = new ArrayList<>();
        String sql = "SELECT s.sesid, s.groupId, s.lecturerId, s.roomId, s.timeId, s.date as sessionDate, s.isTaken, \n"
                + "		g.gname as gname, g.subjectId,\n"
                + "		l.lid as lecturerId, l.lname as lname, l.sex,\n"
                + "		r.number, r.buildingId,\n"
                + "		sub.subname as subName, sub.credit,\n"
                + "		t.timeBegin, t.timeEnd\n"
                + "FROM [Session] AS s INNER JOIN\n"
                + "                 \n"
                + "                  [Group] AS g ON s.groupId = g.gid INNER JOIN\n"
                + "                  Lecturer AS l ON s.lecturerId = l.lid AND g.lid = l.lid INNER JOIN\n"
                + "                  Room AS r ON s.roomId = r.rid INNER JOIN\n"
                + "                  Subject AS sub ON g.subjectId = sub.subid INNER JOIN\n"
                + "                  Time_slot AS t ON s.timeId = t.tid where date >= ? \n"
                + "                  and date <= ? and g.gid IN \n"
                + "                  (select e.gid from Enrollment e JOIN [Group] g \n"
                + "                 ON e.gid = g.gid Where e.sid = ?)";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, fromDate);
            stm.setDate(2, toDate);
            stm.setString(3, studentId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();

                s.setId(rs.getString("sesid"));

                Group g = new Group();
                g.setId(rs.getString("groupId"));
                g.setName(rs.getString("gname"));
                Subject sub = new Subject();
                sub.setId(rs.getString("subjectId"));
                g.setSubject(sub);

                Lecturer lecturer = new Lecturer();
                lecturer.setId(rs.getString("lecturerId"));
                lecturer.setName(rs.getString("lname"));
                lecturer.setSex(rs.getBoolean("sex"));
                g.setLecturer(lecturer);

                Room r = new Room();
                r.setId(rs.getString("roomId"));
                r.setNumber(rs.getInt("number"));
                Building b = new Building();
                b.setId(rs.getString("buildingId"));
                r.setBuilding(b);

                Time_slot t = new Time_slot();
                t.setId(rs.getString("timeId"));
                t.setTimeBegin(rs.getString("timeBegin"));
                t.setTimeEnd(rs.getString("timeEnd"));
                s.setDate(rs.getDate("sessionDate"));
                Object object = rs.getObject("isTaken");
                Boolean isTaken = (Boolean) object;
                s.setIsTaken(isTaken);
                s.setGroup(g);
                s.setLecturer(lecturer);
                s.setRoom(r);
                s.setTime_slot(t);
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessions;
    }

    public ArrayList<Session> getSessionByDateAndLecturerId(Date fromDate, Date toDate, String lid) {
        ArrayList<Session> sessions = new ArrayList<>();
        String sql = "SELECT s.sesid, s.groupId, s.lecturerId, s.roomId, s.timeId, s.date as sessionDate, s.isTaken, \n"
                + "		g.gname as gname, g.subjectId,\n"
                + "		l.lid as lecturerId, l.lname as lname, l.sex,\n"
                + "		r.number, r.buildingId,\n"
                + "		sub.subname as subName, sub.credit,\n"
                + "		t.timeBegin, t.timeEnd\n"
                + "FROM [Session] AS s INNER JOIN\n"
                + "                 \n"
                + "                  [Group] AS g ON s.groupId = g.gid INNER JOIN\n"
                + "                  Lecturer AS l ON s.lecturerId = l.lid AND g.lid = l.lid INNER JOIN\n"
                + "                  Room AS r ON s.roomId = r.rid INNER JOIN\n"
                + "                  Subject AS sub ON g.subjectId = sub.subid INNER JOIN\n"
                + "                  Time_slot AS t ON s.timeId = t.tid where date >= ? and date <= ? ";
        if (lid != null) {
            sql += "and l.lid = ?";
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, fromDate);
            stm.setDate(2, toDate);
            if (lid != null) {
                stm.setString(3, lid);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();

                s.setId(rs.getString("sesid"));

                Group g = new Group();
                g.setId(rs.getString("groupId"));
                g.setName(rs.getString("gname"));
                Subject sub = new Subject();
                sub.setId(rs.getString("subjectId"));
                g.setSubject(sub);

                Lecturer lecturer = new Lecturer();
                lecturer.setId(rs.getString("lecturerId"));
                lecturer.setName(rs.getString("lname"));
                lecturer.setSex(rs.getBoolean("sex"));
                g.setLecturer(lecturer);

                Room r = new Room();
                r.setId(rs.getString("roomId"));
                r.setNumber(rs.getInt("number"));
                Building b = new Building();
                b.setId(rs.getString("buildingId"));
                r.setBuilding(b);

                Time_slot t = new Time_slot();
                t.setId(rs.getString("timeId"));
                t.setTimeBegin(rs.getString("timeBegin"));
                t.setTimeEnd(rs.getString("timeEnd"));
                s.setDate(rs.getDate("sessionDate"));
                Object object = rs.getObject("isTaken");
                Boolean isTaken = (Boolean) object;
                s.setIsTaken(isTaken);
                s.setGroup(g);
                s.setLecturer(lecturer);
                s.setRoom(r);
                s.setTime_slot(t);
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessions;
    }

    public Session getSesionBysesId(String id) {
        Session s = new Session();
        String sql = "select * from Session s JOIN [Group] g on s.groupId = g.gid where sesid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s.setId(rs.getString("sesid"));
                Group g = new Group();
                g.setId(rs.getString("groupId"));
                g.setName(rs.getString("gname"));
                Subject sub = new Subject();
                sub.setId(rs.getString("subjectId"));
                g.setSubject(sub);
                s.setGroup(g);
                
                Semester se = new Semester();
                se.setId(rs.getString("seId"));
                s.setSemester(se);

                Lecturer l = new Lecturer();
                l.setId(rs.getString("lecturerId"));
                s.setLecturer(l);

                Room r = new Room();
                r.setId(rs.getString("roomId"));
                s.setRoom(r);

                s.setDate(rs.getDate("date"));
                Object object = rs.getObject("isTaken");
                Boolean isTaken = (Boolean) object;
                s.setIsTaken(isTaken);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;
    }

    public void updateIsTaken(String sessionId) {
        String sql = "UPDATE [dbo].[Session]\n"
                + "   SET [isTaken] = 1\n"
                + " WHERE sesid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sessionId);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int totalSessionBySubjectIdAndSemester(String subjectId, String semesterId) {
        String sql = "select COUNT(*) as Number_session from Session s "
                + "JOIN [Group] g ON s.groupId = g.gid "
                + "where g.subjectId = ? and s.seId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectId);
            stm.setString(2, semesterId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                int total = rs.getInt("Number_session");
                return total;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
