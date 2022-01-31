package com.title.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.title.avos.repository.AvosRepository;
import com.title.exceptions.ResourceNotFoundException;
import com.title.model.TaskIdModel;
import com.title.model.TitalFinalModal;
import com.title.model.TitleFinalModalList;
import com.title.model.TitleModel;
import com.title.model.TitlePriorityModal;
import com.title.tcrhub.repository.TcrhubRepository;

@Service
public class TitleService {

	/*
	 * @Autowired TitleRepo titleRepo;
	 */
	@Autowired
	private AvosRepository bookRepository;

	@Autowired
	private TcrhubRepository userRepository;

	public List<TitleFinalModalList> getRequest() throws HttpException, IOException, ResourceNotFoundException {

		// List<Map<String,String>> list=titleRepo.getMDM_ID();
		// System.out.println("list------->"+list);

		List<TitleModel> list = new ArrayList<TitleModel>();
		List<TitleModel> tutorials = new ArrayList<TitleModel>();

		tutorials = getQueryData();

		/* list.add(titleModel); */
		ObjectMapper mapper = new ObjectMapper();
		// String newJsonData = gson.toJson(tutorials); // return newJsonData;

		StringBuffer str = new StringBuffer();
		str.append("{");
		str.append("Results");
		str.append(":");
		/*
		 *  
		 * 
		 *  str.append("[");
		 */
		Gson gson = new Gson();
		String result = null;
		int len = tutorials.size();
		System.out.println(len);
		// String newJsonData = gson.toJson(tutorials); // return newJsonData;
		// newJsonData=newJsonData.substring(1, newJsonData.length()-1);
		List<TitleFinalModalList> taskList=new ArrayList<>();
				for (int i = 0; i < tutorials.size(); i++) {

			String newJsonData = gson.toJson(tutorials.get(i));
			System.out.println("newJsonData------>" + newJsonData);
			Base64 b = new Base64();
			String encoding = b.encodeAsString(new String("Puser01:Mdmuser01").getBytes());
			result = newJsonData;
			/*For DAPP*/
			//String postURL = "http://dapp01mdm01:8080/cmx/task/operations/dsql01mdm01-tcr_hub";
			/*FOR QAPP*/
			//String postURL = "http://qapp01mdm01:8080/cmx/task/operations/qsql01mdm01-cmx_ors";
			
			String postURL = "http://dapp01mdm01:8080/cmx/task/operations/dsql01mdm01-tcr_hub";
			PostMethod postMethod = null;
			postMethod = new PostMethod(postURL);
			postMethod.setRequestHeader("Authorization", "Basic " + encoding);

			StringRequestEntity requestEntity = new StringRequestEntity(result, "application/json", "UTF-8");
			postMethod.setRequestEntity(requestEntity);
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			int response = httpClient.executeMethod(postMethod); // Execute the POST method
			
			result = postMethod.getResponseBodyAsString();
				TitalFinalModal t=new Gson().fromJson(result, TitalFinalModal.class);
					taskList.addAll(t.getTasks());
			
			
			System.out.println("The result is  "+result);
			if (result != null)
				str.append(result);
			str.append(",");
		}
		str.deleteCharAt(str.length() - 1);
//		str.append("]");
		str.append("}");

		System.out.println("The final result is" + str);
		/*
		 * result = str.toString(); JSONObject myObject = new JSONObject(result);
		 * System.out.println("myObject===="+myObject);
		 */

		JsonObject json = new Gson().fromJson(result, JsonObject.class);
		
		/*
		 * JsonObject json=new Gson().toJson(taskList);
		 * System.out.println(taskListJSONStr);
		 */
		
		return taskList;

	}

	@SuppressWarnings("unlikely-arg-type")
	public List<TitleModel> getQueryData() throws ResourceNotFoundException {

		java.util.List<TitlePriorityModal> numdatelist = new ArrayList<TitlePriorityModal>();
		List<Map<String, String>> rowidPrioritylist = userRepository.getrowidtasks();
		List<Integer> rowidList = new ArrayList<Integer>();
		List<String> priorityList = new ArrayList<String>();
		List<Map<String,String>> pmodal=new ArrayList<>();
		for (int i = 0; i <= rowidPrioritylist.size() - 1; i++) {
			int rowid=Integer.valueOf(rowidPrioritylist.get(i).get("ROWID_TASK"));
			if(!rowidList.contains(rowid)) {
			rowidList.add(rowid);
			priorityList.add(rowidPrioritylist.get(i).get("taskpriority"));
			Map<String,String> sample=new HashMap<>();
			sample.put("Rowid", String.valueOf(rowid));
			sample.put("priority", rowidPrioritylist.get(i).get("taskpriority"));
			pmodal.add(sample);
			/* pmodal.add(priorityModal); */
			}
		}
		List<TitleModel> tutorials = new ArrayList<TitleModel>();

		try {
			
			if (rowidList.size() != 0) {
				for (int i = 0; i < rowidList.size(); i++) {
					
				}
				TitlePriorityModal tutorial = new TitlePriorityModal();
				if (rowidList.size() != 0) {
					List<Map<String, String>> list = bookRepository.getrowidtasks(rowidList);

					numdatelist = list.stream().map(m -> {

						TitlePriorityModal numdate = new TitlePriorityModal();
						numdate.setTitle((String.valueOf(m.get("title"))));
						numdate.setOpType(String.valueOf(m.get("opType")));
						numdate.setPaprocessid(String.valueOf(m.get("paprocessid")));
						for (int k = 0; k < pmodal.size(); k++) {
							
							if (numdate.getPaprocessid().equals(pmodal.get(k).get("Rowid")))
								
								numdate.setPriority(pmodal.get(k).get("priority"));

						}
						numdate.setTaskId(String.valueOf(m.get("taskId")));
						return numdate;
					}).collect(Collectors.toList());
				}
			}
			int i = 0;
			
			
				for (TitlePriorityModal csvRecord : numdatelist) {

					TaskIdModel taskIdModel = new TaskIdModel();
					List<TaskIdModel> taskIdlist = new ArrayList<TaskIdModel>();
					taskIdModel.setTaskId(csvRecord.getTaskId());
					taskIdlist.add(taskIdModel);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();

					TitleModel titleModel = new TitleModel();

					titleModel.setTasks(taskIdlist);
					titleModel.setTitle(csvRecord.getTitle());	
					titleModel.setPriority(csvRecord.getPriority());
					titleModel.setOpType(csvRecord.getOpType());
					
					if (priorityList.get(i).equals("High")) {
						now = now.plusDays(4);
						titleModel.setDueDate(dtf.format(now));
					} else if (priorityList.get(i).equals("Low")) {
						now = now.plusDays(14);
						titleModel.setDueDate(dtf.format(now));
					}else if (priorityList.get(i).equals("Normal")) {
						now = now.plusDays(8);
						titleModel.setDueDate(dtf.format(now));
					}else {
						titleModel.setDueDate(dtf.format(now));
					}

					tutorials.add(titleModel);
					i = i + 1;

				}
			

		} catch (Exception e) {
//			TitleModel titleModel = new TitleModel();
//			titleModel.setError("There are no task_id's in cmx_ors server");
//			tutorials.add(titleModel);
//			return tutorials;

			throw new ResourceNotFoundException("There are no task_id's in cmx_ors server");

		}
		return tutorials;
	}

}
