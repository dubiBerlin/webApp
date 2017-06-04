package dubravkos.web.app.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dubravkos.web.app.DB.DatabaseOperations;
import dubravkos.web.app.beans.User;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// access the parameter names to see which parameter has been send in order to access the right content of the parameter
		Enumeration enumeration = request.getParameterNames();
		String parametername = (String) enumeration.nextElement();
		System.out.println("paramname: "+parametername);
		
		/*
		 * Admin.js
		 * 
		 * Der Admin möchte alle User haben. Dazu wurde der Button gedrückt
		 * der alle User abruft die in der DB enthalten sind
		 */
		if(parametername.equals("pressedButton")){
			if(request.getParameter("pressedButton").equals("show_all_users_btn")  ){
			System.out.println("show_user_btn wurde gedrückt.");
			
			try {
				List<User> users = DatabaseOperations.getAllUsers();
				
//				response.setContentType("text/plain");
//				response.getWriter().write(users.toString());
				
				JSONArray jsonArray = new JSONArray();
				
				
				for(int i = 0; i < users.size(); i++){
					System.out.println(users.get(i).getFirstName()+" "+users.get(i).getLastName());
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("firstname", users.get(i).getFirstName());
					jsonObj.put("lastname" , users.get(i).getLastName());
					jsonObj.put("username" , users.get(i).getUsername());
					jsonObj.put("email"    , users.get(i).getEmail());
					jsonObj.put("age"    , users.get(i).getAge());
					
					jsonArray.add(jsonObj);
				}
				
				JSONObject returnObj = new JSONObject();
				
				returnObj.put("users", jsonArray);
				
				
				response.setContentType("application/json");
				response.getWriter().write(returnObj.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
		else{
			// admin2.js
			if(parametername.equals("username")){
				String username = request.getParameter("username");
				System.out.println("The user "+username+" has to be deleted");
				
				
				try {
					if(DatabaseOperations.deleteUser(username)==1){
						response.getWriter().write("succes");
					}else{
						response.getWriter().write("failed");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		
		
	}
	
	
	private void printJSONARRAY(JSONArray jsonArray, String param1, String param2){
		System.out.println("print users out of JSONARRAY");
		
		for(int i = 0; i < jsonArray.size(); i++){
			
			JSONObject obj = new JSONObject();
			obj = (JSONObject) jsonArray.get(i);
			
//			System.out.println(obj.get("firstname")+" "+obj.getString("lastname"));
			System.out.println(obj.get(param1)+" "+obj.getString(param2));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
