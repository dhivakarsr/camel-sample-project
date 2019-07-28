package com.centrica.fft.processors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.centrica.fft.constants.FFTConstants;
import com.centrica.fft.util.FFTUtil;
import com.centrica.fft.util.GetPropertiesUtil;
import com.centrica.fft.util.WMISConnectionUtil;

public class EnqWMISMessageProcessor {

	// Exchange exchangeResp;
	private static final transient Logger logger = Logger.getLogger(EnqWMISMessageProcessor.class);
	final GetPropertiesUtil middlewareUtil = new GetPropertiesUtil();
	public void enQueueTaskUpdateMessage1(@Body String stringMSG)
			throws SQLException {
		wimsEnqueue(stringMSG, middlewareUtil.getMiddlewareProperty(FFTConstants.INBOUND_QUEUE_1));
	}

	public void enQueueTaskUpdateMessage2(@Body String stringMSG)
			throws SQLException {
		wimsEnqueue(stringMSG, middlewareUtil.getMiddlewareProperty(FFTConstants.INBOUND_QUEUE_2));
	}

	public void enQueueTaskUpdateMessage3(@Body String stringMSG)
			throws SQLException {
		wimsEnqueue(stringMSG, middlewareUtil.getMiddlewareProperty(FFTConstants.INBOUND_QUEUE_3));
	}

	public void enQueueTaskUpdateMessage4(@Body String stringMSG)
			throws SQLException {
		wimsEnqueue(stringMSG,middlewareUtil.getMiddlewareProperty(FFTConstants.INBOUND_QUEUE_4));
	}

	public void enQueueTaskUpdateMessage5(@Body String stringMSG)
			throws SQLException {
		wimsEnqueue(stringMSG, middlewareUtil.getMiddlewareProperty(FFTConstants.INBOUND_QUEUE_5));
	}

	

