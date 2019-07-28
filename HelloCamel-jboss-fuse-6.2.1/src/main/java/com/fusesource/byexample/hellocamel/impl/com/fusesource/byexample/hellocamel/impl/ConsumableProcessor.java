package com.fusesource.byexample.hellocamel.impl.com.fusesource.byexample.hellocamel.impl;

import org.apache.camel.Body;

import com.dao.VISITENGINEERRETURN;
import com.google.gson.JsonObject;

public class ConsumableProcessor {
	
	public JsonObject CreateEdifactConsymable(@Body JsonObject visit)
	{
		System.out.println(visit+"inside method return");
		
		return visit;
	}

}
