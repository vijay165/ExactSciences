package com.winner.winningRecords.model;

import java.util.List;

public class WinningModelJSONObject {

	public String winnerSourceId;

	public List<String> losingSourceIds;//Sasi handled this in package with comma separated, removed this comma separated code in java

	public String accountName;

	public String specialty;

	public String otherSpecialty;

	public String phone;

	public String phoneExtn;

	public String fax;

	public String status;

	public String addressLine1;

	public String addressLine2;

	public String zipCode;

	public String city;

	public String stateCode;

	public String countryCode;
	
	public List accountsList;

	public String getWinnerSourceId() {
		return winnerSourceId;
	}

	public void setWinnerSourceId(String winnerSourceId) {
		this.winnerSourceId = winnerSourceId;
	}

	public List<String> getLosingSourceIds() {
		return losingSourceIds;
	}

	public void setLosingSourceIds(List<String> losingSourceIds) {
		this.losingSourceIds = losingSourceIds;
	}

	public String getAccountName() {
		return accountName;
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
	
	/* public String messageCreatedDatetime; */
	
}
