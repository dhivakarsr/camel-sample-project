
package com.centrica.fft.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "quantityOrdered",
    "stockReferenceId"
})
public class Consumable {

    @JsonProperty("quantityOrdered")
    private Integer quantityOrdered;
    @JsonProperty("stockReferenceId")
    private String stockReferenceId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The quantityOrdered
     */
    @JsonProperty("quantityOrdered")
    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
     * 
     * @param quantityOrdered
     *     The quantityOrdered
     */
    @JsonProperty("quantityOrdered")
    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    /**
     * 
     * @return
     *     The stockReferenceId
     */
    @JsonProperty("stockReferenceId")
    public String getStockReferenceId() {
        return stockReferenceId;
    }

    /**
     * 
     * @param stockReferenceId
     *     The stockReferenceId
     */
    @JsonProperty("stockReferenceId")
    public void setStockReferenceId(String stockReferenceId) {
        this.stockReferenceId = stockReferenceId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
