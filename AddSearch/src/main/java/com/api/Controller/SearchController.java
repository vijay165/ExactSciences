package com.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.Service.SearchService;
import com.api.model.Address;
import com.api.model.Model;
import com.api.model.Party;




@RestController
public class SearchController {

	@Value("admin")
	private String userName;

	@Value("admin")
	private String password;

	@Value("User")
	private String pass;

	@Autowired
	private SearchService searchservice;



	@RequestMapping("/hello")
	public String sayHello() {

		return "Hello, Application is running on JBOSS EAP 7.2";

	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/searchAddr", method = RequestMethod.POST)
	public String testSecurity(@RequestBody Model model) throws Exception {
		 Address address = model.getAddress();
     Party party = model.getParty();
		return searchservice.display(address, party);//instead string take response entity ResponseEntity<List<AddressDto>>
	}
}
