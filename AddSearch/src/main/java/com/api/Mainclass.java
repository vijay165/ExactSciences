package com.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Mainclass {

	public static void main(String[] args) {
		String yourString="4949 market st";
		String value=StringUtils.capitalize(yourString);
	     System.out.println(value);
	     value = capitalize(yourString);
	     System.out.println(value);
	}
	public static String capitalize(String str)
	{
		String DeliveryAddressLines = str.toLowerCase();
		char[] charArray1 = DeliveryAddressLines.toCharArray();
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
		DeliveryAddressLines = String.valueOf(charArray1);
		return DeliveryAddressLines;
	}
}
