package sutharshan;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Sutharsan
 */
public class DbConn {
    public static Connection ConnectDb(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Sutharsan\\Documents\\NetBeansProjects\\SPM\\SqliteDb.db");
            System.out.println("Successfully connected ");
             return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
             return null;
        }
        
    }
}
