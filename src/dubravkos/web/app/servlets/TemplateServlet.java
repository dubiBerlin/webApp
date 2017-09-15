package dubravkos.web.app.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dubravkos.web.app.DB.DatabaseOperations;
import dubravkos.web.app.beans.Template;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TemplateServlet
 */
@WebServlet("/TemplateServlet")
public class TemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemplateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Hi");
		
		String templatename = request.getParameter("templateName");
		String templ  = request.getParameter("template");
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Template template = new Template();
		template.setTemplatename(templatename);
		template.setTemplate(templ);
		
		/*
		 * param 1: Name of table
		 * param 2: Name of column
		 * param 3: Searched value
		 */
		try {
			if(DatabaseOperations.valueExistInDB( "templates", "template_name", templatename)==false){
			
				try {
					if(DatabaseOperations.insertNewTemplate(template)){
						
						System.out.println("Its successs");
						JSONObject json = new JSONObject();
						json.put("templateSaved", "success");
						json.put("resultText", "Template was saved successfully");
						
						response.getWriter().write(json.toString());	
						
					}else{
						
						// if registry of user failed, send a string "failed" back to the called html (register.html) side
						JSONObject json = new JSONObject();
						json.put("templateSaved", "failed");
						json.put("resultText", "Template has not been saved");
						
						response.getWriter().write(json.toString());
						
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{ 
				JSONObject json = new JSONObject();
				json.put("templateSaved", "failed");
				json.put("resultText", "Templatename allready exists. Please choose another templatename.");
				
				response.getWriter().write(json.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
