package com.api.model;
import lombok.Data;

@Data
public class Model {
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	private Address address;
	private Party party;
	
	  public Model() {
	  
	  }
	public Model(Address address, Party party) {
		super();
		this.address = address;
		this.party = party;
	}
	 
	

}
