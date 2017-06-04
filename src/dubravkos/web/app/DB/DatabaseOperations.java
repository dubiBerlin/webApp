package dubravkos.web.app.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dubravkos.web.app.beans.User;

public class DatabaseOperations {
	
	
	private static java.sql.Connection connection;
		
	
	public static boolean registerUser(User user) throws SQLException{
		connection = DBManager.getMySQLDBConnection();
		
//		String sqlQuery = "insert into user( firstname, lastname, age, email, password) "
//				+ "values ('"+user.getFirstName()+"','"+user.getLastName()+"',"
//				          + "'"+user.getAge()+"','"+user.getEmail()+"','"+user.getPassword()+"'";
		
		String query = "insert into user(firstname, lastname,  age, email, password, username) values(?,?,?,?,?,?)";
		
		PreparedStatement stmt =	connection.prepareStatement(query);
		stmt.setString(1, user.getFirstName());
		stmt.setString(2, user.getLastName());
		stmt.setInt(3, user.getAge());
		stmt.setString(4, user.getEmail());
		stmt.setString(5, user.getPassword());
		stmt.setString(6, user.getUsername());
		
		
		if(stmt.executeUpdate()>0){

			connection.close();
			return true;
			
		}else{

			connection.close();
			return false;
		}
	}
	
	
	public static List<User> getAllUsers() throws SQLException{
		connection = DBManager.getMySQLDBConnection();
		
		List<User> usersList = new ArrayList<User>();
		
		String query = "SELECT * FROM USER";
		
		Statement st = connection.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		
		
		while(rs.next()){
			
			User user = new User();
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setEmail(rs.getString("email"));
			user.setUsername(rs.getString("username"));
			user.setAge(rs.getInt("age"));
			usersList.add(user);
		}
		connection.close();
		
		return usersList;
	}
	
	public static boolean valueExistInDB( String tableName, String columnName, String searchedValue) throws SQLException{
		connection = DBManager.getMySQLDBConnection();
		
		
		String query = "SELECT * FROM "+tableName+" WHERE "+columnName+" = '"+searchedValue+"'";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			
			if(rs.getString(columnName).equalsIgnoreCase(searchedValue)){
				connection.close();
				return true;
			}
			
		}
		connection.close();
		return false;
	}
	
	public static int deleteUser(String username) throws SQLException{
		connection = DBManager.getMySQLDBConnection();
		
		String query = "DELETE FROM user WHERE username = '"+username+"'";
		
		Statement stmt = connection.createStatement();
		int deletionResult = stmt.executeUpdate(query);
		
		return deletionResult;
	}
	

}
