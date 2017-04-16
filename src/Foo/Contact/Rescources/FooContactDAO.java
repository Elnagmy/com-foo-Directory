package Foo.Contact.Rescources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class FooContactDAO {
	DataSource ConnectionPool;

	public FooContactDAO(DataSource dataSource) {
		ConnectionPool = dataSource;
	}

	public ArrayList<Staff> getStaffByName(String Name) throws SQLException, ClassNotFoundException {
		ArrayList<Staff> StaffList = new ArrayList<Staff>();

		// Step1 Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		ResultSet Result = null;
		try {
			// step2 get the connection
			MyDBConnection = ConnectionPool.getConnection();
			// Step3 Write the SQL statement
			String SQLStat = "SELECT * FROM foo_directory_database.staff_tbl" + " where Full_Name like ?";
			// Step4 create the SQL statment Object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			sqlQuary.setString(1, "%" + Name + "%");
			// step5 EXcute the SQL query and get the Result set
			Result = sqlQuary.executeQuery();
			// Step6 process the result set
			while (Result.next()) {
				int StaffId = Result.getInt("Staff_ID");
				String firstname = Result.getString("First_Name");
				String lastname = Result.getString("Last_Name");
				String jobTitle = Result.getString("Job_Title");
				String department = Result.getString("Department");
				// int managerID = Result.getInt("Manager_ID");
				String email = Result.getString("Email");
				String phone = Result.getString("Phone_number");

				// step 8 : create a staffobject and store in the ArrayList
				Staff SelectedStaff = new Staff(StaffId, firstname, lastname, jobTitle, department, email, phone);
				// SelectedStaff.setManagerId(managerID);
				// step 9 : return the array List to the coller
				StaffList.add(SelectedStaff);
			}
			return StaffList;
		} finally {
			close(MyDBConnection, sqlQuary, Result);
		}

	}

	public Staff getStaffById(int staffId) throws SQLException, ClassNotFoundException {
		// Step1 Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		ResultSet Result = null;
		try {
			// step2 get the connection
			MyDBConnection = ConnectionPool.getConnection();
			// Step3 Write the SQL statement
			String SQLStat = "SELECT * FROM foo_directory_database.staff_tbl" + " where Staff_ID= ?";
			// Step4 create the SQL statment Object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			sqlQuary.setInt(1, staffId);
			// step5 EXcute the SQL query and get the Result set
			Result = sqlQuary.executeQuery();
			// Step6 process the result set
			Staff SelectedStaff = null;
			while (Result.next()) {
				int StaffId = Result.getInt("Staff_ID");
				String firstname = Result.getString("First_Name");
				String lastname = Result.getString("Last_Name");
				String jobTitle = Result.getString("Job_Title");
				String department = Result.getString("Department");
				int managerID = Result.getInt("ManagerID");
				String email = Result.getString("Email");
				String phone = Result.getString("Phone_number");
				String CostCode = Result.getString("Cost_Code");
				String Address = Result.getString("Address");
				String AlternativePhone = Result.getString("Alternative_Phone");
				String InternalFax = Result.getString("Internal_Fax");
				String ExternalFax = Result.getString("External_Fax");
				String Desk_Location = Result.getString("Desk_Location");
				String RoleDescription = Result.getString("Role_Description");
				String SkillsBackground = Result.getString("Skills_Background");
				String Interest = Result.getString("Interest");

				SelectedStaff = new Staff(StaffId, firstname, lastname, jobTitle, department, email, phone, null,
						CostCode, Address, AlternativePhone, RoleDescription, SkillsBackground, Interest, InternalFax , ExternalFax , Desk_Location);
				SelectedStaff.setManagerId(managerID);
				String ManagerName = getMangerName(managerID);
				SelectedStaff.setManagerName(ManagerName);
				addTeamMembers(StaffId, SelectedStaff);

			}
			return SelectedStaff;
		} finally {
			close(MyDBConnection, sqlQuary, Result);
		}

	}

	private void addTeamMembers(int staffId, Staff SelectedStaff) throws SQLException {
		// Step1 Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		ResultSet Result = null;
		try {
			// step2 get the connection
			MyDBConnection = ConnectionPool.getConnection();
			// Step3 Write the SQL statement
			String SQLStat = "SELECT Staff_ID ,First_Name,Last_Name  FROM foo_directory_database.staff_tbl"
					+ " where ManagerID=?";
			// Step4 create the SQL statment Object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			sqlQuary.setInt(1, staffId);
			// step5 EXcute the SQL query and get the Result set
			Result = sqlQuary.executeQuery();
			// Step6 process the result set
			while (Result.next()) {
				int StaffId = Result.getInt("Staff_ID");
				String firstname = Result.getString("First_Name");
				String lastname = Result.getString("Last_Name");
				Staff TeamMember = new Staff(StaffId, firstname, lastname);
				SelectedStaff.addToMyTeam(TeamMember);
			}

		} finally {
			close(MyDBConnection, sqlQuary, Result);
		}

	}

	private String getMangerName(int managerID) throws SQLException {
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		ResultSet Result = null;

		try {
			// step2 get the connection
			MyDBConnection = ConnectionPool.getConnection();
			// Step3 Write the SQL statement
			String SQLStat = "SELECT Full_Name FROM foo_directory_database.staff_tbl" + " where Staff_ID= ?";
			// Step4 create the SQL statment Object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			sqlQuary.setInt(1, managerID);
			// step5 EXcute the SQL query and get the Result set
			Result = sqlQuary.executeQuery();
			String ManagerName = null;
			while (Result.next()) {
				ManagerName = Result.getString("Full_Name");
			}
			return ManagerName;
		} finally {
			close(MyDBConnection, sqlQuary, Result);
		}

	}

	private void close(Connection myDBConnection, PreparedStatement sqlQuary, ResultSet result) throws SQLException {
		if (myDBConnection != null) {
			myDBConnection.close();
		}

		if (sqlQuary != null) {
			sqlQuary.close();
		}

		if (result != null) {
			result.close();
		}
	}

	public boolean AuthorizedAccess(int userID, String password) throws SQLException {
		// Step1 Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		ResultSet Result = null;
		try {
			// step2 get the connection
			MyDBConnection = ConnectionPool.getConnection();
			// Step3 Write the SQL statement
			String SQLStat = "SELECT Staff_Id FROM foo_directory_database.password_tbl"
					+ " where Staff_Id = ? and Password = sha1(?)";
			// Step4 create the SQL statment Object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);

			// replace the paramter argument
			sqlQuary.setInt(1, userID);
			sqlQuary.setString(2, password);
			// step5 EXcute the SQL query and get the Result set
			Result = sqlQuary.executeQuery();
			// Step6 process the result set
			while (Result.next()) {
				if (Result.getString("Staff_Id") != null) {
					int StaffID = Integer.parseInt(Result.getString("Staff_Id"));
					if (userID == StaffID) {
						return true;
					} else {
						return false;
					}
				}

			}

		} finally {
			close(MyDBConnection, sqlQuary, Result);
		}
		return false;

	}

	public void UpdateFaxAndLocation(Staff update_details) throws SQLException {
		//Update internal FAX, External Fax and DeskLocation
		
		//Step 1 : Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		//Step 2 : try to get the connection to database
		try{
			MyDBConnection = ConnectionPool.getConnection();
			//Step 3 : write the Update statements with parameters as ?
			String SQLStat = "UPDATE foo_directory_database.staff_tbl"
					+ " SET Internal_Fax=? , External_Fax=? , Desk_Location = ?"
					+ " WHERE Staff_ID=?; " ;
			//Step 4 : create the prepared Statement object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			//Step 5 : replace the ? with the  parameters
			sqlQuary.setString(1,update_details.getInternal_FAX() );
			sqlQuary.setString(2,update_details.getExternal_FAX() );
			sqlQuary.setString(3,update_details.getDeskLocation() );
			sqlQuary.setInt(4,update_details.getStaffId());
			//Step 6 : execute the Query
			sqlQuary.execute();
		}finally{
			//Step 7 : Close the connection
			close(MyDBConnection, sqlQuary, null);
		}

		
		
		
	}

	public void updatepersonalInfo(Staff update_details) throws SQLException {
		// Update FristName , LastName, JobTitle,Department,Email,Phone, and photo
		
				//Step 1 : Create the JDBC objects
				Connection MyDBConnection = null;
				PreparedStatement sqlQuary = null;
				//Step 2 : try to get the connection to database
				try{
					MyDBConnection = ConnectionPool.getConnection();
					//Step 3 : write the Update statements with parameters as ?
					String SQLStat = "UPDATE foo_directory_database.staff_tbl"
							+ " SET First_Name=? , Last_Name=? , Job_Title = ? , Department = ? , Email = ? , Phone_number = ?"
							+ " WHERE Staff_ID=?; " ;
					//Step 4 : create the prepared Statement object
					sqlQuary = MyDBConnection.prepareStatement(SQLStat);
					//Step 5 : replace the ? with the  parameters
					sqlQuary.setString(1,update_details.getFirstName() );
					sqlQuary.setString(2,update_details.getLastName() );
					sqlQuary.setString(3,update_details.getJobTitle() );
					sqlQuary.setString(4,update_details.getDepartment() );
					sqlQuary.setString(5,update_details.getEmail());
					sqlQuary.setString(6,update_details.getPhoneNo());
					sqlQuary.setInt(7,update_details.getStaffId());
					//Step 6 : execute the Query
					sqlQuary.execute();
				}finally{
					//Step 7 : Close the connection
					close(MyDBConnection, sqlQuary, null);
				}

	}

	public void updateCostCode(Staff update_details) throws SQLException {
		// Update CostCode
		//Step 1 : Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		//Step 2 : try to get the connection to database
		try{
			MyDBConnection = ConnectionPool.getConnection();
			//Step 3 : write the Update statements with parameters as ?
			String SQLStat = "UPDATE foo_directory_database.staff_tbl"
					+ " SET Cost_Code=?"
					+ " WHERE Staff_ID=?; " ;
			//Step 4 : create the prepared Statement object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			//Step 5 : replace the ? with the  parameters
			sqlQuary.setString(1,update_details.getCost_Code());
			sqlQuary.setInt(2,update_details.getStaffId());
			//Step 6 : execute the Query
			sqlQuary.execute();
		}finally{
			//Step 7 : Close the connection
			close(MyDBConnection, sqlQuary, null);
		}

	}


	public void updateRole(int staff_Id, String role) throws SQLException {
		// Update Role
		
		//Step 1 : Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		//Step 2 : try to get the connection to database
		try{
			MyDBConnection = ConnectionPool.getConnection();
			//Step 3 : write the Update statements with parameters as ?
			String SQLStat = "UPDATE foo_directory_database.staff_tbl"
					+ " SET Role_Description=?"
					+ " WHERE Staff_ID=?; " ;
			//Step 4 : create the prepared Statement object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			//Step 5 : replace the ? with the  parameters
			sqlQuary.setString(1,role);
			sqlQuary.setInt(2,staff_Id);
			//Step 6 : execute the Query
			sqlQuary.execute();
		}finally{
			//Step 7 : Close the connection
			close(MyDBConnection, sqlQuary, null);
		}
	}

	public void updateSkills(int staff_Id, String skills) throws SQLException {
		// Update Skills
		
			//Step 1 : Create the JDBC objects
				Connection MyDBConnection = null;
				PreparedStatement sqlQuary = null;
				//Step 2 : try to get the connection to database
				try{
					MyDBConnection = ConnectionPool.getConnection();
					//Step 3 : write the Update statements with parameters as ?
					String SQLStat = "UPDATE foo_directory_database.staff_tbl"
							+ " SET Skills_Background=?"
							+ " WHERE Staff_ID=?; " ;
					//Step 4 : create the prepared Statement object
					sqlQuary = MyDBConnection.prepareStatement(SQLStat);
					//Step 5 : replace the ? with the  parameters
					sqlQuary.setString(1,skills);
					sqlQuary.setInt(2,staff_Id);
					//Step 6 : execute the Query
					sqlQuary.execute();
				}finally{
					//Step 7 : Close the connection
					close(MyDBConnection, sqlQuary, null);
				}

	}

	public void updateInterests(int staff_Id, String interests) throws SQLException {
		// Update Interests
		
		//Step 1 : Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		//Step 2 : try to get the connection to database
		try{
			MyDBConnection = ConnectionPool.getConnection();
			//Step 3 : write the Update statements with parameters as ?
			String SQLStat = "UPDATE foo_directory_database.staff_tbl"
					+ " SET Interest=?"
					+ " WHERE Staff_ID=?; " ;
			//Step 4 : create the prepared Statement object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			//Step 5 : replace the ? with the  parameters
			sqlQuary.setString(1,interests);
			sqlQuary.setInt(2,staff_Id);
			//Step 6 : execute the Query
			sqlQuary.execute();
		}finally{
			//Step 7 : Close the connection
			close(MyDBConnection, sqlQuary, null);
		}


	}

	public void updateContact(int staff_Id, String alternatePhone, String address) throws SQLException {
		// Update AlternatePhone, Address
		//Step 1 : Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		//Step 2 : try to get the connection to database
		try{
			MyDBConnection = ConnectionPool.getConnection();
			//Step 3 : write the Update statements with parameters as ?
			String SQLStat = "UPDATE foo_directory_database.staff_tbl"
					+ " SET Alternative_Phone=? , Address =?"
					+ " WHERE Staff_ID=?; " ;
			//Step 4 : create the prepared Statement object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			//Step 5 : replace the ? with the  parameters
			sqlQuary.setString(1,alternatePhone);
			sqlQuary.setString(2,address);
			sqlQuary.setInt(3,staff_Id);
			//Step 6 : execute the Query
			sqlQuary.execute();
		}finally{
			//Step 7 : Close the connection
			close(MyDBConnection, sqlQuary, null);
		}
		
	}

	public Staff isAdmin(int userID , String password) throws SQLException, ClassNotFoundException {
				// Step1 Create the JDBC objects
				Connection MyDBConnection = null;
				PreparedStatement sqlQuary = null;
				ResultSet Result = null;
				try {
					// step2 get the connection
					MyDBConnection = ConnectionPool.getConnection();
					// Step3 Write the SQL statement
					String SQLStat = "SELECT Staff_Id FROM foo_directory_database.password_tbl"
							+ " where Staff_Id = ? and Password = sha1(?)"
							+" and Permission_Role ='Admin'";
					// Step4 create the SQL statment Object
					sqlQuary = MyDBConnection.prepareStatement(SQLStat);

					// replace the paramter argument
					sqlQuary.setInt(1, userID);
					sqlQuary.setString(2, password);
					// step5 EXcute the SQL query and get the Result set
					Result = sqlQuary.executeQuery();
					// Step6 process the result set
					while (Result.next()) {
						if (Result.getString("Staff_Id") != null) {
							int StaffID = Integer.parseInt(Result.getString("Staff_Id"));
							if (userID == StaffID) {
								return getStaffById(StaffID);
							} else {
								return null;
							}
						}

					}

				} finally {
					close(MyDBConnection, sqlQuary, Result);
				}
				return null;
	}

	public boolean insertStaff(Staff newStaff) throws SQLException {
		//Add new staff identified by StaffID , FirstName , LastName , JobTitle , Department , Email , ManagerID ,Phone
		// Step1 Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		try {
			// step2 get the connection
			try {
				MyDBConnection = ConnectionPool.getConnection();
				// Step3 Write the SQL statement
				String SQLStat = "Insert into foo_directory_database.Staff_tbl"
						+ " (Staff_ID , First_Name , Last_Name , Full_Name , Job_Title , Department , Email , Phone_number ,ManagerID) "
						+" values( ? , ? , ? , ? , ? , ? , ? , ? , ? )";
				// Step4 create the SQL statment Object
				sqlQuary = MyDBConnection.prepareStatement(SQLStat);

				// replace the paramter argument
				sqlQuary.setInt(1, newStaff.getStaffId());
				sqlQuary.setString(2, newStaff.getFirstName());
				sqlQuary.setString(3, newStaff.getLastName());
				String fullname = newStaff.getFirstName()+" "+newStaff.getLastName();
				sqlQuary.setString(4,fullname);
				sqlQuary.setString(5, newStaff.getJobTitle());
				sqlQuary.setString(6, newStaff.getDepartment());
				sqlQuary.setString(7, newStaff.getEmail());
				sqlQuary.setString(8, newStaff.getPhoneNo());
				sqlQuary.setInt(9, newStaff.getManagerId());
				sqlQuary.execute();
				create_login(newStaff.getStaffId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
		
	} finally {
		close(MyDBConnection, sqlQuary, null);
	}
		return true;
  }

	private void create_login(int staffId) throws SQLException {
		
		// Step1 Create the JDBC objects
				Connection MyDBConnection = null;
				PreparedStatement sqlQuary = null;
				try {
					// step2 get the connection
					MyDBConnection = ConnectionPool.getConnection();
					// Step3 Write the SQL statement
					String SQLStat = "Insert into foo_directory_database.password_tbl"
							+ " (Staff_ID , Password )"
							+" values( ? , sha1('password'))";
					// Step4 create the SQL statment Object
					sqlQuary = MyDBConnection.prepareStatement(SQLStat);

					// replace the paramter argument
					sqlQuary.setInt(1, staffId);
					sqlQuary.execute();
				} finally {
					close(MyDBConnection, sqlQuary, null);
				}
		
	}

	public void UpdateDirectReport(int managerId, int[] staffIDs) throws SQLException {
		// Step1 Create the JDBC objects
				Connection MyDBConnection = null;
				PreparedStatement sqlQuary = null;
				try {
					// step2 get the connection
					MyDBConnection = ConnectionPool.getConnection();
					// Step3 Write the SQL statement
					String SQLStat = "Update foo_directory_database.Staff_tbl"
							+ " set ManagerID =? where Staff_ID = ?" ;
					// Step4 create the SQL statment Object
					sqlQuary = MyDBConnection.prepareStatement(SQLStat);

					// replace the paramter argument
					sqlQuary.setInt(1, managerId);
					for (int i = 0; i < staffIDs.length; i++) {
						sqlQuary.setInt(2, staffIDs[i]);
						sqlQuary.execute();
						
					}
		
				} finally {
					close(MyDBConnection, sqlQuary, null);
				}
	}

	public ArrayList<Staff> SelectFrist100FromDatabase() throws SQLException {
		//select the first 100 record from the data base and forward them back as an ArrayList
		ArrayList<Staff> StaffList = new ArrayList<Staff>();
		// Step1 Create the JDBC objects
				Connection MyDBConnection = null;
				PreparedStatement sqlQuary = null;
				ResultSet Result = null;
				try {
					// step2 get the connection
					MyDBConnection = ConnectionPool.getConnection();
					// Step3 Write the SQL statement
					String SQLStat = "SELECT Staff_ID, First_Name , Last_Name,Job_Title , Department, Email ,Phone_number "
							+ "  FROM foo_directory_database.staff_tbl" + 
							" where Staff_ID <100";
					// Step4 create the SQL statment Object
					sqlQuary = MyDBConnection.prepareStatement(SQLStat);
					// step5 EXcute the SQL query and get the Result set
					Result = sqlQuary.executeQuery();
					// Step6 process the result set
					while (Result.next()) {
						int StaffId = Result.getInt("Staff_ID");
						String firstname = Result.getString("First_Name");
						String lastname = Result.getString("Last_Name");
						String jobTitle = Result.getString("Job_Title");
						String department = Result.getString("Department");
						//int managerID = Result.getInt("Manager_ID");
						String email = Result.getString("Email");
						String phone = Result.getString("Phone_number");

						// step 8 : create a staffobject and store in the ArrayList
						Staff SelectedStaff = new Staff(StaffId, firstname, lastname, jobTitle, department, email, phone);
						// SelectedStaff.setManagerId(managerID);
						// step 9 : return the array List to the coller
						addTeamMembers(StaffId, SelectedStaff);
						StaffList.add(SelectedStaff);
					}
					return StaffList;
				} finally {
					close(MyDBConnection, sqlQuary, Result);
				}
	}

	
	
	
	public void deleteStaffByID(int staffId) throws SQLException {
		// Step1 Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		try {
			// step2 get the connection
			MyDBConnection = ConnectionPool.getConnection();
			// Step3 Write the SQL statement
			String SQLStat = "Delete "
					+ "  FROM foo_directory_database.staff_tbl" + 
					" where Staff_ID = ?";
			// Step4 create the SQL statment Object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			// step5 EXcute the SQL query and get the Result set
			sqlQuary.setInt(1, staffId);
			sqlQuary.execute();
			DeleteLogin(staffId);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		close(MyDBConnection, sqlQuary, null);
	}
}

	private void DeleteLogin(int staffId) throws SQLException {
		// Step1 Create the JDBC objects
		Connection MyDBConnection = null;
		PreparedStatement sqlQuary = null;
		try {
			// step2 get the connection
			MyDBConnection = ConnectionPool.getConnection();
			// Step3 Write the SQL statement
			String SQLStat = "Delete "
					+ "  FROM foo_directory_database.password_tbl" + 
					" where Staff_ID = ?";
			// Step4 create the SQL statement Object
			sqlQuary = MyDBConnection.prepareStatement(SQLStat);
			// step5 EXcute the SQL query and get the Result set
			sqlQuary.setInt(1, staffId);
			sqlQuary.execute();
		
	} finally {
		close(MyDBConnection, sqlQuary, null);
	}
		
	}
	
}
