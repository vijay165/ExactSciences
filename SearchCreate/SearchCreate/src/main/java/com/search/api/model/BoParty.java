package com.search.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C_BO_PRTY")
public class BoParty {

	@Id
	@Column(name= "ROWID_OBJECT")
	private String ROWID_OBJECT;
	
	@Column(name = "MDM_ID")
	private String MDM_ID;
	
}
