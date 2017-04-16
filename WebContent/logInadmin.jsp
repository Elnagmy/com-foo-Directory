<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*, Foo.Contact.Rescources.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1256">
<link type="text/CSS" rel="stylesheet" href="CSS/style.css">
<title>logIn To Foo Directory</title>
</head>
<% 
Object login = request.getAttribute("inValidLogin");
%>

<body>
	<div id="Top"></div>

	<div id="Login">
		<div id="loginHeader">

			<h2>Foo Directory</h2>
		</div>
		<p>In order to add new staff you should have administrator access , if you
			want to request access please contact your IT administrator</p>


		<form action="FooServlet" method="post">
			<input type="hidden" name="Command" value="LoginAdmin"> <img
				id="LoginImage" alt="FooLogo" src="Images/FooLogologin.png">
			<table>
				<tbody>
					<tr>
						<td><label for="UserID">User ID &nbsp;
								&nbsp;&nbsp;&nbsp; </label></td>
						<td><input type="text" name="UserID"></td>
					</tr>
					<tr>
						<td><label for="password">Password &nbsp;
								&nbsp;&nbsp;&nbsp; </label></td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td><label> &nbsp; &nbsp;&nbsp;&nbsp; </label></td>
						<td><input style="float: right;" class="small" type="submit"
							value="log In &nbsp;"></td>
					</tr>
					<tr>
						<td><label> &nbsp; &nbsp;&nbsp;&nbsp; </label></td>
						<% if(login !=null){ %>
						<td><span style="color: red;"> you should have an administrator access</span>
						<%} %>
					</tr>
				</tbody>
			</table>

		</form>
		<br> <br> <br> <br>
	</div>

</body>
</html>