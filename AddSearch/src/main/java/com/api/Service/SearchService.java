package com.api.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.JsonObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.taskdefs.Concat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.api.Controller.ExceptionClass;
import com.api.DB.CreateSIFClient;
import com.api.model.Address;
import com.api.model.Party;
import com.api.model.PlainPojo;
import com.siperian.sif.client.HttpSiperianClient;
import com.siperian.sif.message.Field;
import com.siperian.sif.message.MatchType;
import com.siperian.sif.message.Record;
import com.siperian.sif.message.SiperianObjectType;
import com.siperian.sif.message.mrm.SearchMatchRequest;
import com.siperian.sif.message.mrm.SearchMatchResponse;



@Service
public class SearchService {

	String persName = null;
	@Value("User")
	private String pass;
	StringBuffer response = null;
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	String jsonPrettyPrintString = null;
	String urduWord = null;
	String HouseNumber1 = null;
	String pageName = null;
	String displayname_ad = "FULL_NM";
	String Phone_ad = "PHONE";
	String Speciality_ad = "SPECIALTY";
	String partytype_ad = "PRTY_TYP";
	String roll_type_ad = "RLE_TYP";
	String Fax_ad = "FAX";
	String flag = "TRUE";
	String flag1 = "FALSE";
	String Value = "VALID";
	String Other_Speciality_ad = "OTHER_SPECIALITY";
	String postal_cd_ext_ad = "PSTL_CD_EXT";
	String PostalCode_ad = "PSTL_CD";
	String add_line_2 = "ADDR_LN_2";
	String Acc_No = "ACCOUNT_NR";
	String Suite1 = "SUIT";
	String Null = null;
	String Suit=null; 
	String address_line_1="ADDR_LN_1";
	
	PlainPojo plainPojo=new PlainPojo();
	
