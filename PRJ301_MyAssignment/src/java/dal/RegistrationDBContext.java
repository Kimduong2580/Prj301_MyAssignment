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
import model.GradingSystem;
import model.Group;
import model.Lecturer;
import model.Registration;
import model.Semester;
import model.Student;
import util.CalculatorAverageMark;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Nguyen Kim Duong
 */
public class RegistrationDBContext extends DBContext<Registration> {

    public static void main(String[] args) {
        RegistrationDBContext reDB = new RegistrationDBContext();
        ArrayList<Registration> list = reDB.getRegistrationByStudentId("HE171819");
//        System.out.println(list.get(0).getGroup().getName());
        for (Registration r : list) {
            System.out.println(r.getAverageMark());
        }
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
                regis.setGroup(g);

                Date dateBegin = rs.getDate("dateBegin");
                Date dateEnd = rs.getDate("dateEnd");
                regis.setDateBegin(dateBegin);
                regis.setDateEnd(dateEnd);
                Double averageMark = (Double) rs.getObject("averageMark");
                LocalDate currentDate = LocalDate.now();
                Date current_date = Date.valueOf(currentDate);
                //neu averagemark == null hoac dang trong thoi gian hoc thi se tinh diem tb va cap nhap status
                if (averageMark == null || (current_date.compareTo(dateBegin) >= 0 && current_date.compareTo(dateEnd) <= 0)) {
                    CalculatorAverageMark calculator = new CalculatorAverageMark();
                    GradingSystemDBContext gradingDB = new GradingSystemDBContext();
                    ArrayList<GradingSystem> gss = gradingDB.getGradingSystemsBysidAndSubidAndSeId(studentId, semesterId, sub.getId());
                    averageMark = calculator.getAverageMark(gss);
                    Boolean status = null;
                    if (current_date.compareTo(dateBegin) >= 0 && current_date.compareTo(dateEnd) <= 0) {
                        status = null;
                    } else if (current_date.compareTo(dateEnd) > 0) {
                        if (averageMark >= 5) {
                            status = true;
                        } else {
                            status = false;
                        }
                    }
                    updateAverageMarkAndStatus(averageMark, status, studentId, semesterId, sub.getId());
                    regis.setAverageMark(averageMark);
                    regis.setStatus(status);
                } else {
                    regis.setAverageMark(rs.getDouble("averageMark"));
                    regis.setStatus(rs.getBoolean("status"));
                }
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
                
                Date dateBegin = rs.getDate("dateBegin");
                Date dateEnd = rs.getDate("dateEnd");
                regis.setDateBegin(dateBegin);
                regis.setDateEnd(dateEnd);
                Double averageMark = (Double) rs.getObject("averageMark");
                LocalDate currentDate = LocalDate.now();
                Date current_date = Date.valueOf(currentDate);
                //neu averagemark == null hoac dang trong thoi gian hoc thi se tinh diem tb va cap nhap status
                if (averageMark == null || (current_date.compareTo(dateBegin) >= 0 && current_date.compareTo(dateEnd) <= 0)) {
                    CalculatorAverageMark calculator = new CalculatorAverageMark();
                    GradingSystemDBContext gradingDB = new GradingSystemDBContext();
                    ArrayList<GradingSystem> gss = gradingDB.getGradingSystemsBysidAndSubidAndSeId(studentId, se.getId(), sub.getId());
                    averageMark = calculator.getAverageMark(gss);
                    Boolean status = null;
                    if (current_date.compareTo(dateBegin) >= 0 && current_date.compareTo(dateEnd) <= 0) {
                        status = null;
                    } else if (current_date.compareTo(dateEnd) > 0) {
                        if (averageMark >= 5) {
                            status = true;
                        } else {
                            status = false;
                        }
                    }
                    updateAverageMarkAndStatus(averageMark, status, studentId, se.getId(), sub.getId());
                    regis.setAverageMark(averageMark);
                    regis.setStatus(status);
                } else {
                    regis.setAverageMark(rs.getDouble("averageMark"));
                    regis.setStatus(rs.getBoolean("status"));
                }

                registrations.add(regis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return registrations;
    }

    public void updateAverageMarkAndStatus(double averageMark, Boolean status, String studentId, String semesterId, String subjectId) {

        String sql = "UPDATE [dbo].[Registration]\n"
                + "   SET \n"
                + "      [averageMark] = ?\n";
        if (status == null) {
            sql += " , [status] = null";
        } else {
            sql += " , [status] = ?";
        }
        sql += " where studentId = ? and semesterId = ? and subjectId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1, averageMark);
            if (status != null) {
                stm.setBoolean(2, status);
                stm.setString(3, studentId);
                stm.setString(4, semesterId);
                stm.setString(5, subjectId);
            } else {
                stm.setString(2, studentId);
                stm.setString(3, semesterId);
                stm.setString(4, subjectId);
            }

            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public Registration getT(String studentId, String semesterId, String subjecId) {
        Registration regis = new Registration();
        String sql = "select * from Registration r where  r.studentId = ? and r.semesterId = ? and r.subjectId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            stm.setString(2, semesterId);
            stm.setString(3, subjecId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                Subject sub = new Subject();
                sub.setId(rs.getString("subjectId"));
                regis.setSubject(sub);

                Semester se = new Semester();
                se.setId(rs.getString("semesterId"));
                regis.setSemester(se);

                Student s = new Student();
                s.setId(rs.getString("studentId"));
                regis.setStudent(s);

                Group g = new Group();
                g.setId(rs.getString("groupId"));

                regis.setGroup(g);
                regis.setId(rs.getString("reid"));
                
                Date dateBegin = rs.getDate("dateBegin");
                Date dateEnd = rs.getDate("dateEnd");
                regis.setDateBegin(dateBegin);
                regis.setDateEnd(dateEnd);
                
                Double averageMark = (Double) rs.getObject("averageMark");
                LocalDate currentDate = LocalDate.now();
                Date current_date = Date.valueOf(currentDate);
                //neu averagemark == null hoac dang trong thoi gian hoc thi se tinh diem tb va cap nhap status
                if (averageMark == null || (current_date.compareTo(dateBegin) >= 0 && current_date.compareTo(dateEnd) <= 0)) {
                    CalculatorAverageMark calculator = new CalculatorAverageMark();
                    GradingSystemDBContext gradingDB = new GradingSystemDBContext();
                    ArrayList<GradingSystem> gss = gradingDB.getGradingSystemsBysidAndSubidAndSeId(studentId, se.getId(), sub.getId());
                    averageMark = calculator.getAverageMark(gss);
                    Boolean status = null;
                    if (current_date.compareTo(dateBegin) >= 0 && current_date.compareTo(dateEnd) <= 0) {
                        status = null;
                    } else if (current_date.compareTo(dateEnd) > 0) {
                        if (averageMark >= 5) {
                            status = true;
                        } else {
                            status = false;
                        }
                    }
                    updateAverageMarkAndStatus(averageMark, status, studentId, se.getId(), sub.getId());
                    regis.setAverageMark(averageMark);
                    regis.setStatus(status);
                } else {
                    regis.setAverageMark(rs.getDouble("averageMark"));
                    regis.setStatus(rs.getBoolean("status"));
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return regis;
    }
    
    public int getTotalAbsent(String studentId, String semesterId, String subjectId) {
        int totalAbsent = 0;
        String sql = "select totalAbsent from Registration where studentId = ? and semesterId = ? and subjectId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentId);
            stm.setString(2, semesterId);
            stm.setString(3, subjectId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                totalAbsent = rs.getInt("totalAbsent");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return totalAbsent;
    }
    
    public void updateTotalAbsent(String studentId, String semesterId, String subjectId, int totalAbsent) {
        String sql = "update Registration set totalAbsent = ? where studentId = ? and semesterId = ? and subjectId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, totalAbsent);
            stm.setString(2, studentId);
            stm.setString(3, semesterId);
            stm.setString(4, subjectId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
