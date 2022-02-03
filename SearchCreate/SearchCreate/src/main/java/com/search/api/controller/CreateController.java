package com.search.api.controller;

import java.util.List;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.search.api.model.Address;
import com.search.api.model.Model;
import com.search.api.model.Party;
import com.search.api.service.CreateService;

import com.search.api.model.AuthenticationRequest;
import com.search.api.model.AuthenticationResponse;





@RestController
public class CreateController {
	
	@Autowired(required=true)
	CreateService createservice;
	
	private final Logger logger = Logger.getLogger(CreateController.class.getName());
	
	  @GetMapping("/hello") public String sayHello() {
	  
	  return "Hello, Application is running on JBOSS EAP 7.2";
	  
	  }
	 
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/createAftsearch", method = RequestMethod.POST)
	public String testSecurity(@RequestBody Model model,@RequestHeader("Cookie") String sessionID) throws Exception {
		 Address address = model.getAddress();
    Party party = model.getParty();
    boolean flag=false;
    boolean pflag=false;
    int status=createservice.CookieeValidate(sessionID);
    if(status==200) {
    	try {
    	List<String> mdm_id=createservice.getMDM_ID();
    	System.out.println("party.getSOURCE_ID()------->Controller class"+party.getSOURCE_ID());
    	System.out.println("party.getAccount_Nr()------->Controller class"+party.getAccount_Nr());
    	 flag=createservice.verifyPkey_source_id(party.getSOURCE_ID());
    	 //pflag=createservice.verifyAddressPkey_source_id(address.getPORTAL_ID());
    	 System.out.println("flag   controller"+flag);
    	party.setMdm_id(mdm_id.get(0));
    	}catch (Exception e) {
			// TODO: handle exception
    		logger.info(e.getMessage());
		}
    	if(flag) {
		return (createservice.display(address,party));
    	}
		/*
		 * else if(flag) { return "Portal_Id Already Exist"; } else if(pflag) { return
		 * "Source_Id Already Exit"; }
		 */else {
    		return "Source_Id Already Exit";
    	}
    }else {
    	return ok().toString();
    	
    }
		
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/MDM_IDS", method = RequestMethod.GET)
	public List<String> testSecurity() throws Exception {
		
		return createservice.getMDM_ID();
		
	}
	
	 @GetMapping("/methodlevel")
	    @ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "OK")
	    public ResponseEntity<String> ok() {
	        return new ResponseEntity<>("UNAUTHORISED USED", 
					   HttpStatus.UNAUTHORIZED);
	    } 
}


