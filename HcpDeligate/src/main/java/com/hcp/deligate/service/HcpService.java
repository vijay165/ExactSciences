package com.hcp.deligate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hcp.deligate.model.UserModel;
import com.hcp.deligate.repository.FetchDataService;


@Service
public class HcpService {

	@Autowired
	private FetchDataService topicRepository;
	
	public List<UserModel> getAllTopics() {
		List<UserModel> Usermodel1 = new ArrayList<>();
		topicRepository.findAll().forEach(Usermodel1::add);
		
		System.out.println(Usermodel1);
		return Usermodel1;
	}
}
