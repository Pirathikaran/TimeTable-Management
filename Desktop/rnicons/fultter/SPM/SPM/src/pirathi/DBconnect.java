/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirathi;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DBconnect {
      public  static Connection ConncetionDb(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:hours.db");
                   //     JOptionPane.showMessageDialog(null,"Connected Successfully" +conn  );
                   System.out.println("Connected");

            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e  );
//                               System.out.println("fhdhfh");

            return null;
            
        }
    }
    
}
