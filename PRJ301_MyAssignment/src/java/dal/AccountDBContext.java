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
import model.Account_type;
import model.Lecturer;
import model.Student;

/**
 *
 * @author Nguyen Kim Duong
 */
public class AccountDBContext extends DBContext<Account> {

    public static void main(String[] args) {
        AccountDBContext db = new AccountDBContext();
        Account acc = db.getT("annv", "123");
        System.out.println(acc.getAccount_type().getId());
    }

    public Account getT(String username, String password) {
        String sql = "SELECT [accid]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[displayName]\n"
                + "      ,[code]\n"
                + "      ,[acc_type]\n"
                + "  FROM [dbo].[Account] where username = ? and password = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("accid"));
                acc.setUsername(username);
                acc.setPassword(password);
                acc.setDisplayName(rs.getString("displayName"));
                acc.setCode(rs.getString("code"));
                Account_type at = new Account_type();
                at.setId(rs.getInt("acc_type"));
                acc.setAccount_type(at);
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
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
