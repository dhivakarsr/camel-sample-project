package com.centrica.fft.constants;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class FFTConstants {

	public static final int INDEX_ZERO = 0;
	public static final int INDEX_ONE = 1;
	public static final int INDEX_TWO = 2;
	public static final int INDEX_THREE = 3;
	public static final int INDEX_FOUR = 4;
	public static final int INDEX_FIVE = 5;
	public static final int INDEX_SIX = 6;
	public static final int INDEX_SEVEN = 7;
	public static final int INDEX_EIGHT = 8;
	public static final int INDEX_NINE = 9;
	public static final int INDEX_TEN = 10;
	public static final String TIMED_OUT = "0";
	public static final String INSTANCE_ID = "WM";
	public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmm";
	public static final String CREATE_TASK = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE service SYSTEM \"servicedefinition.dtd\"><service><name>WorkManagementReception</name><na mode=\"async\"><name>createWork</name></operation></service>";
	public static final String UPDATE_TASK = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE service SYSTEM \"servicedefinition.dtd\"><service><name>WorkManagementReception</name><operation mode=\"async\"><name>updateTask</name></operation></service>";
	public static final String dateformat = "yyyy-MM-dd HH:mm:ss zz";

	// TF Constants
	public static final String TFFIELD_ACK_SIGNON = "SIGNON_ACK";
	public static final String TFFIELD_SIGNON_TYPE_SIGNON = "SIGNON";
	public static final String TFFIELD_SIGNON_TYPE_SIGNOFF = "SIGNOFF";
	public static final String TFFIELD_REPORT_TYPE_SIGNON = "SIGNON";
	public static final String TFFIELD_REPORT_TYPE_SIGNOFF = "SIGNOFF";

	public static final String TFFIELD_REPORT_TYPE_JOBS_INSTRUCTION = "INSTRUCTION";

	public static final String TASKS = "TASKS";
	public static final String MEMOS = "MEMOS";
	public static final String MEMO_TEXT = "MEMO_TEXT";
	public static final String EXTERNAL_TASK_REF = "EXTERNAL_TASK_REF";
	public static final String POST_CODE = "POST_CODE";
	public static final String GRID_REF_E = "GRID_REF_E";
	public static final String GRID_REF_N = "GRID_REF_N";
	public static final String ECBT_DATE = "ECBT_DATE";
	public static final String FSI_DATA_ROOT = "FSI_data";
	public static final String SDBTEXT1 = "SDBTEXT1";
	public static final String SDB_TASK_DETAILS_XML = "SDB_TASK_DETAILS_XML";
	public static final String FSI_DATA = "FsiData";
	public static final String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	public static final String xmlFSIHeader = "&lt;?xml version=&amp;quot;1.0&amp;quot; encoding=&amp;quot;UTF-8&amp;quot;?&gt;";
	public static final String JIN = "JIN";
	public static final String TFFIELD_ABSENCE_IND_Y = "Y";

	// Error

	public static final String UNEXPECTED_ERROR = "UNEXPECTED ERROR";
	public static final String REQUIRED_FIELD_NOT_FOUND = "REQUIRED FIELD NOT FOUND";
	public static final String TFS_PUTDATA_ERROR = "TFS PUTDATA ERROR";
	public static final String TFS_WFB_NULL = "TFS WFB NULL";
	public static final String TFS_INVALID_REPORT_TYPE = "TFS INVALID REPORT TYPE";
	public static final String TFS_INVALID_SIGNON_REPORT = "TFS INVALID SIGNON REPORT";
	public static final String TFS_INVALID_TASKACK_REPORT = "TFS INVALID TASKACK REPORT";
	public static final String TFS_INVALID_REQUEST_TYPE = "TFS INVALID REQUEST TYPE";
	public static final String TFS_EMPTY_PROPERTYLIST = "TFS EMPTY PROPERTYLIST";
	public static final String TFS_EMPTY_PROPERTYSET = "TFS EMPTY PROPERTYSET";
	public static final String TFS_PROPERTYSET_KEY_NOT_FOUND = "TFS PROPERTYSET KEY NOT FOUND";

	// FFT Constants
	public static final int MINUTE_IN_MS = 1000;
	public static final int TWENTY_EIGHT = 28;
	public static final int THIRTY_TWO = 30;
	public static final int FOURTY = 40;
	public static final int MINUTE = 60 * 1000;

	// Processor constants for RequestForWorkProcessor
	public static final String ETAG = "ETag";
	public static final String BACKSLASH_CHAR = "\"";
	public static final String EMPTYSTRING = "";
	public static final String COMMA_CHAR = ",";
	public static final String CAMEL_HTTP_PATH = "CamelHttpPath";
	public static final String CAMEL_HTTP_URI = "CamelHttpUri";
	public static final String REQUEST_MAP = "requestMap";
	public static final String JOB_LIST = "jobList";

	// Processor constants for SignOnPutProcessor
	public static final String CAMEL_HTTP_RESPONSE_CODE = "CamelHttpResponseCode";
	public static final String HTTP_ERROR_RESPONSE_CODE = "404";
	public static final String FALSEDATA = "false";
	public static final String TRUEDATA = "true";
	public static final String ENG_FOUND_IN_COUCHBASE = "eng_found_in_couchbase";
	public static final String HTTP_SUCCESS_RESPONSE_CODE = "200";
	public static final String ORG_REQ_MSG = "orgReqMsg";
	public static final String SIGNONDATETIME = "signOnDateTime";
	public static final String ENGINEER_ID = "engineerId";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String DATA = "data";

	// Processor constants for SignOnResponseProcessor
	public static final String TASKFORCE_PROCESS = "taskforce_process";
	public static final String SUCCESS = "success";
	public static final String STATUS = "status";
	public static final String COULD_NOT_CONNECT_TO_DOWNSTREAM_SYSTEM = "Could not connect to downstream system";
	public static final String PLEASE_RETRY_AFTER_SOMETIME = "Please retry after sometime";
	public static final String NO_CONTENT = "no content";
	public static final String ERROR = "error";

	// Processor constants for TaskforceMsgProcessor
	public static final String IN_ENGINEERID = "inEngineerID";
	public static final String HTTP_PATH_SIGNON = "signon";
	public static final String HTTP_PATH_SIGOFF = "signoff";
	public static final String HTTP_PATH_JOBS = "jobs";
	public static final String JOBS_REPORT_TYPE = "JOBS";
	public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
	public static final String TF_DATE_FORMAT = "dd/MM/yy HH:mm";
	public static final String CB_DATE_FORMAT = "yyyy-MM-dd";
	public static final String CB_TIME_FORMAT = "HH:mm";
	public static final String SIGNOFFDATETIME = "signOffDateTime";
	public static final String HTTP_BAD_GATEWAY_STATUS_CODE = "502";
	public static final String TASKFORCE_PROCESS_FAILED = "failed";
	public static final String UNABLE_TO_FIND_REPORT = "Unable to find report type signon or signoff";
	public static final String HTTP_METHOD_GET = "GET";
	public static final String UNABLE_TO_CONNECT_TO_TASKFORCE = "Unable to connect to Taskforce, no error message content";
	public static final String CONTENT_TYPE = "application/json";
	public static final String BADGATEWAY_RESPONSE = "502 Bad Gateway";

	// Processor constants for WMISDequeueProcessor
	public static final String OPERATIONNAME = "operationName";
	public static final String SUPPORTINGDATAFORDISPATCH = "supportingdatafordispatch";
	public static final String WDF_SUPPORTDATA_WITH_EDI_XML = "samplexml/wdf-supportdata-with-edi.xml";
	public static final String PARTCOLLECTIONREQ = "partcollectionreq";
	public static final String WDF_PCREQUIREMENT_XML = "samplexml/WDF-PCRequirement.xml";
	public static final String CREATETASK_XML = "createTask";
	public static final String WDF_CREATE_TASK_XML = "samplexml/WDF_Create_Task.xml";
	public static final String UPDATETASK_XML = "updateTask";
	public static final String WDF_UPDATETASK_WITH_EDI_XML = "samplexml/wdf-updatetask-with-edi.xml";

	public static final String NEWLINE_CHAR = "\n";
	public static final String TAB_CHAR = "\t";
	public static final String XML_DOCTYPE = "DOCTYPE";
	public static final String XML_DOCTYPE_START_TAG = "<!";
	public static final String XML_DOCTYPE_END_TAG = "\">";

	// Processor constants for WMISEnqueueProcessor
	public static final String TOPIC = "topic";
	public static final String QUEUENAME_RESPONSE = "RESPONSES";
	public static final String ENQUEUE_MSG_TO_WIMS = "ENQUEUE_MSG_TO_WIMS";

	// util constants for FFTUtil
	public static final String DIVISION_PROPERTIES_FILE = "properties/Division.properties";
	public static final String ABSENCECODES_PROPERTIES_FILE = "properties/AbsenceCodes.properties";
	public static final String MEMOTEXT_PROPERTIES_FILE = "properties/MemoText.properties";
	public static final String EDIFACT_START_TAG = "<EDIFACT>";
	public static final String EDIFACT_END_TAG = "</EDIFACT>";
	public static final String TEMPORARY_VISIT_INFORMATION_START_TAG = "<TEMPORARY_VISIT_INFORMATION>";
	public static final String TEMPORARY_VISIT_INFORMATION_END_TAG = "</TEMPORARY_VISIT_INFORMATION>";
	public static final String XML_CDATA = "]]><![CDATA[";
	public static final String AMPERSAND_CHAR = "&amp;";
	public static final String AMPERSAND_SYMBOL = "&";
	public static final String LESSTHAN_SIGN = "&lt;";
	public static final String LEFT_TAG = "<";
	public static final String GREATERTHAN_SIGN = "&gt;";
	public static final String RIGHT_TAG = ">";
	public static final String APOSTROPHE_SIGN = "&apos;";
	public static final String APOSTROPHE_SYMBOL = "'";
	public static final String QUOT_SIGN = "&quot;";

	// util constants for InstructionSetUtil
	public static final String sMemoText = "Error occurred performing get() from Taskforce Field Server";
	public static final String CLIENT_ID_KEY = "CLIENT_ID";
	public static final String CLIENT_ID_VALUE = "WMS_35_1";

	// util constants for TaskProcessUtil
	public static final String modifiedMessage_QUOT = "&amp;amp;quot;";
	public static final String modifiedMessage_LESSTHAN = "&amp;lt;";
	public static final String modifiedMessage_GREATERTHAN = "&amp;gt;";
	public static final String modifiedMessage_AMPERSAND = "&amp;amp;";
	public static final String IN_JOB_ID = "inJobID";
	public static final String INPUT_XML = "inputXML";
	public static final String CANCELTASK_XML = "cancelTask";
	public static final String CANCEL = "Cancel";
	public static final String JOBPUT = "jobPut";
	public static final String INPUTJSON = "inputJson";
	public static final String INDEX_OF_CANCELTASK = "/cancelTask";
	public static final String PARTCOLLECTIONS = "partcollections";
	public static final String INDEX_OF_PARTCOLLECTIONS = "/partcollections";

	// util constants for TDFConversionUtil
	public static final String TASKTYPE = "taskType";
	public static final String CREATE_TASK_ROOT_ELEMENT = "CREATE_TASK";
	public static final String AMEND_TASK_ROOT_ELEMENT = "AMEND_TASK";
	public static final String CANCEL_TASK_ROOT_ELEMENT = "CANCEL_TASK";
	public static final String ADD_FSI_ROOT_ELEMENT = "ADD_FSI";
	public static final String WMIS_CONNECTIONCLOSE_CALL_SUCCESS = "Success";
	public static final String WMIS_CONNECTIONCLOSE_CALL_Failure = "Failure";
	public static final String WMIS_CONNECTION = "connection";

	public static final String ORBINITIALHOST = "org.omg.CORBA.ORBInitialHost";
	public static final String ORBINITIALPORT = "org.omg.CORBA.ORBInitialPort";
	public static final String ORBINITIALPORT_VALUE = "15600";
	public static final String DTD_PATH = "DTD_PATH";
	public static final String TASKFORCESDK_XML = "/opt/product/middleware/config/taskforce/taskforceSDK/xml";
	public static final String JAVA_LIBRARY_PATH = "java.library.path";
	public static final String JAVA_LIBRARY_PATH_VALUE = "/opt/product/middleware/config/taskforce/taskforceSDK/lib/RedHat_64Bit";
	public static final String SYS_PATHS = "sys_paths";
	public static final String AXIS_LIB = "AXIS_Lib";
	public static final String AXIS_LIB_VALUE = "/opt/product/middleware/config/taskforce/axis/lib";
	public static final String CORE = "CORE";
	public static final String TFEE = "TFEE";
	public static final String AUTHENTICATEUSER600 = "user600";
	public static final String AUTHENTICATEUSER600_VALUE = "sv4401";

	// util constants for TFConfigurationUtil
	public static final String REPORT_TYPE = "TYPE";
	public static final String ENTERED_START_DATE = "ENTERED_START_DATE";
	public static final String SIGNON_DATE = "SIGNON_DATE";
	public static final String INVALID_SIGNON_FORMAT = "Invalid signOn format - missing SIGNON_DATE. ";
	public static final String ENTERED_FINISH_DATE = "ENTERED_FINISH_DATE";
	public static final String INVALID_SIGNOFF_FORMAT = "Invalid signOff format - missing ENTERED_FINISH_DATE. ";
	public static final String INVALID_SIGNON_FORMAT_INVALID_TYPE = "Invalid signOn format: invalid type - ";
	public static final String INSTRUCTIONS = "INSTRUCTIONS";
	public static final String REQUEST_TYPE = "REQUEST_TYPE";
	public static final String EXCEPTION_ADDING_PROPERTYSET = "Exception adding PropertySet to PropertyList - ";
	public static final String FOUND_UNEXPECTED_EMPTY_REPORT_LIST = "Found unexpected empty report list";
	public static final String FOUND_UNEXPECTED_NULL_REPORT_LIST = "Found unexpected null report list";
	public static final String ADD_PROPERTYSET_VALUE_INVALID_ARGUMENTS = "addPropertySetValue(): invalid arguments - cannot be null - ";
	public static final String READ_PSVALUE_FAILED_TO_FIND_KEY = "readPSValue: Failed to find key ";
	public static final String IN_PROPERTY_SET = " in property set";
	public static final String READ_PSVALUE_EMPTY_OR_NULL_PROPERTY_SET = "readPSValue: Empty or null property set";

	// util constants for TFConnector
	public static final String UNEXPECTED_REPORT_TYPE = "Unexpected Report Type: ";
	public static final String REQUEST_DATA = "REQUEST_DATA";
	public static final String FAILED_TO_READ_REQUEST_TYPE = "Failed to read Request Type from PropertySet";
	public static final String UPLOAD_OF_SIGNON_DATA_TO_TASKFORCE = "communicate: Upload of signOn data to Taskforce returned with an error - ";
	public static final String FAILED_TO_CREATE_WORKFLOWBRIDGE_OBJECT = "communicate(): failed to create WorkFlowbridge object";
	public static final String ERROR_MSG = "ERROR_MSG";

	// util constants for TFConnectorUtil
	public static final String CUSTOM_PROPERTIES = "customProperties.txt";
	public static final String MIDDLEWARE_CONFIG = "/opt/product/middleware/config";
	public static final String GENERAL_EXCEPTION_READING_TASKFORCE = "General Exception reading Taskforce CustomPropertyFile reference data - ";

	// util constants for WDFMsgFmtUtil
	public static final String WDF_MESSAGE = "wdfMsg";
	public static final String ZFDL = "ZFDL";
	public static final String UNT = "UNT";
	public static final String ZFDM = "ZFDM";
	public static final String ZFDC = "ZFDC";
	public static final String UNT_WITH_APOSTROPHE = "'UNT";
	public static final String ZFDL_WITH_APOSTROPHE = "ZFDL'1";
	public static final String ZFDL1 = "ZFDL1";
	public static final String ZFDM_WITH_APOSTROPHE = "ZFDM'1";
	public static final String ZFDM1 = "ZFDM1";
	public static final String ZFDC_WITH_APOSTROPHE = "ZFDC'1";
	public static final String ZFDC1 = "ZFDC1";
	public static final String FAPP_FSI_END_TAG = "</FAPP_FSI>";
	public static final String CREATE_TASK_START_TAG = "<CREATE_TASK>";
	public static final String TASKTYPE_EDIFACT = "taskTypeEdifact";
	public static final String AMEND_TASK_START_TAG = "<AMEND_TASK>";
	public static final String UPDATE = "update";
	public static final String ADD_FSI_START_TAG = "<ADD_FSI>";
	public static final String SUPPORTDATA = "supportdata";
	public static final String CDATA = "CDATA";
	public static final String IS_EDIFACT = "isedifact";
	public static final String IS_EDIFACT_VALUE_TRUE = "true";
	public static final String IS_EDIFACT_VALUE_FALSE = "false";


	// exceptions constants for FFTBusinessException
	public static final String ERRORDESCRIPTION = "ErrorDescription :: ";
	public static final String ERRORDESCRIPTION_NEWLINE_CHAR = "\n";
	public static final String EXCEPTION_ROOTCAUSE = "Exception RootCause  ";

	// mapper constants for CreateTaskMapper
	public static final String CREATE_TASK_JSON_FILE = "createTaskJson.json";
	public static final String MAPPER_NEWLINE_CHAR = "\n";
	public static final String MAPPER_EMPTY_STRING = "";
	public static final String MAPPER_TAB_CHAR = "\t";
	public static final String CREATE_MAPPER_TASKTYPE = "create";
	public static final String CREATETASKPOJO_MAPPER = "createTaskPojo";
	public static final String UPDATE_MAPPER_TASKTYPE = "update";
	public static final String UPDATETASKPOJO_MAPPER = "updateTaskPojo";
	public static final String SUPPORTDATA_MAPPER_TASKTYPE = "supportdata";
	public static final String SUPPORTDATAPOJO_MAPPER = "supportdatapojo";

	public static final String SUPPORTDATAJSON_MAPPER = "supportdatajson";
	public static final String UPDATETASKJSON_MAPPER = "updateTaskjson";
	public static final String OUTPUTJSON_MAPPER = "outputjson";
	public static final String JOBGETRESPONSE="jobGetResponse";
	public static final String INBOUND_QUEUE_1 = "INBOUND_QUEUE_1";
	public static final String INBOUND_QUEUE_2 = "INBOUND_QUEUE_2";
	public static final String INBOUND_QUEUE_3 = "INBOUND_QUEUE_3";
	public static final String INBOUND_QUEUE_4 = "INBOUND_QUEUE_4";
	public static final String INBOUND_QUEUE_5 = "INBOUND_QUEUE_5";
	public static final String ZFCQ = "ZFCQ";
	public static final String ZFCQ_WITH_APOSTROPHE = "ZFCQ'1";
	
	
	
	

}
