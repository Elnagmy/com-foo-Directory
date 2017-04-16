<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	Staff CurrentStaff = (Staff) request.getAttribute("CurrentStaff");
	
	if(CurrentStaff !=null){
		session.setAttribute("CurrentStaff", CurrentStaff);
	}else{
		CurrentStaff = (Staff) session.getAttribute("CurrentStaff");
	}

%>
<body>
	<div id="wrapper">
		<div id="TopHeader">
			<h2>
				<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <a href="Index.html">Foo
				Directory </a>
			</h2>
		</div>

		<div id="WelcomeDive">
			<img alt="Foo Logo" id="logo" src="Images/FooLogo.png">
			Welcome&nbsp;<%=CurrentStaff.getFullName()%>

		</div>

		<div id="mainArticle">
			<br>
			<div id="Right2">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td>Internal fax</td>
							<td><%=CurrentStaff.getInternal_FAX()%></td>
						</tr>
						<tr>
							<td>External fax</td>
							<td><%=CurrentStaff.getExternal_FAX()%></td>
						</tr>
						<tr>
							<td>Desk Location&nbsp;&nbsp;</td>
							<td><%=CurrentStaff.getDeskLocation()%></td>
						</tr>
					</tbody>
				</table>
				<button class="updateButton" id="Update_Fax"
					onclick="openUpdate('Update_Fax', 'FaxModal' , 0)">Update</button>
				<br>
			</div>


			<!-- Update form -->
			<div class="modal" id="FaxModal">
				<div class="modal-content">
					<div class="modal-header">
						<span class="close">x</span>
						<h2>Update my details form</h2>
					</div>
					<div class="madal-body">
						<form action="FooServlet" method="post">
							<input type="hidden" name="Command" value="UpdateFax">
							<input type="hidden" name="CurrentStaffID" value="<%=CurrentStaff.getStaffId()%>">
							<table class="ProfileTable">
								<tbody>
									<tr>
										<td>Internal fax</td>
										<td><input type="text" name="IntFax"
											value="<%=CurrentStaff.getInternal_FAX()%>"></td>
									</tr>
									<tr>
										<td>External fax</td>
										<td><input type="text" name="extFax"
											value="<%=CurrentStaff.getExternal_FAX()%>"></td>
									</tr>
									<tr>
										<td>Desk Location&nbsp;&nbsp;</td>
										<td><input type="text" name="DeskLocation"
											value="<%=CurrentStaff.getDeskLocation()%>"></td>
									</tr>
								</tbody>
							</table>
							<input style="float: right;" type="submit" value="Submit">
						</form>
					</div>
					<div class="modal-footer">
						<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
						<br>
					</div>
				</div>
			</div>
			<!-- Update form ends-->

			<div id="div1">
				<div id="StaffName">
					<p><%=CurrentStaff.getFullName()%>
					<p>
				</div>
				<br>
				<div id="StaffParamters">
					<img alt="Photo" src="Images/FooLogo.png">
					<p><%=CurrentStaff.getJobTitle()%></p>
					<p><%=CurrentStaff.getDepartment()%></p>
					<p><%=CurrentStaff.getEmail()%></p>
					<p><%=CurrentStaff.getPhoneNo()%></p>

				</div>
				<button class="updateButton" id="Update_personalInfo"
					onclick="openUpdate('Update_personalInfo', 'PersnalModal', 1)">Update</button>
				<br>
			</div>
			<!-- Update form -->
			<div class="modal" id="PersnalModal">
				<div class="modal-content">
					<div class="modal-header">
						<span class="close">x</span>
						<h2>Update my details form</h2>
					</div>
					<div class="madal-body">
						<form action="FooServlet" method="post">
							<input type="hidden" name="Command" value="Update_personalInfo">
							<input type="hidden" name="CurrentStaffID" value="<%=CurrentStaff.getStaffId()%>">
							<table class="ProfileTable">
								<tbody>
									<tr>
										<td>FristName</td>
										<td><input type="text" name="FristName"
											value="<%=CurrentStaff.getFirstName()%>"></td>
									</tr>
									<tr>
										<td>Last Name</td>
										<td><input type="text" name="LastName"
											value="<%=CurrentStaff.getLastName()%>"></td>
									</tr>
									<tr>
										<td>Job Title</td>
										<td><input type="text" name="JobTitle"
											value="<%=CurrentStaff.getJobTitle()%>"></td>
									</tr>
									<tr>
										<td>Department</td>
										<td><input type="text" name="Department"
											value="<%=CurrentStaff.getDepartment()%>"></td>
									</tr>
									<tr>
										<td>Email</td>
										<td><input type="text" name="Email"
											value="<%=CurrentStaff.getEmail()%>"></td>
									</tr>
									<tr>
										<td>Phone Number</td>
										<td><input type="text" name="Phone"
											value="<%=CurrentStaff.getPhoneNo()%>"></td>
									</tr>
									<tr>
										<td>Update Photo</td>
										<td><input type="file" name="photo"></td>
									</tr>
								</tbody>
							</table>
							<input style="float: right;" type="submit" value="Submit">
						</form>
					</div>
					<div class="modal-footer">
						<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
						<br>
					</div>
				</div>
			</div>
			<!-- Update form ends-->

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
								<td><%=CurrentStaff.getCost_Code()%></td>
							</tr>
						</tbody>
					</table>
					<button class="updateButton" id="Update_costCode" onclick="openUpdate('Update_costCode', 'CostCode' ,2)">Update</button>
					<br>
				</div>
			</div>
			<br>
			
			<!-- Update form -->
			<div class="modal" id="CostCode">
				<div class="modal-content">
					<div class="modal-header">
						<span class="close">x</span>
						<h2>Update my details form</h2>
					</div>
					<div class="madal-body">
						<form action="FooServlet" method="post">
							<input type="hidden" name="Command" value="UpdateCostCode">
							<input type="hidden" name="CurrentStaffID" value="<%=CurrentStaff.getStaffId()%>">
							<table class="ProfileTable">
								<tbody>
									<tr>
										<td>Company Name</td>
										<td><input type="text" name="Company" 
											value="Foo Group" disabled="disabled"></td>
									</tr>
									<tr>
										<td>Company Code</td>
										<td><input type="text" name="Company" 
											value="001" disabled="disabled"></td>
									</tr>
									<tr>
										<td>cost code </td>
										<td><input type="text" name="CostCode"
											value="<%=CurrentStaff.getCost_Code()%>"></td>
									</tr>
								</tbody>
							</table>
							<input style="float: right;" type="submit" value="Submit">
						</form>
					</div>
					<div class="modal-footer">
						<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
						<br>
					</div>
				</div>
			</div>
			<!-- Update form ends-->

			<div id="div2" class="ProfileTable">
				<table>
					<tbody>
						<tr>
							<td>Staff ID</td>
							<td><%=CurrentStaff.getStaffId()%></td>
						</tr>
						<tr>
							<td>Entity Manager&nbsp;&nbsp;</td>
							<td><%=CurrentStaff.getManagerName()%></td>
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
							<td><%=CurrentStaff.getPhoneNo()%></td>
						</tr>
						<tr>
							<td>Alternative location</td>
							<td><%=CurrentStaff.getAddress()%></td>
						</tr>
						<tr>
							<td>Alternative telephone</td>
							<td><%=CurrentStaff.getAlternative_Phone()%></td>
						</tr>
					</tbody>
				</table>
				<button class="updateButton" id="Update_contact" onclick="openUpdate('Update_contact', 'ContactForm', 3)">Update</button>
				<br>
							<!-- Update form -->
				<div class="modal" id="ContactForm">
					<div class="modal-content">
						<div class="modal-header">
							<span class="close">x</span>
							<h2>Update my details form</h2>
						</div>
						<div class="madal-body">
							<form action="FooServlet" method="post">
								<input type="hidden" name="Command" value="UpdateContact">
								<input type="hidden" name="CurrentStaffID" value="<%=CurrentStaff.getStaffId()%>">
								<table class="ProfileTable">
									<tbody>
										<tr>
											<td>Alternative telephone</td>
											<td><input type="text" name="AlternatePhone"
												value="<%=CurrentStaff.getAlternative_Phone()%>"></td>
										</tr>
										<tr>
											<td>Address</td>
											<td><textarea cols="30" rows="3" name="Address"
													title="Adress"><%=CurrentStaff.getAddress()%></textarea></td>
										</tr>
									</tbody>
								</table>
								<input style="float: right;" type="submit" value="Submit">
							</form>
						</div>
						<div class="modal-footer">
							<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
							<br>
						</div>
					</div>
				</div>
				<!-- Update form ends-->
			</div>
			<br>
			<div id="div4">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td class="textAria">Role Description and Responsibilities</td>
							<td><%=CurrentStaff.getRoleDescription()%></td>
						</tr>
					</tbody>
				</table>
				<button class="updateButton" id="Update_Role" onclick="openUpdate('Update_Role', 'UpdateRole',4)">Update</button>
				<br>
			</div>
				<!-- Update form -->
				<div class="modal" id="UpdateRole">
					<div class="modal-content">
						<div class="modal-header">
							<span class="close">x</span>
							<h2>Update my details form</h2>
						</div>
						<div class="madal-body">
							<form action="FooServlet" method="post">
								<input type="hidden" name="Command" value="UpdateRole">
								<input type="hidden" name="CurrentStaffID" value="<%=CurrentStaff.getStaffId()%>">
								<table class="ProfileTable">
									<tbody>

										<tr>
											<td>Role Description and Responsibilities</td>
											<td><textarea cols="40" rows="5" name="Role"
													title="Adress"><%=CurrentStaff.getRoleDescription()%></textarea></td>
										</tr>
									</tbody>
								</table>
								<input style="float: right;" type="submit" value="Submit">
							</form>
						</div>
						<div class="modal-footer">
							<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
							<br>
						</div>
					</div>
				</div>
				<!-- Update form ends-->
			<br>
			<div id="div5">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td class="textAria">Skills and Background</td>
							<td><%=CurrentStaff.getSkillsBackground()%></td>
						</tr>
					</tbody>
				</table>
				<button class="updateButton" id="Update_Skills" onclick="openUpdate('Update_Skills', 'Skills_Form',5)">Update</button>
				<br>
			</div>
			<br>
				<!-- Update form -->
				<div class="modal" id="Skills_Form">
					<div class="modal-content">
						<div class="modal-header">
							<span class="close">x</span>
							<h2>Update my details form</h2>
						</div>
						<div class="madal-body">
							<form action="FooServlet" method="post">
								<input type="hidden" name="Command" value="UpdateSkills">
								<input type="hidden" name="CurrentStaffID" value="<%=CurrentStaff.getStaffId()%>">
								<table class="ProfileTable">
									<tbody>

										<tr>
											<td>Skills and Background</td>
											<td><textarea cols="40" rows="5" name="Skills"
													title="Adress"><%=CurrentStaff.getSkillsBackground()%></textarea></td>
										</tr>
									</tbody>
								</table>
								<input style="float: right;" type="submit" value="Submit">
							</form>
						</div>
						<div class="modal-footer">
							<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
							<br>
						</div>
					</div>
				</div>
				<!-- Update form ends-->
			<div id="div6">
				<table class="ProfileTable">
					<tbody>
						<tr>
							<td class="textAria">Interests</td>
							<td><%=CurrentStaff.getInterest()%></td>
						</tr>
					</tbody>
				</table>
				<button class="updateButton" id="Update_Interests" onclick="openUpdate('Update_Interests', 'Interests_Form',6)" >Update</button>
				<br>
			</div>
				<!-- Update form -->
				<div class="modal" id="Interests_Form">
					<div class="modal-content">
						<div class="modal-header">
							<span class="close">x</span>
							<h2>Update my details form</h2>
						</div>
						<div class="madal-body">
							<form action="FooServlet" method="post">
								<input type="hidden" name="Command" value="UpdateInterests">
								<input type="hidden" name="CurrentStaffID" value="<%=CurrentStaff.getStaffId()%>">
								<table class="ProfileTable">
									<tbody>

										<tr>
											<td>Interests</td>
											<td><textarea cols="40" rows="5" name="Interests"
													title="Adress"><%=CurrentStaff.getInterest()%></textarea></td>
										</tr>
									</tbody>
								</table>
								<input style="float: right;" type="submit" value="Submit">
							</form>
						</div>
						<div class="modal-footer">
							<img alt="Foo Logo" id="logo" src="Images/FooLogo.png"> <br>
							<br>
						</div>
					</div>
				</div>
				<!-- Update form ends-->

			<br>
		</div>
		<br> <br>


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