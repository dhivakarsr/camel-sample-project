package com.centrica.fft.exceptions;

import org.apache.log4j.Logger;

import com.centrica.fft.constants.FFTConstants;

/**
 * PROGRAM NAME		    : ILException.java
 * PROGRAM DESCRIPTION  : This Exception class is responsible for capturing both
 * TechnicalException and BusinessException.
 *
 * AUTHOR 				: Cognizant Technology Solutions.
 */
@SuppressWarnings("serial")
public class FFTException extends java.lang.RuntimeException {
	private String errorCode = null;

	private String errorDesc = null;

	private static transient Logger logger = Logger
			.getLogger(FFTException.class);

	private String errorKey = null;

	private Throwable rootCause = null;

	/**
	 * Default constructor implementation.
	 */
	FFTException() {
		super();
	}

	/**
	 * Throwable parameterized constructor implementation.
	 * 
	 * @param Throwable
	 *            rootCause
	 */
	FFTException(Throwable rootCause) {
		this.rootCause = rootCause;
	}

	/**
	 * Construct a new BaseException with a message using the default handler.
	 * 
	 * @param errCode
	 *            a descriptive message.
	 * 
	 */
	public FFTException(String errCode) {
		errorCode = errCode;
	}

	/**
	 * Construct a new BaseException with a errorDesc and error code using the
	 * default handler.
	 * 
	 * @param errCode
	 *            a descriptive message.
	 * @param errDesc
	 *            a descriptive message.
	 */
	public FFTException(String errCode, String errDesc) {
		errorCode = errCode;
		errorDesc = errDesc;
		
	}

	/**
	 * Logging both ErrorDescription and Exception RootCause of the exception.
	 * 
	 */
	public void handleException() {
		StringBuffer str = new StringBuffer();
		str.append(FFTConstants.ERRORDESCRIPTION);
		str.append(this.getLocalizedMessage());
		str.append(FFTConstants.ERRORDESCRIPTION_NEWLINE_CHAR);
		str.append(FFTConstants.EXCEPTION_ROOTCAUSE);
		str.append(this.getCause().getLocalizedMessage());
		logger.error(str.toString());
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
		logger.debug("The value coming in setter is :" + errorDesc);
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	public void setRootCause(Throwable rootCause) {
		this.rootCause = rootCause;
	}
}
