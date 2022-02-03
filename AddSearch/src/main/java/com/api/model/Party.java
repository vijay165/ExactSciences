package com.api.model;

public class Party {

	private String Rowid=null;
	public String getRowid() {
		return Rowid;
	}

	public void setRowid(String rowid) {
		Rowid = rowid;
	}

	private String Name_Prefix_Cd=null;
	private String First_Name=null;
	private String Last_Name=null;
	private String Middle_Name=null;
	private String Party_Type=null;
	private String Gender_Cd=null;
	private String Display_Name=null;
	private String Phone=null;
	private String Fax=null;
	private String Speciality=null;
	private String RoleType=null;
	private String Other_Speciality=null;

	public String getOther_Speciality() {
		return Other_Speciality;
	}

	public void setOther_Speciality(String other_Speciality) {
		Other_Speciality = other_Speciality;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getFax() {
		return Fax;
	}

	public void setFax(String fax) {
		Fax = fax;
	}

	public String getSpeciality() {
		return Speciality;
	}

	public void setSpeciality(String speciality) {
		Speciality = speciality;
	}

	public String getRoleType() {
		return RoleType;
	}

	public void setRoleType(String roleType) {
		RoleType = roleType;
	}

	/*
	 * public Party() {
	 * 
	 * }
	 */
	public Party(String Name_Prefix_Cd, String First_Name, String Last_Name, String Middle_Name, String Party_Type,
			String Gender_Cd,String Display_Name,String Phone,String Fax,String RoleType,String Speciality,String Other_Speciality) {
		super();
		this.Name_Prefix_Cd = Name_Prefix_Cd;
		this.First_Name = First_Name;
		this.Last_Name = Last_Name;
		this.Middle_Name = Middle_Name;
		this.Party_Type = Party_Type;
		this.Gender_Cd = Gender_Cd;
		this.Display_Name = Display_Name;
		this.Phone = Phone;
		this.Fax = Fax;
		this.Speciality = Speciality;
		this.RoleType = RoleType;
		this.Other_Speciality=Other_Speciality;
	}

	public String getName_Prefix_Cd() {
		return Name_Prefix_Cd;
	}

	public void setName_Prefix_Cd(String name_Prefix_Cd) {
		Name_Prefix_Cd = name_Prefix_Cd;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getMiddle_Name() {
		return Middle_Name;
	}

	public void setMiddle_Name(String middle_Name) {
		Middle_Name = middle_Name;
	}

	public String getParty_Type() {
		return Party_Type;
	}

	public void setParty_Type(String party_Type) {
		Party_Type = party_Type;
	}

	public String getGender_Cd() {
		return Gender_Cd;
	}

	public String getDisplay_Name() {
		return Display_Name;
	}

	public void setDisplay_Name(String display_Name) {
		Display_Name = display_Name;
	}

	public void setGender_Cd(String gender_Cd) {
		Gender_Cd = gender_Cd;
	}

}

