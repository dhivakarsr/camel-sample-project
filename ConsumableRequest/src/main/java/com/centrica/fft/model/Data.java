
package com.centrica.fft.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "engineerId",
    "consumables"
})
public class Data {

    @JsonProperty("engineerId")
    private String engineerId;
    @JsonProperty("consumables")
    private List<Consumable> consumables = new ArrayList<Consumable>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The engineerId
     */
    @JsonProperty("engineerId")
    public String getEngineerId() {
        return engineerId;
    }

    /**
     * 
     * @param engineerId
     *     The engineerId
     */
    @JsonProperty("engineerId")
    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    /**
     * 
     * @return
     *     The consumables
     */
    @JsonProperty("consumables")
    public List<Consumable> getConsumables() {
        return consumables;
    }

    /**
     * 
     * @param consumables
     *     The consumables
     */
    @JsonProperty("consumables")
    public void setConsumables(List<Consumable> consumables) {
        this.consumables = consumables;
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
