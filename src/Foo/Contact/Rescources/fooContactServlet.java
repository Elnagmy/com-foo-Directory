package Foo.Contact.Rescources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class FooServlet
 */
@WebServlet("/FooServlet")
public class fooContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/foo_directory_database")
	private DataSource ConnectionPool;
	private FooContactDAO DBUtil;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			DBUtil = new FooContactDAO(ConnectionPool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Command = request.getParameter("Command");

		try {

			switch (Command) {
			case "getByName": {
				String Name = request.getParameter("Name");
				ListStaffByName(Name, request, response);
				break;
			}
			case "LoadOneStaff": {
				int StaffId = Integer.parseInt(request.getParameter("StaffID"));
				getStaffByID(StaffId, request, response);
				break;
			}
			
			
			case "DeleteStaff":{
				//Get the Staff By IDs and populate them in the screen 
				getStaffsTodelete(request, response);
				break;
			} 	 	
			
			case "Delete":{
				int StaffId = Integer.parseInt(request.getParameter("StaffID"));
				DeleteByStaffID(StaffId);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AddStaff.jsp");
			    // forward the request object and the response object to the
			    dispatcher.forward(request, response);
			    break;
			}

			default:
				break;
			}
			// set the result to HTML pase
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void DeleteByStaffID(int staffId) throws SQLException {
		
		DBUtil.deleteStaffByID(staffId);
		
	}

	private void getStaffsTodelete(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		String[] StaffIds = request.getParameterValues("StaffID");
		int[] STAFFIDS = new int[StaffIds.length];
		ArrayList<Staff> ListStaff = new ArrayList<Staff>();
		for (int i = 0; i < STAFFIDS.length; i++) {
			STAFFIDS[i] = Integer.parseInt(StaffIds[i]);
			Staff SelectedStaff = DBUtil.getStaffById(STAFFIDS[i]);
			ListStaff.add(SelectedStaff);
		}
		request.setAttribute("SelectedStaffToDelete", ListStaff);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AddStaff.jsp");
	    // forward the request object and the response object to the
	    dispatcher.forward(request, response);
		
		
	}

	private void getStaffByID(int staffId, HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		// Step 1 : get the Staff object from Director DAO
		Staff SelectedStaff = DBUtil.getStaffById(staffId);
		// Step 2 : add this list to the request attributes
		request.setAttribute("CurrentStaff", SelectedStaff);
		// Step 3 : get the request dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewStaffProfile.jsp");
		// Step 4 : forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	private void ListStaffByName(String string, HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException, ServletException {
		// Step 1 : get the list of names from Director DAO
		ArrayList<Staff> StaffList = DBUtil.getStaffByName(string);
		// Step 2 : add this list to the request attributes
		request.setAttribute("StaffList", StaffList);
		// Step 3 : get the requrest dipatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ListStaff.jsp");
		// Step 4 : forward the request object and the response obiect to the
		// jsp page
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Command = request.getParameter("Command");
		try {
				if (Command.equals("Login")) {
						LoginRequest(Integer.parseInt(request.getParameter("UserID")), request.getParameter("password"),
								request, response);
				}
		
					switch (Command) {
			
							case "UpdateFax": {
								// Update internal FAX, External Fax and DeskLocation
					
								updateFaxAndDeskLocation(request, response);
								break;
							}
					
							case "Update_personalInfo": {
								// Update FristName , LastName, JobTitle,Department,Email,Phone, and photo
								updatePersonalInfo(request, response);
								break;
							}
					
							case "UpdateCostCode": {
								// Update CostCode
								updateCostCode(request, response);
								break;
							}
					
							case "UpdateContact": {
								// Update AlternatePhone, Address
								
								updateContact(request, response);
								break;
							}
					
							case "UpdateRole": {
								// Update Role
								updateRole(request, response);
								break;
							}
					
							case "UpdateSkills": {
								// Update Skills
								updateSkills(request, response);
								break;
							}
					
							case "UpdateInterests": {
								// Update Interests
								updateInterests(request, response);
								break;
							}
							
							
							case "LoginAdmin" :{
								//Step 1 check if it is authorize admin login
								processAdminLogin(request, response);
								
								break;
							}
							
							case "AddStaff" :{
								//Add new staff identified by StaffID , FirstName , LastName , JobTitle , Department , Email , ManagerID ,Phone
								processAddStaff(request,response);
								
							}
							
							case "UpdateDirectReport" :{
								//Update the direct report identified ManagerID , and Class name DirectReportStaff
								processUpdateDriectReport(request , response);
							}
					
							default:
								break;
					}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void processUpdateDriectReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//Update the direct report identified ManagerID , and Class name DirectReportStaff
		int ManagerId = Integer.parseInt(request.getParameter("ManagerID"));
		String[] Staffids = request.getParameterValues("StaffID");
		int[] StaffIDs = new int[Staffids.length];
		for (int i= 0 ; i < StaffIDs.length ; i++) {
			StaffIDs[i]= Integer.parseInt(Staffids[i]);
		}
		DBUtil.UpdateDirectReport(ManagerId ,StaffIDs);
		
		// RETURN back to ViewStaffProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AddStaff.jsp");
		// forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	private void processAddStaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//Add new staff identified by StaffID , FirstName , LastName , JobTitle , Department , Email , ManagerID ,Phone
		//Step 1 : Get the Request Parameters 
		int StaffId = Integer.parseInt(request.getParameter("StaffID"));
		String FirstName = request.getParameter("FirstName");
		String LastName = request.getParameter("LastName");
		String JobTitle = request.getParameter("JobTitle");
		String Department = request.getParameter("Department");
		String Email = request.getParameter("Email");
		int ManagerID = Integer.parseInt(request.getParameter("ManagerID"));
		String Phone = request.getParameter("Phone");
		
		//Step 2 : Contract a Staff Object 
		Staff NewStaff = new Staff(StaffId ,FirstName,LastName,JobTitle,Department,Email,ManagerID,Phone);
		
		//Step 3 : Send the contracted object to DBUtil to be inserted in DB; 
		boolean insertDone = DBUtil.insertStaff(NewStaff);
		//Step 4 : Return the requester to the addStaff JSP
		// RETURN back to ViewStaffProfile
		Boolean InsertDone = new Boolean(insertDone);
		request.setAttribute("insertDoneSuccessfuly", InsertDone);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AddStaff.jsp");
	    // forward the request object and the response object to the
	    dispatcher.forward(request, response);
	}

	private void processAdminLogin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, ServletException, IOException {
		boolean admin = false;
		
		Staff LogninStaff = DBUtil.isAdmin(Integer.parseInt(request.getParameter("UserID")), request.getParameter("password"));
		
				if(LogninStaff != null){
					admin =true;
				}
				
		if(admin){
			
			//Select the 1st 100 staff from the Data base to display them 
			ArrayList<Staff> StaffList = DBUtil.SelectFrist100FromDatabase();
			request.setAttribute("Frist100Record", StaffList);
			//Step 2 : set the Request Attribute to authorize the access
			request.setAttribute("AdminAccess", LogninStaff);
			// Step 3 : get the request dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AddStaff.jsp");
			// Step 4 : forward the request object and the response object to
			// the
			dispatcher.forward(request, response);
		} else {
			// Step 2 : add this invalid access to the request attributes
			request.setAttribute("inValidLogin", "False");
			// Step 3 : get the request dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher("/logInadmin.jsp");
			// Step 4 : forward the request object and the response object to
			// the
			// jsp page
			dispatcher.forward(request, response);
		}
	}

	private void updateInterests(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//get the form parameters
		int Staff_Id = Integer.parseInt(request.getParameter("CurrentStaffID"));
		String Interests = request.getParameter("Interests");
		

		// Call Database DAO to update the selected data
		DBUtil.updateInterests(Staff_Id , Interests);
		// RETURN back to ViewStaffProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMyDetails.jsp");
		// forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	private void updateSkills(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//get the form parameters
		int Staff_Id = Integer.parseInt(request.getParameter("CurrentStaffID"));
		String Skills = request.getParameter("Skills");
		
		// Call Database Acessor to update the selected data
		DBUtil.updateSkills(Staff_Id , Skills);
		
		// RETURN back to ViewStaffProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMyDetails.jsp");
		// forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	private void updateRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//get the form parameters
		int Staff_Id = Integer.parseInt(request.getParameter("CurrentStaffID"));
		String Role = request.getParameter("Role");
		
		// Call Database Acessor to update the selected data
		DBUtil.updateRole(Staff_Id, Role);

		// RETURN back to ViewStaffProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMyDetails.jsp");
		// forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	private void updateContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//get the form parameters
		int Staff_Id = Integer.parseInt(request.getParameter("CurrentStaffID"));
		String  AlternatePhone = request.getParameter("AlternatePhone");
		String Address = request.getParameter("Address");

		// Call Database DAO to update the selected data
		DBUtil.updateContact(Staff_Id,AlternatePhone,Address);
		

		// RETURN back to ViewStaffProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMyDetails.jsp");
		// forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	private void updateCostCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//get the form parameters
		int Staff_Id = Integer.parseInt(request.getParameter("CurrentStaffID"));
		String CostCode = request.getParameter("CostCode");
		
		// Contract a Staff object with the parameters
		Staff Update_details = new Staff(Staff_Id, CostCode);

		// Call Database Assessor to update the selected data
		DBUtil.updateCostCode(Update_details);
		
		// RETURN back to ViewStaffProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMyDetails.jsp");
		// forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	private void updatePersonalInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// get the form parameters
		int Staff_Id = Integer.parseInt(request.getParameter("CurrentStaffID"));
		String FristName = request.getParameter("FristName");
		String LastName = request.getParameter("LastName");
		String JobTitle = request.getParameter("JobTitle");
		String Department = request.getParameter("Department");
		String Email = request.getParameter("Email");
		String Phone = request.getParameter("Phone");


		// Contract a Staff object with the parameters
		Staff Update_details = new Staff(Staff_Id, FristName, LastName, JobTitle, Department, Email, Phone);

		// Call Database Acessor to update the selected data
		DBUtil.updatepersonalInfo(Update_details);

		// RETURN back to ViewStaffProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMyDetails.jsp");
		// forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	
	
	private void updateFaxAndDeskLocation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// get the form parameters
		int Staff_Id = Integer.parseInt(request.getParameter("CurrentStaffID"));
		String Internal_FAX = request.getParameter("IntFax");
		String External_FAX = request.getParameter("extFax");
		String DeskLocation = request.getParameter("DeskLocation");

		// Contract a Staff object with the parameters
		Staff Update_details = new Staff(Staff_Id, Internal_FAX, External_FAX, DeskLocation);

		// Call Database DAO to update the selected data
		DBUtil.UpdateFaxAndLocation(Update_details);

		// RETURN back to ViewStaffProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMyDetails.jsp");
		// forward the request object and the response object to the
		dispatcher.forward(request, response);
	}

	
	
	private void LoginRequest(int userID, String password, HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException, ServletException {
		// Step1 : Check if the password it correct if correct display staff by
		// ID
		if (DBUtil.AuthorizedAccess(userID, password)) {
			// Step 1 : get the Staff object from Director DAO
			Staff SelectedStaff = DBUtil.getStaffById(userID);
			// Step 2 : add this list to the request attributes
			request.setAttribute("CurrentStaff", SelectedStaff);
			// Step 3 : get the request dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMyDetails.jsp");
			// Step 4 : forward the request object and the response object to
			// the
			dispatcher.forward(request, response);
		} else {
			// Step 2 : add this list to the request attributes
			request.setAttribute("inValidLogin", "False");
			// Step 3 : get the request dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher("/logIn.jsp");
			// Step 4 : forward the request object and the response object to
			// the
			// jsp page
			dispatcher.forward(request, response);
		}

	}

}
