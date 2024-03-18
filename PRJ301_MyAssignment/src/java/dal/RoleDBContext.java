/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Kim Duong
 */
public class RoleDBContext extends DBContext<Role> {
    public static void main(String[] args) {
       RoleDBContext roleDB = new RoleDBContext();
       ArrayList<Role> roles = roleDB.list(5, "/lecturer/time_table");
        System.out.println(roles.get(0).getName());
    }

    public ArrayList<Role> list(int accid, String url) {
        ArrayList<Role> roles = new ArrayList<>();
        String sql = "select r.rid, r.rname from Account a \n"
                + "JOIN RoleAccount ra ON a.accid = ra.accid \n"
                + "JOIN [Role] r ON r.rid = ra.rid \n"
                + "JOIN RoleFeature rf ON rf.rid = r.rid \n"
                + "JOIN Feature f ON f.fid = rf.fid\n"
                + "where f.url = ? and a.accid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, url);
            stm.setInt(2, accid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                roles.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return roles;
    }

    @Override
    public ArrayList<Role> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Role getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
