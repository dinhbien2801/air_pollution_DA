package com.example.da_ari_pollution.model.arcGis;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Geometry {

    private List<List<double[]>> rings;

    private HashMap<String, Integer> spatialReference;

    public Geometry() {
        this.spatialReference = new HashMap<>();
        this.spatialReference.put("wkid", 4326);
    }
}