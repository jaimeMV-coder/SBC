package gov.sandia.jess.example.pricing.MysqlConnection;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class MySqlConnection{
    private Connection conn;
    
    private static final String url = "jdbc:mysql://brguvhv0ojfdepjudovc-mysql.services.clever-cloud.com:3306/brguvhv0ojfdepjudovc";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user= "uzx1r1xw4mgrktaj";
    private static final String password = "FPGAGKsN5BweVSOI8Clf";
    


    MySqlConnection(){
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
     
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cuenta de usuario o contraseña invalida");
        }
    }
    
    public Connection getConnection() throws SQLException{
        return conn;
    }
    
    public void Salir(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al salir");
        }
    }
    
}