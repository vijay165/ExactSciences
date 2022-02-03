package com.search.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.taskdefs.Concat;
import org.json.JSONObject;

public class MainClass {
	@SuppressWarnings("null")
	public static void main(String[] args) {
		String str = "{{\"tasks\":[{\"taskId\":\"urn:b4p2:130012\",\"status\":\"Completed\"}]},{\"tasks\":[{\"taskId\":\"urn:b4p2:130021\",\"status\":\"Completed\"}]},{\"tasks\":[{\"taskId\":\"urn:b4p2:130022\",\"status\":\"Completed\"}]},{\"tasks\":[{\"taskId\":\"urn:b4p2:130023\",\"status\":\"Completed\"}]},{\"tasks\":[{\"taskId\":\"urn:b4p2:130039\",\"status\":\"Completed\"}]},{\"tasks\":[{\"taskId\":\"urn:b4p2:130042\",\"status\":\"Completed\"}]}}";
		JSONObject json=new JSONObject(str);
		System.out.println(json);
		String department=null;
		String subbuilding=null;
		if(verification(department) && verification(subbuilding)) {
			department=department.concat(" ").concat(subbuilding);
			
		}
		System.out.println(department);
		
		
		String mdm_id="MH0000096906";
		String sub = mdm_id.substring(0, 2);
		String remainder = mdm_id.substring(2);
		mdm_id = sub.concat(String.format("%09d",(Integer.valueOf(remainder)+1)));
		System.out.println("mdm_id-------------------------->"+mdm_id);
		/*
		 * String tagname="Province"; String str2=getTagValue(str,tagname);
		 * System.out.println(str2);
		 */
		/*
		 * final JSONObject json = new JSONObject(str.toString());
		 * System.out.println(json);
		 */
		/*
		 * String str2 = null; str = str + "," + str2; if (str2 == null) {
		 * System.out.println("null value"); }
		 */

		/*
		 * str=capitalize(str); System.out.println(str); char y[]=str.toCharArray(); int
		 * size=y.length; y[0] = (char)(y[0]-32); int i=1; while(i!=size) { if(y[i]=='
		 * ') { y[i+1]=(char) (y[i+1]-32); } ++i; }
		 */
		/*
		 * String str1=null; String str2=str.concat(" ").concat(str1);
		 * System.out.println(str);
		 */
	}
	public static boolean verification(String value) {
		if(value!=null && !value.equalsIgnoreCase("")
				&& !value.equalsIgnoreCase("null")&& !value.equalsIgnoreCase("Null")) {
			return true;
		}else {
		return false;
		}
		
	}
	public static String getTagValue(String xml, String tagName){
	    return xml.split("<"+tagName+">")[1].split("</"+tagName+">")[0];
	}
	
	
	/*
	 * public static String capitalize(String str) { String str1 =
	 * str.toLowerCase(); char[] charArray1 = str1.toCharArray(); boolean
	 * foundSpace1 = true; for (int i = 0; i < charArray1.length; i++) {
	 * 
	 * // if the array element is a letter if (Character.isLetter(charArray1[i])) {
	 * 
	 * // check space is present before the letter if (foundSpace1) {
	 * 
	 * // change the letter into uppercase charArray1[i] =
	 * Character.toUpperCase(charArray1[i]); foundSpace1 = false; } }
	 * 
	 * else { // if the new character is not character foundSpace1 = true; } }
	 * 
	 * // convert the char array to the string str1 = String.valueOf(charArray1);
	 * return str1; }
	 */
}