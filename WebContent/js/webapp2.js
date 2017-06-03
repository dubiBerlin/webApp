$(document).ready(function(){

	
	function validateEmail(strEmail){
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(strEmail);
	}
	
	function validatePassword(password){
		var re = "";
		return re.test(password);
	}
	
	
	
	$("#navHeader").load("nav_header.html");
	
	
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
	
});