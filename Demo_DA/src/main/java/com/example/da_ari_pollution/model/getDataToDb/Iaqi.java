package com.example.da_ari_pollution.model.getDataToDb;

import lombok.Data;

@Data
public class Iaqi {
    private PollutionValue co;
    private PollutionValue pm10;
    private PollutionValue pm25;
    private PollutionValue no2;
    private PollutionValue so2;
}

