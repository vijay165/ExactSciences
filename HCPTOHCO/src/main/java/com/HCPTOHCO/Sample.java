package com.HCPTOHCO;



import java.io.IOException;

import org.json.JSONObject;

public class Sample {

	public static void main(String[] args) throws IOException {
		String sb2 = "{\r\n"
				+ "    [\r\n"
				+ "        \" HCP \": \"CCT23788\",\r\n"
				+ "        \" MDMID \": \"MC\",\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71390\",\r\n"
				+ "            \" MDMID \": \"MH100045654\",\r\n"
				+ "            \" PRIMARY \": \"No\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71391\",\r\n"
				+ "            \" MDMID \": \"MH100045655\",\r\n"
				+ "            \" PRIMARY \": \"No\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71392\",\r\n"
				+ "            \" MDMID \": \"MH100045656\",\r\n"
				+ "            \" PRIMARY \": \"Yes\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    [\r\n"
				+ "        \" HCP \": \"CCT237880\",\r\n"
				+ "        \" MDMID \": \"MC\",\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71390\",\r\n"
				+ "            \" MDMID \": \"MH100045654\",\r\n"
				+ "            \" PRIMARY \": \"No\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71391\",\r\n"
				+ "            \" MDMID \": \"MH100045655\",\r\n"
				+ "            \" PRIMARY \": \"Yes\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71392\",\r\n"
				+ "            \" MDMID \": \"MH100045656\",\r\n"
				+ "            \" PRIMARY \": \"No\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    [\r\n"
				+ "        \" HCP \": \"CCT23789\",\r\n"
				+ "        \" MDMID \": \"MC\",\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71390\",\r\n"
				+ "            \" MDMID \": \"MH100045654\",\r\n"
				+ "            \" PRIMARY \": \"Yes\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71391\",\r\n"
				+ "            \" MDMID \": \"MH100045655\",\r\n"
				+ "            \" PRIMARY \": \"No\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CCR71392\",\r\n"
				+ "            \" MDMID \": \"MH100045656\",\r\n"
				+ "            \" PRIMARY \": \"No\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    [\r\n"
				+ "        \" HCP \": \"CT177835\",\r\n"
				+ "        \" MDMID \": \"MC\",\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CR714231\",\r\n"
				+ "            \" MDMID \": \"MH\",\r\n"
				+ "            \" PRIMARY \": \"No\"\r\n"
				+ "        },\r\n"
				+ "    ],\r\n"
				+ "    [\r\n"
				+ "        \" HCP \": \"CT177836\",\r\n"
				+ "        \" MDMID \": \"MC\",\r\n"
				+ "        {\r\n"
				+ "            \" HCO \": \"CR714231\",\r\n"
				+ "            \" MDMID \": \"MH\",\r\n"
				+ "            \" PRIMARY \": \"No\"\r\n"
				+ "        },\r\n"
				+ "    ]\r\n"
				+ "}";

		JSONObject json = new JSONObject(sb2);

		System.out.println(json);
				
	}

}
