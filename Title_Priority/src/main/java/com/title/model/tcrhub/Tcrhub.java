package com.title.model.tcrhub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "C_BO_PRTY_MTCH")
public class Tcrhub {
	@Id
	@Column(name="ROWID_OBJECT")
	private String ROWID_OBJECT;
	
	@Column(name="MATCH_SCORE")
	private String MATCH_SCORE;

	public String getROWID_OBJECT() {
		return ROWID_OBJECT;
	}

	public void setROWID_OBJECT(String rOWID_OBJECT) {
		ROWID_OBJECT = rOWID_OBJECT;
	}

	public String getMATCH_SCORE() {
		return MATCH_SCORE;
	}

	public void setMATCH_SCORE(String mATCH_SCORE) {
		MATCH_SCORE = mATCH_SCORE;
	}

	public Tcrhub(String rOWID_OBJECT, String mATCH_SCORE) {
		super();
		ROWID_OBJECT = rOWID_OBJECT;
		MATCH_SCORE = mATCH_SCORE;
	}

	public Tcrhub() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
