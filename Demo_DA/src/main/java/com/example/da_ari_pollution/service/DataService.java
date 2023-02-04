package com.example.da_ari_pollution.service;

public interface DataService {
    void crawlData();
    void crawlCo();
    void crawlSo2();
    void crawlTmp();

    void calculateAQIs();

    double[][] getSO2Values();

    double[][] getO3Values();

    double[][] getNO2Values();

    double[][] getPM25Values();

    double[][] getPM10Values();

    double[][] getCOValues();

    double[][] getAirValues();

    double[][] getTmpValues();

}

