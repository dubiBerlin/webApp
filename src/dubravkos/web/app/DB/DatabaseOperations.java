package dubravkos.web.app.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dubravkos.web.app.beans.User;

public class DatabaseOperations {
	
	
	private java.sql.Connection connection;
	private static final String DB_NAME = "webappdb";
	private static final String JDBC_DRIVER = "com.mysql.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
	
	private static final String USER = "webappDBuser";
	private static final String PASSWORD = "geheim";
	
	public DatabaseOperations() {
		
		
	}
	
	
	public boolean registerUser(User user) throws SQLException{
		connection = DBManager.getMySQLDBConnection();
		
//		String sqlQuery = "insert into user( firstname, lastname, age, email, password) "
//				+ "values ('"+user.getFirstName()+"','"+user.getLastName()+"',"
//				          + "'"+user.getAge()+"','"+user.getEmail()+"','"+user.getPassword()+"'";
		
		String query = "insert into user(firstname, lastname,  age, email, password) values(?,?,?,?,?)";
		
		PreparedStatement stmt =	connection.prepareStatement(query);
		stmt.setString(1, user.getFirstName());
		stmt.setString(2, user.getLastName());
		stmt.setInt(3, user.getAge());
		stmt.setString(4, user.getEmail());
		stmt.setString(5, user.getPassword());
		
		if(stmt.execute()){

			connection.close();
			return true;
			
		}else{

			connection.close();
			return false;
		}
	}

}
