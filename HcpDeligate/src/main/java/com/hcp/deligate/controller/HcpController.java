package com.hcp.deligate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcp.deligate.model.UserModel;
import com.hcp.deligate.service.HcpService;

@RestController
public class HcpController {

	@Autowired
	private HcpService hcpService;
	
	@RequestMapping("Hello")
	public String display(){
		return "Welcome HCP Api";
				
	}
	@RequestMapping("/HcpData")
	public List<UserModel> getAllTopics(){
		return hcpService.getAllTopics();
				
	}
}
