package com.example.da_ari_pollution.service.serviceImpl;

import com.example.da_ari_pollution.common.IDWUtil;
import com.example.da_ari_pollution.common.JsonMapper;
//import com.example.da_ari_pollution.common.NumberUtil;
//import com.example.da_ari_pollution.exception.Args;
//import com.example.da_ari_pollution.exception.BusinessException;
import com.example.da_ari_pollution.model.arcGis.*;
import com.example.da_ari_pollution.model.request.AddGeometriesRequest;
import com.example.da_ari_pollution.model.request.UpdateGeometriesRequest;
import com.example.da_ari_pollution.service.ArcGisService;
import com.example.da_ari_pollution.service.DataService;
import com.example.da_ari_pollution.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArcGisServiceImpl implements ArcGisService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DataService dataService;

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

//    private static final double MIN_LAT = 20.904725715521362;
//    private static final double MAX_LAT = 20.91932226209243;
//    private static final double MAX_LONG = 105.85208422724175;
//    private static final double MIN_LONG = 105.83528271419623;
//    private static final double MIN_LAT = 21.11142;
//    private static final double MAX_LAT = 21.12379;
//    private static final double MAX_LONG = 105.78145;
//    private static final double MIN_LONG = 105.76377 ;
    private static final double MIN_LAT = 21.10880;
    private static final double MAX_LAT = 21.12594;
    private static final double MIN_LONG = 105.76089;
    private static final double MAX_LONG = 105.78657;

    private static final double LAT_EDGE = ((MAX_LAT - MIN_LAT)/50);
    private static final double LONG_EDGE = ((MAX_LONG - MIN_LONG)/50);
    private static final int GRID_SIZE = 50;

    @Override
    public boolean generateGrid(String url) {
        sendGenerateLayerRequest(url);
        return true;
    }

    @Override
    public boolean pushData(String url, String pollutantType) {
        double[][] values;
        switch (pollutantType) {
            case "so2":
                values = dataService.getSO2Values();
                break;
            case "no2":
                values = dataService.getNO2Values();
                break;
            case "co":
                values = dataService.getCOValues();
                break;
            case "pm10":
                values = dataService.getPM10Values();
                break;
            case "pm25":
                values = dataService.getPM25Values();
                break;
            case "o3":
                values = dataService.getO3Values();
                break;
            case "aqi":
                values = dataService.getAirValues();
                break;
            case "tmp":
                values = dataService.getTmpValues();
                break;
            default:
                values = null;
                break;
        }
        sendUpdateRequest(url, IDWUtil.distanceInterpolate(values));
        return true;
    }

    @Override
    public void generateLayers() {
        sendGenerateLayerRequest(layerO3Url);
        sendGenerateLayerRequest(layerSO2Url);
        sendGenerateLayerRequest(layerNO2Url);
        sendGenerateLayerRequest(layerPM10Url);
        sendGenerateLayerRequest(layerCOUrl);
        sendGenerateLayerRequest(layerPM25Url);
        sendGenerateLayerRequest(layerAirUrl);
    }

    @Override
    public void generateLayerO3() {
        sendGenerateLayerRequest(layerO3Url);
    }

    @Override
    public void generateLayerPM10() {
        sendGenerateLayerRequest(layerPM10Url);
    }

    @Override
    public void updateLayerSO2() {
        double[][] so2Values = dataService.getSO2Values();
        sendUpdateRequest(layerSO2Url, IDWUtil.distanceInterpolate(so2Values));
    }

    @Override
    public void updateLayers() {
        double[][] so2Values = dataService.getSO2Values();
        sendUpdateRequest(layerSO2Url, IDWUtil.distanceInterpolate(so2Values));

        double[][] no2Values = dataService.getNO2Values();
        sendUpdateRequest(layerNO2Url, IDWUtil.distanceInterpolate(no2Values));

        double[][] coValues = dataService.getCOValues();
        sendUpdateRequest(layerCOUrl, IDWUtil.distanceInterpolate(coValues));

        double[][] pm10Values = dataService.getPM10Values();
        sendUpdateRequest(layerPM10Url, IDWUtil.distanceInterpolate(pm10Values));

        double[][] pm25Values = dataService.getPM25Values();
        sendUpdateRequest(layerPM25Url, IDWUtil.distanceInterpolate(pm25Values));

        double[][] o3Values = dataService.getO3Values();
        sendUpdateRequest(layerO3Url, IDWUtil.distanceInterpolate(o3Values));

        double[][] aqiValues = dataService.getAirValues();
        sendUpdateRequest(layerAirUrl, IDWUtil.distanceInterpolate(aqiValues));
    }

    private void sendGenerateLayerRequest(String url) {
        AddGeometriesRequest request = getGenerateLayerRequest();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

        MultiValueMap<String, String> addRequestInformation = new LinkedMultiValueMap<>();
        addRequestInformation.add("f", request.getF());
        addRequestInformation.add("token", request.getToken());
        addRequestInformation.add("adds", JsonMapper.writeValueAsString(request.getAddGeometryModels()));

        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<>(addRequestInformation, httpHeaders);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, requestBody, String.class);
          logger.info("entity response {} \n", JsonMapper.writeValueAsString(entity));

    }

    private AddGeometriesRequest getGenerateLayerRequest() {
        AddGeometriesRequest request = new AddGeometriesRequest();
        request.setF("json");
        request.setToken(tokenService.getCurrentToken());
        List<AddGeometryModel> addGeometryModels = new LinkedList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Geometry geometry = new Geometry();
                double[] topLeftPoint = {MIN_LONG + j * LONG_EDGE, MIN_LAT + i * LAT_EDGE};
                double[] topRightPoint = {MIN_LONG + j * LONG_EDGE + LONG_EDGE, MIN_LAT + i * LAT_EDGE};
                double[] botRightPoint = {MIN_LONG + j * LONG_EDGE + LONG_EDGE, MIN_LAT + i * LAT_EDGE + LAT_EDGE};
                double[] botLeftPoint = {MIN_LONG + j * LONG_EDGE, MIN_LAT + i * LAT_EDGE + LAT_EDGE};
                List<double[]> ring = new LinkedList<>();
                ring.add(topLeftPoint);
                ring.add(topRightPoint);
                ring.add(botRightPoint);
                ring.add(botLeftPoint);
                List<List<double[]>> rings = new LinkedList<>();
                rings.add(ring);
                geometry.setRings(rings);
                AddGeometryModel model = new AddGeometryModel();
                model.setAttributes(new WeightValue(0));
                model.setGeometry(geometry);
                addGeometryModels.add(model);
            }
        }
        request.setAddGeometryModels(addGeometryModels);
        return request;
    }

    private void sendUpdateRequest(String url, double[][] values) {
        UpdateGeometriesRequest request = getUpdateGeometriesRequest(values);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

        MultiValueMap<String, String> updateRequestInformation = new LinkedMultiValueMap<>();
        updateRequestInformation.add("f", request.getF());
        updateRequestInformation.add("token", request.getToken());
        updateRequestInformation.add("updates", JsonMapper.writeValueAsString(request.getUpdateGeometryModels()));

        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<>(updateRequestInformation, httpHeaders);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, requestBody, String.class);
                logger.info("entity response {} \n", JsonMapper.writeValueAsString(entity));

    }



    private UpdateGeometriesRequest getUpdateGeometriesRequest(double[][] values) {
        UpdateGeometriesRequest request = new UpdateGeometriesRequest();
        request.setF("json");
        request.setToken(tokenService.getCurrentToken());
        List<UpdateGeometryModel> updateGeometryModels = new LinkedList<>();
        for (int i = 0; i < 50; i ++) {
            for (int j = 0; j < 50; j ++) {
                UpdateGeometryModel model = new UpdateGeometryModel();
                UpdateInfo info = new UpdateInfo();
                info.setObjectId(i * 50 + j + 1);
                info.setValue((double) Math.round(values[i][j] * 100) / 100);//làm tròn 2 chữ so tp
                model.setAttributes(info);
                updateGeometryModels.add(model);
            }
        }
        request.setUpdateGeometryModels(updateGeometryModels);
        return request;
    }


}

