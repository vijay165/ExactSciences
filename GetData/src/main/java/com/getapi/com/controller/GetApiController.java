package com.getapi.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getapi.com.model.UserModel;
import com.getapi.com.repository.FetchDataService;
import com.getapi.com.service.GetApiService;

@RestController
public class GetApiController {
	
	@Autowired
	private GetApiService dataservice;
	
	FetchDataService  fetchdataservice;
	@RequestMapping("Hello")
	public String display(){
		return "Welcome Get Api";
				
	}
	@RequestMapping("/Getdata")
	public List<UserModel> getAllTopics(){
		return dataservice.getAllTopics();
				
	}

}
