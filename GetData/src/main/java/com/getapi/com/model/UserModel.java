package com.getapi.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PACKAGE_GET_QUERY_API")
public class UserModel {
	
	
	
	
	
	//Additional Three cols BO rowid_object, Xref Orig_rowid_object, xref Rowid_system 

	@Id
    @Column(name="ROWID_OBJECT")
	private int MDM_ID;
	
	
	@Column(name="SOURCE_ID")
	private String ACCOUNT_OR_CONTACT_NUMBER;
	@Column(name = "XREF_MDM_ID")
	private String XREF_MDM_ID;
	@Column(name = "XREF_CR_NUMBER")
	private String XREF_CR_NUMBER;
	@Column(name = "XREF_ORIG_ROWID_OBJECT")
	private String XREF_ORIG_ROWID_OBJECT;
	@Column(name = "XREF_ROWID_SYSTEM")
	private String XREF_ROWID_SYSTEM;
	public int getMDM_ID() {
		return MDM_ID;
	}
	public void setMDM_ID(int mDM_ID) {
		MDM_ID = mDM_ID;
	}
	public String getACCOUNT_OR_CONTACT_NUMBER() {
		return ACCOUNT_OR_CONTACT_NUMBER;
	}
	public void setACCOUNT_OR_CONTACT_NUMBER(String aCCOUNT_OR_CONTACT_NUMBER) {
		ACCOUNT_OR_CONTACT_NUMBER = aCCOUNT_OR_CONTACT_NUMBER;
	}
	public String getXREF_MDM_ID() {
		return XREF_MDM_ID;
	}
	public void setXREF_MDM_ID(String xREF_MDM_ID) {
		XREF_MDM_ID = xREF_MDM_ID;
	}
	public String getXREF_CR_NUMBER() {
		return XREF_CR_NUMBER;
	}
	public void setXREF_CR_NUMBER(String xREF_CR_NUMBER) {
		XREF_CR_NUMBER = xREF_CR_NUMBER;
	}
	public String getXREF_ORIG_ROWID_OBJECT() {
		return XREF_ORIG_ROWID_OBJECT;
	}
	public void setXREF_ORIG_ROWID_OBJECT(String xREF_ORIG_ROWID_OBJECT) {
		XREF_ORIG_ROWID_OBJECT = xREF_ORIG_ROWID_OBJECT;
	}
	public String getXREF_ROWID_SYSTEM() {
		return XREF_ROWID_SYSTEM;
	}
	public void setXREF_ROWID_SYSTEM(String xREF_ROWID_SYSTEM) {
		XREF_ROWID_SYSTEM = xREF_ROWID_SYSTEM;
	}
	public UserModel() {
		
	}
	
	public UserModel(String ACCOUNT_OR_CONTACT_NUMBER, String XREF_MDM_ID, String XREF_CR_NUMBER,int MDM_ID,String XREF_ROWID_SYSTEM,String XREF_ORIG_ROWID_OBJECT) {
		super();
		this.MDM_ID=MDM_ID;
		this.ACCOUNT_OR_CONTACT_NUMBER = ACCOUNT_OR_CONTACT_NUMBER;
		this.XREF_MDM_ID = XREF_MDM_ID;
		this.XREF_CR_NUMBER = XREF_CR_NUMBER;
		this.XREF_ROWID_SYSTEM=XREF_ROWID_SYSTEM;
		this.XREF_ORIG_ROWID_OBJECT=XREF_ORIG_ROWID_OBJECT;
	}
	
}

