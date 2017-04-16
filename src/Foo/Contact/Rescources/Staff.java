package Foo.Contact.Rescources;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

public class Staff {
    private int StaffId;
    private String FirstName;
    private String LastName;
    private String JobTitle;
    private String Department;
    private String ManagerName;
    private int ManagerId;
    private String Email;
    private String PhoneNo;
    private Image photo;
    private String FullName;
    private String Cost_Code ;
	private String Address;
    private String Alternative_Phone;
    private String RoleDescription;
    private String SkillsBackground;
    private String Interest;
    private String Internal_FAX;
    private String External_FAX;
    private String DeskLocation ;
    private ArrayList<Staff> myTeamList ;
    
    
    








	public Staff(int id, String internalfax , String externalfax , String desklocation) {
    	
    	StaffId = id;
       Internal_FAX = internalfax;
       External_FAX = externalfax;
      DeskLocation =desklocation ;
    	
    }
    
    
    
    
   


	public Staff(int staffId, String firstName, String lastName, String jobTitle, String department, 
			String email, String phoneNo, Image photo, String cost_Code, String address,
			String alternative_Phone, String roleDescription, String skillsBackground, String interest , String intfax ,String extfax , String desklocation) {
		StaffId = staffId;
		FirstName = firstName;
		LastName = lastName;
		JobTitle = jobTitle;
		Department = department;
		Email = email;
		PhoneNo = phoneNo;
		this.photo = photo;
		Cost_Code = cost_Code;
		Address = address;
		Alternative_Phone = alternative_Phone;
		RoleDescription = roleDescription;
		SkillsBackground = skillsBackground;
		Interest = interest;
		Internal_FAX = intfax;
		External_FAX = extfax;
		DeskLocation = desklocation;
		myTeamList = new ArrayList<Staff>() ;
	}
	
	
	
	public Staff(int staffId, String firstName, String lastName, String jobTitle, String department, String email,
			String phoneNo) {
		StaffId = staffId;
		FirstName = firstName;
		LastName = lastName;
		JobTitle = jobTitle;
		Department = department;
		Email = email;
		PhoneNo = phoneNo;
		myTeamList = new ArrayList<Staff>() ;
	}
	
	public Staff (int staffId , String firstName, String lastName){
		StaffId = staffId;
		FirstName = firstName;
		LastName = lastName;
	}




	public Staff(int staff_Id, String costCode) {
		StaffId=staff_Id;
		Cost_Code=costCode;
	}







	public Staff(int staffId2, String firstName2, String lastName2, String jobTitle2, String department2, String email2,
			int managerID2, String phone) {
		StaffId=staffId2;
		FirstName=firstName2;
		LastName=lastName2;
		JobTitle = jobTitle2;
		Department=department2;
		Email = email2;
		ManagerId=managerID2;
		PhoneNo = phone;
	}







	public int getStaffId() {
		return StaffId;
	}
	public void setStaffId(int staffId) {
		StaffId = staffId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getJobTitle() {
		return JobTitle;
	}
	public void setJobTitle(String jobTitle) {
		JobTitle = jobTitle;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getManagerName() {
		return ManagerName;
	}
	public void setManagerName(String managerName) {
		ManagerName = managerName;
	}
	public int getManagerId() {
		return ManagerId;
	}
	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	public String getFullName() {
		 FullName = FirstName +" "+ LastName;
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getCost_Code() {
		return Cost_Code;
	}
	public void setCost_Code(String cost_Code) {
		Cost_Code = cost_Code;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getAlternative_Phone() {
		return Alternative_Phone;
	}
	public void setAlternative_Phone(String alternative_Phone) {
		Alternative_Phone = alternative_Phone;
	}
	public String getRoleDescription() {
		return RoleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		RoleDescription = roleDescription;
	}
	public String getSkillsBackground() {
		return SkillsBackground;
	}
	public void setSkillsBackground(String skillsBackground) {
		SkillsBackground = skillsBackground;
	}
	public String getInterest() {
		return Interest;
	}
	public void setInterest(String interest) {
		Interest = interest;
	}


	public String getStringStaffID() {
		return StaffId+"";
	}
	
	public String getStringManagerID() {
		return ManagerId+"";
	}
	
	public void addToMyTeam(Staff myteamstaff){
		myTeamList.add(myteamstaff);
	}
	
	public Iterator<Staff> getTeamMembers(){
		return myTeamList.iterator();
	}
	
    public String getInternal_FAX() {
		return Internal_FAX;
	}







	public void setInternal_FAX(String internal_FAX) {
		Internal_FAX = internal_FAX;
	}







	public String getExternal_FAX() {
		return External_FAX;
	}







	public void setExternal_FAX(String external_FAX) {
		External_FAX = external_FAX;
	}







	public String getDeskLocation() {
		return DeskLocation;
	}







	public void setDeskLocation(String deskLocation) {
		DeskLocation = deskLocation;
	}
	

	@Override
	public String toString() {
		return "Staff [Name=" + getFullName() +" StaffId=" + StaffId + ", JobTitle=" + JobTitle + ", Department=" + Department + ", Email="
				+ Email + ", PhoneNo=" + PhoneNo +  "]";
	}

	
	
}
