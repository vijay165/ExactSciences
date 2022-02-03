package com.search.api.model;

public class Address {
	
	private String countryOfOrigin="";
	private String StreetWithHNo="";
	private String countryType="";
	private String LineSeparator="";
	private String preferredLanguage="";
	private String capitalization="";
	private String FormattedAddressWithOrganization="";
	private String street="";
	private String HouseNumber="";
	private String locality="";
	private String postalCode="";
	private String province="";
	private String country="";
	private String Party_type="";
	private String PORTAL_ID="";
	private String Street2="";
	
	
	public String getParty_type() {
		return Party_type;
	}
	public void setParty_type(String party_type) {
		Party_type = party_type;
	}
	
	
	
	
	public String getPORTAL_ID() {
		return PORTAL_ID;
	}
	public void setPORTAL_ID(String pORTAL_ID) {
		PORTAL_ID = pORTAL_ID;
	}
	public String getStreet2() {
		return Street2;
	}
	public void setStreet2(String street2) {
		Street2 = street2;
	}
	public Address(String countryOfOrigin, String countryType, String StreetWithHNo, String LineSeparator, String preferredLanguage,
			String capitalization,String FormattedAddressWithOrganization,String street,String HouseNumber,String locality,String postalCode,
			String province,String country,String PORTAL_ID,String Street2) {
		super();
		this.countryOfOrigin = countryOfOrigin;
		this.countryType = countryType;
		this.StreetWithHNo = StreetWithHNo;
		this.LineSeparator = LineSeparator;
		this.preferredLanguage = preferredLanguage;
		this.capitalization = capitalization;
		this.FormattedAddressWithOrganization = FormattedAddressWithOrganization;
		this.street = street;
		this.HouseNumber = HouseNumber;
		this.locality = locality;
		this.postalCode = postalCode;
		this.province = province;
		this.country = country;
		this.PORTAL_ID = PORTAL_ID;
		this.Street2 = Street2;
		
	}
	
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public String getStreetWithHNo() {
		return StreetWithHNo;
	}
	public void setStreetWithHNo(String streetWithHNo) {
		StreetWithHNo = streetWithHNo;
	}
	public String getCountryType() {
		return countryType;
	}
	public void setCountryType(String countryType) {
		this.countryType = countryType;
	}
	public String getLineSeparator() {
		return LineSeparator;
	}
	public void setLineSeparator(String lineSeparator) {
		LineSeparator = lineSeparator;
	}
	
	public String getPreferredLanguage() {
		return preferredLanguage;
	}
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	
	public String getCapitalization() {
		return capitalization;
	}
	public void setCapitalization(String capitalization) {
		this.capitalization = capitalization;
	}
	public String getFormattedAddressWithOrganization() {
		return FormattedAddressWithOrganization;
	}
	public void setFormattedAddressWithOrganization(String formattedAddressWithOrganization) {
		FormattedAddressWithOrganization = formattedAddressWithOrganization;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return HouseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}
	
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

}
