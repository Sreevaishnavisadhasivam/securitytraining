package webgoattraining;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {
	public static Connection con;
	 public static Connection getConnection() throws ClassNotFoundException
	 {
		  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlinjection?allowMultiQueries=true","root","root");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		 return con; 
	 }	 

}
