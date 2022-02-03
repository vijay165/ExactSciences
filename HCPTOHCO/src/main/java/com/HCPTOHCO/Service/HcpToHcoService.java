package com.HCPTOHCO.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCPTOHCO.Model.HcoBasicModal;
import com.HCPTOHCO.Model.HcpHcoBasicModal;
import com.HCPTOHCO.Model.HcpToHcoModal;
import com.HCPTOHCO.Repo.HcpToHcoRepo;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class HcpToHcoService {

	@Autowired
	HcpToHcoRepo hcpToHcoRepo;

	@SuppressWarnings("unchecked")
	public JSONObject getdata() {
		List<HcpToHcoModal> hcpHcoList = hcpToHcoRepo.findAll();
		List<HcpHcoBasicModal> list = new ArrayList<>();
		JSONObject json = new JSONObject();
		try {
			for (HcpToHcoModal sample : hcpHcoList) {
				HcpHcoBasicModal hcpHcoBasicModal = new HcpHcoBasicModal();
				hcpHcoBasicModal.setHcp(sample.getHCP());
				hcpHcoBasicModal.setMdmid(sample.getMDMID());
				String hco = sample.getHCO();
				String primaryid = sample.getPrimary();
				List<HcoBasicModal> hcolist = new ArrayList<>();
				List<String> primaryidList = new ArrayList<>();

				StringBuffer sb1 = new StringBuffer();
				if (hco.contains(",")) {
					String[] terms = hco.split(",");
					String[] primaryids = primaryid.split(",");

					for (int i = 0; i < terms.length; i++) {
						String substr = terms[i];
						HcoBasicModal hcoBasicModal = new HcoBasicModal();
						String[] hcomdmlist = substr.split(":");
						hcoBasicModal.setHco(hcomdmlist[0]);
						hcoBasicModal.setMdmid(hcomdmlist[1]);
						hcoBasicModal.setPrimary(primaryids[i]);
						hcolist.add(hcoBasicModal);
					}
					hcpHcoBasicModal.setHcosublist(hcolist);
				} else {

					HcoBasicModal hcoBasicModal = new HcoBasicModal();
					String[] hcomdmlist = hco.split(":");
					hcoBasicModal.setHco(hcomdmlist[0]);
					hcoBasicModal.setMdmid(hcomdmlist[1]);
					hcoBasicModal.setPrimary(primaryid);
					hcolist.add(hcoBasicModal);
					hcpHcoBasicModal.setHcosublist(hcolist);
				}

				hcpHcoBasicModal.setLast_modified_date(sample.getLast_Modified_Date());
				hcpHcoBasicModal.setCreate_date(sample.getCreate_Date());
				list.add(hcpHcoBasicModal);
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			
			json.put("HCP_TO_HCO_HIGH_ORDER", list);
			json.put("Run_Time_Date", dtf.format(now));
			
		} catch (Exception e) {

		}
		return json;

	}

}
