package com.HCPTOHCO.Model;

import java.util.ArrayList;
import java.util.List;

public class HcpHcoBasicModal {

	private String hcp;

	private String mdmid;

	List<HcoBasicModal> hcosublist = new ArrayList<>();

	private String last_modified_date;

	private String create_date;

	public HcpHcoBasicModal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HcpHcoBasicModal(String hcp, String mdmid, List<HcoBasicModal> hcosublist, String last_modified_date,
			String create_date) {
		super();
		this.hcp = hcp;
		this.mdmid = mdmid;
		this.hcosublist = hcosublist;
		this.last_modified_date = last_modified_date;
		this.create_date = create_date;
	}

	public String getHcp() {
		return hcp;
	}

	public void setHcp(String hcp) {
		this.hcp = hcp;
	}

	public String getMdmid() {
		return mdmid;
	}

	public void setMdmid(String mdmid) {
		this.mdmid = mdmid;
	}

	public List<HcoBasicModal> getHcosublist() {
		return hcosublist;
	}

	public void setHcosublist(List<HcoBasicModal> hcosublist) {
		this.hcosublist = hcosublist;
	}

	public String getLast_modified_date() {
		return last_modified_date;
	}

	public void setLast_modified_date(String last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

}
