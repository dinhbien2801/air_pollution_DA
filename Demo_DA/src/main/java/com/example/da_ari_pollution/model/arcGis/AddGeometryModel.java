package com.example.da_ari_pollution.model.arcGis;

import lombok.Data;

@Data
public class AddGeometryModel {

    private String geometryType = "esriGeometryPolygon";

    private Geometry geometry;

    private WeightValue attributes;

}