	@SuppressWarnings("null")
	public JSONObject retriveValues(String results, String status, String display_name, String Phone, String Fax,
			String Speciality, String party_type, String RoleType, String message, String Other_Speciality,
			String postal_cd_exe) {
		JSONObject json = null;
		StringBuffer sb2 = null;
		try {
			
			String locality="CITY";
			String country="CNTRY_CD";
			String province="STATE";
			String street_name="STREET_NM";
			String street_no="STREET_NO";
			String pst = null;
			String pst_1 = null;
			results = results.replace("\"", "");
			System.out.println("Message is----" + message);
			String[] keyValuePairs = results.split(","); // split the string to creat key-value pairs
			Map<String, String> map = new HashMap<>();
			Pattern p = Pattern.compile("[^A-Za-z0-9]");
			Matcher m = p.matcher(postal_cd_exe);
			// boolean b = m.matches();
			boolean b = m.find();
			if (b) {
				String[] splitString = postal_cd_exe.split("-");
				pst = splitString[0];
				pst_1 = splitString[1];
			} else {
				pst = postal_cd_exe;
			}
			sb2=new StringBuffer();
			
			sb2.append("{");
			sb2.append("Output:");
			sb2.append(System.lineSeparator());
			sb2.append("[");
			sb2.append("{");

			if (status.equals("C1")||status.equals("C2")||status.equals("C3")||status.equals("C4")
					||status.equals("V2")||status.equals("V4")) {
				sb2.append("\"" + Value + "\"" + ":");
				sb2.append(true);
				sb2.append(",");
				sb2.append(System.lineSeparator());
			} else {
				sb2.append("\"" + Value + "\"" + ":");
				sb2.append(false);
				sb2.append(",");
				sb2.append(System.lineSeparator());
			}
			 
			if (verification(display_name)) {
				sb2.append("\"" + displayname_ad + "\"" + ":" + "\"" + capitalize(display_name) + "\"");
				sb2.append(",");
				
			} else {
				sb2.append("\"" + displayname_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(Phone)) {
				sb2.append("\"" + Phone_ad + "\"" + ":" + "\"" + Phone + "\"");
				sb2.append(",");
			} else {
				sb2.append("\"" + Phone_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(Fax)) {
				sb2.append("\"" + Fax_ad + "\"" + ":" + "\"" + Fax + "\"");
				sb2.append(",");
			} else {
				sb2.append("\"" + Fax_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(Speciality)) {
				sb2.append("\"" + Speciality_ad + "\"" + ":" + "\"" + capitalize(Speciality) + "\"");
				sb2.append(",");
			} else {
				sb2.append("\"" + Speciality_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(party_type)) {
				sb2.append("\"" + partytype_ad + "\"" + ":" + "\"" + party_type + "\"");
				sb2.append(",");
			} else {
				sb2.append("\"" + partytype_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(RoleType)) {
				sb2.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RoleType + "\"");
				sb2.append(",");
			} else {
				sb2.append("\"" + roll_type_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(Other_Speciality)) {
				sb2.append("\"" + Other_Speciality_ad + "\"" + ":" + "\"" + capitalize(Other_Speciality) + "\"");
				sb2.append(",");
			} else {
				sb2.append("\"" + Other_Speciality_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(pst_1)) {
				sb2.append("\"" + postal_cd_ext_ad + "\"" + ":" + "\"" + pst_1 + "\"");
				sb2.append(",");
			} else {
				sb2.append("\"" + postal_cd_ext_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(pst)) {
				sb2.append("\"" + PostalCode_ad + "\"" + ":" + "\"" + pst + "\"");
				sb2.append(",");
			} else {
				sb2.append("\"" + PostalCode_ad + "\"" + ":" + Null);
				sb2.append(",");
			}
			sb2.append("\"" + Acc_No + "\"" + ":" + Null);
			sb2.append(",");

			if(verification(plainPojo.getSuit())) {
			  sb2.append("\"" + Suite1 + "\"" + ":" + capitalize(plainPojo.getSuit()));
			  sb2.append(",");
			}else {
				sb2.append("\"" + Suite1 + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(plainPojo.getSt2())) {

				sb2.append("\"" + add_line_2 + "\"" + ":" + "\"" + capitalize(plainPojo.getSt2()) + "\"");
				sb2.append(",");
			} else {

				sb2.append("\"" + add_line_2 + "\"" + ":" + Null);
				sb2.append(",");
			}
			
			if (verification(plainPojo.getAddress_line1())) {

				sb2.append("\"" + address_line_1 + "\"" + ":" + "\"" + capitalize(plainPojo.getAddress_line1()) + "\"");
				sb2.append(",");
			} else {

				sb2.append("\"" + address_line_1 + "\"" + ":" + Null);
				sb2.append(",");
			}
			sb2.append("\"" + country + "\"" + ":" + "\"" + plainPojo.getCountry() + "\"");
			sb2.append(",");
			sb2.append("\"" + province + "\"" + ":" + "\"" + plainPojo.getProvince() + "\"");
			sb2.append(",");
			if(verification(plainPojo.getLocality())) {
			sb2.append("\"" + locality + "\"" + ":" + "\"" + capitalize(plainPojo.getLocality()) + "\"");
			sb2.append(",");
			}else {
				sb2.append("\"" + locality + "\"" + ":" + Null);
				sb2.append(",");
			}	
			if (verification(plainPojo.getHousenumber())) {

				sb2.append("\"" + street_no + "\"" + ":" + "\"" + plainPojo.getHousenumber() + "\"");
				sb2.append(",");
			} else {

				sb2.append("\"" + street_no + "\"" + ":" + Null);
				sb2.append(",");
			}
			if (verification(plainPojo.getStreet()) ) {

				sb2.append("\"" + street_name + "\"" + ":" + "\"" + capitalize(plainPojo.getStreet()) + "\"");
				sb2.append(",");
			} else {

				sb2.append("\"" + street_name + "\"" + ":" + Null);
				sb2.append(",");
			}
			sb2.deleteCharAt(sb2.length() - 1);
			sb2.append("}");
			sb2.append(System.lineSeparator());
			sb2.append("]");
			sb2.append("}");
			json = new JSONObject(sb2.toString());
			sb2.delete(0, sb2.length());
		} catch (Exception e) {
			throw new ExceptionClass(e);
			
		}
		return json;

	}

	public String display(Address address, Party party) {

		String Party_type_value = party.getDisplay_Name();
		String age = null;
		String CustomerID = "163226";
		String DepartmentID = "0";
		String Password = "Strike1";
		String CountryOfOrigin = address.getCountryOfOrigin();
		String StreetWithHNo = "false";
		String CountryType = address.getCountryType();
		String LineSeparator = "LST_SEMICOLON";
		String PreferredLanguage = address.getPreferredLanguage();
		String Capitalization = address.getCapitalization();
		String FormattedAddressWithOrganization = "false";
		String Street = address.getStreet();
		String Street2 = address.getStreet2();
		plainPojo.setSt2(Street2);
		String department=null;
		String residue=null;
		String building=null;

		String HouseNumber = address.getHouseNumber();
		String Locality = address.getLocality();
		String PostalCode = address.getPostalCode();
		String Province = address.getProvince();
		String Country = address.getCountry();
		String DeliveryAddressLines = null;
		String DeliveryAddressLines2=null;
		String subbulding=null;
		String status = null;
		String Organization=null;
		String deliveryService=null;
		String st2=null;
		/* String Suit=null; */
		try {
			String basicAuthPayload = "Basic " + pass;
			System.out.println("Basic auth Base64 password: " + basicAuthPayload);

			String url = "https://validator5.addressdoctor.com/Webservice5/v4/AddressValidation.asmx";

			URL myURL = new URL(url);

			HttpURLConnection connection = (HttpURLConnection) myURL.openConnection();
			connection.setConnectTimeout(7000);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "text/xml");
			connection.setRequestProperty("SOAPAction", "http://validator5.AddressDoctor.com/Webservice5/v4/Process");
			String xml = "<soap:Envelope\r\n"
					+ "	xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"\r\n"
					+ "	xmlns:v4=\"http://validator5.AddressDoctor.com/Webservice5/v4\">\r\n"
					+ "	<soap:Header/>\r\n"
					+ "	<soap:Body>\r\n"
					+ "		<v4:Process>\r\n"
					+ "			<v4:login>"+CustomerID+"</v4:login>\r\n"
					+ "			<v4:password>"+Password+"</v4:password>\r\n"
					+ "			<v4:parameters>\r\n"
					+ "				<v4:ProcessMode>BATCH</v4:ProcessMode>\r\n"
					+"			<v4:ValidationParameters>\r\n"
					+ "					<v4:CountryType>ISO2</v4:CountryType>\r\n"
					+ "					<v4:CountryOfOriginISO3>COO_ALWAYS_USE_DESTINATION_COUNTRY</v4:CountryOfOriginISO3>\r\n"
					+ "					<v4:StreetWithNumber>"+StreetWithHNo+"</v4:StreetWithNumber>\r\n"
					+ "				</v4:ValidationParameters>\r\n"
					+ "			</v4:parameters>\r\n"
					+ "			<v4:addresses>\r\n"
					+ "				<v4:Address>\r\n"
					+ "					<v4:RecordId>1</v4:RecordId>\r\n"
					+ "					<v4:DeliveryAddressLines>\r\n"
					+ "					<v4:string>"+Street+"</v4:string>\r\n"
					+ "					</v4:DeliveryAddressLines>\r\n"
					+ "					<v4:Locality>\r\n"
					+ "						<v4:string>"+Locality+"</v4:string>\r\n"
					+ "					</v4:Locality>\r\n"
					+ "					<v4:PostalCode>\r\n"
					+ "						<v4:string>"+PostalCode+"</v4:string>\r\n"
					+ "					</v4:PostalCode>\r\n"
					+ "					<v4:Province>\r\n"
					+ "						<v4:string>"+Province+"</v4:string>\r\n"
					+ "						</v4:Province>\r\n"
					+ "					<v4:Country>\r\n"
					+ "						<v4:string>"+Country+"</v4:string>\r\n"
					+ "					</v4:Country>\r\n"
					+ "				</v4:Address>\r\n"
					+ "			</v4:addresses>\r\n"
					+ "		</v4:Process>\r\n"
					+ "	</soap:Body>\r\n"
					+ "</soap:Envelope>";
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			System.out.print(wr);
			wr.writeBytes(xml);
			wr.flush();
			wr.close();
			String responseStatus = connection.getResponseMessage();
			System.out.println(responseStatus);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			System.out.println("response--------->" + response);
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			connection.disconnect();
			System.out.println("response:" + response.toString());
			JSONObject xmlJSONObj = XML.toJSONObject(response.toString());
			jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			jsonPrettyPrintString = jsonPrettyPrintString.replaceAll("[{}]", " ");
			String xml1 = response.toString();
			System.out.println(jsonPrettyPrintString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder;
			try {
				builder = factory.newDocumentBuilder();

				// Use String reader
				org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(xml1)));
				NodeList n1 = document.getElementsByTagName("HouseNumber");
				if (n1.getLength() > 0) {
					HouseNumber = n1.item(0).getTextContent();
					plainPojo.setHousenumber(HouseNumber);
				}

				NodeList n2 = document.getElementsByTagName("Street");
				if (n2.getLength() > 0) {
					st2=n2.item(0).getTextContent();
					Street=st2;
					plainPojo.setStreet(st2);
				}
				Locality = document.getElementsByTagName("Locality").item(0).getTextContent();
				plainPojo.setLocality(Locality);
				PostalCode = document.getElementsByTagName("PostalCode").item(0).getTextContent();
				
				
				NodeList n3 = document.getElementsByTagName("Province");
				for(int i=0;i<getvalues(n3).size();i++) {
					if(i==0) {
						String str=getvalues(n3).get(i).toString();
						System.out.println("String is provience :"+str);
						plainPojo.setProvince(str);
					}
				}
				NodeList n4 = document.getElementsByTagName("SubBuilding");
				if (n4.getLength() > 0) {
					String[] str=getvalues(n4).stream().toArray(String[]::new);
					subbulding = String.join(" ",str);
					Suit=str[0];
					}
				plainPojo.setSuit(Suit);
				if(verification(HouseNumber) && verification(st2)&& verification(subbulding)) {
					  DeliveryAddressLines =
					  HouseNumber.concat(" ").concat(st2).concat(" ").concat(subbulding);
					  plainPojo.setAddress_line1(DeliveryAddressLines);
					}else if(verification(st2) && verification(HouseNumber)) {
						DeliveryAddressLines = HouseNumber.concat(" ").concat(st2);
						plainPojo.setAddress_line1(DeliveryAddressLines);
					}else if(verification(st2) && verification(subbulding)){
						DeliveryAddressLines = st2.concat(" ").concat(subbulding);
						plainPojo.setAddress_line1(DeliveryAddressLines);
					}else if(verification(HouseNumber) && verification(subbulding)){
						DeliveryAddressLines = HouseNumber.concat(" ").concat(subbulding);
						plainPojo.setAddress_line1(DeliveryAddressLines);
					}else if(verification(st2)) {
						DeliveryAddressLines = st2;
						plainPojo.setAddress_line1(DeliveryAddressLines);
					}else if(verification(HouseNumber)) {
						DeliveryAddressLines = HouseNumber;
						plainPojo.setAddress_line1(DeliveryAddressLines);
					}else if(verification(subbulding)) {
						DeliveryAddressLines = subbulding;
						plainPojo.setAddress_line1(DeliveryAddressLines);
					}
				NodeList n9=document.getElementsByTagName("DeliveryService");
				if(n9.getLength()>0) {
					deliveryService=n9.item(0).getTextContent();
				}
				System.out.println(plainPojo.getAddress_line1());
				System.out.println(deliveryService);
				if(verification(deliveryService)) {
						if(verification(DeliveryAddressLines)) {
						DeliveryAddressLines=DeliveryAddressLines.concat(" ").concat(deliveryService);
						plainPojo.setAddress_line1(DeliveryAddressLines);
						}else {
							DeliveryAddressLines=deliveryService;
							plainPojo.setAddress_line1(DeliveryAddressLines);
						}
					
				}
				System.out.println(plainPojo.getAddress_line1());
				

				 
				status = document.getElementsByTagName("ProcessStatus").item(0).getTextContent();
				Country = document.getElementsByTagName("Country").item(0).getTextContent();
				plainPojo.setCountry(Country);
				NodeList n10 = document.getElementsByTagName("Building");
				if (n10.getLength() > 0) {
					building = n10.item(0).getTextContent();
				}
				NodeList n11 = document.getElementsByTagName("Organization");
				if (n11.getLength() > 0) {
					Organization = n11.item(0).getTextContent();
				}
				NodeList n12 = document.getElementsByTagName("Residue");
				if (n12.getLength() > 0) {
					residue = n12.item(0).getTextContent();
				}
				if(verification(building)) {
					DeliveryAddressLines=DeliveryAddressLines.concat(" ").concat(building);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				}
				if(verification(Organization)) {
					DeliveryAddressLines=DeliveryAddressLines.concat(" ").concat(Organization);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				}
				if(verification(residue)) {
					DeliveryAddressLines=DeliveryAddressLines.concat(" ").concat(residue);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				}
				DeliveryAddressLines = document.getElementsByTagName("DeliveryAddressLines").item(0).getTextContent();
				/*NodeList n5 = document.getElementsByTagName("Department");
				if (n5.getLength() > 0) {
					department = n5.item(0).getTextContent();
				}
				NodeList n6 = document.getElementsByTagName("Building");
				if (n6.getLength() > 0) {
					building = n6.item(0).getTextContent();
				}
				NodeList n7 = document.getElementsByTagName("Residue");
				if (n7.getLength() > 0) {
					residue = n7.item(0).getTextContent();
				}
				NodeList n8 = document.getElementsByTagName("Organization");
				if (n8.getLength() > 0) {
					Organization = n8.item(0).getTextContent();
				}
				
				
				 * if(verification(subbulding) && verification(building) &&
				 * verification(residue)) { DeliveryAddressLines2=
				 * subbulding.concat(" ").concat(building).concat(" ").concat(residue);
				 * plainPojo.setSt2(DeliveryAddressLines2); }else if(verification(subbulding) &&
				 * verification(building)) { DeliveryAddressLines2=
				 * subbulding.concat(" ").concat(building);
				 * plainPojo.setSt2(DeliveryAddressLines2); }else if(verification(building) &&
				 * verification(residue)) { DeliveryAddressLines2=
				 * building.concat(" ").concat(residue);
				 * plainPojo.setSt2(DeliveryAddressLines2); }else if(verification(subbulding) &&
				 * verification(residue)) { DeliveryAddressLines2=
				 * subbulding.concat(" ").concat(residue);
				 * plainPojo.setSt2(DeliveryAddressLines2); }else if(verification(subbulding)) {
				 * DeliveryAddressLines2= subbulding; plainPojo.setSt2(DeliveryAddressLines2);
				 * }else if(verification(residue)) { DeliveryAddressLines2= residue;
				 * plainPojo.setSt2(DeliveryAddressLines2);
				 * 
				 * }else if(verification(building)) { DeliveryAddressLines2= building;
				 * plainPojo.setSt2(DeliveryAddressLines2); }
				 * 
				 * if(verification(department) && verification(plainPojo.getSt2())) {
				 * plainPojo.setSt2(plainPojo.getSt2().concat(" ").concat(department)); }else
				 * if(verification(department)){ DeliveryAddressLines2= department;
				 * plainPojo.setSt2(DeliveryAddressLines2); } if(verification(Organization)&&
				 * verification(plainPojo.getSt2())) {
				 * plainPojo.setSt2(plainPojo.getSt2().concat(" ").concat(Organization)); }else
				 * if(verification(Organization)){ DeliveryAddressLines2= Organization;
				 * plainPojo.setSt2(DeliveryAddressLines2); }
				 */
				

				System.out.println(age);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ExceptionClass(e);
			}

		} catch (Exception e) {
			throw new ExceptionClass(e);
		}
		return searchMatch(Locality, PostalCode, Province, Country, DeliveryAddressLines, Street, HouseNumber, party,
				status).toString();
	}

	public JSONObject searchMatch(String Locality, String PostalCode, String Province, String Country,
			String DeliveryAddressLines, String Street, String HouseNumber, Party party, String status) {

		String display_name = party.getDisplay_Name();
		String Phone = party.getPhone();
		String Fax = party.getFax();
		String RoleType = party.getRoleType();
		String Speciality = party.getSpeciality();
		String party_type = party.getParty_Type();
		String Other_Speciality = party.getOther_Speciality();
		String DisplayMandatory = "Display_name is Mandatory ";
		String PartyMandatory = "Party_Type is Mandatory ";
		String RolMandatory = "Role_Type is Mandatory ";
		String CountryMandatory = "Country is Mandatory ";
		String streetRoleMandatory = "Street is  Mandatory ";
		String SpecialityMandatory = "Speciality is Mandatory ";
		String Country_ad = "Country";
		String street_ad = "Street";
		StringBuffer sb3 = new StringBuffer();
		
		if (!party.getParty_Type().equalsIgnoreCase("Org")) {
			String strorg = "{PRTY_TYP : PRTY_TYP should be Org}";
			JSONObject json = new JSONObject(strorg);
			return json;
		}
		if(!party.getRoleType().equalsIgnoreCase("HCO")){
			String strtype= "{RLE_TYP : RLE_TYP should be HCO}";
			JSONObject json = new JSONObject(strtype);
			return json;
		}
		/* try { */
		if (verification(display_name) && verification(party_type) && verification(RoleType) && verification(Country)
				&& verification(Street) && verification(Speciality)) {
			
		} else {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("ERROR : Manditory Fields Cannot be null or Empty");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			return json;
		}
		if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals("")) && (Country.equals(""))
				&& (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals("")) && (Country.equals(""))
				&& (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (RoleType.equals("")) && (Country.equals("")) && (Street.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals("")) && (Country.equals("")) && (Street.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (Country.equals("")) && (Street.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals("")) && (Street.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals("")) && (Country.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals(""))
				&& (Country.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((RoleType.equals("")) && (Country.equals("")) && (Street.equals("")) && (Speciality.equals(""))
				&& (RoleType.equals(null)) && (Country.equals(null)) && (Street.equals(null))
				&& (Speciality.equals(null))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if (((display_name.equals("")) && (party_type.equals("")) && (Street.equals(""))
				&& (Speciality.equals("")))
				|| ((display_name.equals(null)) && (party_type.equals(null)) && (Street.equals(null))
						&& (Speciality.equals(null)))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if (((party_type.equals("")) && (RoleType.equals("")) && (Country.equals("")) && (Street.equals("")))
				|| ((party_type.equals(null)) && (RoleType.equals(null)) && (Country.equals(null))
						&& (Street.equals(null)))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (Country.equals("")) && (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((RoleType.equals("")) && (Country.equals("")) && (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (Country.equals("")) && (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (RoleType.equals("")) && (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (RoleType.equals("")) && (Country.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (RoleType.equals("")) && (Country.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (Country.equals("")) && (Street.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			//// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals("")) && (Street.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals("")) && (Country.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals("")) && (Country.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (Street.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (Country.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (Country.equals(""))
				&& (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals(""))
				&& (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();
			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((Country.equals("")) && (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (Country.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (RoleType.equals("")) && (Country.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals("")) && (Country.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();
			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			//// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (RoleType.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (RoleType.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (Country.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (Country.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();
			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (Country.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (Country.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((RoleType.equals("")) && (Country.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((RoleType.equals("")) && (Country.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((RoleType.equals("")) && (Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals(""))
				&& (Country.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals(""))
				&& (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals("")) && (RoleType.equals(""))
				&& (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((Country.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((Country.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((Street.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (RoleType.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (RoleType.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (party_type.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (Country.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((display_name.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((RoleType.equals("")) && (Country.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((RoleType.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((RoleType.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (Country.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (Street.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if ((party_type.equals("")) && (Speciality.equals(""))) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append(",");
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if (display_name.equals("")) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + displayname_ad + "\"" + ":" + "\"" + DisplayMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if (RoleType.equals("")) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + roll_type_ad + "\"" + ":" + "\"" + RolMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();
			// jsonArray.put(json);
			return json;
		} else if (party_type.equals("")) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + partytype_ad + "\"" + ":" + "\"" + PartyMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if (Speciality.equals("")) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Speciality_ad + "\"" + ":" + "\"" + SpecialityMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if (Street.equals("")) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + street_ad + "\"" + ":" + "\"" + streetRoleMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		} else if (Country.equals("")) {
			sb3.append("{");
			sb3.append(System.lineSeparator());
			sb3.append("\"" + Country_ad + "\"" + ":" + "\"" + CountryMandatory + "\"");
			sb3.append("}");
			JSONObject json = new JSONObject(sb3.toString());
			// JSONArray jsonArray = new JSONArray();

			// jsonArray.put(json);
			return json;
		}
		/*
		 * } catch (Exception e) { System.out.println(e); }
		 */

		System.out.println("display_name-----?" + display_name + "Phone-------?" + Phone + "Fax---?" + Fax
				+ "-----RoleType?" + RoleType + "Speciality-----" + Speciality + "DeliveryAddressLines-------"
				+ DeliveryAddressLines + "Country------" + Country);
		SearchMatchRequest requestSearch = new SearchMatchRequest();
		SearchMatchResponse responseS = new SearchMatchResponse();
		System.out.println(party_type);
		requestSearch.setSiperianObjectUid(SiperianObjectType.PACKAGE.makeUid("PACKAGE_SEARCH_MATCH_API"));
		Field Ndisplay_name = new Field("Organization_Name"); // Required match column
		Ndisplay_name.setStringValue(display_name);
		requestSearch.addMatchColumnField(Ndisplay_name);
		try {

			DeliveryAddressLines = capitalize(DeliveryAddressLines);

			Field NAddr_Line1 = new Field("Addr_Line1"); // Required match column
			NAddr_Line1.setStringValue(DeliveryAddressLines);
			requestSearch.addMatchColumnField(NAddr_Line1);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(e);
		}

		Locality = capitalize(Locality);
		System.out.println(Locality);
		System.out.println(Locality);
		Field NLocality = new Field("City"); // Required match column
		NLocality.setStringValue(Locality);
		requestSearch.addMatchColumnField(NLocality);
		String postal_cd_exe = PostalCode;
		String symbol = "-";
		try {
			if (PostalCode.length() > 0 &&PostalCode.contains("-") ) {
				PostalCode = PostalCode.substring(0, PostalCode.indexOf("-"));
			}
			System.out.println(PostalCode);
			Field NPostalCode = new Field("Postal_Code");// Required match column
			NPostalCode.setStringValue(PostalCode);
			requestSearch.addMatchColumnField(NPostalCode);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(e);
		}

		if (Country.equals("USA")) {
			Country = "US";
		}
		Field NCountry = new Field("Country_Code"); // Required match column
		NCountry.setStringValue(Country);
		requestSearch.addMatchColumnField(NCountry);

		Field NDeliveryAddressLines = new Field("Addr_Line1"); // Required match column
		NDeliveryAddressLines.setStringValue(DeliveryAddressLines);
		requestSearch.addMatchColumnField(NDeliveryAddressLines);

		Field NStreet = new Field("Address_Part1"); // Required match column
		NStreet.setStringValue(Street);
		requestSearch.addMatchColumnField(NStreet);

		/* Fax = "+" + Fax; */
		System.out.println(Fax);
		Field NFax = new Field("CTC_FAX"); // Required match column
		NFax.setStringValue(Fax);
		requestSearch.addMatchColumnField(NFax);
		/* Phone = "+" + Phone; */
		System.out.println(Phone);
		Field Nphone = new Field("CTC_PHNE"); // Required match column
		Nphone.setStringValue(Phone);
		requestSearch.addMatchColumnField(Nphone);

		Field NSpeciality = new Field("Specialty"); // Required match column
		NSpeciality.setStringValue(Speciality);
		requestSearch.addMatchColumnField(NSpeciality);

		Field NProvince = new Field("State"); // Required match column
		NProvince.setStringValue(Province);
		requestSearch.addMatchColumnField(NProvince);
		Field Nparty_type = new Field("party_type"); // Required match column
		Nparty_type.setStringValue(party_type);
		requestSearch.addMatchColumnField(Nparty_type);

		Field NHouseNumber = new Field("Address_Part2"); // Required match column
		NHouseNumber.setStringValue(HouseNumber);
		requestSearch.addMatchColumnField(NHouseNumber);
		Field NRole_Type = new Field("Role_Type"); // Required match column
		NRole_Type.setStringValue(RoleType);
		requestSearch.addMatchColumnField(NRole_Type);
		Field NSuite_Number = new Field("Suite_Number"); // Required match column
		NSuite_Number.setStringValue("");
		requestSearch.addMatchColumnField(NSuite_Number);

		requestSearch.setMatchRuleSetUid("MATCH_RULE_SET.C_BO_PRTY|HCO(R2)");// Dup_Customer
																				// MATCH_RULE_SET.C_BO_XXXXXXXXXX|SIF_SOME_RULE
		requestSearch.setRecordsToReturn(10); // Required
		requestSearch.setMatchType(MatchType.BOTH);
		requestSearch.setDisablePaging(false);
		requestSearch.setReturnTotal(true);
		requestSearch.setSortCriteria("MATCH_SCORE DESC");
		System.out.println("Start execution " + new Date());
		System.out.println(requestSearch.toString());
		HttpSiperianClient sipClient = null;
		try {
			sipClient = (HttpSiperianClient) CreateSIFClient.getSIFClientObject().getHttpSIFClient();
			System.out.println(sipClient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			responseS = (SearchMatchResponse) sipClient.process(requestSearch);
			System.out.println(responseS);
		} catch (Exception e) {
			System.out.println("-------Get Api Failure message------" + e);
		}
		/* responseS = (SearchMatchResponse) sipClient.process(requestSearch); */
		Field f = null;
		System.out.println(responseS.getData());
		System.out.println("End execution " + new Date());
		System.out.println("Matched records count : " + responseS.getRecordCount());// not getting exact value
		System.out.println("SearchMatchResponse  : " + responseS.getMessage());
		System.out.println("SearchMatchResponse : " + responseS.getRecords());
		List<Record> lst = responseS.getData();

		System.out.println(lst.size());

		List<String> flds = new ArrayList<String>();

		int i = 0;
		StringBuffer sb1 = new StringBuffer();
		Record respRecord = null;
		List<String> res = null; // Display all matched records

		sb1.append("{");
		sb1.append("Output:");
		sb1.append(System.lineSeparator());
		sb1.append("[");

		/*
		 * if (status.equals("C")) { sb1.append("Status : True");
		 * sb1.append(System.lineSeparator()); } else { sb1.append("Status : False");
		 * sb1.append(System.lineSeparator()); } if (lst.size() == 0) {
		 * sb1.append(jsonobject(response)); }
		 */

		String[] sentences = null;
		String dump = null;

		for (Iterator iter = responseS.getRecords().iterator(); iter.hasNext();) {
			System.out.println("=================Printing matched record " + i);
			i++;

			respRecord = (Record) iter.next();
			if (respRecord.getField("DEFINITIVE_MATCH_IND") != null) {
				BigDecimal definitiveMatch = respRecord.getField("DEFINITIVE_MATCH_IND").getBigDecimalValue();
				if (definitiveMatch.intValue() == 1) {
					System.out.println("Matched on an auto-merge rule");
				} else {
					System.out.println("Did not Match on an auto-merge rule");
				}
			}
			@SuppressWarnings("rawtypes")
			Collection fields = respRecord.getFields();
			sb1.append("{");
			sb1.append(System.lineSeparator());
			if (status.equals("C1")||status.equals("C2")||status.equals("C3")||status.equals("C4")
					||status.equals("V2")||status.equals("V4")) {
				sb1.append("\"" + Value + "\"" + ":");
				sb1.append(true);
				sb1.append(",");
				sb1.append(System.lineSeparator());
			} else {
				sb1.append("\"" + Value + "\"" + ":");
				sb1.append(false);
				sb1.append(",");
				sb1.append(System.lineSeparator());
			}
			for (@SuppressWarnings("rawtypes")
			Iterator fieldIter = fields.iterator(); fieldIter.hasNext();) { // iterate through rest of fields
				f = (Field) fieldIter.next();
				if ((f.getName().equals("RULESET_NAME")) || (f.getName().equals("MATCH_SCORE"))
						|| (f.getName().equals("DEFINITE_MATCH_IND")) || (f.getName().equals("ROWID_OBJECT"))
						|| (f.getName().equals("RULE_NUMBER")) || (f.getName().equals("HUB_STATE_IND"))
						|| (f.getName().equals("C1"))) {
				} else if (f.getValue() == null) {
					sb1.append(System.lineSeparator());
					sb1.append("\"" + f.getName() + "\"" + ":" + Null);
					sb1.append(",");
				} else {
					sb1.append(System.lineSeparator());
					sb1.append("\"" + f.getName() + "\"" + ":" + "\"" + f.getValue() + "\"");
					sb1.append(",");
				}
			}
			sb1.deleteCharAt(sb1.length() - 1);
			sb1.append("}");
			sb1.append(",");
			sb1.append(System.lineSeparator());

		}
		sb1.append(System.lineSeparator());
		sb1.append("]");
		sb1.append("}");
		System.out.println(sb1);
		if (lst.size() > 0) {
			JSONObject json = new JSONObject(sb1.toString());
//			 //JSONArray jsonArray = new JSONArray();
//				//jsonArray.put(json);
			return json;
		} else {
			String message = responseS.getMessage();
			return retriveValues(jsonPrettyPrintString, status, display_name, Phone, Fax, Speciality, party_type,
					RoleType, message, Other_Speciality, postal_cd_exe);
		}

	}

	public static String capitalize(String str) {
		String str1 = str.toLowerCase();
		char[] charArray1 = str1.toCharArray();
		boolean foundSpace1 = true;
		for (int i = 0; i < charArray1.length; i++) {

			// if the array element is a letter
			if (Character.isLetter(charArray1[i])) {

				// check space is present before the letter
				if (foundSpace1) {

					// change the letter into uppercase
					charArray1[i] = Character.toUpperCase(charArray1[i]);
					foundSpace1 = false;
				}
			}

			else {
				// if the new character is not character
				foundSpace1 = true;
			}
		}

		// convert the char array to the string
		str1 = String.valueOf(charArray1);
		return str1;
	}
	public ArrayList<String> getvalues(NodeList list) {
		 ArrayList<String> destinationArray = new ArrayList<String>();
	        if (list != null && list.getLength() > 0) {
	            NodeList subList = list.item(0).getChildNodes();

	            if (subList != null && subList.getLength() > 0) {
	            	for(int i=0;i<subList.getLength();i++) {
	            	
	            		destinationArray.add(subList.item(i).getTextContent());
	            	}
	            	
	            }
	        }
			return destinationArray;
	}

	public boolean verification(String value) {
		if (value != null && !value.equalsIgnoreCase("") && !value.equalsIgnoreCase("null")
				&& !value.equalsIgnoreCase("Null") && !value.equalsIgnoreCase("NULL")
				&& !value.equalsIgnoreCase(" ")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean equalscomparision(String str1, String str2) {
		if (str1.equalsIgnoreCase(str2)) {
			return true;
		} else {
			return false;
		}

	}
}