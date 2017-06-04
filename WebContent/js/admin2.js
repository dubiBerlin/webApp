$(document).ready(function(){

	
	$("#searchUserBtn").click(function(e){
		
		alert("Serach user");
		
		
	});
	
	
	$("button").click(function() {
	    //alert(this.id); // or alert($(this).attr('id'));
	    
		var btn_id = this.id;
		
	    if(id ===  "searchUserBtn" ){
	    	alert("show da user");
	    }else{
	    	var id = btn_id.slice(-1);
	    	
	    	
	    	// selects the values of td in table
	    	var username = $("#row_id_"+id).find("td:eq(1)").text(); 
	    	var firstname = $("#row_id_"+id).find("td:eq(2)").text();
	    	var lastname = $("#row_id_"+id).find("td:eq(3)").text();
	    	var email = $("#row_id_"+id).find("td:eq(4)").text();
	    	var age = $("#row_id_"+id).find("td:eq(5)").text();
	    	
	    	if(confirm("Möchsten Sie folgenden Datensatz löschen?\n" +
	    			"username : "+username+" \n" +
	    			"name     : "+firstname+" "+lastname+" \n" +
	    			"email    : "+email+"    \n" +
	    			"age      : "+age+"      \n")){
	    		
	    		// admin has pressed ok so the user has to be deleted
	    		// send it to the adminservlet and parameter is the username
	    		$.get("AdminServlet", {username: username}, function(data){
	    			alert(data);
	    		});
	    		
	    		
	    	}
	    	
	    	
	    	
	    	
	    	
	    }
	    
	    
	    
	    
	    
	});
	
	
	
	
});
