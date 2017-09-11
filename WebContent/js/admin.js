$(document).ready(function(){
	
	 var objs_users = [];
	
	 $("#get_all_users_form").submit(function(e){
	         e.preventDefault();
	  });
	
	 
	 
	 $("#show_all_users_btn").click(function(e){		 
		 /*
		  * Zuerst Suchzeile, die checkboxen die angeben wonach gesucht werden soll und den Usersuch-Button erstellen und an die  #result id anhängen
		  */
		 
		 var html = "";
		 
		 var label = "<label for='searchUserField'>Search user</label>";
		 
		 var br1 = "<br/>";
		 
		 var userSearchField = "<input type='text' id='searchUserField' size='80' class='searchUser' ></textarea>";
		 
		 var searchUserBtn = "<button id='searchUserBtn' class='searchUser' >search</button>"
		 
		 var br2 = "<br/>";
		 
		 var checkBoxGroup = "<table>" +
		 						"<tr>" +
		 							"<td>" +
		 								"<label for='username'>Username</label>" +
		 								"<input type='checkbox' id='usernameCheck'>" +
		 							"</td>" +
		 							"<td>" +
			 							"<label for='firstname'>Firstname</label>" +
			 							"<input type='checkbox' id='firstnameCheck'>" +
		 							"</td>" +
		 							"<td>" +
			 							"<label for='lastname'>Lastname</label>" +
			 							"<input type='checkbox' id='lastnameCheck'>" +
		 							"</td>" +
		 							"<td>" +
			 							"<label for='email'>Email</label>" +
			 							"<input type='checkbox' id='emailCheck'>" +
		 							"</td>" +
		 							"<td >" +
			 							"<div class='errorMessage' id='searchUserErrorMessage'></div>"+
		 							"</td>" +
		 						"</tr>"+
		 					 "</table>"
		 var br3 = "<br/>"
		 						
		 html = label + br1 + userSearchField + searchUserBtn + br2 + checkBoxGroup +br3;
		 
		
		 
		 /**
		  * Es werden als Parameter {pressedButton: "show_all_users_btn}" gesendet, damit im
		  * Servlet unterschieden werden kann, welche Methode die Abfrage benötigt.  
		  * Der Wert innerhalb des pressedButton Parameter wird im Servlet durech die Methode 
		  * request.getParameter("pressedButton") abgerufen.
		  */
		 $.get("AdminServlet", {pressedButton: "show_all_users_btn"}, function(data){
			 
			 
			 html = html +  "<div class='all_users_table'>" +
				 				"<table>" +
				 					"<tr>" +
				 						"<th>Nr</th>"+
				 						"<th>Username</th>"+
				 						"<th>Firstname</th>"+
				 						"<th>Lastname</th>"+
				 						"<th>Email</th>"+
				 						"<th>Age</th>"+
				 					"</tr>";
			 
			 for(var i = 0; i < data.users.length; i++){				 	 
				 html = html+	"<tr id='row_id_"+i+"'>" +
				 					"<td>"+(i+1)+"</td>"+
				 					"<td>"+data.users[i].username+"</td>"+
				 					"<td>"+data.users[i].firstname+"</td>" +
				 					"<td>"+data.users[i].lastname+"</td>" +
				 					"<td>"+data.users[i].email+"</td>" +
				 					"<td>"+data.users[i].age+"</td>" +
				 					"<td>"+"<button id='del_btn_"+i+"'   >Delete</button></td>" +
				 				"</tr>" ;
			
				 var objUser = createUserObject(data.users[i].username, data.users[i].firstname, data.users[i].lastname, data.users[i].email, data.users[i].age);
				 objs_users.push(objUser);
				 
				 console.log(data.users[i].firstname+" "+data.users[i].lastname+" "+data.users[i].age);
//				$("#result").text("Full name: "+data.users[i].firstname+" "+data.users[i].lastname);
			 }
			 
			 html = html+ "</table>" +
			 			  	"</div>"
			 
			 $(html).appendTo("#result");
			 
			 $.getScript("js/admin2.js");
		 });
	 });
	
		function createUserObject(strUsername, strFirstname, strLastname, strEmail, age ){
			var user = {
				username: strUsername,
				firstname: strFirstname,
				lastname: strLastname,
				email: strEmail,
				age: age
			}
			return user;
		}
	 
		function deleteUser(){
			alert("delete user");
		}
	 
});