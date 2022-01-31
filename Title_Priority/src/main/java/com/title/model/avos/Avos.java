package com.title.model.avos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AeB4PTaskDateView")
public class Avos {

	@Id
	@Column(name="ProcessId")
	private String ProcessId;
	@Column(name="Name")
	private String name;
	public String getProcessId() {
		return ProcessId;
	}
	public void setProcessId(String processId) {
		ProcessId = processId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Avos(String processId, String name) {
		super();
		ProcessId = processId;
		this.name = name;
	}
	public Avos() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
