package dubravkos.web.app.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import dubravkos.web.app.DB.DatabaseOperations;
import dubravkos.web.app.beans.User;
import dubravkos.web.app.helpers.GlobalValues;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    

	private static final String REGISTER_SUCCESS = "Your registration was successfull";
	private static final String REGISTER_FAILED  = "Your registration failed";
	private static final String USERNAME_EXISTS  = "Uername exists. Please choose another one.";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("Firstname: "+firstname);
		System.out.println("Lastname: "+lastname);
		System.out.println("age: "+age);
		System.out.println("email: "+email);
		System.out.println("password: "+password);
		
		// Hier gehts los
		response.setContentType("application/json");
		
		
		try {
			if(DatabaseOperations.valueExistInDB("User", "username", username)==false){
				
				User user = new User();
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setAge(Integer.valueOf(age));
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				
				/*
				 * DatabaseOperations has all methodes for database operations 
				 */
				//DatabaseOperations db = new DatabaseOperations();
				
				try {
					
					// If registry of new user is a success
					if(DatabaseOperations.registerUser(user)){
						System.out.println("Its success");
						JSONObject json = new JSONObject();
						json.put("registration", "success");
						json.put("resultText", REGISTER_SUCCESS);
						
						response.getWriter().write(json.toString());	
						
						// send a string "success" back to the called html side
						//response.getWriter().write(REGISTER_SUCCESS);
						
					}else{
						
						// if registry of user failed, send a string "failed" back to the called html (register.html) side
						JSONObject json = new JSONObject();
						json.put("registration", "failed");
						json.put("resultText", REGISTER_FAILED);
						
						response.getWriter().write(json.toString());		
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				
				JSONObject json = new JSONObject();
				json.put("registration", "failed_username");
				json.put("resultText", USERNAME_EXISTS);
				
				response.getWriter().write(json.toString());	
				//response.getWriter().write(USERNAME_EXISTS);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		
	}

}
