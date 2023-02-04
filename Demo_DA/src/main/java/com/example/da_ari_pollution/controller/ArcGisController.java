package com.example.da_ari_pollution.controller;

import com.example.da_ari_pollution.service.ArcGisService;
import com.example.da_ari_pollution.service.DataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArcGisController {

    @Autowired
    private DataService dataService;

    @Autowired
    private ArcGisService arcGisService;

    @Value("${arcgis.layer.o3}")
    private String layerO3Url;

    @Value("${arcgis.layer.so2}")
    private String layerSO2Url;

    @Value("${arcgis.layer.no2}")
    private String layerNO2Url;

    @Value("${arcgis.layer.pm10}")
    private String layerPM10Url;

    @Value("${arcgis.layer.co}")
    private String layerCOUrl;

    @Value("${arcgis.layer.pm25}")
    private String layerPM25Url;

    @Value("${arcgis.layer.air}")
    private String layerAirUrl;

    @GetMapping("/saveData")
    public ResponseEntity<?> crawlData() {
        dataService.crawlData();
        return ResponseEntity.status(200).body("");
    }

    @GetMapping("/saveCo")
    public ResponseEntity<?> crawlCo() {
        dataService.crawlCo();
        return ResponseEntity.status(200).body("");
    }

    @GetMapping("/saveSo2")
    public ResponseEntity<?> crawlSo2() {
        dataService.crawlSo2();
        return ResponseEntity.status(200).body("");
    }

    @GetMapping("/save-tmp")
    public ResponseEntity<?> crawlTmp() {
        dataService.crawlTmp();
        return ResponseEntity.status(200).body("");
    }

    @GetMapping("/saveAqiValue")
    public ResponseEntity<?> calculateAqis() {
        dataService.calculateAQIs();
        return ResponseEntity.status(200).body("");
    }

    @ApiOperation(value = "API Generate Grid to layer", response = String.class)
    @GetMapping("/generate-grid")
    public ResponseEntity<?> generateGrid(@RequestParam String url) {
        arcGisService.generateGrid(url);
        return ResponseEntity.status(200).body("");
    }

    @ApiOperation(value = "API update data to layer", response = String.class)
    @GetMapping("/push-data")
    public ResponseEntity<?> pushData(@RequestParam String url, @RequestParam String pollutantType) {
        arcGisService.pushData(url, pollutantType);
        return ResponseEntity.status(200).body("");
    }
    @GetMapping("/push-data-all")
    public ResponseEntity<?> pushDataAll() {
        arcGisService.pushData(layerAirUrl,"aqi");
        arcGisService.pushData(layerSO2Url,"so2");
        arcGisService.pushData(layerNO2Url,"no2");
        arcGisService.pushData(layerCOUrl,"co");
        arcGisService.pushData(layerPM10Url,"pm10");
        arcGisService.pushData(layerPM25Url,"pm25");
        arcGisService.pushData(layerO3Url,"o3");

        return ResponseEntity.status(200).body("");
    }
}
