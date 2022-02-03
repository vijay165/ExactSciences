package com.winner.winningRecords.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.FileHandler;
import java.util.stream.Collectors;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.google.gson.Gson;
import com.winner.winningRecords.model.WinningModel;
import com.winner.winningRecords.model.WinningModelJSONObject;
import com.winner.winningRecords.repository.WinningRepo;
import com.winner.winningRecords.service.WinningService;

@RestController
public class WinningController {

	@Autowired
	WinningService winningservice;

	WinningModel WinningModel = new WinningModel();
	@Autowired
	WinningRepo winningrepo;
	String result = null;
	private final Logger logger = Logger.getLogger(WinningController.class.getName());
	private FileHandler fh = null;

	
	public WinningController() { // just to make our log file nicer :)
		try {
			SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
			String home = System.getProperty("user.home");

			String name = "D:\\API LOG FILES\\WinningRecordsLog_";
			fh = new FileHandler(name + format.format(Calendar.getInstance().getTime()) + ".log");
		} catch (Exception e) {
			e.printStackTrace();
		}

		fh.setFormatter(new SimpleFormatter());
		logger.addHandler(fh);

	}
	 
	@RequestMapping("Hello")
	public String display() {
		logger.info("This is an info log example");
		return "welcome to winning records api";
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@RequestMapping(value = "GetWinningRecords", produces = "application/json")
	public String getrecords() throws HttpException, IOException {
		System.out.println("In getRecords()");
		final int N = 10;
		List<WinningModelJSONObject> list = winningservice.getallrecords();
		System.out.println(list.size());

		int chunkSize = 100;
		logger.info("chunkSize is  :   " + chunkSize);
		AtomicInteger counter = new AtomicInteger();
		/* Dividing the list into 100 parts */
		final Collection<List<WinningModelJSONObject>> partitionedList = list.stream()
				.collect(Collectors.groupingBy(i -> counter.getAndIncrement() / chunkSize)).values();
		logger.info("Number of parts the list divided is    :     " + partitionedList.size());
		List<String> ids = new ArrayList<String>();
		for (List<WinningModelJSONObject> subList : partitionedList) {
			List<WinningModelJSONObject> sublistparts = new ArrayList<>();
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
					.format(Calendar.getInstance().getTime());
			for (WinningModelJSONObject first : subList) {
				sublistparts.add(first);
			}
			Gson gson = new Gson();
			JSONObject json = new JSONObject();
			json.put("accountsList", sublistparts);
			json.put("messageCreatedDatetime", timeStamp);
			String result = gson.toJson(json);
			/* Calling the external API */
			int value = callapi(result);
			/* Verifying the that extrenal api is returing 200k response or not */
			String home = System.getProperty("user.home");
			String filename = "WinningRecords";
			/*
			 * FileWriter writer = new FileWriter(home + "/Downloads/" + filename + ".log");
			 */
			if (value == 200) {
				for (WinningModelJSONObject second : sublistparts) {
					/* Updating the C_BO_PRTY PROCESS_IND to 'Y' */

					/* writer.write(second.getWinnerSourceId() + System.lineSeparator()); */

					winningrepo.updateUser(second.getWinnerSourceId());
					/* System.out.println("Calling update loop  : " +second.getWinnerSourceId()); */
				}
			}
			subList.forEach(m -> logger.info((m.getWinnerSourceId())));
			/* writer.close(); */
			subList.forEach(m -> System.out.println((m.getWinnerSourceId())));
			/* break; */
		}
		/*
		 * JSONObject resultjson = new JSONObject(); resultjson.put("Status",result );
		 */
		return result;
	}

	public int callapi(String jsonobj) throws HttpException, IOException {
		JSONObject str = null;
		int response = 0;
		try {
			/*
			 * WSDL from EAI team
			 * 
			 * 
			 * QA DETAILS
			 * https://providermanagement-papi-v1-ftoct.us-w1.cloudhub.io/api/mdm/
			 * accountmergerequests client_id : 6af96e30414448d2b862b8154a86e0b2
			 * client_secret: 1946923692044374b10Fa441C13E005B
			 */

			/*
			 * WSDL from EAI team
			 * 
			 * 
			 * DEV DETAILS
			 * https://providermanagement-papi-v1-devoct.us-w1.cloudhub.io/api/mdm/
			 * accountmergerequests client_id : 456971cdaa4e40d087ce5f2152dd6e05
			 * client_secret: A799d911a734434Ba6cEBEb8EaEa979B
			 */

			/*
			 * WSDL from EAI team
			 * 
			 * 
			 * SIT DETAILS
			 * 
			 * Base URL
			 * :https://providermanagement-papi-v1-sitora.us-w1.cloudhub.io/api/mdm/
			 * accountmergerequests Client ID: de3a58a58e34468a9b487f08de19a7c3 Client
			 * Secret : 409A788a86994FD2A8AFD3c825cA3224
			 */

			/*
			 * { PROD DETAILS "asset": "providermanagement-papi", "client_id":
			 * "bf6254c4e5414df29e3eb65bb4a8ea9c", "client_secret":
			 * "4760B81ae50b4bbC92eE5924D8C6a79F", "url":
			 * "https://providermanagement-papi-v1.us-w1.cloudhub.io/api/mdm/accountmergerequests"
			 * },
			 * 
			 * @Sasi Addepalli Let me know if there is anything else you need for the MDM
			 * release.
			 */
			
			/* new UAT Details
			 * URL: https://providermanagement-papi-v1-sitpur.us-w1.cloudhub.io/api/mdm/
			 * accountmergerequests 
			 * Client ID: 4dfbe277563a48e4adc04f569782d344 Client Secret:
			 * 448B0bA3f15746CbA06d6877B2A7bC57
			 */

			
			/* Tuesday 16th november details
			 * 
			 * Dev Details
			 * 
			 * 
			 * https://providermanagement-papi-v1-devdec.us-w1.cloudhub.io/api/mdm/accountmergerequests
			 * cd565ad0f5a8417cb615a1a02e238630 CC4ce226Ac3F4958940e7Dc0104B565F
			 * 
			 * QA Details
			 * 
			 * 
			 * https://providermanagement-papi-v1-ftpur.us-w1.cloudhub.io/api/mdm/accountmergerequests
			 * b2383df242694099a140086e6aeceda6 a0348161376445e5b80301701C78a5d0
			 * 
			 * 
			 * SIT DETAILS
			 * 
			 * https://providermanagement-papi-v1-sitpur.us-w1.cloudhub.io/api/mdm/accountmergerequests
			 * 4dfbe277563a48e4adc04f569782d344 448B0bA3f15746CbA06d6877B2A7bC57
			 */

			
			
			String postURL = "https://providermanagement-papi-v1-devdec.us-w1.cloudhub.io/api/mdm/accountmergerequests";
			PostMethod postMethod = null;
			postMethod = new PostMethod(postURL);
			postMethod.setRequestHeader("Content-Type", "application/json");
			postMethod.setRequestHeader("client_id", "cd565ad0f5a8417cb615a1a02e238630");
			postMethod.setRequestHeader("client_secret", "CC4ce226Ac3F4958940e7Dc0104B565F");
			UUID uniqueKey = UUID.randomUUID();
			System.out.println(uniqueKey);
			postMethod.setRequestHeader("x-transaction-id", uniqueKey.toString());
			StringRequestEntity requestEntity = new StringRequestEntity(jsonobj, "application/json", "UTF-8");
			postMethod.setRequestEntity(requestEntity);
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			response = httpClient.executeMethod(postMethod); // Execute the POST method
			result = postMethod.getResponseBodyAsString();
			logger.info("On Calling External Api Return Message is :  " + result);
			System.out.println("On Calling External Api Return Message is :  " + result);

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return response;

	}

}
