$(document).ready(function(){
	

	
	 $("#get_all_users_form").submit(function(e){
	         e.preventDefault();
	  });
	
	 $("#show_all_users_btn").click(function(e){
		 
		 
		 /**
		  * Es werden als Parameter {pressedButton: "show_all_users_btn}" gesendet, damit im
		  * Servlet unterschieden werden kann, welche Methode die Abfrage benötigt.  
		  * Der Wert innerhalb des pressedButton Parameter wird im Servlet durech die Methode 
		  * request.getParameter("pressedButton") abgerufen.
		  */
		 $.get("AdminServlet", {pressedButton: "show_all_users_btn"}, function(data){
			 
			 var html = "<div class='all_users_table'>" +
			 				"<table>" +
			 					"<tr>" +
			 						"<th>Username</th>"+
			 						"<th>Firstname</th>"+
			 						"<th>Lastname</th>"+
			 						"<th>Email</th>"+
			 					"</tr>";
			 
			 for(var i = 0; i < data.users.length; i++){
				 
				 
				 html = html+	"<tr>" +
				 					"<td>"+data.users[i].username+"</td>"+
				 					"<td>"+data.users[i].firstname+"</td>" +
				 					"<td>"+data.users[i].lastname+"</td>" +
				 					"<td>"+data.users[i].email+"</td>" +
				 				"</tr>" ;
				
				console.log(data.users[i].firstname+" "+data.users[i].lastname);
//				$("#result").text("Full name: "+data.users[i].firstname+" "+data.users[i].lastname);
			 }
			 
			 html = html+ "</table>" +
			 			  	"</div>"
			 
			 $(html).appendTo("#result") ;
		 });
		 
		 
		 
	 });
	
});