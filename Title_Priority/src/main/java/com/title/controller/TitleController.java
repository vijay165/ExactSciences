package com.title.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.title.avos.repository.AvosRepository;
import com.title.exceptions.ResourceNotFoundException;
import com.title.model.TitleFinalModalList;
import com.title.model.TitleModel;
import com.title.model.avos.Avos;
import com.title.model.tcrhub.Tcrhub;
import com.title.service.TitleService;
import com.title.tcrhub.repository.TcrhubRepository;



@RestController
public class TitleController {
	
	@Autowired
	TitleService titleService;
	
	@Autowired
	TitleService fileService;
	
	@GetMapping("display")
	public String display() {
		return "Welocme to Title priority API";
	}
	/*
	 * @PostMapping("title") public String getRequest(@RequestBody TitleModel title)
	 * throws HttpException, IOException { return titleService.getRequest(title); }
	 */
	
	/*
	 * @PostMapping("/upload") public JsonObject uploadFile(@RequestParam("file")
	 * MultipartFile file) throws HttpException, IOException { String message = "";
	 * 
	 * if (CSVHelper.hasCSVFormat(file)) {
	 * 
	 * return fileService.getRequest(file);
	 * 
	 * 
	 * }else { return null;
	 * 
	 * } }
	 */
	  
	  @Autowired
		private AvosRepository bookRepository;

		@Autowired
		private TcrhubRepository userRepository;


		@GetMapping("/getUsers")
		public List<Tcrhub> getUsers() {
			return userRepository.findAll();
		}

		@GetMapping("/getBooks")
		public List<Avos> getBooks() {
			return bookRepository.findAll();
		}

		@GetMapping("/TitlePriority")
		public List<TitleFinalModalList> getQueryData() throws HttpException, IOException, ResourceNotFoundException {

			return titleService.getRequest();

		}
		

}
