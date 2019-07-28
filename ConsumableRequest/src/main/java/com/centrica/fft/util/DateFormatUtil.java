package com.centrica.fft.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.centrica.fft.constants.FFTConstants;

public class DateFormatUtil {
	
	private static final transient Logger logger = Logger.getLogger(DateFormatUtil.class);

	/**
	 * @Method_Name : convertDateFormat
	 * @Input_Parameters : dateInString
	 * @Return_Type : String
	 * @Functionality : Converting Date Format 
	 */
	public  static String convertDateFormat(String dateInString){
		logger.info("DateFormatUtil class Logger, convertDateFormat() method started..");
		String datestring = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = formatter.parse(dateInString);
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			  datestring = dateFormat.format(date);
		} catch (ParseException e) {
			//e.printStackTrace();
			logger.error("DateFormatUtilLoggerError,  convertDateFormat() mehtod :", e);
		}
		return datestring;
	
	}
	
	/**
	 * @Method_Name : convertTimeFormat
	 * @Input_Parameters : timeInString
	 * @Return_Type : String
	 * @Functionality : Converting Time Format 
	 */
	public static String convertTimeFormat(String timeInString){
		logger.info("DateFormatUtil class Logger, convertTimeFormat() method started..");
		 String timestring = "";
		SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
		try {
			Date date = formatter.parse(timeInString);
			 DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			 timestring = dateFormat.format(date);
		} catch (ParseException e) {
			//e.printStackTrace();
			logger.error("DateFormatUtilLoggerError,  convertTimeFormat() mehtod :", e);
		}
		return timestring;
	}

	/**
	 * @Method_Name : convertDateFormatTF
	 * @Input_Parameters : sourceFormat
	 * @Return_Type : String
	 * @Functionality : Converting Date Format 
	 */
	public static String convertDateFormatTF (String sourceFormat) {
		logger.info("DateFormatUtil class Logger, convertDateFormatTF() method started..");
		DateFormat dfSource = new SimpleDateFormat(FFTConstants.TF_DATE_FORMAT);
		DateFormat dfTarget = new SimpleDateFormat(FFTConstants.CB_DATE_FORMAT);
		Date date = new Date();
		try {
			date = dfSource.parse(sourceFormat);
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("TFConfigurationUtil " + " :: Exception in addPropertySetValue method " + e);
		}
		return dfTarget.format(date);
	}
	
	/**
	 * @Method_Name : convertTimeFormatTF
	 * @Input_Parameters : sourceFormat
	 * @Return_Type : String
	 * @Functionality : Converting Time Format 
	 */
	public static String convertTimeFormatTF (String sourceFormat) {
		logger.info("DateFormatUtil class Logger, convertTimeFormatTF() method started..");
		DateFormat dfSource = new SimpleDateFormat(FFTConstants.TF_DATE_FORMAT);
		DateFormat dfTarget = new SimpleDateFormat(FFTConstants.CB_TIME_FORMAT);
		Date date = new Date();
		try {
			date = dfSource.parse(sourceFormat);
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("TFConfigurationUtil " + " :: Exception in addPropertySetValue method " + e);
		}
		return dfTarget.format(date);
	}

}
