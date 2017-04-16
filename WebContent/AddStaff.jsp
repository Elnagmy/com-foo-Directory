<%@page import="com.mysql.jdbc.EscapeTokenizer"%>
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
<link type="text/CSS" rel="stylesheet" href="CSS/ViewStaff.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<title>Foo Directory App</title>
</head>

<%
	ArrayList<Staff> List = (ArrayList<Staff>) request.getAttribute("SelectedStaffToDelete");
	if (List == null) {
		List = (ArrayList<Staff>) request.getAttribute("Frist100Record");
		if (List != null)
			session.setAttribute("StaffList", List);
	} else {
		session.setAttribute("StaffList", List);
	}

	if (List == null) {
		List = (ArrayList<Staff>) session.getAttribute("StaffList");
	}

	Staff CurrentStaff = (Staff) request.getAttribute("AdminAccess");
	if (CurrentStaff != null) {
		session.setAttribute("CurrentStaff", CurrentStaff);
	} else {
		CurrentStaff = (Staff) session.getAttribute("CurrentStaff");
	}
%>
<body>
	<div id="wrapper">
		<div id="TopHeader">
			<h2>
				<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <a
					href="Index.html">Foo Directory </a>
			</h2>
		</div>

		<div id="WelcomeDive">
			<img alt="Foo Logo" id="logo" src="Images/FooLogo.png">
			Welcome&nbsp;<%=CurrentStaff.getFullName()%>
			<div class="dropdown">
				<button class="dropbtn">Admin Role</button>
				<div class="dropdown-content">
					<a id="AddStaff" onclick="openForm('AddStaff', 'AddForm' , 0)">Add
						new Staff</a> <a id="UpdateDriectReport"
						onclick="openForm('UpdateDriectReport', 'AddDirectreportForm' , 1)">Update
						Direct Report</a> <a id="DeleteLink"
						onclick="openForm('DeleteLink', 'delete_Staff_form' , 2)">Delete
						Staff</a>
				</div>
			</div>
		</div>
		<!-- Admin Add Staff form -->
		<div class="modal" id="AddForm">
			<div class="modal-content">
				<div class="modal-header">
					<span class="closeForm">x</span>
					<h2>Update my details form</h2>
				</div>
				<div class="madal-body">
					<form action="FooServlet" method="post">
						<input type="hidden" name="Command" value="AddStaff">
						<table class="ProfileTable">
							<tbody>
								<tr>
									<td>Staff ID</td>
									<td><input type="text" name="StaffID"></td>
								</tr>
								<tr>
									<td>First Name</td>
									<td><input type="text" name="FirstName"></td>
								</tr>
								<tr>
									<td>Last Name</td>
									<td><input type="text" name="LastName"></td>
								</tr>
								<tr>
									<td>Job Title</td>
									<td><input type="text" name="JobTitle"></td>
								</tr>
								<tr>
									<td>Department</td>
									<td><input type="text" name="Department"></td>
								</tr>
								<tr>
									<td>Email</td>
									<td><input type="text" name="Email"></td>
								</tr>
								<tr>
									<td>Manager ID</td>
									<td><input type="text" name="ManagerID"></td>
								</tr>
								<tr>
									<td>Phone number</td>
									<td><input type="text" name="Phone"></td>
								</tr>
							</tbody>
						</table>
						<input style="float: right;" type="submit" value="Submit">
					</form>
				</div>
				<div class="modal-footer">
					<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
					<p>Login user ID and password create automatically please
						update if there are any staff direct reports to the added staff
					<p>
				</div>
			</div>
		</div>

		<!-- Admin AddDirectreport Staff form -->
		<div class="modal" id="AddDirectreportForm">
			<div class="modal-content">
				<div class="modal-header">
					<span class="closeForm">x</span>
					<h2>Update my details form</h2>
				</div>
				<div class="madal-body">
					<form action="FooServlet" method="post">
						<input type="hidden" name="Command" value="UpdateDirectReport">
						<table class="ProfileTable">
							<tbody id="AddDriectReport">
								<tr>
									<td>Manage ID</td>
									<td><input type="text" name="ManagerID"></td>
								</tr>
								<tr>
									<td>Direct Report StaffID</td>
									<td><input type="text" name="StaffID"></td>
									<td><input type="button" value="Add more" id="AddStaff"
										onclick="addMore('AddDriectReport' , 'AddStaff')"></td>
								</tr>

							</tbody>
						</table>

						<input style="float: right;" type="submit" value="Submit">
					</form>
				</div>
				<div class="modal-footer">
					<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
					<p>Enter the New Manager Id and all staffIds who you want to
						amend their manager ID.
					<p>
				</div>
			</div>
		</div>

		<!-- Admin delete Staff form -->
		<div class="modal" id="delete_Staff_form">
			<div class="modal-content">
				<div class="modal-header">
					<span class="closeForm">x</span>
					<h2>Delete Staff</h2>
				</div>
				<div class="madal-body">
					<form action="FooServlet" method="get">
						<input type="hidden" name="Command" value="DeleteStaff">
						<table class="ProfileTable">
							<tbody id="DeleteTableID">
								<tr>
									<td>StaffID</td>
									<td><input type="text" name="StaffID"></td>
									<td><input type="button" value="Add more"
										id="AddMoreDelete"
										onclick="addMortoDelete ('DeleteTableID' , 'AddMoreDelete')"></td>
								</tr>

							</tbody>
						</table>

						<input style="float: right;" type="submit" value="Submit">
					</form>
				</div>
				<div class="modal-footer">
					<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"><br>
					<p>If you are attempting to delete a people manager staffs you
						will need to update the direct reports members first.
					<p>
				</div>
			</div>
		</div>
		<br>
		<div>
			<h3>&nbsp; Staff List :</h3>

		</div>

		<div id="StaffList">
			<table id="DisplayTable">
				<tbody>
					<tr>
						<th class="Tablepadding">Name</th>
						<th>Jobe title</th>
						<th>Department name</th>
						<th>Email</th>
						<th>Phone Number</th>
						<th>Staffs direct reports</th>
					</tr>
					<%
						for (Staff temp : List) {
					%>
					<tr>
						<c:url var="StaffLink" value="FooServlet">
							<c:param name="Command" value="LoadOneStaff"></c:param>
							<c:param name="StaffID" value="<%=temp.getStringStaffID()%>"></c:param>


						</c:url>
						<td class="Tablepadding"><a href="${ StaffLink}"> <%=temp.getFullName()%></a></td>
						<td><%=temp.getJobTitle()%></td>
						<td><%=temp.getDepartment()%></td>
						<td><%=temp.getEmail()%></td>
						<td><%=temp.getPhoneNo()%></td>
						<c:url var="Deletelink" value="FooServlet">
							<c:param name="Command" value="Delete"></c:param>
							<c:param name="StaffID" value="<%=temp.getStringStaffID()%>"></c:param>
						</c:url>
						<td><p>
								<%
									Iterator<Staff> it = temp.getTeamMembers();
										int i = 0;
										int staffID = 0;
										while (it.hasNext()) {
											staffID = it.next().getStaffId();
								%>
								<c:url var="drectreportlink" value="FooServlet">
									<c:param name="Command" value="LoadOneStaff"></c:param>
									<c:param name="StaffID" value="<%=Integer.toString(staffID)%>"></c:param>
								</c:url>
								<a href="${drectreportlink}"> <%
								 	out.print(staffID);
								 			i++;
								 			if (i % 5 == 0) {
								 				out.println("<br>");
								 			}
								 %>
								</a>
								<%
									}
										if (i == 0) {
								%>
								<a href="${Deletelink}"
									onclick="if (!(confirm('Are you sure you want to delete this staff permanently?')))return false;">Delete</a>
								<%
									}
								%>
							</p></td>
					</tr>
					<%
						}
					%>

				</tbody>
			</table>
		</div>
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
