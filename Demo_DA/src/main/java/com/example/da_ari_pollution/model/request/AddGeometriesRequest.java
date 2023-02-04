package com.example.da_ari_pollution.model.request;

import com.example.da_ari_pollution.model.arcGis.AddGeometryModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddGeometriesRequest {

    private String f;

    private String token;

    @JsonProperty("adds")
    private List<AddGeometryModel> addGeometryModels;

}