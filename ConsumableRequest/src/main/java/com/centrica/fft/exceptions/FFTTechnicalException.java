package com.centrica.fft.exceptions;

import org.apache.log4j.Logger;

import com.centrica.fft.constants.FFTConstants;
/**
 * PROGRAM NAME		    : ILTechnicalException.java
 * PROGRAM DESCRIPTION  : This ILTechnicalException class is responsible for 
 * capturing exceptions related Technical.
 * 
 * AUTHOR 				: Cognizant Technology Solutions.
 */
public class FFTTechnicalException extends FFTException {

	private static final long serialVersionUID = 1L;
	private static transient Logger logger = Logger
			.getLogger(FFTTechnicalException.class);


	/**
	 * Default constructor implementation.
	 */
	public FFTTechnicalException() {
		super();
	}

	/**
	 * Construct a new BaseException with a message using the default handler.
	 * 
	 * @param message
	 *            a descriptive message.
	 */
	public FFTTechnicalException(String errCode) {
		super(errCode);
	}

	/**
	 * Construct a new BaseException with a errorDesc and error code using the
	 * default handler.
	 * 
	 * @param message
	 *            a descriptive message.
	 */
	public FFTTechnicalException(String errCode, String errDesc) {
		super(errCode, errDesc);
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
}
