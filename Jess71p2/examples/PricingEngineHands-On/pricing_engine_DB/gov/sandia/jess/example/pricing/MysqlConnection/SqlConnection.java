package gov.sandia.jess.example.pricing.MysqlConnection;
import gov.sandia.jess.example.pricing.model.CatalogItem;
import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class SqlConnection{
    public ArrayList readCatalog(){
        ArrayList lista = new ArrayList();
        MySqlConnection conexion = new MySqlConnection();
        PreparedStatement stmt;
        String query = "Select * from CatalogItems";
    try{
        stmt = conexion.getConnection().prepareStatement(query);
        ResultSet result = stmt.executeQuery();
        while(result.next()){
             CatalogItem item = new CatalogItem( result.getString(1),  result.getInt(2),  result.getFloat(3));
            lista.add(item);
        }
    }catch(SQLException ex){
        System.out.println("Fallo");
    }
        conexion.Salir();
        return lista;
    }
}