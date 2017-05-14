package dubravkos.web.app.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private static final String DB_NAME = "webappdb";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
	
	private static final String USER = "webappDBuser";
	private static final String PASSWORD = "geheim";

	
	public static java.sql.Connection getMySQLDBConnection(){
		java.sql.Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			
			// Open Connection
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return conn;
	}

}
