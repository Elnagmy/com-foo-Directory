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
<title>Foo Directory App</title>
</head>

<%
	Staff SelctedStaff = (Staff) request.getAttribute("CurrentStaff");
%>
<body>
	<div id="wrapper">
		<div id="TopHeader">
			<h2>
				<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <a href="Index.html">Foo
				Directory </a>
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
		<br>
		<hr>
		<br>
		<div id="mainArticle">
			<br>
			<div id="Right2">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td>Internal fax</td>
							<td><%=SelctedStaff.getInternal_FAX()%></td>
						</tr>
						<tr>
							<td>External fax</td>
							<td><%=SelctedStaff.getExternal_FAX()%></td>
						</tr>
						<tr>
							<td>Desk Location&nbsp;&nbsp;</td>
							<td><%=SelctedStaff.getDeskLocation()%></td>
						</tr>
					</tbody>
				</table>

			</div>

			<div id="div1">
				<div id="StaffName">
					<p><%=SelctedStaff.getFullName()%>
					<p>
				</div>
				<br>
				<div id="StaffParamters">
					<img alt="Photo" src="Images/FooLogo.png">
					<p><%=SelctedStaff.getJobTitle()%></p>
					<p><%=SelctedStaff.getDepartment()%></p>
					<p><%=SelctedStaff.getEmail()%></p>
					<p><%=SelctedStaff.getPhoneNo()%></p>

				</div>
			</div>

			<div id="Right1">
				<div id="Right1Body">
					<table class="ProfileTable">
							<tbody>
								<tr>
									<td>Company</td>
									<td>foo Company</td>
								</tr>
								<tr>
									<td>Company code</td>
									<td>001</td>
								</tr>
								<tr>
									<td>Sort/transit/cost code&nbsp;&nbsp;</td>
									<td><%=SelctedStaff.getCost_Code()%></td>
								</tr>
							</tbody>
						</table>
				</div>
			</div>
			<br>

			<div id="div2">
				<table>
					<tbody class="ProfileTable">
						<tr>
							<td>Staff ID</td>
							<td><%=SelctedStaff.getStaffId()%></td>
						</tr>
						<c:url var="StaffLink" value="FooServlet">
							<c:param name="Command" value="LoadOneStaff"></c:param>
							<c:param name="StaffID"
								value="<%=SelctedStaff.getStringManagerID()%>"></c:param>


						</c:url>
						<tr>
							<td>Entity Manager&nbsp;&nbsp;</td>
							<td><a href="${StaffLink}"> <%=SelctedStaff.getManagerName()%>
							</a></td>
						</tr>
					</tbody>
				</table>
			</div>


			<br>
			<div id="div3">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td>Mobile phone</td>
							<td><%=SelctedStaff.getPhoneNo()%></td>
						</tr>
						<tr>
							<td>Alternative location</td>
							<td><%=SelctedStaff.getAddress()%></td>
						</tr>
						<tr>
							<td>Alternative telephone</td>
							<td><%=SelctedStaff.getAlternative_Phone()%></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br>
			<div id="div4">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td  class="textAria">Role Description and Responsibilities</td>
							<td><%=SelctedStaff.getRoleDescription()%></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br>
			<div id="div5">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td class="textAria">Skills and Background</td>
							<td><%=SelctedStaff.getSkillsBackground()%></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br>
			<div id="div6">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td class="textAria">Interests</td>
							<td><%=SelctedStaff.getInterest()%></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br>
			<div id="teamList">
				<p>Direct reports</p>
				<br>

				<%
					Iterator<Staff> it = SelctedStaff.getTeamMembers();
					int i = 0;
					while (it.hasNext()) {
						Staff member = (Staff) it.next();
						i++;
				%>
				<p class="teamMember">
					<c:url var="StaffLink" value="FooServlet">
						<c:param name="Command" value="LoadOneStaff"></c:param>
						<c:param name="StaffID" value="<%=member.getStringStaffID()%>"></c:param>


					</c:url>
						<a href="${ StaffLink}"> <%=member.getFullName()%></a>
					</p>
				<%}%>
	
			</div>
			<br>
		</div>
		<br>
	
	<br>
	<div class="footer">
		<p>totam rem aperiam, eaque ipsa quae ab illo inventore veritatis
			et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim
			ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed
			quia consequuntur magni dolores eos qui ratione voluptatem sequi
			nesciunt.
		<p>
			<img alt="Foo Logo" src="Images/FooLogo.png">
	</div>
</div>


</body>
<script src="JavaScript/Script.js"></script>
</html>
