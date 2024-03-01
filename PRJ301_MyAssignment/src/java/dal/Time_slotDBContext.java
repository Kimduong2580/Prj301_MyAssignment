/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Time_slot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Kim Duong
 */
public class Time_slotDBContext extends DBContext<Time_slot>{
    public static void main(String[] args) {
        Time_slotDBContext db = new Time_slotDBContext();
        ArrayList<Time_slot> list = db.list();
        System.out.println(list.size());
    }

    @Override
    public ArrayList<Time_slot> list() {
        ArrayList<Time_slot> times = new ArrayList<>();
        String sql = "select * from Time_slot";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Time_slot t = new Time_slot();
                t.setId(rs.getString("tid"));
                t.setTimeBegin(rs.getString("timeBegin"));
                t.setTimeEnd(rs.getString("timeEnd"));
                times.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Time_slotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return times;
    }

    @Override
    public void insert(Time_slot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Time_slot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Time_slot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Time_slot getT(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
