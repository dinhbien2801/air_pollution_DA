package com.example.da_ari_pollution.service;

public interface ArcGisService {
    boolean generateGrid(String url);

    boolean pushData(String url, String pollutantType);

    void generateLayers();

    void generateLayerO3();

    void generateLayerPM10();

    void updateLayerSO2();

    void updateLayers();
}
