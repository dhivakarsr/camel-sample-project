package com.centrica.fft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.centrica.fft.constants.FFTConstants;

public class FFTUtil {

	private static transient Logger logger = Logger.getLogger(FFTUtil.class);


	/**
	 * @Method_Name : stackTraceToString
	 * @Input_Parameters : exception
	 * @Return_Type : String
	 * @Functionality : 
	 */
    public static String stackTraceToString(Exception e) {
        StringWriter sw = new StringWriter(100);
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }


	/**
	 * @Method_Name : getDateTimeInStringFmt
	 * @Input_Parameters : 
	 * @Return_Type : String
	 * @Functionality : 
	 */
	public static String getDateTimeInStringFmt() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(FFTConstants.SIMPLE_DATE_FORMAT);
		return formatter.format(date);
	}

	/**
	 * @Method_Name : createErrResponse
	 * @Input_Parameters : respCode, errorMsg
	 * @Return_Type : JSONObject
	 * @Functionality : 
	 * @throws
	 */
	public static JSONObject createErrResponse(String respCode, String errorMsg) throws JSONException  {
		JSONObject response = new JSONObject();
		response.put(FFTConstants.STATUS, FFTConstants.BADGATEWAY_RESPONSE);
		JSONArray arr = new JSONArray();
		arr.put(FFTConstants.COULD_NOT_CONNECT_TO_DOWNSTREAM_SYSTEM);
		arr.put(FFTConstants.PLEASE_RETRY_AFTER_SOMETIME);
		arr.put(respCode);
		arr.put(errorMsg);
		response.put(FFTConstants.ERROR, arr);

		return response;
	}

	public static JSONObject createErrResponseOthers(String respCode, String errorMsg) throws JSONException  {
		JSONObject response = new JSONObject();
		response.put(FFTConstants.STATUS, FFTConstants.BADGATEWAY_RESPONSE);
		JSONArray arr = new JSONArray();
		arr.put(respCode);
		arr.put(errorMsg);
		response.put(FFTConstants.ERROR, arr);

		return response;
	}

}
