package com.search.api.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.search.api.Db.CreateConnection;
import com.search.api.controller.ExceptionClass;
import com.search.api.model.Address;
import com.search.api.model.Party;
import com.search.api.model.PlainPojo;
import com.search.api.repository.BoPartyRepo;
import com.siperian.sif.client.HttpSiperianClient;
import com.siperian.sif.message.Field;
import com.siperian.sif.message.Record;
import com.siperian.sif.message.RecordKey;
import com.siperian.sif.message.SiperianObjectType;
import com.siperian.sif.message.mrm.GetRequest;
import com.siperian.sif.message.mrm.GetResponse;
import com.siperian.sif.message.mrm.PutRequest;
import com.siperian.sif.message.mrm.PutResponse;
import com.siperian.sif.message.mrm.TokenizeRequest;
import com.siperian.sif.message.mrm.TokenizeResponse;
import java.util.logging.FileHandler;
import java.util.stream.Collectors;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import com.informatica.mdm.cs.client.*;//spi.externalcall.customLogicFactory,spi.externalcall.Externalcallprocessor//compositeService client
//private static logger logger =logger.getlogger(customLogicService.class.getname()

@Service
public class CreateService {

	@Autowired
	BoPartyRepo boPartyRepo;

	String Party_id = null;
	String Address_id = null;
	String persName = null;
	@Value("User")
	private String pass;
	StringBuffer response = null;
	String jsonPrettyPrintString = null;
	RecordKey Record_Key = null;
	String Action_Type = null;
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	PlainPojo plainPojo = new PlainPojo();

	private final Logger logger = Logger.getLogger(CreateService.class.getName());
	private FileHandler fh = null;

	
	public CreateService() { // just to make our log file nicer :)
		try {
			SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
			String home = System.getProperty("user.home");
			String filename = "SearchCreate";
			String name = "D:\\API LOG FILES\\SearchCreate";
			fh = new FileHandler(name + format.format(Calendar.getInstance().getTime()) + ".log");
		} catch (Exception e) {
			e.printStackTrace();
		}

		fh.setFormatter(new SimpleFormatter());
		logger.addHandler(fh);

	}
	 

