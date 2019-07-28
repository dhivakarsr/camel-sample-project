package com.centrica.consumables;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;

import com.centrica.fft.util.WDFMsgFmtUtil;

public class WDFMsgFmtUtilTest {
	
	public WDFMsgFmtUtilTest(){}
	
	public Exchange exchange;
	
	  public String convert() throws FileNotFoundException, IOException {
	       StringBuilder sb = new StringBuilder();
	       String line="";
	       BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("samplexml/WDF_Create_Task.xml")));
	    	   while(( line =br.readLine())!=null) {
	               sb.append(line);
	           }
	       return sb.toString();
	  }
	  
	  public String convert1() throws FileNotFoundException, IOException {
	       StringBuilder sb = new StringBuilder();
	       String line="";
	       BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("samplexml/WDF-Amend_Task.xml")));
	    	   while(( line =br.readLine())!=null) {
	               sb.append(line);
	           }
	       return sb.toString();
	  }
	  
	  public String convert2() throws FileNotFoundException, IOException {
	       StringBuilder sb = new StringBuilder();
	       String line="";
	       BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("samplexml/WDF-Add_Fsi.xml")));
	       
	    	   while(( line =br.readLine())!=null) {
	               sb.append(line);
	           }
	       return sb.toString();
	  }
	  
	  
	  
	  @Test
		public void extractEDIFACTMsgTest() throws Exception {
		  WDFMsgFmtUtilTest  wDFMsgFmtUtilTest=new WDFMsgFmtUtilTest();
		  WDFMsgFmtUtil wDFMsgFmtUtil = new WDFMsgFmtUtil();
		  CamelContext camelContext = getCamelExchangeObj();
		  exchange = new DefaultExchange(camelContext);
		  wDFMsgFmtUtil.getXmlWithEdi(wDFMsgFmtUtilTest.convert(),exchange);	
		}

	  @Test
		public void checkTaskTypeTest() throws Exception {
		  WDFMsgFmtUtilTest  wDFMsgFmtUtilTest=new WDFMsgFmtUtilTest();
		  WDFMsgFmtUtil wDFMsgFmtUtil = new WDFMsgFmtUtil();
		  CamelContext camelContext = getCamelExchangeObj();
		  exchange = new DefaultExchange(camelContext);
		  wDFMsgFmtUtil.checkTaskType(wDFMsgFmtUtilTest.convert(),exchange);	
		}
	  
	  @Test
		public void checkTaskTypeTest1() throws Exception {
		  WDFMsgFmtUtilTest  wDFMsgFmtUtilTest=new WDFMsgFmtUtilTest();
		  WDFMsgFmtUtil wDFMsgFmtUtil = new WDFMsgFmtUtil();
		  CamelContext camelContext = getCamelExchangeObj();
		  exchange = new DefaultExchange(camelContext);
		  wDFMsgFmtUtil.checkTaskType(wDFMsgFmtUtilTest.convert1(),exchange);	
		}
	  
	  @Test
			public void checkTaskTypeTest2() throws Exception {
		    WDFMsgFmtUtilTest  wDFMsgFmtUtilTest=new WDFMsgFmtUtilTest();
		    WDFMsgFmtUtil wDFMsgFmtUtil = new WDFMsgFmtUtil();
			CamelContext camelContext = getCamelExchangeObj();
			exchange = new DefaultExchange(camelContext);
			wDFMsgFmtUtil.checkTaskType(wDFMsgFmtUtilTest.convert2(),exchange);	
			}
		  
	  @Test
		public void createEdifactFormatTest() throws Exception {
		 WDFMsgFmtUtilTest  wDFMsgFmtUtilTest=new WDFMsgFmtUtilTest();
		 WDFMsgFmtUtil wDFMsgFmtUtil = new WDFMsgFmtUtil();
		  CamelContext camelContext = getCamelExchangeObj();
		  exchange = new DefaultExchange(camelContext);
		  wDFMsgFmtUtil.createEdifactFormat(wDFMsgFmtUtilTest.convert2(),exchange);	
		}
	  
	 @Test
		public void combineEdifactMessageTest() throws Exception {
		 WDFMsgFmtUtilTest  wDFMsgFmtUtilTest=new WDFMsgFmtUtilTest();
		 WDFMsgFmtUtil wDFMsgFmtUtil = new WDFMsgFmtUtil();
		  CamelContext camelContext = getCamelExchangeObj();
		  exchange = new DefaultExchange(camelContext);
		  exchange.setProperty("wdfMsg", wDFMsgFmtUtilTest.convert2());
		  wDFMsgFmtUtil.combineEdifactMessage(wDFMsgFmtUtil.createEdifactFormat(wDFMsgFmtUtilTest.convert2(), exchange),wDFMsgFmtUtilTest.convert2(),exchange);	
		}
	  
	 @Test
		public void getXmlWithEdiTest() throws Exception {
		 WDFMsgFmtUtilTest  wDFMsgFmtUtilTest=new WDFMsgFmtUtilTest();
		 WDFMsgFmtUtil wDFMsgFmtUtil = new WDFMsgFmtUtil();
		  CamelContext camelContext = getCamelExchangeObj();
		  exchange = new DefaultExchange(camelContext);
		  wDFMsgFmtUtil.getXmlWithEdi(wDFMsgFmtUtilTest.convert(),exchange);	
		}
	    
	  public CamelContext getCamelExchangeObj() {
			return new DefaultCamelContext();
		}
}
