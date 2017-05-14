<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link href="/css/bootstrap.css" type="text/css" rel="stylesheet" >

</head>
<body>
	<br><br><br>
	<br><br><br>
	<center>
		<h1>Login</h1>
	</center>
	<br><br><br>
	<form action="loginServlet" method="POST">
		<table align="center">
			<tr>
				<th align="right">Username:</th>
				<td><input type="text" name="txtusername"></td>
			</tr>
			<tr>
				<th align="right">Password:</th>
				<td><input type="password" name="txtpassword"></td>
			</tr>
			<tr>
				<th colspan="2" align="right"><input type="submit" class="btn btn-primary" value="Log In"></th>
			</tr>
		</table>
	</form>
</body>
</html>