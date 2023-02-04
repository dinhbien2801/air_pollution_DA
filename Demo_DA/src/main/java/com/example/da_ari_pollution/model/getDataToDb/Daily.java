package com.example.da_ari_pollution.model.getDataToDb;

import lombok.Data;

import java.util.List;

@Data
public class Daily {
    List<ForecastMax> o3;
    List<ForecastMax> pm10;
    List<ForecastMax> pm25;
}