package com.title.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C_BO_PRTY")
public class PartyModel {

	@Id
	@Column(name="ROWID_OBJECT")
	private String ROWID_OBJECT;
	
	@Column(name="FULL_NM")
	private String FULL_NM;

	public String getROWID_OBJECT() {
		return ROWID_OBJECT;
	}

	public void setROWID_OBJECT(String rOWID_OBJECT) {
		ROWID_OBJECT = rOWID_OBJECT;
	}

	public String getFULL_NM() {
		return FULL_NM;
	}

	public void setFULL_NM(String fULL_NM) {
		FULL_NM = fULL_NM;
	}
	
	
	
}
