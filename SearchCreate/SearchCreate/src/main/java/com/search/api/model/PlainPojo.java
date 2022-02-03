package com.search.api.model;

public class PlainPojo {
	
	private String street=null;
	private String country=null;
	private String locality=null;
	private String province=null;
	private String address_line1=null;
	private String suit=null;
	private String housenumber=null;
	private String streetname=null;
	private String st2=null;
	private String PostalCode=null;
	private String subbuilding=null;
	private String residue=null;
	private String building=null;
	private String status=null;
	private String organisation=null;
	private String deliveryservice=null;
	
	public PlainPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PlainPojo(String street, String country, String locality, String province, String address_line1,
			String suit,String housenumber,String streetname,String st2,String PostalCode,
			String subbuilding,
	String residue,
	String building,String status,String organisation,String deliveryservice) {
		super();
		this.street = street;
		this.country = country;
		this.locality = locality;
		this.province = province;
		this.address_line1 = address_line1;
		this.suit = suit;
		this.housenumber=housenumber;
		this.streetname=streetname;
		this.st2=st2;
		this.PostalCode=PostalCode;
		this.subbuilding=subbuilding;
		this.building=building;
		this.residue=residue;
		this.status=status;
		this.deliveryservice=deliveryservice;
		this.organisation=organisation;
	}


	


	public String getOrganisation() {
		return organisation;
	}


	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}


	public String getDeliveryservice() {
		return deliveryservice;
	}


	public void setDeliveryservice(String deliveryservice) {
		this.deliveryservice = deliveryservice;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPostalCode() {
		return PostalCode;
	}


	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}


	public String getSubbuilding() {
		return subbuilding;
	}


	public void setSubbuilding(String subbuilding) {
		this.subbuilding = subbuilding;
	}


	public String getResidue() {
		return residue;
	}


	public void setResidue(String residue) {
		this.residue = residue;
	}


	public String getBuilding() {
		return building;
	}


	public void setBuilding(String building) {
		this.building = building;
	}


	public String getSt2() {
		return st2;
	}


	public void setSt2(String st2) {
		this.st2 = st2;
	}


	public String getHousenumber() {
		return housenumber;
	}


	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}


	public String getStreetname() {
		return streetname;
	}


	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}


	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	

}
