$(document).ready(function(){
	
	
	
	function checkUserInput(){
		 
		cleanErrorFields("errorMessage");
		
		 var x = document.getElementById("firstname").value;
		 
		 var result = true;
		 
		 if (x == "") {
		      document.getElementById("firstname_alert").innerHTML = "missing"
		      result = false;
		 }
		 
		 x = document.getElementById("lastname").value;
		 
		 if(x == ""){
			 document.getElementById("lastname_alert").innerHTML = "missing"
				 result = false;
		 }
		 
		 x = document.getElementById("age").value;
		 
		 if(x == ""){
			 document.getElementById("age_alert").innerHTML = "missing"
				 result = false;
		 }
		 
		 x = document.getElementById("email").value;
		 
		 if(x == ""){
			 document.getElementById("email_alert").innerHTML = "missing"
			 result = false;
		 }else{
			 if(!validateEmail(x)){
				 document.getElementById("email_alert").innerHTML = "Not a valid emailname!"
				 result = false;
			 }
		 }
		 
		 var password = document.getElementById("password").value;
		 
		 if(password == ""){
			 document.getElementById("password_alert").innerHTML = "missing"
				result = false;
		 }
		 
		 var password_repeat = document.getElementById("password_repeat").value;
		 
		 if(password_repeat.toString()!= password.toString()){
			 document.getElementById("password_alert").innerHTML = "The password must be identical"
			 result = false;
		 }
		 return result;
	}


	/**
	 * 
	 * @param className : Classname 
	 * 
	 * 
	 */
	function cleanErrorFields(className){
		// Gibt Liste von Elementen zurück die die entsprechende Klasse haben
		var elementsToBeCleaned = document.getElementsByClassName(className);
		
		for(var i = 0; i < elementsToBeCleaned.length; i++ ){
			elementsToBeCleaned[i].innerHTML = "";
		}
	}
	
	
		/**
		 * Stops the submit request
		 */
	    $("#sendToRegisterServlet").submit(function(e){
	           e.preventDefault();
	    });
	    
	    $("#registerButton").click(function(e){
	    	
	    	if(checkUserInput()){
	    		
	    		//get the form data and then serialize that
	            var dataString = $("#sendToRegisterServlet").serialize();
	    		
	            
	            $.ajax({
	                type: "POST",
	                url: "RegisterServlet",
	                data: dataString,
	                dataType: "text",
	               
	                //if received a response from the server
	                success: function(dataresponse) {
	                    //our country code was correct so we have some information to display
	                    //$("#responsetext").text(data);
	                    
	                    alert("Hallo und ein herzliches Willkommen "+dataresponse+"!");
	                },
	                
	                error: function(){
	                	alert("error in response");
	                },
	            });  
	            
	    		
	    	}
	    });
	    
	    
	    
});
	
	
    
    
    
