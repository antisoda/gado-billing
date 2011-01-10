package gado;

import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;*/

public class Koneksi{
  	public Koneksi(){
  		
  	}
  	
	public Connection Buka() throws SQLException {
        Connection connection = null;
     	try{
		    Class.forName("org.sqlite.JDBC");
      	    connection=DriverManager.getConnection("jdbc:sqlite:lib/gado.db");
		    return connection;
		}catch (SQLException se){
      	    System.out.println("No Connection Open");
      	    return null;
    	}catch (Exception ex){
      	   System.out.println("Cound not open connection");
      	   return null;
    	}
  	}
}