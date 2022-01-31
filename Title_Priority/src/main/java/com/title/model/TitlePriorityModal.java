package com.title.model;

import java.util.ArrayList;
import java.util.List;

public class TitlePriorityModal {

	private String taskId;

	private String title;

	private String Priority;

	private String opType;

	private String paprocessid;

	public TitlePriorityModal(String taskId, String title, String priority, String opType, String paprocessid) {
		super();
		this.taskId = taskId;
		this.title = title;
		Priority = priority;
		this.opType = opType;
		this.paprocessid = paprocessid;
	}

	public TitlePriorityModal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPaprocessid() {
		return paprocessid;
	}

	public void setPaprocessid(String paprocessid) {
		this.paprocessid = paprocessid;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

}
