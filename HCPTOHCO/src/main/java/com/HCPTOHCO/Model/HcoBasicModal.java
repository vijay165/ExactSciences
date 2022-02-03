package com.HCPTOHCO.Model;

public class HcoBasicModal {

	private String Hco;

	private String Mdmid;

	private String Primary;

	public String getHco() {
		return Hco;
	}

	public void setHco(String hco) {
		Hco = hco;
	}

	public String getMdmid() {
		return Mdmid;
	}

	public void setMdmid(String mdmid) {
		Mdmid = mdmid;
	}

	public String getPrimary() {
		return Primary;
	}

	public void setPrimary(String primary) {
		Primary = primary;
	}

	public HcoBasicModal(String hco, String mdmid, String primary) {
		super();
		Hco = hco;
		Mdmid = mdmid;
		Primary = primary;
	}

	public HcoBasicModal() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HcoBasicModal [Hco=" + Hco + ", Mdmid=" + Mdmid + ", Primary=" + Primary + ", getHco()=" + getHco()
				+ ", getMdmid()=" + getMdmid() + ", getPrimary()=" + getPrimary() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
