/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection conn;
    
    public static Connection geConnection(){
        if(conn == null){
            try{
               String url = "jdbc:mysql://localhost:3306/123230110_responsi";
               String user = "root";
               String pass = "";
               Class.forName("com.mysql.cj.jdbc.Driver");
               conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Connect to DB");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
