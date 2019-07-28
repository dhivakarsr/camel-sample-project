package com.centrica.fft.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.Message;
import org.apache.camel.language.XPath;
import org.apache.log4j.Logger;

import com.centrica.fft.constants.FFTConstants;
import com.centrica.fft.datamodel.engg.EngineerNew;
import com.centrica.fft.datamodel.engg.Meta;
import com.centrica.fft.datamodel.task.CreateTask;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnrichConsumableJson {
	private static final transient Logger logger = Logger.getLogger(EnrichConsumableJson.class);
	ObjectMapper objecMapper = new ObjectMapper();
	GetPropertiesUtil middlewareUtil = new GetPropertiesUtil();
	String createTask = null;
	String updateTask = null;
	String division = "0";
	String region = "0";

	/**
	 * @Method_Name : addDefaultJsonValues
	 * @Input_Parameters:inputXml in String
	 * @Return_Type :String
	 * @Functionality : Adding meta values in json
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */

	public String addDefaultJsonValues(String inputJson) throws JsonParseException, JsonMappingException, IOException {
		EngineerNew engineerNew = objecMapper.readValue(inputJson, EngineerNew.class);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(FFTConstants.SIMPLE_DATE_FORMAT);

		if (engineerNew.getMeta() != null) {
			engineerNew.getMeta().setUpdatedTimestamp(formatter.format(date));
		} else {
			Meta meta = new Meta();
			meta.setUpdatedTimestamp(FFTUtil.getDateTimeInStringFmt());
			engineerNew.setMeta(meta);
		}

		return objecMapper.writeValueAsString(engineerNew);
	}

	/**
	 * @Method_Name : getDivision
	 * @Input_Parameters: domain in String
	 * @Return_Type :String
	 * @Functionality : This method used to get the division and region using
	 *                domain from the hash map.
	 * 
	 */
	public String getDivision(@XPath("//DOMAIN") String domain) {
		try {
			String domainValue = middlewareUtil.getDivProperty(domain);
			int index = 0;
			if (domainValue != null && (!domainValue.equals(""))) {
				String[] domainArray = domainValue.split(",");
				if (domainArray.length > 0) {
					division = domainArray[index];
					region = domainArray[index + 1];
				}
			}
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("MiddlewareUtilExceptionLogger :: Exception " + "in getDivision method" + e);
		}
		return division;
	}

	/**
	 * @Method_Name : removeAmp
	 * @Input_Parameters: messageBody in String
	 * @Return_Type :String
	 * @Functionality : This method is used to remove &amp;amp; string from the
	 *                message.
	 * 
	 */
	public String removeAmp(@Body String messageBody) {

		String modifiedMessage = null;
		try {
			modifiedMessage = messageBody.replaceAll("&amp;amp;quot;", "&quot;");
			modifiedMessage = modifiedMessage.replaceAll("&amp;lt;", "&lt;");
			modifiedMessage = modifiedMessage.replaceAll("&amp;gt;", "&gt;");
			// modifiedMessage = modifiedMessage.replaceAll("'", "&amp;apos;");
			modifiedMessage = modifiedMessage.replaceAll("&amp;amp;", "&amp;");
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("MiddlewareUtilExceptionLogger " + " :: Exception in removeAmp() method " + e);
		}
		return modifiedMessage;
	}

	/**
	 * @Method_Name : setUrlId
	 * @Input_Parameters: input in String,operationNames in ExchangeProperty
	 *                    exchange in Exchange
	 * @Return_Type :void
	 * @Functionality : This method is used set Job Id and Enginner Id
	 * 
	 */
	public void setUrlId(String inputJson, @ExchangeProperty("couchbaseName") String operationName, Exchange exchange)
			throws JsonParseException, JsonMappingException, IOException {
		if (operationName.equalsIgnoreCase(FFTConstants.CREATETASK_XML)) {
			CreateTask createTaskSchema = objecMapper.readValue(inputJson, CreateTask.class);
			exchange.setProperty(FFTConstants.IN_JOB_ID, createTaskSchema.getId());
		} else if (operationName.equalsIgnoreCase(FFTConstants.PARTCOLLECTIONREQ)) {
			EngineerNew engineerNew = objecMapper.readValue(inputJson, EngineerNew.class);
			exchange.setProperty(FFTConstants.IN_ENGINEERID, engineerNew.getId());
		} else if (operationName.equalsIgnoreCase(FFTConstants.JOBPUT)) {
			CreateTask createTaskSchema = objecMapper.readValue(inputJson, CreateTask.class);
			exchange.setProperty(FFTConstants.IN_JOB_ID, createTaskSchema.getId());
		}
		exchange.setProperty(FFTConstants.INPUTJSON, inputJson);
		exchange.getIn().setHeader(FFTConstants.CAMEL_HTTP_PATH, "");
		exchange.getIn().setHeader(FFTConstants.CAMEL_HTTP_URI, "");
	}

	/**
	 * @Method_Name : setETag
	 * @Input_Parameters: exchange in Exchange
	 * @Return_Type :String
	 * @Functionality : This method is used to remove &amp;amp; string from the
	 *                message.
	 * 
	 */
	public String setETag(Exchange exchange) throws JsonParseException, JsonMappingException, IOException {
		String result = null;
		Message inMessage = exchange.getIn();
		String versionEtag = inMessage.getHeader(FFTConstants.ETAG, String.class);
		if (versionEtag != null) {
			versionEtag = versionEtag.replaceAll(FFTConstants.BACKSLASH_CHAR, FFTConstants.EMPTYSTRING);
			String operationName = exchange.getProperty("couchbaseName", String.class);
			if (operationName.equalsIgnoreCase(FFTConstants.JOBPUT)) {
				String inputXML = exchange.getProperty(FFTConstants.INPUTJSON, String.class);
				CreateTask createTaskSchema = objecMapper.readValue(inputXML, CreateTask.class);
				createTaskSchema.setVersion(versionEtag);
				result = objecMapper.writeValueAsString(createTaskSchema);
			} else if (operationName.equalsIgnoreCase("partcollectionreq")) {
				String inputXML = exchange.getProperty(FFTConstants.INPUTJSON, String.class);
				EngineerNew engineerNew = objecMapper.readValue(inputXML, EngineerNew.class);
				engineerNew.setVersion(versionEtag);
				result = objecMapper.writeValueAsString(engineerNew);
			}
		}
		return result;
	}

	/**
	 * @Method_Name : setIds
	 * @Input_Parameters: exchange in Exchange
	 * @Return_Type :void
	 * @Functionality : 
	 * 
	 */
	public void setIds(Exchange exchange) {
		Message inMessage = exchange.getIn();
		String operationName = exchange.getProperty(FFTConstants.OPERATIONNAME, String.class);
		if (operationName.equalsIgnoreCase("partcollections")) {
			String engIdPathParam = inMessage.getHeader(FFTConstants.ENGINEER_ID, String.class);
			exchange.setProperty(FFTConstants.IN_ENGINEERID, engIdPathParam);
		}
	}
	public String getRegion() {
		return region;
	}
	public String getCreateServiceDefinition() {
		return FFTConstants.CREATE_TASK;
	}
	public String getUpdateServiceDefinition() {
		return FFTConstants.UPDATE_TASK;
	}
}
