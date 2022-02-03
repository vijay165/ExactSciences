package com.api.DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import com.siperian.sif.client.HttpSiperianClient;
import com.siperian.sif.client.SiperianClient;

public class CreateSIFClient {

	private static CreateSIFClient sifClientObject;
	private SiperianClient sifClient;
	private HttpSiperianClient httpSifClient;

//static Logger log = Logger.getLogger(CreateSIFClient.class);
	/** A private Constructor prevents any other class from instantiating. */
	private CreateSIFClient() {
// Optional Code
	}

	public static synchronized CreateSIFClient getSIFClientObject() {
		if (sifClientObject == null) {
			sifClientObject = new CreateSIFClient();
		}
		return sifClientObject;

	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

//Creating MDM Client
	public SiperianClient getSIFClient() throws IOException {

		Properties properties = new Properties();
		try {
			
			/*
			 * FileInputStream fileInputStream = new FileInputStream(new File(
			 * "C:\\Users\\admvantony\\Documents\\workspace-spring-tool-suite-4-4.9.0.RELEASE\\AddSearch\\AddSearch\\siperian-client.properties"
			 * ));
			 */
///app/etc/tst/con
			/* Properties */properties = new Properties();
			properties.load(this.getClass().getClassLoader().getResourceAsStream("siperian-client.properties"));
			/* properties.load(fileInputStream); */
			sifClient = SiperianClient.newSiperianClient(properties);
			System.out.println("Got connection ");
			String encryptedPassword = sifClient.getConfig().getProperty("siperian-client.password");
			System.out.println("encryptedPassword " + encryptedPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sifClient;
	}

	// Creating MDM HttpSiperianClient
	public HttpSiperianClient getHttpSIFClient() throws IOException {

		Properties properties = new Properties();
		try {
			/*
			 * FileInputStream fileInputStream = new FileInputStream(new File
			 * ("C:\\Users\\admvantony\\Documents\\workspace-spring-tool-suite-4-4.9.0.RELEASE\\AddSearch\\AddSearch\\siperian-client.properties"
			 * ));
			 */
			/* properties.load(fileInputStream); */
			InputStream  is = this.getClass().getClassLoader().getResourceAsStream("siperian-client.properties");
			properties.load(is);
			httpSifClient = (HttpSiperianClient) HttpSiperianClient.newSiperianClient(properties);
			System.out.println("Got connection ");
//String encryptedPassword = sifClient.getConfig().getProperty("siperian-client.password");
//System.out.println("encryptedPassword "+encryptedPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return httpSifClient;
	}
}
