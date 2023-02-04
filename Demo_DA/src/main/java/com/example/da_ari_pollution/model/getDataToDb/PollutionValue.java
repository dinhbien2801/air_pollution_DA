package com.example.da_ari_pollution.model.getDataToDb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PollutionValue {
    @JsonProperty("v")
    private double value;
}

