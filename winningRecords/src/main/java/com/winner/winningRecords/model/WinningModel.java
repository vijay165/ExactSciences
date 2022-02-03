package com.winner.winningRecords.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Query;

import microsoft.sql.DateTimeOffset;

@Entity
@Table(name = "PACKAGE_WINNING_RECORDS")
public class WinningModel {

	
	
	/*
	 * @Id //remove this from ID
	 * 
	 * @Column(name = "ROWID_XREF") private String ROWID_XREF;
	 */
	
	 
	
	 @Id
	@Column(name = "winnerSourceid")//Add this as ID in java
	private String winnerSourceId;

	@Column(name = "loserSourceids")
	private String losingSourceIds;

	/*
	 * @Query("select C_BO_PRTY_XREF.SOURCE_ID from C_BO_PRTY_XREF LEFT join  C_BO_PRTY on C_BO_PRTY_XREF.ROWID_OBJECT=C_BO_PRTY.ROWID_OBJECT \r\n"
	 * + "and C_BO_PRTY_XREF.SOURCE_ID !=C_BO_PRTY.SOURCE_ID\r\n" +
	 * "where C_BO_PRTY.SOURCE_ID='CR009678'") List<loserSourceids> getall();
	 */

	@Column(name = "accountName")
	private String accountName;

	@Column(name = "specialty")
	private String specialty;

	@Column(name = "otherSpecialty")
	private String otherSpecialty;

	@Column(name = "phone")
	private String phone;

	@Column(name = "phoneExtn")
	private String phoneExtn;

	@Column(name = "fax")
	private String fax;

	@Column(name = "status")
	private String status;

	@Column(name = "addressLine1")
	private String addressLine1;

	@Column(name = "addressLine2")
	private String addressLine2;

	@Column(name = "zipCode")
	private String zipCode;

	@Column(name = "city")
	private String city;

	@Column(name = "stateCode")
	private String stateCode;

	@Column(name = "countryCode")
	private String countryCode;

	/*
	 * @Column(name = "messageCreatedDatetime") private String
	 * messageCreatedDatetime;
	 */

	/*
	 * ArrayList<String> arguments = new ArrayList<String>();
	 * 
	 * 
	 * 
	 * 
	 * public ArrayList<String> getArguments() { return arguments; }
	 * 
	 * 
	 * public void setArguments(ArrayList<String> arguments) { this.arguments =
	 * arguments; }
	 */

	/*
	 * public String getMessageCreatedDatetime() { return messageCreatedDatetime; }
	 * 
	 * public void setMessageCreatedDatetime(String messageCreatedDatetime) {
	 * this.messageCreatedDatetime = messageCreatedDatetime; }
	 */

	public WinningModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "WinningModel [winnerSourceId=" + winnerSourceId + ", losingSourceIds=" + losingSourceIds
				+ ", accountName=" + accountName + ", specialty=" + specialty + ", otherSpecialty=" + otherSpecialty
				+ ", phone=" + phone + ", phoneExtn=" + phoneExtn + ", fax=" + fax + ", status=" + status
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", zipCode=" + zipCode
				+ ", city=" + city + ", stateCode=" + stateCode + ", countryCode=" + countryCode + "]";
	}



	public WinningModel(String rOWID_XREF, String winnerSourceid, String loserSourceids, String accountName,
			String specialty, String otherSpecialty, String phone, String phoneExtn, String fax, String status,
			String addressLine1, String addressLine2, String zipCode, String city, String stateCode, String countryCode,
			String messageCreatedDatetime) {
		super();
		/* ROWID_XREF = rOWID_XREF; */
		winnerSourceId = winnerSourceid;
		this.losingSourceIds = loserSourceids;
		this.accountName = accountName;
		this.specialty = specialty;
		this.otherSpecialty = otherSpecialty;
		this.phone = phone;
		this.phoneExtn = phoneExtn;
		this.fax = fax;
		this.status = status;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipCode = zipCode;
		this.city = city;
		this.stateCode = stateCode;
		this.countryCode = countryCode;
		/* this.messageCreatedDatetime = messageCreatedDatetime; */
	}

//	public String getROWID_XREF() {
//		return ROWID_XREF;
//	}
//
//	public void setROWID_XREF(String rOWID_XREF) {
//		ROWID_XREF = rOWID_XREF;
//	}

	public String getAccountName() {
		return accountName;
	}

	public String getWinnerSourceId() {
		return winnerSourceId;
	}

	public void setWinnerSourceId(String winnerSourceId) {
		this.winnerSourceId = winnerSourceId;
	}

	public String getLosingSourceIds() {
		return losingSourceIds;
	}

	public void setLosingSourceIds(String losingSourceIds) {
		this.losingSourceIds = losingSourceIds;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getOtherSpecialty() {
		return otherSpecialty;
	}

	public void setOtherSpecialty(String otherSpecialty) {
		this.otherSpecialty = otherSpecialty;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneExtn() {
		return phoneExtn;
	}

	public void setPhoneExtn(String phoneExtn) {
		this.phoneExtn = phoneExtn;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;

	}

	/*
	 * public String messageCreatedDatetime() { return messageCreatedDatetime; }
	 * 
	 * public void setmessageCreatedDatetime(String messageCreatedDatetime) {
	 * this.messageCreatedDatetime = messageCreatedDatetime;
	 * 
	 * 
	 * }
	 */
}
