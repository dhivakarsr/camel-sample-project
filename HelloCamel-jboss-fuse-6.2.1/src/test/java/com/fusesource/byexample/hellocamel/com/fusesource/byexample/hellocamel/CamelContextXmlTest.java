package com.fusesource.byexample.hellocamel.com.fusesource.byexample.hellocamel; 
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.VISITENGINEERRETURN;
import com.dao.VISITENGINEERRETURN.APPLICABILITY;
import com.fusesource.byexample.hellocamel.impl.com.fusesource.byexample.hellocamel.impl.ConsumableJson;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

public class CamelContextXmlTest extends CamelSpringTestSupport {

    // templates to send to input endpoints
    @Produce(uri = "jetty:http://0.0.0.0:8889/hellocamel")
    protected ProducerTemplate inputEndpoint;

    @Test
    public void testCamelRoute() throws Exception {
    	ConsumableJson con=new ConsumableJson();
    	JSONArray consumableslist = new JSONArray();
    	JSONObject jo = new JSONObject();
    	jo.put("engineerId", "12345");
    	jo.put("consumables",consumableslist);

    	JSONObject consumable = new JSONObject();
    
    	consumable.put("stockRefId", "12345");
    	consumable.put("orderednumber","12");
    	consumableslist.put(consumable);

    	 JSON json = JSONSerializer.toJSON(con);
    	
    	/*
    	VISITENGINEERRETURN vist =new VISITENGINEERRETURN();
    	APPLICABILITY app=new APPLICABILITY();
    	app.setTOPIC("CON_ORDER");
    	vist.setAPPLICABILITY(app);*/
        ConsumableJson response = inputEndpoint.requestBody(json, ConsumableJson.class);

        assertEquals("Hello Camel", response);
    }

    @Override
    protected ClassPathXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
    }

}
