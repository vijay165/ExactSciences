package com.HCPTOHCO.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PACKAGE_HCP_TO_HCO")
public class HcpToHcoModal {

	@Id
	@Column(name = "HCP")
	private String HCP;

	@Column(name = "MDMID")
	private String MDMID;

	@Column(name = "HCO")
	private String HCO;

	@Column(name = "primaryind")
	private String Primary;

	@Column(name = "LAST_MODIFIED_DATE")
	private String Last_Modified_Date;

	@Column(name = "CREATE_DATE")
	private String Create_Date;

	public String getPrimary() {
		return Primary;
	}

	public void setPrimary(String primary) {
		Primary = primary;
	}

	public String getHCP() {
		return HCP;
	}

	public void setHCP(String hCP) {
		HCP = hCP;
	}

	public String getMDMID() {
		return MDMID;
	}

	public void setMDMID(String mDMID) {
		MDMID = mDMID;
	}

	public String getHCO() {
		return HCO;
	}

	public void setHCO(String hCO) {
		HCO = hCO;
	}

	public String getLast_Modified_Date() {
		return Last_Modified_Date;
	}

	public void setLast_Modified_Date(String last_Modified_Date) {
		Last_Modified_Date = last_Modified_Date;
	}

	public String getCreate_Date() {
		return Create_Date;
	}

	public void setCreate_Date(String create_Date) {
		Create_Date = create_Date;
	}

	public HcpToHcoModal(String hCP, String mDMID, String hCO, String primary, String last_Modified_Date,
			String create_Date) {
		super();
		HCP = hCP;
		MDMID = mDMID;
		HCO = hCO;
		Primary = primary;
		Last_Modified_Date = last_Modified_Date;
		Create_Date = create_Date;
	}

	public HcpToHcoModal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
