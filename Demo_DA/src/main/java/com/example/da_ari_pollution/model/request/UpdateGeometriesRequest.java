package com.example.da_ari_pollution.model.request;

import com.example.da_ari_pollution.model.arcGis.UpdateGeometryModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateGeometriesRequest {
    private String f;

    private String token;

    @JsonProperty("updates")
    private List<UpdateGeometryModel> updateGeometryModels;
}