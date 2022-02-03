package com.HCPTOHCO.Controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HCPTOHCO.Model.HcpHcoBasicModal;
import com.HCPTOHCO.Service.HcpToHcoService;

@RestController
public class HCPTOHCOController {
	
	@Autowired
	HcpToHcoService hcpToHcoService;
	
	@GetMapping("Hello")
	public String display() {
		return "Welcome HCPTOHCO data";
	}
	@GetMapping("/getHcpToHcoData")
	public JSONObject getHcpToHcoData(){
		System.out.println("Controller Class");
		return hcpToHcoService.getdata();
	}
	
}
