package com.centrica.fft.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class PartsExpProcessor implements Processor {
	private static final transient Logger logger = Logger.getLogger(PartsExpProcessor.class);

	/**
	 * @Method_Name : process
	 * @Input_Parameters:exchange
	 * @Return_Type :void
	 * @Functionality : Updating Premises object
	 * @throws : JSONException
	 */
	@Override
	public void process(Exchange exchange) throws JSONException  {
		logger.info("ExceptionProcessor class Logger, process() method started..");
		String respCode = exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, String.class);
		String respBody = exchange.getIn().getBody(String.class);
		String exceptionMsg = exchange.getProperty("exceptionMsg") != null
				? exchange.getProperty("exceptionMsg").toString() : "";

		JSONObject response = new JSONObject();
		if (respCode != null && respCode.equalsIgnoreCase("200"))

		{
			logger.info("Success response received from downnstream system : " + respCode);
		} else

		{

			if (respCode != null) {
				response.put("status", respCode);
			} else {
				response.put("status", "500");
			}
			JSONArray arr = new JSONArray();
			arr.put("Could not connect to downstream system");
			arr.put("Please retry after sometime");
			if (respCode != null) {
				arr.put(respCode);
			} else {
				arr.put("500 Internal Server Error in Jboss Fuse");
			}
			/*if (exceptionMsg != null) {
				arr.put(exceptionMsg);
			} else if (respBody != null) {
				// JSONObject errResp = new JSONObject(respBody);
				arr.put(respBody);
			} else {
				arr.put("no content");
			}*/

			response.put("error", arr);
		}

		if (respCode != null)

		{
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, respCode);
		} else

		{
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
		}

		exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
		exchange.getOut().setBody(response.toString());
		logger.info("ExceptionProcessor class Logger, process() method ended..");
	}

}