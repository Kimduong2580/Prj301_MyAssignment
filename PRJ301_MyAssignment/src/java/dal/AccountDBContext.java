/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Student;
import model.UserType;

/**
 *
 * @author Nguyen Kim Duong
 */
public class AccountDBContext extends DBContext<Account> {

    public static void main(String[] args) {
        AccountDBContext db = new AccountDBContext();
        Account acc = db.getT("sonnt5", "1234");
        System.out.println(acc.getLecturer().getId());
    }

    public Account getT(String username, String password) {
        Account account = null;
        String sql = "select * from Account where username = ? and password = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setDisplayName(rs.getString("displayName"));
                Student student = new Student();
                Lecturer lecturer = new Lecturer();
                if (rs.getString("studentId") != null) {
                    student.setId(rs.getString("studentId"));
                    account.setStudent(student);
                }else {
                    account.setStudent(null);
                }
                if (rs.getString("lecturerId") != null) {
                    lecturer.setId(rs.getString("lecturerId"));
                    account.setLecturer(lecturer);
                }else {
                    account.setLecturer(null);
                }
                UserType userType = new UserType();
                userType.setId(rs.getInt("userTypeId"));
                account.setUserType(userType);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return account;
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
