package com.hcp.deligate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "package_hcp_deligate")
public class UserModel {
	
	@Id
	@Column(name= "ROWID_XREF")
	private String ROWID_XREF;
	
	@Column(name = "MDM_ID_P1")
	private String MDM_ID_P1;

	@Column(name = "SFDC_PERSON_1")
	private String SFDC_PERSON_1;

	@Column(name = "SOURCE_SYSTEM")
	private String SOURCE_SYSTEM;

	@Column(name = "RLE_TYP_P1")
	private String RLE_TYP_P1;
	
	@Column(name = "RLE_TYP_P2")
	private String RLE_TYP_P2;
	
	@Column(name = "MDM_ID_P2")
	private String MDM_ID_P2;

	@Column(name = "SFDC_PERSON_2")
	private String SFDC_PERSON_2;
	
	@Column(name = "REL_CREATE_DATE")
	private String REL_CREATE_DATE;
	
	@Column(name = "REL_LAST_UPDATE_DATE")
	private String REL_LAST_UPDATE_DATE;

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserModel(String MDM_ID_P1, String SFDC_PERSON_1,String RLE_TYP_P1, String SOURCE_SYSTEM, String MDM_ID_P2,
			String SFDC_PERSON_2, String RLE_TYP_P2,String REL_LAST_UPDATE_DATE,String REL_CREATE_DATE) {
		super();
		this.MDM_ID_P1 = MDM_ID_P1;
		this.SFDC_PERSON_1 = SFDC_PERSON_1;
		this.SOURCE_SYSTEM = SOURCE_SYSTEM;
		this.RLE_TYP_P1 = RLE_TYP_P1;
		this.MDM_ID_P2 = MDM_ID_P2;
		this.SFDC_PERSON_2 = SFDC_PERSON_2;
		this.RLE_TYP_P2 = RLE_TYP_P2;
		this.REL_LAST_UPDATE_DATE=REL_LAST_UPDATE_DATE;
		this.REL_CREATE_DATE=REL_CREATE_DATE;
		
	}

	public String getMDM_ID_P1() {
		return MDM_ID_P1;
	}

	public void setMDM_ID_P1(String mDM_ID_P1) {
		MDM_ID_P1 = mDM_ID_P1;
	}

	public String getSFDC_PERSON_1() {
		return SFDC_PERSON_1;
	}

	public void setSFDC_PERSON_1(String sFDC_PERSON_1) {
		SFDC_PERSON_1 = sFDC_PERSON_1;
	}

	public String getSOURCE_SYSTEM() {
		return SOURCE_SYSTEM;
	}

	public void setSOURCE_SYSTEM(String sOURCE_SYSTEM) {
		SOURCE_SYSTEM = sOURCE_SYSTEM;
	}

	public String getRLE_TYP_P1() {
		return RLE_TYP_P1;
	}

	public void setRLE_TYP_P1(String rLE_TYP_P1) {
		RLE_TYP_P1 = rLE_TYP_P1;
	}

	public String getMDM_ID_P2() {
		return MDM_ID_P2;
	}

	public void setMDM_ID_P2(String mDM_ID_P2) {
		MDM_ID_P2 = mDM_ID_P2;
	}

	public String getSFDC_PERSON_2() {
		return SFDC_PERSON_2;
	}

	public void setSFDC_PERSON_2(String sFDC_PERSON_2) {
		SFDC_PERSON_2 = sFDC_PERSON_2;
	}

	public String getRLE_TYP_P2() {
		return RLE_TYP_P2;
	}

	public void setRLE_TYP_P2(String rLE_TYP_P2) {
		RLE_TYP_P2 = rLE_TYP_P2;
	}

	public String getREL_CREATE_DATE() {
		return REL_CREATE_DATE;
	}

	public void setREL_CREATE_DATE(String rEL_CREATE_DATE) {
		REL_CREATE_DATE = rEL_CREATE_DATE;
	}

	public String getREL_LAST_UPDATE_DATE() {
		return REL_LAST_UPDATE_DATE;
	}

	public void setREL_LAST_UPDATE_DATE(String rEL_LAST_UPDATE_DATE) {
		REL_LAST_UPDATE_DATE = rEL_LAST_UPDATE_DATE;
	}
	
	
}
