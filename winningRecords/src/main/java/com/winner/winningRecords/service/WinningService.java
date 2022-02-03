package com.winner.winningRecords.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.google.gson.Gson;
import com.winner.winningRecords.model.WinningModel;
import com.winner.winningRecords.model.WinningModelJSONObject;
import com.winner.winningRecords.repository.WinningRepo;

@Service
public class WinningService {

	@Autowired
	WinningRepo winningrepo;

	public List<WinningModelJSONObject> getallrecords() {

		List<WinningModel> WinningModel1 = new ArrayList<>();
		List<String> listDest = new ArrayList<>();
		/*Retriving the data PACKAGE_WINNING_SOURCEID*/
		winningrepo.findAll().forEach(WinningModel1::add);
		/*GRouping the data based on SourceID*/
		Map<String, List<WinningModel>> multipleFieldsMap = WinningModel1.stream()
				.collect(Collectors.groupingBy(WinningModel::getWinnerSourceId));

		

		Map<String, WinningModelJSONObject> mapFinal = new HashMap<>();
		List<WinningModelJSONObject> alFinalList = new ArrayList<>();

		/*Iterating the data received from PACKAGE and Converting to json*/
		for (WinningModel list : WinningModel1) {
			WinningModelJSONObject wjson = new WinningModelJSONObject();
			wjson.accountName = replaceNull(list.getAccountName());
			wjson.addressLine1 = replaceNull(list.getAddressLine1());
			wjson.addressLine2 = replaceNull(list.getAddressLine2());
			/*CAlling replaceNull() method to replace null with
			  empty because external api won't allow the  null */
			wjson.fax = replaceNull(list.getFax());
			wjson.city = replaceNull(list.getCity());
			wjson.countryCode = replaceNull(list.getCountryCode());
			
			wjson.losingSourceIds = Arrays.asList(list.getLosingSourceIds().split(",",-1));
			wjson.otherSpecialty = replaceNull(list.getOtherSpecialty());
			wjson.phone = replaceNull(list.getPhone());
			wjson.phoneExtn = replaceNull(list.getPhoneExtn());
			wjson.specialty = replaceNull(list.getSpecialty());
			wjson.stateCode = replaceNull(list.getStateCode());
			wjson.status = replaceNull(list.getStatus());
			wjson.winnerSourceId = (list.getWinnerSourceId());
			wjson.zipCode = replaceNull(list.getZipCode());
			 alFinalList.add(wjson);
		}

		

		System.out.println("Group by on multiple properties" + multipleFieldsMap);
		System.out.print(WinningModel1);
		return alFinalList;

	}
	
	public String replaceNull(String input) {
		return input == null ? "" : input;
	}

	}
