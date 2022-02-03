package com.search.api.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.api.service.CreateService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {
	
	@Autowired(required=true)
	CreateService createservice;
	
	 @GetMapping("/mdmSession")
		public  String callapi(String username, String password) throws ProtocolException, MalformedURLException {

			System.out.println("Inside external api method");
			String access_token = null;
			String refresh_token=null;
			JSONObject jsonObject=null;
			String sessionid=null;

			try {
				System.out.println("username----->"+username+"password----->"+password);
				//String postURL = "http://dapp01mdm01:8080/cmx/auth/dsql01mdm01-tcr_hub/"; //ICT and MDM session ID getting from this by using keyclock mapping
				String postURL = "http://qapp01mdm01:8080/cmx/auth/qsql01mdm01-cmx_ors/";
				PostMethod postMethod = null;
				postMethod = new PostMethod(postURL);
				String data ="{\r\n"
						+ "    \"username\" : \"Puser01\",\r\n"
						+ "    \"password\" : {\r\n"
						+ "        \"encrypted\" : false,\r\n"
						+ "        \"password\" : \"Mdmuser01\"\r\n"
						+ "    },\r\n"
						+ "    \"userInfo\" : {\r\n"
						+ "        \"sessionTimeout\":-1,\r\n"
						+ "        \"role\":\"Manager\"\r\n"
						+ "    }\r\n"
						+ "}";
				
				StringRequestEntity request=new StringRequestEntity(data,"application/json","UTF-8");
				postMethod.setRequestEntity(request);
				org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
				int response = httpClient.executeMethod(postMethod); // Execute the POST method
				System.out.println(access_token);
				try {
					Map<String, String> map = new HashMap<String, String>();
					Header[] hM = postMethod.getResponseHeaders();
					  for (Header header : hM) {
					   map.put(header.getName(), header.getValue());
					   System.out.println(header.getName()+ " The value is :     "+header.getValue());
					   if(header.getName().equals("Set-Cookie")) {
						    sessionid=header.getValue();
					   }
					  }
				} catch (JSONException err) {

				}

			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getSessionId(sessionid);

		}

		@GetMapping("Hello")
		public int display(@RequestHeader("Cookie") String sessionID)
				throws HttpException, IOException {
			return createservice.CookieeValidate(sessionID);

		}
		 
//	 @GetMapping("MDMCookie")
//	 public void sendCookie(@RequestHeader("Cookie") String sessionID) throws HttpException, IOException
//	 {
//		  mdmAuth.sample(sessionID);
//	 }
		
	public String getSessionId(String Session_ID) {
		System.out.println("getSessionId------->"+Session_ID);
		return Session_ID;
	}
}
