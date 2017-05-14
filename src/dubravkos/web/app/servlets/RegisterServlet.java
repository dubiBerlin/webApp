package dubravkos.web.app.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dubravkos.web.app.DB.DatabaseOperations;
import dubravkos.web.app.beans.User;
import dubravkos.web.app.helpers.GlobalValues;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }	
    
    
	/**
	 * We send the regster form with the POST method out of the rregister.html
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/**
		 * take all the send parameters to String and variables
		 */
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("Firstname: "+firstname);
		System.out.println("Lastname: "+lastname);
		System.out.println("age: "+age);
		System.out.println("email: "+email);
		System.out.println("password: "+password);
		
		
		// Hier gehts los
		response.setContentType("text/plain");
		
		
		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setAge(Integer.valueOf(age));
		user.setEmail(email);
		user.setPassword(password);
		
		
		/*
		 * DatabaseOperations has all methodes for database operations 
		 */
		DatabaseOperations db = new DatabaseOperations();
		
		try {
			
			// If registry of new user is a success
			if(db.registerUser(user)){
				
				// send a string "success" back to the called html side
				response.getWriter().write(GlobalValues.getStrServletResponseSuccess());		
			}else{
				
				// if registry of user failed, send a string "failed" back to the called html (register.html) side
				response.getWriter().write(GlobalValues.getStrServletResponseFailed());		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