	/**
	 * @Method_Name : process
	 * @Input_Parameters : exchange
	 * @Return_Type :void
	 * @Functionality : 
	 * @throws : Exception
	 */
	public void wimsEnqueue(final String message, final String queueName)
			throws SQLException {
		logger.info("WMISEnqueueProcessor class Logger, process() method started..");

		String xmlMessage = null;
	
		
		
		try {
			final GetPropertiesUtil middlewareUtil = new GetPropertiesUtil();
			
			JdbcTemplate enqueueTemplate = new JdbcTemplate(WMISConnectionUtil.getDataSource());

			logger.debug("EnQueueDebugLogger :: QueueName:" + queueName + " Message to be Enqueued:" + xmlMessage);
			final String rootElement = getTopicName(xmlMessage);;
			logger.debug("topic===" + rootElement);
			final String updatedMessage = xmlMessage;
			enqueueTemplate.execute(new CallableStatementCreator() {
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement cs;

					cs = con.prepareCall(middlewareUtil.getMiddlewareProperty(FFTConstants.ENQUEUE_MSG_TO_WIMS));

					// Setting the input and out parameters
					cs.registerOutParameter(FFTConstants.INDEX_ONE, java.sql.Types.INTEGER);
					cs.setString(FFTConstants.INDEX_TWO, rootElement);
					cs.setString(FFTConstants.INDEX_THREE, queueName);
					cs.setString(FFTConstants.INDEX_FOUR, updatedMessage);
					cs.setNull(FFTConstants.INDEX_FIVE, java.sql.Types.INTEGER);
					cs.setNull(FFTConstants.INDEX_SIX, java.sql.Types.INTEGER);
					cs.setNull(FFTConstants.INDEX_SEVEN, java.sql.Types.INTEGER);
					cs.setNull(FFTConstants.INDEX_EIGHT, java.sql.Types.INTEGER);
					cs.registerOutParameter(FFTConstants.INDEX_NINE, java.sql.Types.CLOB);

					return cs;
				}
			}, new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs) {
					int returnValue = 0;
					boolean status = true;
					try {
						cs.execute();
						returnValue = cs.getInt(1);
						Clob completionMessageClob = cs.getClob(FFTConstants.INDEX_NINE);
						logger.debug("EnQueueDebugLogger :: Error Code -> " + returnValue);
						if ((completionMessageClob != null)) {
							String completionXml = completionMessageClob.getSubString((long) 1,
									(int) completionMessageClob.length());

							checkEnqueueStatus(returnValue);
							logger.debug("EnQueueDebugLogger :: Completion Xml is : " + completionXml);
						} else {
							logger.debug("EnQueueDebugLogger :: WMIS Eequeue completion message is null");
						}

					} catch (SQLException sqlException) {
						logger.error("ILTechnicalExceptionLogger :: "
								+ "SQLException in CallableStatementCallback method" + sqlException);

					}
					return status;
				}
			});

			logger.debug("EnQueueDebugLogger :: WIMS Enqueue completed for " + queueName);
		} catch (Exception e) {
			FFTUtil.stackTraceToString(e);
			logger.error("ILTechnicalExceptionLogger" + " :: Exception in wimsEnqueue() method" + e);
		}
		logger.info("WMISEnqueueProcessor class Logger, process() method ended..");
	}

	/**
	 * @Method_Name : checkEnqueueStatus
	 * @Input_Parameters : iResult
	 * @Return_Type : boolean
	 * @Functionality : 
	 */
	boolean checkEnqueueStatus(int iResult) {
		logger.info("WMISEnqueueProcessor class Logger, checkEnqueueStatus() method started..");
		int RC_WMIS_SUCCESS = 1;
		int ERR_WMIS_ENQUEUE_ERROR = -20020;
		int ERR_WMIS_TOPIC_AQ_MISMATCH = -20021;
		int ERR_WMIS_TOPIC_AQ_ERROR = -20022;
		int ERR_WMIS_TOPIC_EQ_ERROR = -20023;
		int ERR_WMIS_QUEUE_NAME_NULL = -20024;
		int ERR_WMIS_TOPIC_NULL = -20025;
		int ERR_WMIS_MSG_NULL = -20026;
		if ((iResult == RC_WMIS_SUCCESS)) {
			logger.debug("EnQueueDebugLogger :: Enqueue Status is true, commit.");
			return true;
		} else {

			if (((iResult == ERR_WMIS_QUEUE_NAME_NULL) || (iResult == ERR_WMIS_TOPIC_NULL)
					|| (iResult == ERR_WMIS_MSG_NULL))) {
				logger.debug("EnQueueDebugLogger :: Enqueue Procedure returned invalid message error-" + iResult);

				logger.debug("EnQueueDebugLogger :: Treat as Business Error and " + "consume invalid message");
				return true;
			} else {
				if (((iResult == ERR_WMIS_ENQUEUE_ERROR) || (iResult == ERR_WMIS_TOPIC_AQ_MISMATCH)
						|| (iResult == ERR_WMIS_TOPIC_AQ_ERROR) || (iResult == ERR_WMIS_TOPIC_EQ_ERROR))

				) {
					logger.debug("EnQueueDebugLogger :: Enqueue Procedure returned enqueue "
							+ "or configuration error - " + iResult);
					return false;
				} else {
					logger.debug("EnQueueDebugLogger :: Unexpected procedure return code:" + iResult);
					return false;
				}
			}
		}
	}
	
	private String getTopicName(String xmlMessage) {
		String topicName = "";
		try {
			InputStream is = new ByteArrayInputStream(xmlMessage.getBytes());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);
			NodeList nodeList = doc.getElementsByTagName("TOPIC");
			Element firstNameElement = (Element) nodeList.item(0);
			NodeList textNList = firstNameElement.getChildNodes();
			Node node = (Node) textNList.item(0);
			topicName = node.getNodeValue();
		} catch (ParserConfigurationException e) {
			logger.error(
					"FFTTechnicalExceptionLogger"
							+ " :: ParserConfigurationException in ILOracleDao.getTopicName() method",
					e);
		} catch (SAXException e) {
			logger.error("ILTechnicalExceptionLogger"
					+ " :: SAXException in EnqWMISMessageProcessor.getTopicName() method",
					e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("ILTechnicalExceptionLogger"
					+ " :: IOException in EnqWMISMessageProcessor.getTopicName() method", e);
		}catch (Exception e) {
			logger.error("ILTechnicalExceptionLogger"
					+ " :: Exception in getTopicName method", e);
		}
		return topicName;
	}
}
