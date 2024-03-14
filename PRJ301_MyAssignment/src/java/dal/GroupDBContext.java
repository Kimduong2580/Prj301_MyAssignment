/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Group;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;

/**
 *
 * @author Nguyen Kim Duong
 */
public class GroupDBContext extends DBContext<Group>{
    public static void main(String[] args) {
        GroupDBContext groupDB = new GroupDBContext();
        ArrayList<Group> lists = groupDB.list("sonnt5");
        for (Group g : lists) {
            System.out.println(g.getName());
        }
    }
    
    public ArrayList<Group> list(String lid) {
        ArrayList<Group> groups = new ArrayList<>();
        String sql = "select * from [Group] g where g.lid = ?";
        try {
            PreparedStatement stm  = connection.prepareStatement(sql);
            stm.setString(1, lid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Group g = new Group();
                g.setId(rs.getString("gid"));
                g.setName(rs.getString("gname"));
                Lecturer l = new Lecturer();
                l.setId(lid);
                g.setLecturer(l);
                
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return groups;
    }

    @Override
    public ArrayList<Group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