	@SuppressWarnings("unused")
	public String display(Address address, Party party) throws IOException, ParserConfigurationException, SAXException,
			TransformerException, Exception, NullPointerException, IOException {

		String age = null;
		String CustomerID = "163226";
		String DepartmentID = "0";
		String Password = "Strike1";
		String CountryOfOrigin = address.getCountryOfOrigin();
		String StreetWithHNo = "false";
		/* String CountryType = address.getCountryType(); */
		String LineSeparator = "LST_SEMICOLON";
		/*
		 * String PreferredLanguage = address.getPreferredLanguage(); String
		 * Capitalization = address.getCapitalization();
		 */
		String FormattedAddressWithOrganization = "false";
		String Street = address.getStreet();
		String HouseNumber = address.getHouseNumber();
		String Locality = address.getLocality();
		String PostalCode = address.getPostalCode();
		String Province = address.getProvince();
		String Country = address.getCountry();
		String Street2 = address.getStreet2();
		System.out.println("address.getStreet2()-------->"+address.getStreet2());
		String PORTAL_ID = address.getPORTAL_ID();
		String DeliveryAddressLines = null;
		String DeliveryAddressLines2 = null;
		String Suit = null;
		String status = null;
		String department = null;
		String building = null;
		String subbulding = null;
		String residue = null;
		String deliveryService = null;
		String Organization = null;
		String str2 = null;

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
			String xml = "<soap:Envelope\r\n" + "	xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"\r\n"
					+ "	xmlns:v4=\"http://validator5.AddressDoctor.com/Webservice5/v4\">\r\n" + "	<soap:Header/>\r\n"
					+ "	<soap:Body>\r\n" + "		<v4:Process>\r\n" + "			<v4:login>" + CustomerID
					+ "</v4:login>\r\n" + "			<v4:password>" + Password + "</v4:password>\r\n"
					+ "			<v4:parameters>\r\n" + "				<v4:ProcessMode>BATCH</v4:ProcessMode>\r\n"
					+ "			<v4:ValidationParameters>\r\n"
					+ "					<v4:CountryType>ISO2</v4:CountryType>\r\n"
					+ "					<v4:CountryOfOriginISO3>COO_ALWAYS_USE_DESTINATION_COUNTRY</v4:CountryOfOriginISO3>\r\n"
					+ "					<v4:StreetWithNumber>" + StreetWithHNo + "</v4:StreetWithNumber>\r\n"
					+ "				</v4:ValidationParameters>\r\n" + "			</v4:parameters>\r\n"
					+ "			<v4:addresses>\r\n" + "				<v4:Address>\r\n"
					+ "					<v4:RecordId>1</v4:RecordId>\r\n"
					+ "					<v4:DeliveryAddressLines>\r\n" + "					<v4:string>" + Street
					+ "</v4:string>\r\n" + "					</v4:DeliveryAddressLines>\r\n"
					+ "					<v4:Locality>\r\n" + "						<v4:string>" + Locality
					+ "</v4:string>\r\n" + "					</v4:Locality>\r\n"
					+ "					<v4:PostalCode>\r\n" + "						<v4:string>" + PostalCode
					+ "</v4:string>\r\n" + "					</v4:PostalCode>\r\n"
					+ "					<v4:Province>\r\n" + "						<v4:string>" + Province
					+ "</v4:string>\r\n" + "						</v4:Province>\r\n"
					+ "					<v4:Country>\r\n" + "						<v4:string>" + Country
					+ "</v4:string>\r\n" + "					</v4:Country>\r\n" + "				</v4:Address>\r\n"
					+ "			</v4:addresses>\r\n" + "		</v4:Process>\r\n" + "	</soap:Body>\r\n"
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
			logger.info("Address Doctor Response is      :   \n" + response.toString());
			JSONObject xmlJSONObj = XML.toJSONObject(response.toString());
			jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			jsonPrettyPrintString = jsonPrettyPrintString.replaceAll("[{}]", " ");
			String xml1 = response.toString();
			System.out.println(jsonPrettyPrintString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder;
			try {
				builder = factory.newDocumentBuilder();
				org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(xml1)));
				NodeList n1 = document.getElementsByTagName("HouseNumber");
				if (n1.getLength() > 0) {
					HouseNumber = n1.item(0).getTextContent();
					plainPojo.setHousenumber(HouseNumber);
				}

				NodeList n2 = document.getElementsByTagName("Street");
				if (n2.getLength() > 0) {
					str2 = n2.item(0).getTextContent();
					plainPojo.setStreet(str2);
				}

				NodeList n3 = document.getElementsByTagName("Locality");
				if (n3.getLength() > 0) {
					Locality = n3.item(0).getTextContent();
					plainPojo.setLocality(Locality);
				}
				PostalCode = document.getElementsByTagName("PostalCode").item(0).getTextContent();
				plainPojo.setPostalCode(PostalCode);
				NodeList n4 = document.getElementsByTagName("Province");
				for (int i = 0; i < getvalues(n4).size(); i++) {
					if (i == 0) {
						String str = getvalues(n4).get(i).toString();
						plainPojo.setProvince(str);
					}
				}

				Country = document.getElementsByTagName("Country").item(0).getTextContent();
				plainPojo.setCountry(Country);
				NodeList n5 = document.getElementsByTagName("SubBuilding");
				if (n5.getLength() > 0) {
					String[] str = getvalues(n5).stream().toArray(String[]::new);
					subbulding = String.join(" ", str);
					Suit = str[0];
				}
				System.out.println(subbulding);
				plainPojo.setSuit(subbulding);
				NodeList n9 = document.getElementsByTagName("DeliveryService");
				if (n9.getLength() > 0) {
					deliveryService = n9.item(0).getTextContent();
				}
				if (verification(HouseNumber) && verification(str2) && verification(subbulding)) {
					DeliveryAddressLines = HouseNumber.concat(" ").concat(str2).concat(" ").concat(subbulding);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				} else if (verification(str2) && verification(HouseNumber)) {
					DeliveryAddressLines = HouseNumber.concat(" ").concat(str2);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				} else if (verification(str2) && verification(subbulding)) {
					DeliveryAddressLines = str2.concat(" ").concat(subbulding);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				} else if (verification(HouseNumber) && verification(subbulding)) {
					DeliveryAddressLines = HouseNumber.concat(" ").concat(subbulding);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				} else if (verification(str2)) {
					DeliveryAddressLines = str2;
					plainPojo.setAddress_line1(DeliveryAddressLines);
				} else if (verification(HouseNumber)) {
					DeliveryAddressLines = HouseNumber;
					plainPojo.setAddress_line1(DeliveryAddressLines);
				} else if (verification(subbulding)) {
					DeliveryAddressLines = subbulding;
					plainPojo.setAddress_line1(DeliveryAddressLines);
				}
				if (verification(deliveryService)) {
					if (verification(DeliveryAddressLines)) {
						DeliveryAddressLines = DeliveryAddressLines.concat(" ").concat(deliveryService);
						plainPojo.setAddress_line1(DeliveryAddressLines);
					} else {
						DeliveryAddressLines = deliveryService;
						plainPojo.setAddress_line1(DeliveryAddressLines);
					}

				}
				System.out.println("address line one is " + plainPojo.getAddress_line1());
				status = document.getElementsByTagName("ProcessStatus").item(0).getTextContent();
				plainPojo.setStatus(status);
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
				if (verification(building)) {
					DeliveryAddressLines = DeliveryAddressLines.concat(" ").concat(building);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				}
				if (verification(Organization)) {
					DeliveryAddressLines = DeliveryAddressLines.concat(" ").concat(Organization);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				}
				if (verification(residue)) {
					DeliveryAddressLines = DeliveryAddressLines.concat(" ").concat(residue);
					plainPojo.setAddress_line1(DeliveryAddressLines);
				}
				DeliveryAddressLines = document.getElementsByTagName("DeliveryAddressLines").item(0).getTextContent();
				/*
				 * NodeList n6 = document.getElementsByTagName("Department"); if (n6.getLength()
				 * > 0) { department = n6.item(0).getTextContent(); } NodeList n7 =
				 * document.getElementsByTagName("Building"); if (n7.getLength() > 0) { building
				 * = n7.item(0).getTextContent(); plainPojo.setBuilding(building); } NodeList n8
				 * = document.getElementsByTagName("Residue"); if (n8.getLength() > 0) { residue
				 * = n8.item(0).getTextContent(); }
				 * 
				 * if (subbulding != null && building != null && residue != null &&
				 * !subbulding.equalsIgnoreCase("") && !subbulding.equalsIgnoreCase("null") &&
				 * !subbulding.equalsIgnoreCase("Null") && !building.equalsIgnoreCase("") &&
				 * !building.equalsIgnoreCase("null") && !building.equalsIgnoreCase("Null") &&
				 * !residue.equalsIgnoreCase("") && !residue.equalsIgnoreCase("null") &&
				 * !residue.equalsIgnoreCase("Null")) { DeliveryAddressLines2 =
				 * subbulding.concat(" ").concat(building).concat(" ").concat(residue);
				 * plainPojo.setSt2(DeliveryAddressLines2); } else if (subbulding != null &&
				 * building != null && !subbulding.equalsIgnoreCase("") &&
				 * !subbulding.equalsIgnoreCase("null") && !subbulding.equalsIgnoreCase("Null")
				 * && !building.equalsIgnoreCase("") && !building.equalsIgnoreCase("null") &&
				 * !building.equalsIgnoreCase("Null")) { DeliveryAddressLines2 =
				 * subbulding.concat(" ").concat(building);
				 * plainPojo.setSt2(DeliveryAddressLines2); } else if (building != null &&
				 * residue != null && !building.equalsIgnoreCase("") &&
				 * !building.equalsIgnoreCase("null") && !building.equalsIgnoreCase("Null") &&
				 * !residue.equalsIgnoreCase("") && !residue.equalsIgnoreCase("null") &&
				 * !residue.equalsIgnoreCase("Null")) { DeliveryAddressLines2 =
				 * building.concat(" ").concat(residue);
				 * plainPojo.setSt2(DeliveryAddressLines2); } else if (subbulding != null &&
				 * residue != null && !subbulding.equalsIgnoreCase("") &&
				 * !subbulding.equalsIgnoreCase("null") && !subbulding.equalsIgnoreCase("Null")
				 * && !residue.equalsIgnoreCase("") && !residue.equalsIgnoreCase("null") &&
				 * !residue.equalsIgnoreCase("Null")) { DeliveryAddressLines2 =
				 * subbulding.concat(" ").concat(residue);
				 * plainPojo.setSt2(DeliveryAddressLines2); } else if (subbulding != null &&
				 * !subbulding.equalsIgnoreCase("") && !subbulding.equalsIgnoreCase("Null") &&
				 * !subbulding.equalsIgnoreCase("Null")) { DeliveryAddressLines2 = subbulding;
				 * plainPojo.setSt2(DeliveryAddressLines2); } else if (residue != null &&
				 * !residue.equalsIgnoreCase("") && !residue.equalsIgnoreCase("Null") &&
				 * !residue.equalsIgnoreCase("Null")) { DeliveryAddressLines2 = residue;
				 * plainPojo.setSt2(DeliveryAddressLines2); } else if (building != null &&
				 * !building.equalsIgnoreCase("") && !building.equalsIgnoreCase("null") &&
				 * !building.equalsIgnoreCase("Null")) { DeliveryAddressLines2 = building;
				 * plainPojo.setSt2(DeliveryAddressLines2); }
				 */
				TransformerFactory tranFactory = TransformerFactory.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();
				Source src = new DOMSource(document);

				/*
				 * Result dest = new StreamResult(new File(
				 * "C:\\Users\\admvantony\\Documents\\workspace-spring-tool-suite-4-4.9.0.RELEASE\\SearchCreate\\SearchCreate\\xmlFileName.xml"
				 * ));
				 * 
				 * 
				 * aTransformer.transform(src, dest);
				 */
				InputSource src1 = new InputSource();
				src1.setCharacterStream(new StringReader(xml1));
				System.out.println(age);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);

		}
		return searchMatch(party, address).toString();
	}

	public JSONObject searchMatch(Party party, Address address) {
		try {
			StringBuffer sb = new StringBuffer();
			if (verification(party.getDisplay_Name()) && verification(party.getParty_Type())
					&& verification(party.getRoleType()) && verification(address.getCountry())) {

			} else {
				sb.append("{");
				sb.append(System.lineSeparator());
				sb.append("ERROR : Manditory Fields Cannot be null or Empty");
				sb.append("}");
				JSONObject json = new JSONObject(sb.toString());
				return json;
			}

			executePUTFORPARTY(party, address);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
		}

		return search();

	}

	public boolean executePUTFORPARTY(Party party, Address address) throws NullPointerException {

		System.out.println("put method");
		String sourceKey, systemName;
		sourceKey = "";
		systemName = "Portal";
		try {
			PutRequest request = new PutRequest();
			RecordKey recKey = new RecordKey();
			if ((sourceKey == null) || sourceKey.equals("")) {
				request.setGenerateSourceKey(true);
				System.out.println("NEW SOURCE KEY GENERATED");
				System.out.println("inside if");
			} else {
				System.out.println("inside else");
				recKey.setSourceKey(sourceKey);
			}

			recKey.setSystemName(systemName);
			request.setRecordKey(recKey);

			System.out.println(recKey);

			Record record = new Record();
			record.setSiperianObjectUid(SiperianObjectType.BASE_OBJECT.makeUid("C_BO_PRTY"));// Name PUT // Package
																								// String
																								// // NAME_PREFIX_CD =
																								// //
			try {
				if (verification(party.getDisplay_Name())) {
					record.setField(new Field("FULL_NM", capitalize(party.getDisplay_Name())));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			if (verification(party.getParty_Type())) {
				record.setField(new Field("PRTY_TYP", capitalize(party.getParty_Type())));
			}
			if (verification(party.getSpeciality())) {
				record.setField(new Field("SPECIALTY", capitalize(party.getSpeciality())));
			} else {
				record.setField(new Field("SPECIALTY", (party.getSpeciality())));
			}
			if (verification(party.getRoleType()))
				record.setField(new Field("RLE_TYP", party.getRoleType()));
			if (verification(party.getAccount_Nr())) {
				record.setField(new Field("ACCOUNT_NR", party.getAccount_Nr()));
			}
			if (party.getSOURCE_ID() != null && !party.getSOURCE_ID().equalsIgnoreCase("")) {
				/*
				 * System.out.println("party.getSOURCE_ID()------>"+party.getSource_Id()+
				 * "Party_id------->"+Party_id);
				 */
				record.setField(new Field("SOURCE_ID", party.getSOURCE_ID()));
			}
			if (verification(party.getOther_Speciality())) {
				record.setField(new Field("OTHER_SPECIALITY", capitalize(party.getOther_Speciality())));
			} else {
				record.setField(new Field("OTHER_SPECIALITY", (party.getOther_Speciality())));
			}
			String mdm_id = party.getMdm_id();
			String sub = mdm_id.substring(0, 2);
			String remainder = mdm_id.substring(2);
			mdm_id = sub.concat(String.format("%09d", (Integer.valueOf(remainder) + 1)));

			System.out.println("mdm_id-------is------->" + party.getMdm_id());
			record.setField(new Field("MDM_ID", mdm_id));
			request.setRecord(record);
			/* allow */
			HttpSiperianClient sipClient = null;
			try {
				sipClient = (HttpSiperianClient) CreateConnection.getSIFClientObject().getHttpSIFClient();
				System.out.println(sipClient);
				logger.info("sipClient---------------->" + sipClient);
			} catch (IOException e) { // TODOAuto-generated catch block
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			System.out.println(sipClient);
			PutResponse response = (PutResponse) sipClient.process(request);
			System.out.println("MESSAGE FROM PUT :: " + response.getMessage().toString());
			logger.info("MESSAGE FROM PUT :: " + response.getMessage().toString());

			if (response.getRecordKey().getRowid() != null && !response.getRecordKey().getRowid().trim().isEmpty()) {
				Party_id = response.getRecordKey().getRowid().trim();
				System.out.println("ROWID OBJECT :: " + response.getRecordKey().getRowid().trim());
				System.out.println("Record Key :: " + response.getRecordKey());
				System.out.println("getActionType :: " + response.getActionType());
				System.out.println("getMessage :: " + response.getMessage());
				System.out.println("getInteractionId :: " + response.getInteractionId());
			}
			Record_Key = response.getRecordKey();
			Action_Type = response.getActionType();
			return executePUTFORPHONEANDFAX(party, address);
		} catch (Exception ex) {
			System.out.println("ERROR" + ex.getMessage());
			logger.info(ex.getMessage());
			return false;
		}

	}

	public boolean executePUTFORPHONEANDFAX(Party party, Address address) throws NullPointerException {

		System.out.println("executePUTFORPHONEANDFAX");
		String sourceKey, systemName;
		sourceKey = "";
		systemName = "Portal";
		try {
			PutRequest request = new PutRequest();
			RecordKey recKey = new RecordKey();
			if ((sourceKey == null) || sourceKey.equals("")) {
				request.setGenerateSourceKey(true);
				System.out.println("NEW SOURCE KEY GENERATED FOR PHONE AND FAX");
			} else {
				recKey.setSourceKey(sourceKey);
			}

			recKey.setSystemName(systemName);
			request.setRecordKey(recKey);
			System.out.println(recKey);

			Record record = new Record();
			record.setSiperianObjectUid(SiperianObjectType.BASE_OBJECT.makeUid("C_BO_PRTY_RLE_PHN_COMM"));// Name PUT //
			try {

				record.setField(new Field("PHN_NUM", party.getPhone()));
				record.setField(new Field("FAX_NUMBER", party.getFax()));
				record.setField(new Field("PRTY_FK", Party_id));

				request.setRecord(record);
				System.out.println(record);
			} catch (Exception e) {
				System.out.println(e);
			}
			HttpSiperianClient sipClient = null;
			try {
				sipClient = (HttpSiperianClient) CreateConnection.getSIFClientObject().getHttpSIFClient();
				System.out.println(sipClient);
				logger.info("sipClient------C_BO_PRTY_RLE_PHN_COMM----------    :   " + sipClient);
			} catch (IOException e) {
				// TODOAuto-generated catch block
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			System.out.println(sipClient);
			PutResponse response = (PutResponse) sipClient.process(request);
			System.out.println("MESSAGE FROM PUT :: " + response.getMessage().toString());
			logger.info("MESSAGE FROM PUT :: " + response.getMessage().toString());
			if (response.getRecordKey().getRowid() != null && !response.getRecordKey().getRowid().trim().isEmpty()) {
				Address_id = response.getRecordKey().getRowid().trim();
				System.out.println("ROWID OBJECT :: " + response.getRecordKey().getRowid().trim());
				System.out.println("Record Key :: " + response.getRecordKey());
				System.out.println("getActionType :: " + response.getActionType());
				System.out.println("getMessage :: " + response.getMessage());
				System.out.println("getInteractionId :: " + response.getInteractionId());
			}

			return executePUTFORADDRESS(party, address);
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			throw new ExceptionClass(ex);
		}

	}

	public boolean executePUTFORADDRESS(Party party, Address address) throws NullPointerException {

		System.out.println("executePUTAndTokenizeFORADDRESS");
		String sourceKey, systemName;
		sourceKey = "";
		systemName = "Portal";
		try {
			PutRequest request = new PutRequest();
			RecordKey recKey = new RecordKey();
			if ((sourceKey == null) || sourceKey.equals("")) {
				request.setGenerateSourceKey(true);
				System.out.println("NEW SOURCE KEY GENERATED");
			} else {
				recKey.setSourceKey(sourceKey);
			}

			recKey.setSystemName(systemName);
			request.setRecordKey(recKey);
			System.out.println(recKey);

			Record record = new Record();
			record.setSiperianObjectUid(SiperianObjectType.BASE_OBJECT.makeUid("C_BO_PSTL_ADDR"));// Name PUT //
			try {

				System.out.println(plainPojo.getAddress_line1());
				if (verification(plainPojo.getLocality())) {
					record.setField(new Field("CITY", capitalize(plainPojo.getLocality())));
				}
				String PSTL_CD_EXT = null;
				String PostalCode = null;
				Pattern p = Pattern.compile("[^A-Za-z0-9]");
				Matcher m = p.matcher(plainPojo.getPostalCode());
				// boolean b = m.matches();
				boolean b = m.find();
				if (b) {
					String[] splitString = plainPojo.getPostalCode().split("-");
					PostalCode = splitString[0];
					PSTL_CD_EXT = splitString[1];
				} else {
					PostalCode = PostalCode;
				}
				if (PostalCode != null && !PostalCode.equalsIgnoreCase("")) {
					record.setField(new Field("PSTL_CD", PostalCode));
				}
				if (PSTL_CD_EXT != null && !PSTL_CD_EXT.equalsIgnoreCase("")) {
					record.setField(new Field("PSTL_CD_EXT", PSTL_CD_EXT));
				}

				if (verification(plainPojo.getProvince())) {
					record.setField(new Field("STATE", plainPojo.getProvince()));
				}
				if (verification(plainPojo.getCountry())) {
					record.setField(new Field("CNTRY_CD", plainPojo.getCountry()));
				}

				if (verification(plainPojo.getStreet())) {
					record.setField(new Field("STREET_NM", capitalize(plainPojo.getStreet())));
				} else {
					record.setField(new Field("STREET_NM", plainPojo.getStreet()));
				}
				if (plainPojo.getHousenumber() != null && !plainPojo.getHousenumber().equalsIgnoreCase("")) {
					record.setField(new Field("STREET_NO", plainPojo.getHousenumber()));
				}
				if (verification(plainPojo.getAddress_line1())) {
					record.setField(new Field("ADDR_LN_1", capitalize(plainPojo.getAddress_line1())));
				} else {
					record.setField(new Field("ADDR_LN_1", plainPojo.getAddress_line1()));
				}
				System.out.println(capitalize(plainPojo.getAddress_line1()));
				if (plainPojo.getStatus() != null && !plainPojo.getStatus().equalsIgnoreCase("")) {
					record.setField(new Field("VLDTN_STS_CD", (plainPojo.getStatus())));
				}
				System.out.println(plainPojo.getSuit());
				if (verification(plainPojo.getSuit())) {
					record.setField(new Field("SUITE", capitalize(plainPojo.getSuit())));
				} else {
					record.setField(new Field("SUITE", (plainPojo.getSuit())));
				}

				if (verification(address.getStreet2())) {

					record.setField(new Field("ADDR_LN_2", capitalize(address.getStreet2())));
				} else {
					record.setField(new Field("ADDR_LN_2", address.getStreet2()));
				}
				if (address.getPORTAL_ID() != null && !address.getPORTAL_ID().equalsIgnoreCase("")) {
					record.setField(new Field("SOURCE_ID", address.getPORTAL_ID()));
				}
				request.setRecord(record);
				System.out.println(record);
			} catch (Exception e) {
				logger.info(e.getMessage());
				System.out.println(e);
			}
			HttpSiperianClient sipClient = null;
			try {
				sipClient = (HttpSiperianClient) CreateConnection.getSIFClientObject().getHttpSIFClient();
				System.out.println(sipClient);
				logger.info("sipClient--------C_BO_PSTL_ADDR---------:  " + sipClient);
			} catch (IOException e) { // TODOAuto-generated catch block
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			System.out.println(sipClient);
			PutResponse response = (PutResponse) sipClient.process(request);
			System.out.println("MESSAGE FROM PUT :: " + response.getMessage().toString());
			logger.info("MESSAGE FROM PUT :: " + response.getMessage().toString());

			if (response.getRecordKey().getRowid() != null && !response.getRecordKey().getRowid().trim().isEmpty()) {
				Address_id = response.getRecordKey().getRowid().trim();
				System.out.println("ROWID OBJECT :: " + response.getRecordKey().getRowid().trim());
				System.out.println("Record Key :: " + response.getRecordKey());
				System.out.println("getActionType :: " + response.getActionType());
				System.out.println("getMessage :: " + response.getMessage());
				System.out.println("getInteractionId :: " + response.getInteractionId());
			}
			/*Calling update date for C_BO_PRTY_XREF*/
			updatePkeySrc(party);
			/*Calling update date for C_BO_PSTL_addr_XREF*/
			//updateAddressPkeySrc(address);
			return executePUTFORCROSSREF();// executePUTFORPHONEANDFAX( party, address);
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			throw new ExceptionClass(ex);
		}

	}

	public boolean executePUTFORCROSSREF() {

		System.out.println("put method");
		String sourceKey, systemName;
		sourceKey = "";
		systemName = "Portal";
		try {
			PutRequest request = new PutRequest();
			RecordKey recKey = new RecordKey();
			if ((sourceKey == null) || sourceKey.equals("")) {
				request.setGenerateSourceKey(true);
				System.out.println("NEW SOURCE KEY GENERATED");
			} else {
				recKey.setSourceKey(sourceKey);
			}

			recKey.setSystemName(systemName);
			request.setRecordKey(recKey);
			System.out.println(recKey);

			Record record = new Record();
			record.setSiperianObjectUid(SiperianObjectType.BASE_OBJECT.makeUid("C_BR_PRTY_RLE_PSTL_ADDR"));// Name
			record.setField(new Field("PRTY_FK", Party_id));
			record.setField(new Field("PSTL_ADDR_FK", Address_id));
			request.setRecord(record);
			HttpSiperianClient sipClient = null;
			try {
				sipClient = (HttpSiperianClient) CreateConnection.getSIFClientObject().getHttpSIFClient();
				System.out.println(sipClient);
				logger.info("sipClient-------C_BR_PRTY_RLE_PSTL_ADDR------- : " + sipClient);
			} catch (IOException e) { // TODOAuto-generated catch block
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			System.out.println(sipClient);
			PutResponse response = (PutResponse) sipClient.process(request);
			System.out.println("MESSAGE FROM PUT :: " + response.getMessage().toString());
			logger.info("MESSAGE FROM PUT :: " + response.getMessage().toString());

			if (response.getRecordKey().getRowid() != null && !response.getRecordKey().getRowid().trim().isEmpty()) {
				System.out.println("ROWID OBJECT :: " + response.getRecordKey().getRowid().trim());
				System.out.println("Record Key :: " + response.getRecordKey());
				System.out.println("getActionType :: " + response.getActionType());
				System.out.println("getMessage :: " + response.getMessage());
				System.out.println("getInteractionId :: " + response.getInteractionId());
			}
			return TokenizationProcess();
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			throw new ExceptionClass(ex);
		}
	}

	public boolean TokenizationProcess() {
		// Generate Match Token
		try {
			HttpSiperianClient sipClient = null;
			try {
				sipClient = (HttpSiperianClient) CreateConnection.getSIFClientObject().getHttpSIFClient();
				System.out.println(sipClient);
				logger.info("sipClient-----TokenizationProcess-----PACKAGE_CREATE_API----  : s" + sipClient);
			} catch (IOException e) { // TODOAuto-generated catch block
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			System.out.println(sipClient);
			TokenizeRequest tokenizeRequest = new TokenizeRequest();
			tokenizeRequest = new TokenizeRequest();
			tokenizeRequest.setSiperianObjectUid(SiperianObjectType.PACKAGE.makeUid("PACKAGE_CREATE_API"));
			tokenizeRequest.setRecordKey(Record_Key);
			tokenizeRequest.setActionType(Action_Type);
			TokenizeResponse tokenizeResponse = (TokenizeResponse) sipClient.process(tokenizeRequest);
			logger.info(
					"MESSAGE FROM Tokenize Request WITH PHONE AND FAX :: " + tokenizeResponse.getMessage().toString());
			System.out.println(
					"MESSAGE FROM Tokenize Request WITH PHONE AND FAX :: " + tokenizeResponse.getMessage().toString());
			/* sb1.append(tokenizeResponse.getMessage().toString()); */
			return true;
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new ExceptionClass(e);
		}
	}

	public JSONObject search() {
		JSONObject json = null;

		try {
			int i = 0;
			Record respRecord = null;
			Field field = null;
			StringBuilder sb1 = new StringBuilder();
			sb1.delete(0, sb1.length());
			GetRequest request = new GetRequest();
			GetResponse response = new GetResponse();
			RecordKey recordKey = new RecordKey();
			recordKey.setRowid(Party_id);
			System.out.println(recordKey);
			request.setRecordKey(recordKey);
			request.setSiperianObjectUid("PACKAGE.PACKAGE_CREATE_API");
			HttpSiperianClient sipClient = null;
			try {
				sipClient = (HttpSiperianClient) CreateConnection.getSIFClientObject().getHttpSIFClient();
				logger.info("sipClient-------searchMethod-------  :  " + sipClient);
				System.out.println(sipClient);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			try {
				response = (GetResponse) sipClient.process(request);
				logger.info("GetResponse is  :  " + response);
				System.out.println(response);
			} catch (Exception e) {
				System.out.println("-------Get Api Failure message------" + e);
				logger.info("-------Get Api Failure message------" + e.getMessage());
			}
			for (Iterator iter = response.getRecords().iterator(); iter.hasNext();) {
				System.out.println("=================Printing matched record " + i);
				i++;

				respRecord = (Record) iter.next();
				if (respRecord.getField("DEFINITIVE_MATCH_IND") != null) {
					BigDecimal definitiveMatch = respRecord.getField("DEFINITIVE_MATCH_IND").getBigDecimalValue();
					if (definitiveMatch.intValue() == 1) {
						System.out.println("Matched on an auto-merge rule");
						logger.info("Matched on an auto-merge rule");
					} else {
						System.out.println("Did not Match on an auto-merge rule");
						logger.info("Did not Match on an auto-merge rule");
					}
				}

				Collection fields = respRecord.getFields();
				if (sb1.length() == 0) {
					sb1.append("{");
					sb1.append(System.lineSeparator());
					for (Iterator fieldIter = fields.iterator(); fieldIter.hasNext();) {
						// iterate through rest of fields
						field = (Field) fieldIter.next();
						sb1.append(System.lineSeparator());
						sb1.append(field.getName() + ": " + field.getValue() + " ");
						sb1.append(",");
						sb1.append(System.lineSeparator());
					}
					sb1.append("}");

				}
				System.out.println(sb1);

				try {
					logger.info("Response is :  \n" + sb1.toString());
					json = new JSONObject(sb1.toString());

				} catch (Exception e) {
					System.out.println(e);
					logger.info(e.getMessage());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			throw new ExceptionClass(e);

		}
		return json;

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
				for (int i = 0; i < subList.getLength(); i++) {

					destinationArray.add(subList.item(i).getTextContent());
				}

			}
		}
		return destinationArray;
	}

	public static boolean isStringOnlyAlphabetWithSpace(String str) {
		return ((str != null) && (!str.equals("")) && (str.matches("^[\\p{L} .'-]+$")));
	}

	public static boolean isStringOnlyAlphabet(String str) {
		return ((str != null) && (!str.equals("")) && (str.matches("^[\\p{L} .']+$")));
	}

	public static boolean isStringAlphanumeric(String str) {
		return ((str != null) && (!str.equals("")) && (str.matches("\\p{Alnum}+")));
	}

	public static boolean isParty_type(String str) {
		return ((str != null) && (!str.equals("")) && (str.equals("Org")));
	}

	public static boolean isRol_type(String str) {
		return ((str != null) && (!str.equals("")) && (str.equals("HCO")));
	}

	public static boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}

	public boolean verification(String value) {
		if (value != null && !value.equalsIgnoreCase("") && !value.equalsIgnoreCase("null")
				&& !value.equalsIgnoreCase("Null") && !value.equalsIgnoreCase("NULL") && !value.equalsIgnoreCase(" ")) {
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

	public List<String> getMDM_ID() {
		List<String> Mdm_Id = new ArrayList<String>();
		try {
			Mdm_Id = boPartyRepo.getMDM_ID();

		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
		}
		return Mdm_Id;
	}

	public void updatePkeySrc(Party party) {

		System.out.println("party.getSOURCE_ID()------>" + party.getSOURCE_ID() + "Party_id------->" + Party_id);
		boPartyRepo.updatePkeySrc(party.getSOURCE_ID(), Party_id);
	}
	public void updateAddressPkeySrc(Address address) {

		System.out.println("party.getSOURCE_ID()------>" + address.getPORTAL_ID()+ "Address_id------->" + Address_id);
		boPartyRepo.updateAddressPkeySrc(address.getPORTAL_ID(), Address_id);
	}
	

	public boolean verifyPkey_source_id(String source_id) {

		System.out.println("inside verifyPkey_source_id");
		boolean flag=false;
		List<String> Pkey=boPartyRepo.verigySourceId(source_id);
		if(Pkey.size()==0) {
			flag=true;
		}else {
			flag=false;
		}
		System.out.println("The flag in verify method is"+flag);
		return flag;
	}
	
	public boolean verifyAddressPkey_source_id(String portal_id) {

		System.out.println("inside verifyPkey_source_id");
		boolean pflag=false;
		List<String> Pkey=boPartyRepo.verigyportalId(portal_id);
		if(Pkey.size()==0) {
			pflag=true;
		}else {
			pflag=false;
		}
		System.out.println("The flag in verify method is"+pflag);
		return pflag;
	}
	
	public int CookieeValidate(String sessionID) throws HttpException, IOException {

		String result = null;
		int response = 0;
		String authValidateURL = "http://qapp01mdm01:8080/cmx/session/validate";
		GetMethod getMethod = null;
		getMethod = new GetMethod(authValidateURL);
		getMethod.setRequestHeader("Cookie", sessionID);
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		response = httpClient.executeMethod(getMethod); // Execute the POST method
		result = getMethod.getResponseBodyAsString();
		/*
		 * if (response == 200) { return new
		 * ResponseEntity<>("Welcome to Authentication based on MDM AUTH",
		 * HttpStatus.OK); } else { return new ResponseEntity<>(result,
		 * HttpStatus.UNAUTHORIZED); }
		 */
		return response;

	}
}
