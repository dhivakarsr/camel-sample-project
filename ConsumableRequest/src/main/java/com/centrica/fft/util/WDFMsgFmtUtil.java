package com.centrica.fft.util;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.codehaus.jettison.json.JSONException;

import com.apsolve.mow.framework.process.problemmanagement.AccessException;
import com.apsolve.mow.framework.process.problemmanagement.ServiceException;
import com.apsolve.mow.framework.process.problemmanagement.SystemException;
import com.centrica.fft.constants.FFTConstants;

public class WDFMsgFmtUtil {

	/**
	 * @Method_Name : createEdifactFormat
	 * @Input_Parameters: body, exchange
	 * @Return_Type : String
	 * @Functionality : Formating Edifact Message
	 */
	public String createEdifactFormat(String body, Exchange exchange) {
		StringBuffer edifact = new StringBuffer();
		exchange.setProperty("wdfMsg", body);
		String zfdl = body.substring(body.indexOf(FFTConstants.ZFDL),
				body.indexOf(FFTConstants.UNT, body.indexOf(FFTConstants.ZFDL)));
		String zfdm = body.substring(body.indexOf(FFTConstants.ZFDM),
				body.indexOf(FFTConstants.UNT, body.indexOf(FFTConstants.ZFDM)));
		String zfdc = body.substring(body.indexOf(FFTConstants.ZFDC),
				body.indexOf(FFTConstants.UNT_WITH_APOSTROPHE, body.indexOf(FFTConstants.ZFDC)));
		edifact.append(zfdl.replaceFirst(FFTConstants.ZFDL_WITH_APOSTROPHE, FFTConstants.ZFDL1));
		edifact.append(zfdm.replaceFirst(FFTConstants.ZFDM_WITH_APOSTROPHE, FFTConstants.ZFDM1));
		edifact.append(zfdc.replaceFirst(FFTConstants.ZFDC_WITH_APOSTROPHE, FFTConstants.ZFDC1));
		return edifact.toString();
	}

	/**
	 * @Method_Name : combineEdifactMessage
	 * @Input_Parameters: currentmsg, oldmsg, exchange
	 * @Return_Type : String
	 * @Functionality : Converting Edifact to xml and combining it into WDF xml
	 */
	public String combineEdifactMessage(String currentmsg, @ExchangeProperty("wdfMsg") String oldmsg,
			Exchange exchange) {
		checkTaskType(oldmsg, exchange);
		StringBuffer convertedMsg = new StringBuffer(oldmsg);
		convertedMsg.replace(oldmsg.indexOf(FFTConstants.EDIFACT_START_TAG),
				oldmsg.indexOf(FFTConstants.FAPP_FSI_END_TAG), currentmsg);
		return convertedMsg.toString();

	}

	/**
	 * @Method_Name : checkTaskType
	 * @Input_Parameters: currentmsg, exchange
	 * @Return_Type : void
	 * @Functionality : Checking Task Type to pass edifact
	 */
	public void checkTaskType(String currentmsg, Exchange exchange) {
		if (currentmsg.contains(FFTConstants.CREATE_TASK_START_TAG)) {
			exchange.setProperty(FFTConstants.TASKTYPE_EDIFACT, "create");
		} else if (currentmsg.contains(FFTConstants.AMEND_TASK_START_TAG)) {
			exchange.setProperty(FFTConstants.TASKTYPE_EDIFACT, FFTConstants.UPDATE);
		} else if (currentmsg.contains(FFTConstants.ADD_FSI_START_TAG)) {
			exchange.setProperty(FFTConstants.TASKTYPE_EDIFACT, FFTConstants.SUPPORTDATA);
		} else if (currentmsg.contains("<CANCEL_TASK>")) {
			exchange.setProperty(FFTConstants.TASKTYPE_EDIFACT, "cancelTask");
		}
	}

	/**
	 * @Method_Name : getXmlWithEdi
	 * @Input_Parameters: body, exchange
	 * @Return_Type : void
	 * @Functionality : Getting xml with edifact message
	 */
	public void getXmlWithEdi(String body, Exchange exchange) {
		if (body.contains(FFTConstants.EDIFACT_START_TAG) && body.contains(FFTConstants.CDATA)) {
			exchange.setProperty(FFTConstants.IS_EDIFACT, FFTConstants.IS_EDIFACT_VALUE_TRUE);
		} else {
			exchange.setProperty(FFTConstants.IS_EDIFACT, FFTConstants.IS_EDIFACT_VALUE_FALSE);
		}
	}

}
