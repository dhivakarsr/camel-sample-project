package com.centrica.fft.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;

import org.apache.camel.Body;
import org.apache.log4j.Logger;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import com.centrica.fft.constants.FFTConstants;
import com.centrica.fft.exceptions.FFTTechnicalException;

public class GetPropertiesUtil {

	private static transient Logger logger = Logger.getLogger(GetPropertiesUtil.class);
	private Properties mdlwareProperties = null;
	private HashMap<String, String> divProperties = null;
	private HashMap<String, String> absenceProperties = null;
	private HashMap<String, String> memoProperties = null;

	public GetPropertiesUtil() {
		SchemaFactory mySchemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			mySchemaFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
		} catch (SAXNotRecognizedException | SAXNotSupportedException e) {
			FFTUtil.stackTraceToString(e);
		}
	}

	/**
	 * This method is used to the il.properties file to properties Object.
	 * 
	 */
	public void processBundle() {
		try {
			logger.debug("MiddlewareUtil.properties file loading started");
			setBundle(SetProperties("properties/middleware.properties"));
			loadSystemPropertyFiles();
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("MiddlewareUtilExceptionLogger :: Failed " + "to get IL properties" + e);
			throw new FFTTechnicalException("MiddlewareUtilExceptionLogger :: Failed " + "to get IL properties" + e);
		}
	}

	private Properties SetProperties(String filename) {
		Properties middlewareProperties = new Properties();
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
			if (inputStream != null) {
				middlewareProperties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + filename + "' not found in the classpath");
			}
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("MiddlewareUtilExceptionLogger :: Failed " + "to SetProperties" + e);
			throw new FFTTechnicalException("MiddlewareUtilExceptionLogger :: Failed " + "to SetProperties" + e);
		}

		return middlewareProperties;
	}

	/**
	 * This method is used to load the memo test,division,absence property files
	 * to Hash Map.
	 */
	public void loadSystemPropertyFiles() {
		try {

			try {
				divProperties = loadMap(FFTConstants.DIVISION_PROPERTIES_FILE);
				absenceProperties = loadMap(FFTConstants.ABSENCECODES_PROPERTIES_FILE);
				memoProperties = loadMap(FFTConstants.MEMOTEXT_PROPERTIES_FILE);
			} catch (Exception e) {
				FFTUtil.stackTraceToString(e);
				logger.error("MiddlewareUtilExceptionLogger :: Failed " + "to get MiddlewareUtil properties" + e);
			}

		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("MiddlewareUtilExceptionLogger :: Failed to load" + " Properties file " + e);
		}

	}

	/**
	 * This method is used to adding properties to Hash Map.
	 */
	private HashMap<String, String> loadMap(String filename) {
		HashMap<String, String> properties = new HashMap<String, String>();
		Enumeration<Object> propEnum = null;
		Properties props = new Properties();
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
			props = new Properties();
			props.load(inputStream);
			for (propEnum = props.keys(); propEnum.hasMoreElements();) {
				String key = (String) propEnum.nextElement();
				properties.put(key, props.getProperty(key).trim());
			}

		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("MiddlewareUtilExceptionLogger :: Failed to " + "load Properties file " + e);
		}
		return properties;
	}

	public void setBundle(Properties bundle1) {
		mdlwareProperties = bundle1;
	}

	public String getMiddlewareProperty(String key) {
		if (mdlwareProperties == null) {
			processBundle();
		}
		return mdlwareProperties.getProperty(key);
	}

	public String getDivProperty(String key) {
		SchemaFactory mySchemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			mySchemaFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
		} catch (SAXNotRecognizedException | SAXNotSupportedException e) {
			FFTUtil.stackTraceToString(e);
			logger.error("MiddlewareUtilExceptionLogger :: Failed to " + "getDivProperty " + e);
		}
		if (divProperties == null) {
			loadSystemPropertyFiles();
		}
		return divProperties.get(key);
	}

	public String getAbsenseCode(String key) {
		if (absenceProperties == null) {
			loadSystemPropertyFiles();
		}
		return absenceProperties.get(key);
	}

	public String getMemoText(String key) {
		if (memoProperties == null) {
			loadSystemPropertyFiles();
		}
		return memoProperties.get(key);
	}

	public static String replaceSpecialChars(String xmlStr, String startElement, String endElement) {
		String part2 = null;
		String midPart = null;
		String resultStr = "";
		boolean flag = true;
		if (xmlStr.contains(startElement)) {
			do {
				if (flag == true) {
					int startIndex1 = xmlStr.indexOf(startElement);
					String part1 = xmlStr.substring(0, startIndex1 + startElement.length());

					int endIndex1 = xmlStr.indexOf(endElement);
					part2 = xmlStr.substring(endIndex1, xmlStr.length());

					String elementBody = xmlStr.substring(startIndex1 + startElement.length(), endIndex1);
					String replacedBody = addXMLSpecialChars(elementBody);

					midPart = part1 + replacedBody + part2.substring(0, endElement.length());

					flag = false;
				} else {
					xmlStr = part2.substring(endElement.length(), part2.length());
					int startIndex1 = xmlStr.indexOf(startElement);
					String part1 = xmlStr.substring(0, startIndex1 + startElement.length());

					int endIndex1 = xmlStr.indexOf(endElement);
					part2 = xmlStr.substring(endIndex1, xmlStr.length());

					String elementBody = xmlStr.substring(startIndex1 + startElement.length(), endIndex1);
					String replacedBody = addXMLSpecialChars(elementBody);

					midPart = part1 + replacedBody + part2.substring(0, endElement.length());

				}
				resultStr = resultStr + midPart;
			} while (part2.contains(startElement));
			// adding last part into result.
			resultStr = resultStr + part2.substring(endElement.length(), part2.length());
			return resultStr;
		} else {

			return xmlStr;
		}

	}

	public static String specialCharsConversion(@Body String visitMsg) {

		if (visitMsg.contains(FFTConstants.EDIFACT_START_TAG)) {
			visitMsg = removeApos(visitMsg, FFTConstants.EDIFACT_START_TAG, FFTConstants.EDIFACT_END_TAG);
		}
		if (visitMsg.contains(FFTConstants.TEMPORARY_VISIT_INFORMATION_START_TAG)) {
			visitMsg = removeApos(visitMsg, FFTConstants.TEMPORARY_VISIT_INFORMATION_START_TAG,
					FFTConstants.TEMPORARY_VISIT_INFORMATION_END_TAG);
		}
		return visitMsg;
	}

	public String removeExtraCDATA(@Body String visitMsg) {
		String cDATA = FFTConstants.XML_CDATA;
		while (visitMsg.contains(cDATA)) {
			int startIndex = visitMsg.indexOf(cDATA);
			int endIndex = startIndex + cDATA.length();
			visitMsg = visitMsg.substring(0, startIndex) + visitMsg.substring(endIndex, visitMsg.length());
		}
		return visitMsg;
	}

	/**
	 * This method is used to remove &amp;amp; string from the message.
	 * 
	 * @param messageBody
	 * @return String
	 */
	public static String removeApos(String messageBody, String startElement, String endElement) {

		String sFsiString = messageBody;
		logger.debug("Before Removing Apos:" + sFsiString);
		try {
			String arr[] = sFsiString.split(startElement);
			StringBuilder buf = new StringBuilder();
			buf.append(arr[0]);

			for (int i = 1; i < arr.length; i++) {
				logger.debug("Splitup Msg_" + i + " " + arr[i]);
				String subarr[] = arr[i].split(endElement);
				subarr[0] = addXMLSpecialChars(subarr[0]);
				arr[i] = startElement + subarr[0] + endElement + subarr[1];
				buf.append(arr[i]);
			}
			sFsiString = buf.toString();
			logger.debug("After Removing Apos:" + sFsiString);

		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("ILTechnicalExceptionLogger " + " :: Exception in removeApos() method " + e);
		}
		return sFsiString;
	}

	/**
	 * This method is used to remove &amp;amp; string from the message.
	 * 
	 * @param messageBody
	 * @return String
	 */
	public static String addXMLSpecialChars(String messageBody) {

		String modifiedMessage = null;
		try {
			modifiedMessage = messageBody.replaceAll(FFTConstants.AMPERSAND_CHAR, FFTConstants.AMPERSAND_SYMBOL);
			modifiedMessage = modifiedMessage.replaceAll(FFTConstants.LESSTHAN_SIGN, FFTConstants.LEFT_TAG);
			modifiedMessage = modifiedMessage.replaceAll(FFTConstants.GREATERTHAN_SIGN, FFTConstants.RIGHT_TAG);
			modifiedMessage = modifiedMessage.replaceAll(FFTConstants.APOSTROPHE_SIGN, FFTConstants.APOSTROPHE_SYMBOL);
			modifiedMessage = modifiedMessage.replaceAll(FFTConstants.QUOT_SIGN, FFTConstants.BACKSLASH_CHAR);
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("ILTechnicalExceptionLogger " + " :: Exception in addXMLSpecialChars() method " + e);
		}
		return modifiedMessage;
	}

	/**
	 * This method is used to remove &amp;amp; string from the message.
	 * 
	 * @param messageBody
	 * @return String
	 */
	public String removeXMLSpecialChars(String messageBody) {

		String modifiedMessage = null;
		try {

			modifiedMessage = messageBody.replaceAll(FFTConstants.AMPERSAND_SYMBOL, FFTConstants.AMPERSAND_CHAR);
			modifiedMessage = modifiedMessage.replaceAll(FFTConstants.LEFT_TAG, FFTConstants.LESSTHAN_SIGN);
			modifiedMessage = modifiedMessage.replaceAll(FFTConstants.RIGHT_TAG, FFTConstants.GREATERTHAN_SIGN);
			modifiedMessage = modifiedMessage.replaceAll(FFTConstants.APOSTROPHE_SYMBOL, FFTConstants.APOSTROPHE_SIGN);
			modifiedMessage = modifiedMessage.replaceAll(FFTConstants.BACKSLASH_CHAR, FFTConstants.QUOT_SIGN);
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("ILTechnicalExceptionLogger " + " :: Exception in removeXMLSpecialChars() method " + e);
		}
		return modifiedMessage;
	}

	/**
	 * This method is used to create <PostCode> element value from
	 * <POST_OUT_CODE> and <POST_IN_CODE> elements values.
	 * 
	 * @param postCodeOut
	 * @param postCodeIn
	 * @return String value of constructed PostCode value
	 */
	public String getPostCode(String postCodeOut, String postCodeIn) {
		logger.debug("CreateTask#log: postCodeValue creation Started ...");
		String postCode = null;
		try {
			if (postCodeOut.length() == 0) {
				postCode = "    " + postCodeIn;
			} else if (postCodeOut.length() == 1) {
				postCode = postCodeOut + "   " + postCodeIn;
			} else if (postCodeOut.length() == 2) {
				postCode = postCodeOut + "  " + postCodeIn;
			} else if (postCodeOut.length() == 3) {
				postCode = postCodeOut + " " + postCodeIn;
			} else {
				postCode = postCodeOut + postCodeIn;
			}

			logger.debug("CreateTask#log: postCodeValue creation done(postCodeValue:" + postCode + ")...");

		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("ILTechnicalExceptionLogger " + " :: Exception in getPostCode() method " + e);
		}
		return postCode;
	}

	/**
	 * This method is used to format the <PostCode> element value.
	 * 
	 * @param String
	 *            postCodeValue
	 * @return String value of formatting PostCode value
	 */
	public static String getFormattedPostCode(String postCodeValue) {

		logger.debug("CreateTask#log: postCodeValue formation started (postCodeValue:" + postCodeValue + ")...");
		String postCode = null;
		try {
			if (postCodeValue.length() == 0) {
				postCode = "       ";
			} else if (postCodeValue.length() == 3) {
				postCode = "    " + postCodeValue;
			} else if (postCodeValue.length() == 4) {
				postCode = "   " + postCodeValue;
			} else if (postCodeValue.length() == 5) {
				postCode = postCodeValue.charAt(0) + "   " + postCodeValue.substring(2);
			} else if (postCodeValue.length() == 6) {
				postCode = postCodeValue.substring(0, 2) + "  " + postCodeValue.substring(3);
			} else {
				postCode = postCodeValue;
			}

			logger.debug("CreateTask#log: postCodeValue formation done(postCodeValue:" + postCode + ")...");
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("ILTechnicalExceptionLogger " + " :: Exception in getPostCode() method " + e);
		}
		return postCode;
	}
}
