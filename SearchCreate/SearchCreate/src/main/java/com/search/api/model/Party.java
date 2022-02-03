package com.search.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Party {

	private String Rowid;

	public String getRowid() {
		return Rowid;
	}

	public void setRowid(String rowid) {
		Rowid = rowid;
	}

	private String Name_Prefix_Cd;
	private String First_Name;
	private String Last_Name;
	private String Middle_Name;
	private String party_Type;
	private String Gender_Cd;
	private String display_Name;
	private String phone;
	private String fax;
	private String speciality;
	private String roleType;
	@JsonProperty("Other_Speciality")
	private String Other_Speciality;
	
	private String mdm_id;
	/*
	 * public String getOther_speciality() { return Other_speciality; }
	 * 
	 * public void setOther_speciality(String other_speciality) { Other_speciality =
	 * other_speciality; }
	 */
	@JsonProperty("Account_Nr")
	private String Account_Nr;
	
	@JsonProperty("SOURCE_ID")
	private String SOURCE_ID;
	/* private String Other_speciality; */

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
		return party_Type;
	}

	public void setParty_Type(String party_Type) {
		this.party_Type = party_Type;
	}

	public String getGender_Cd() {
		return Gender_Cd;
	}

	public void setGender_Cd(String gender_Cd) {
		Gender_Cd = gender_Cd;
	}

	public String getDisplay_Name() {
		return display_Name;
	}

	public void setDisplay_Name(String display_Name) {
		this.display_Name = display_Name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getOther_Speciality() {
		return Other_Speciality;
	}

	public void setOther_Speciality(String other_Speciality) {
		Other_Speciality = other_Speciality;
	}

	public String getMdm_id() {
		return mdm_id;
	}

	public void setMdm_id(String mdm_id) {
		this.mdm_id = mdm_id;
	}

	public String getAccount_Nr() {
		return Account_Nr;
	}

	public void setAccount_Nr(String account_Nr) {
		Account_Nr = account_Nr;
	}

	public String getSOURCE_ID() {
		return SOURCE_ID;
	}

	public void setSOURCE_ID(String sOURCE_ID) {
		SOURCE_ID = sOURCE_ID;
	}

	public Party(String rowid, String name_Prefix_Cd, String first_Name, String last_Name, String middle_Name,
			String party_Type, String gender_Cd, String display_Name, String phone, String fax, String speciality,
			String roleType, String other_Speciality, String mdm_id, String account_Nr, String SOURCE_ID) {
		super();
		this.Rowid = rowid;
		this.Name_Prefix_Cd = name_Prefix_Cd;
		this.First_Name = first_Name;
		this.Last_Name = last_Name;
		this.Middle_Name = middle_Name;
		this.party_Type = party_Type;
		this.Gender_Cd = gender_Cd;
		this.display_Name = display_Name;
		this.phone = phone;
		this.fax = fax;
		this.speciality = speciality;
		this.roleType = roleType;
		this.Other_Speciality = other_Speciality;
		this.mdm_id = mdm_id;
		this.Account_Nr = account_Nr;
		this.SOURCE_ID = SOURCE_ID;
	}

	public Party() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	
	
}
