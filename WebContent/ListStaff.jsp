<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*, Foo.Contact.Rescources.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link type="text/CSS" rel="stylesheet" href="CSS/style.css">
<title>Foo Directory App</title>
</head>
<%
	ArrayList<Staff> List = (ArrayList<Staff>) request.getAttribute("StaffList");
%>
<body>
	<div id="wrapper">
		<div id="TopHeader">
			<h2>
				<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <a
					href="Index.html">Foo Directory </a>
			</h2>
		</div>

		<br>
		<div id="searchArea">
			<ul class="tab">
				<li><a href="javascript:void(0)" id="defaultOpen"
					class="tablinks" onclick="openTab(event, 'Name')">Name search</a></li>
				<li><a href="javascript:void(0)" class="tablinks"
					onclick="openTab(event, 'Advanced')">Advanced search</a></li>
				<li><a href="javascript:void(0)" class="tablinks"
					onclick="openTab(event, 'Department')">Department search</a></li>
			</ul>

			<div id="Name" class="tabcontent">
				<form action="FooServlet" method="get">
					<input type="hidden" name="Command" value="getByName">
					<table>
						<tbody>
							<tr>
								<td><label for="Name">Name &nbsp;
										&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</label></td>
								<td><input type="text" name="Name"></td>
							</tr>

							<tr>

								<td><label></label></td>
								<td><br> <input class="small" type="submit" value="Go">
									<input class="small" type="button" value="Clear"></td>
							</tr>
						</tbody>

					</table>
				</form>
			</div>

			<div id="Advanced" class="tabcontent">
				<form action="FooServlet" method="get">
					<input type="hidden" name="Command" value="Advanced">
					<table>
						<tbody>
							<tr>
								<td><label for="advanced">To be completed</label></td>
							</tr>

						</tbody>

					</table>
				</form>
			</div>

			<div id="Department" class="tabcontent">
				<form action="FooServlet" method="get">
					<input type="hidden" name="Command" value="Department">
					<table>
						<tbody>
							<tr>
								<td><label for="Department">To be completed</label></td>
							</tr>

						</tbody>

					</table>
				</form>
			</div>
		</div>
		<div class="card" style="display: none;" >
			<img src="Images/FooLogo.png" alt="Avatar" style="width: 100% ;">
			<div class="container">
				<h4>
					<b></b>
				</h4>
				<p>></p>
				<p></p>
			</div>
		</div>


		<br>
		<hr>
		<br>

		<table id="DisplayTable" class="Tabel">
			<tbody>
				<tr>
					<th>Name</th>
					<th>Jobe title</th>
					<th>Department name</th>
					<th>Email</th>
					<th>Phone Number</th>
				</tr>
				<%
					for (Staff temp : List) {
				%>
				<tr>
					<c:url var="StaffLink" value="FooServlet">
						<c:param name="Command" value="LoadOneStaff"></c:param>
						<c:param name="StaffID" value="<%=temp.getStringStaffID()%>"></c:param>


					</c:url>
					<td><a href="${ StaffLink}"> <%=temp.getFullName()%></a></td>
					<td><%=temp.getJobTitle()%></td>
					<td><%=temp.getDepartment()%></td>
					<td><%=temp.getEmail()%></td>
					<td><%=temp.getPhoneNo()%></td>
				</tr>
				<%
					}
				%>


			</tbody>
		</table>
		<br>
		<div class="footer">
			<p>totam rem aperiam, eaque ipsa quae ab illo inventore veritatis
				et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim
				ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit,
				sed quia consequuntur magni dolores eos qui ratione voluptatem sequi
				nesciunt.
			<p>
				<img alt="Foo Logo" src="Images/FooLogo.png">
		</div>
	</div>



</body>
<script src="JavaScript/Script.js"></script>
</html>