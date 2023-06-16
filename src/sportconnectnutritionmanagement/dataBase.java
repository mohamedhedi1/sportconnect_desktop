/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportconnectnutritionmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aymen
 */
public class dataBase {
    private static dataBase instance;
    Connection cnx;
    
     private  dataBase() 
    {
        try {
            cnx=DriverManager.getConnection("jdbc:mysql://localhost/sportconnect2","root","");
            System.out.println("Connection réussie !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static Connection connectDb(){
        
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/sportconnect", "root", "");
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    public static dataBase getInstance() {
        if(instance == null)
        {
            instance = new dataBase();
        }
        return instance;
    }
}
