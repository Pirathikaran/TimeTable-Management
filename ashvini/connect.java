/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ashvini;

import ashvini.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ashvinipraisoody
 */
public class connect {
    private static Connection con;
    private static Statement st;
    
    public static Statement connection(){
         try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:SqliteDb.db";
            con = DriverManager.getConnection(url);
            st = con.createStatement();
            System.out.println("Connection Succesful");
        } catch (Exception e) {
            System.out.println("Connection Failed");
        }
         return st;
    }
}
