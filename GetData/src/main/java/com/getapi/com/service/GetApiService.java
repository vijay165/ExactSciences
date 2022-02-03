package com.getapi.com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getapi.com.model.UserModel;
import com.getapi.com.repository.FetchDataService;


@Service
public class GetApiService {
	
	@Autowired
	private FetchDataService topicRepository;
	
	public List<UserModel> getAllTopics() {
		List<UserModel> Usermodel = new ArrayList<>();
		topicRepository.findAll().forEach(Usermodel::add);
		System.out.println(Usermodel);
		return Usermodel;
	}
}
