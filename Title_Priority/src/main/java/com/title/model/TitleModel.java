package com.title.model;

import java.util.ArrayList;
import java.util.List;

public class TitleModel {

	List<TaskIdModel> tasks = new ArrayList<TaskIdModel>();
	
	private String priority;

	private String title;

	private String dueDate;

	private String opType;
	
//	private String error;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public List<TaskIdModel> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskIdModel> tasks) {
		this.tasks = tasks;
	}

	
	
//	public String getError() {
//		return error;
//	}
//
//	public void setError(String error) {
//		this.error = error;
//	}

	public TitleModel(String priority, String title, String dueDate, String opType, List<TaskIdModel> tasks) {
		super();
		this.priority = priority;
		this.title = title;
		this.dueDate = dueDate;
		this.opType = opType;
		this.tasks = tasks;
//		this.error=error;
	}

	public TitleModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
