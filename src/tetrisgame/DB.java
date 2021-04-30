/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DB {
    public static Connection con;
    
    public static boolean Open() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-CIM7T02B\\SQLEXPRESS:1433;databaseName=TetrisGame;integratedSecurity=true");
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public static boolean Close() {
        try{
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args){
        System.out.println(Open());
    }
}
