package com.api.model;

public class Address {
	
	private String CountryOfOrigin=null;
	private String StreetWithHNo=null;
	private String CountryType=null;
	private String LineSeparator=null;
	private String PreferredLanguage=null;
	private String Capitalization=null;
	private String FormattedAddressWithOrganization=null;
	private String Street=null;
	private String HouseNumber=null;
	private String Locality=null;
	private String PostalCode=null;
	private String Province=null;
	private String Country=null;
	private String Party_type=null;
	private String Street2;
	
	public String getParty_type() {
		return Party_type;
	}
	public void setParty_type(String party_type) {
		Party_type = party_type;
	}
	public Address(String CountryOfOrigin, String CountryType, String StreetWithHNo, String LineSeparator, String PreferredLanguage,
			String Capitalization,String FormattedAddressWithOrganization,String Street,String HouseNumber,String Locality,String PostalCode,
			String Province,String Country,String Street2) {
		super();
		this.CountryOfOrigin = CountryOfOrigin;
		this.CountryType = CountryType;
		this.StreetWithHNo = StreetWithHNo;
		this.LineSeparator = LineSeparator;
		this.PreferredLanguage = PreferredLanguage;
		this.Capitalization = Capitalization;
		this.FormattedAddressWithOrganization = FormattedAddressWithOrganization;
		this.Street = Street;
		this.HouseNumber = HouseNumber;
		this.Locality = Locality;
		this.PostalCode = PostalCode;
		this.Province = Province;
		this.Country = Country;
		this.Street2=Street2;
	}
	
	public String getStreet2() {
		return Street2;
	}
	public void setStreet2(String street2) {
		Street2 = street2;
	}
	public String getCountryOfOrigin() {
		return CountryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		CountryOfOrigin = countryOfOrigin;
	}
	public String getStreetWithHNo() {
		return StreetWithHNo;
	}
	public void setStreetWithHNo(String streetWithHNo) {
		StreetWithHNo = streetWithHNo;
	}
	public String getCountryType() {
		return CountryType;
	}
	public void setCountryType(String countryType) {
		CountryType = countryType;
	}
	public String getLineSeparator() {
		return LineSeparator;
	}
	public void setLineSeparator(String lineSeparator) {
		LineSeparator = lineSeparator;
	}
	public String getPreferredLanguage() {
		return PreferredLanguage;
	}
	public void setPreferredLanguage(String preferredLanguage) {
		PreferredLanguage = preferredLanguage;
	}
	public String getCapitalization() {
		return Capitalization;
	}
	public void setCapitalization(String capitalization) {
		Capitalization = capitalization;
	}
	public String getFormattedAddressWithOrganization() {
		return FormattedAddressWithOrganization;
	}
	public void setFormattedAddressWithOrganization(String formattedAddressWithOrganization) {
		FormattedAddressWithOrganization = formattedAddressWithOrganization;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getHouseNumber() {
		return HouseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}
	public String getLocality() {
		return Locality;
	}
	public void setLocality(String locality) {
		Locality = locality;
	}
	public String getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}

}

