package com.fusesource.byexample.hellocamel.impl.com.fusesource.byexample.hellocamel.impl;

	import java.util.ArrayList;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	 
	import com.fasterxml.jackson.annotation.JsonAnyGetter;
	import com.fasterxml.jackson.annotation.JsonAnySetter;
	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	import com.fasterxml.jackson.annotation.JsonProperty;
	import com.fasterxml.jackson.databind.annotation.JsonSerialize;
	 

	public class ConsumableJson {
	 
	    @JsonProperty
	    private String engineerId;
	    
	 
	 
	  
	 
	    public String getEngineerId() {
			return engineerId;
		}

		public void setEngineerId(String engineerId) {
			this.engineerId = engineerId;
		}

		public List<Consumable> getConsumables() {
			return consumables;
		}

		public void setConsumables(List<Consumable> consumables) {
			this.consumables = consumables;
		}

		@JsonProperty
	    private List<Consumable> consumables = new ArrayList<Consumable>();
	 
	    public class Consumable {
	    	 @JsonProperty
	 	    private String stockRefId;
	    	 public String getStockRefId() {
				return stockRefId;
			}
			public void setStockRefId(String stockRefId) {
				this.stockRefId = stockRefId;
			}
			public String getOrderednumber() {
				return orderednumber;
			}
			public void setOrderednumber(String orderednumber) {
				this.orderednumber = orderednumber;
			}
			@JsonProperty
	 	    private String orderednumber;
	    	
	    }
	   
	 
	
	 

}
