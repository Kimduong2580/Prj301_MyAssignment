/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.IEntity;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Kim Duong
 * @param <T>
 */
public abstract class DBContext <T>{

    public Connection connection = null;

    public DBContext() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment_Prj301;user=duongnk;password=123456;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }

    }
    
    public abstract ArrayList<T> list();
    public abstract void insert(T entity);
    public abstract void update(T entity);
    public abstract void delete(T entity);
    public abstract T getT(int id);
}
