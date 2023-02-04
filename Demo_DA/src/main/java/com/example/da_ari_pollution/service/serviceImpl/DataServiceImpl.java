package com.example.da_ari_pollution.service.serviceImpl;

//import com.example.da_ari_pollution.common.NumberUtil;
import com.example.da_ari_pollution.entity.*;
import com.example.da_ari_pollution.model.getDataToDb.Iaqi;
import com.example.da_ari_pollution.model.response.CrawlResponse;
import com.example.da_ari_pollution.repository.*;
import com.example.da_ari_pollution.service.DataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {

    private static final double MIN_LAT = 21.10880;
    private static final double MAX_LAT = 21.12594;
    private static final double MIN_LONG = 105.76089;
    private static final double MAX_LONG = 105.78657;

    private static final double LAT_EDGE = ((MAX_LAT - MIN_LAT) / 50);
    private static final double LONG_EDGE = ((MAX_LONG - MIN_LONG) / 50);
    private static final int GRID_SIZE = 50;

    @Autowired
    private CORepo coRepo;

    @Autowired
    private PM10Repo pm10Repo;

    @Autowired
    private SO2Repo so2Repo;

    @Autowired
    private NO2Repo no2Repo;


    @Autowired
    private O3Repo o3Repo;

    @Autowired
    private PM25Repo pm25Repo;

    @Autowired
    private AirRepo airRepo;

    @Autowired
    private TmpRepo tmpRepo;

    @Value("${crawlDataUrl}")
    private String crawlDataUrl;

    private ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public void crawlData() {
        crawl();
    }

    @Override
    public double[][] getSO2Values() {
        double[][] values = new double[50][50];
        List<SO2Pollution> so2Pollutions = so2Repo.findAll();
        for (SO2Pollution so2Pollution : so2Pollutions) {
            int latMod = (so2Pollution.getLat() - MIN_LAT) % LAT_EDGE == 0 ? 0 : 1;
            int row = (int) ((so2Pollution.getLat() - MIN_LAT) / LAT_EDGE) + latMod;
            int lonMod = (so2Pollution.getLon() - MIN_LONG) % LONG_EDGE == 0 ? 0 : 1;
            int col = (int) ((so2Pollution.getLon() - MIN_LONG) / LONG_EDGE) + lonMod;
            values[row - 1][col - 1] = so2Pollution.getValue();
        }
        return values;
    }

    @Override
    public double[][] getO3Values() {
        double[][] values = new double[50][50];
        List<O3Pollution> o3Pollutions = o3Repo.findAll();
        for (O3Pollution o3Pollution : o3Pollutions) {
            int latMod = (o3Pollution.getLat() - MIN_LAT) % LAT_EDGE == 0 ? 0 : 1;
            int row = (int) ((o3Pollution.getLat() - MIN_LAT) / LAT_EDGE) + latMod;
            int lonMod = (o3Pollution.getLon() - MIN_LONG) % LONG_EDGE == 0 ? 0 : 1;
            int col = (int) ((o3Pollution.getLon() - MIN_LONG) / LONG_EDGE) + lonMod;
            values[row - 1][col - 1] = o3Pollution.getValue();
        }
        return values;
    }

    @Override
    public double[][] getNO2Values() {
        double[][] values = new double[50][50];
        List<NO2Pollution> no2Pollutions = no2Repo.findAll();
        for (NO2Pollution no2Pollution : no2Pollutions) {
            int latMod = (no2Pollution.getLat() - MIN_LAT) % LAT_EDGE == 0 ? 0 : 1;
            int row = (int) ((no2Pollution.getLat() - MIN_LAT) / LAT_EDGE) + latMod;
            int lonMod = (no2Pollution.getLon() - MIN_LONG) % LONG_EDGE == 0 ? 0 : 1;
            int col = (int) ((no2Pollution.getLon() - MIN_LONG) / LONG_EDGE) + lonMod;
            values[row - 1][col - 1] = no2Pollution.getValue();
        }
        return values;
    }


    @Override
    public double[][] getPM25Values() {
        double[][] values = new double[50][50];
        List<PM25Pollution> pm25Pollutions = pm25Repo.findAll();
        for (PM25Pollution pm25Pollution : pm25Pollutions) {
            int latMod = (pm25Pollution.getLat() - MIN_LAT) % LAT_EDGE == 0 ? 0 : 1;
            int row = (int) ((pm25Pollution.getLat() - MIN_LAT) / LAT_EDGE) + latMod;
            int lonMod = (pm25Pollution.getLon() - MIN_LONG) % LONG_EDGE == 0 ? 0 : 1;
            int col = (int) ((pm25Pollution.getLon() - MIN_LONG) / LONG_EDGE) + lonMod;
            values[row - 1][col - 1] = pm25Pollution.getValue();
        }
        return values;
    }

    @Override
    public double[][] getPM10Values() {
        double[][] values = new double[50][50];
        List<PM10Pollution> pm10Pollutions = pm10Repo.findAll();
        for (PM10Pollution pm10Pollution : pm10Pollutions) {
            int latMod = (pm10Pollution.getLat() - MIN_LAT) % LAT_EDGE == 0 ? 0 : 1;
            int row = (int) ((pm10Pollution.getLat() - MIN_LAT) / LAT_EDGE) + latMod;
            int lonMod = (pm10Pollution.getLon() - MIN_LONG) % LONG_EDGE == 0 ? 0 : 1;
            int col = (int) ((pm10Pollution.getLon() - MIN_LONG) / LONG_EDGE) + lonMod;
            values[row - 1][col - 1] = pm10Pollution.getValue();
        }
        return values;
    }

    @Override
    public double[][] getCOValues() {
        double[][] values = new double[50][50];
        List<COPollution> coPollutions = coRepo.findAll();
        for (COPollution coPollution : coPollutions) {
            int latMod = (coPollution.getLat() - MIN_LAT) % LAT_EDGE == 0 ? 0 : 1;
            int row = (int) ((coPollution.getLat() - MIN_LAT) / LAT_EDGE) + latMod;
            int lonMod = (coPollution.getLon() - MIN_LONG) % LONG_EDGE == 0 ? 0 : 1;
            int col = (int) ((coPollution.getLon() - MIN_LONG) / LONG_EDGE) + lonMod;
            values[row - 1][col - 1] = coPollution.getValue();
        }
        return values;
    }

    @Override
    public double[][] getAirValues() {
        double[][] values = new double[50][50];
        List<AirPollution> airPollutions = airRepo.findAll();
        for (AirPollution airPollution : airPollutions) {
            int latMod = (airPollution.getLat() - MIN_LAT) % LAT_EDGE == 0 ? 0 : 1;
            int row = (int) ((airPollution.getLat() - MIN_LAT) / LAT_EDGE) + latMod;
            int lonMod = (airPollution.getLon() - MIN_LONG) % LONG_EDGE == 0 ? 0 : 1;
            int col = (int) ((airPollution.getLon() - MIN_LONG) / LONG_EDGE) + lonMod;
            values[row - 1][col - 1] = airPollution.getValue();
        }
        return values;
    }

    @Override
    public double[][] getTmpValues() {
        double[][] values = new double[50][50];
        List<Tmp> tmpPollutions = tmpRepo.findAll();
        for (Tmp tmpPollution: tmpPollutions) {
            int latMod = (tmpPollution.getLat() - MIN_LAT) % LAT_EDGE == 0 ? 0 : 1;
            int row = (int) ((tmpPollution.getLat() - MIN_LAT) / LAT_EDGE) + latMod;
            int lonMod = (tmpPollution.getLon() - MIN_LONG) % LONG_EDGE == 0 ? 0 : 1;
            int col = (int) ((tmpPollution.getLon() - MIN_LONG) / LONG_EDGE) + lonMod;
            values[row - 1][col - 1] = tmpPollution.getValue();
        }
        System.out.println(values);
        return values;
    }


    @Override
    public void calculateAQIs() {
        double[][] pm10Ranges = new double[][]{{0, 54}, {55, 154}, {155, 254}, {255, 354}, {355, 424}, {425, Double.MAX_VALUE}};
        double[][] pm25Ranges = new double[][]{{0, 12}, {12.1, 35.4}, {35.5, 55.4}, {55.5, 150.4}, {150.5, 250.4}, {250.5, Double.MAX_VALUE}};
        double[][] so2Ranges = new double[][]{{0, 35}, {36, 75}, {76, 185}, {186, 304}, {305, 1249}, {1250, Double.MAX_VALUE}};
        double[][] coRanges = new double[][]{{0, 4.4}, {4.5, 9.4}, {9.5, 12.4}, {12.5, 15.4}, {15.5, 30.4}, {30.5, Double.MAX_VALUE}};
        double[][] no2Ranges = new double[][]{{0, 40}, {41, 80}, {81, 180}, {181, 280}, {281, 400}, {401, Double.MAX_VALUE}};
        double[][] o3Ranges = new double[][]{{0, 54}, {55, 70}, {71, 85}, {86, 105}, {106, 200}, {201, Double.MAX_VALUE}};

        List<PM10Pollution> pm10Pollutions = pm10Repo.findAll();
        List<PM25Pollution> pm25Pollutions = pm25Repo.findAll();
        List<SO2Pollution> so2Pollutions = so2Repo.findAll();
        List<COPollution> coPollutions = coRepo.findAll();
        List<NO2Pollution> no2Pollutions = no2Repo.findAll();
        List<O3Pollution> o3Pollutions = o3Repo.findAll();
        List<AirPollution> airPollutions = new ArrayList<>();
        Long tmpId =0L;
        for (int i = 0; i < 576; i++) {
            tmpId+=1;
            AirPollution airPollution = new AirPollution();
            airPollution.setLat(pm10Pollutions.get(i).getLat());
            airPollution.setLon(pm10Pollutions.get(i).getLon());
//            airPollution.setId(pm10Pollutions.get(i).getId());
            double aqi = 0;
            aqi = Math.max(aqi, calculateAQI(pm10Pollutions.get(i).getValue(), pm10Ranges));
            aqi = Math.max(aqi, calculateAQI(pm25Pollutions.get(i).getValue(), pm25Ranges));
            aqi = Math.max(aqi, calculateAQI(so2Pollutions.get(i).getValue(), so2Ranges));
            aqi = Math.max(aqi, calculateAQI(coPollutions.get(i).getValue(), coRanges));
            aqi = Math.max(aqi, calculateAQI(no2Pollutions.get(i).getValue(), no2Ranges));
            aqi = Math.max(aqi, calculateAQI(o3Pollutions.get(i).getValue(), o3Ranges));

            Optional<AirPollution> airPollution1 = airRepo.findById(tmpId);
            if(airPollution1.isPresent()){
                airPollution = airPollution1.get();
                airPollution.setValue(aqi);
                airRepo.save(airPollution);
            }else {
                airPollution.setId(pm10Pollutions.get(i).getId());
                airPollution.setValue(aqi);
                airPollutions.add(airPollution);
                airRepo.save(airPollution);
            }
        }
//        airRepo.saveAll(airPollutions);
    }

    private double calculateAQI(double value, double[][] ranges) {
        double[][] aqiRanges = new double[][]{{0, 50}, {51, 100}, {101, 150}, {151, 200}, {201, 300}, {301, Double.MAX_VALUE}};
        int index = 0;
        int valueInt = (int) value;
        while (!(valueInt >= ranges[index][0] && valueInt <= ranges[index][1])) {
            index++;
        }
        return ((aqiRanges[index][1] - aqiRanges[index][0]) / (ranges[index][1] - ranges[index][0])) * (valueInt - ranges[index][0])
                + aqiRanges[index][0];
    }

//    private void crawl() {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Object> requestBody = new HttpEntity<>(null, new HttpHeaders());
//
//        for (int i = 2; i <= 48; i += 2) {
//            for (int j = 2; j <= 48; j += 2) {
//                double currentLat = MIN_LAT + (i - 1) * LAT_EDGE + LAT_EDGE / 2;
//                double currentLon = MIN_LONG + (j - 1) * LONG_EDGE + LONG_EDGE / 2;
//                String currentUrl = crawlDataUrl.replaceAll("%lat%", String.valueOf(currentLat + (float) (i - 2) / 2))
//                        .replaceAll("%lon%", String.valueOf(currentLon + (float) (j - 2) / 2));
//                ResponseEntity<String> responseString = restTemplate.exchange(currentUrl, HttpMethod.GET, requestBody, String.class);
//                TypeReference<CrawlResponse> reference = new TypeReference<CrawlResponse>() {
//                };
//                try {
//                    CrawlResponse response = MAPPER.readValue(responseString.getBody(), reference);
//                    Iaqi iaqi = response.getData().getIaqi();
//                    coRepo.save(COPollution.builder()
//                            .lat(currentLat)
//                            .lon(currentLon)
//                            .value(iaqi.getCo() == null ? 0 : iaqi.getCo().getValue())
//                            .build());
//
//                    no2Repo.save(NO2Pollution.builder()
//                            .lat(currentLat)
//                            .lon(currentLon)
//                            .value(iaqi.getNo2() == null ? 0 : iaqi.getNo2().getValue())
//                            .build());
//
//                    so2Repo.save(SO2Pollution.builder()
//                            .lat(currentLat)
//                            .lon(currentLon)
//                            .value(iaqi.getSo2() == null ? 0 : iaqi.getSo2().getValue())
//                            .build());
//
//                    pm25Repo.save(PM25Pollution.builder()
//                            .lat(currentLat)
//                            .lon(currentLon)
//                            .value(response.getData().getForecast().getDaily().getPm25().get(0).getMax())
//                            .build());
//
//                    pm10Repo.save(PM10Pollution.builder()
//                            .lat(currentLat)
//                            .lon(currentLon)
//                            .value(response.getData().getForecast().getDaily().getPm10().get(0).getMax())
//                            .build());
//
//                    o3Repo.save(O3Pollution.builder()
//                            .lat(currentLat)
//                            .lon(currentLon)
//                            .value(response.getData().getForecast().getDaily().getO3().get(0).getMax())
//                            .build());
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }

    private void crawl() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> requestBody = new HttpEntity<>(null, new HttpHeaders());
        long tmpId =0;
        for (int i = 2; i <= 48; i += 2) {
            for (int j = 2; j <= 48; j += 2) {
                tmpId+=1;
                double currentLat = MIN_LAT + (i - 1) * LAT_EDGE + LAT_EDGE / 2;
                double currentLon = MIN_LONG + (j - 1) * LONG_EDGE + LONG_EDGE / 2;
                String currentUrl = crawlDataUrl.replaceAll("%lat%", String.valueOf(currentLat + (float) (i - 2) / 2))
                        .replaceAll("%lon%", String.valueOf(currentLon + (float) (j - 2) / 2));
                ResponseEntity<String> responseString = restTemplate.exchange(currentUrl, HttpMethod.GET, requestBody, String.class);
                TypeReference<CrawlResponse> reference = new TypeReference<CrawlResponse>() {
                };
                try {
                    CrawlResponse response = MAPPER.readValue(responseString.getBody(), reference);
                    Iaqi iaqi = response.getData().getIaqi();

                    Optional<COPollution> coPollution = coRepo.findById(tmpId);
                    if(coPollution.isPresent()){
                        COPollution coPollution1 = coPollution.get();
                        coPollution1.setValue(iaqi.getCo() == null ? 0 : iaqi.getCo().getValue());
                        coRepo.save(coPollution1);
                    }else {
                        coRepo.save(COPollution.builder()
                                .lat(currentLat)
                                .lon(currentLon)
                                .value(iaqi.getCo() == null ? 0 : iaqi.getCo().getValue())
                                .build());
                    }
                    Optional<NO2Pollution> no2Pollution = no2Repo.findById(tmpId);
                    if(no2Pollution.isPresent()){
                        NO2Pollution no2Pollution1 = no2Pollution.get();
                        no2Pollution1.setValue(iaqi.getNo2() == null ? 0 : iaqi.getNo2().getValue());
                        no2Repo.save(no2Pollution1);
                    } else{
                        no2Repo.save(NO2Pollution.builder()
                                .lat(currentLat)
                                .lon(currentLon)
                                .value(iaqi.getNo2() == null ? 0 : iaqi.getNo2().getValue())
                                .build());
                    }

                    Optional<SO2Pollution> so2Pollution =so2Repo.findById(tmpId);
                    if(so2Pollution.isPresent()){
                        SO2Pollution so2Pollution1 = so2Pollution.get();
                        so2Pollution1.setValue(iaqi.getSo2() == null ? 0 : iaqi.getSo2().getValue());
                        so2Repo.save(so2Pollution1);
                    }else {
                        so2Repo.save(SO2Pollution.builder()
                                .lat(currentLat)
                                .lon(currentLon)
                                .value(iaqi.getSo2() == null ? 0 : iaqi.getSo2().getValue())
                                .build());
                    }

                    Optional<PM25Pollution> pm25Pollution = pm25Repo.findById(tmpId);
                    if(pm25Pollution.isPresent()){
                        PM25Pollution pm25Pollution1 = pm25Pollution.get();
                        pm25Pollution1.setValue(response.getData().getForecast().getDaily().getPm25().get(0).getMax());
                        pm25Repo.save(pm25Pollution1);
                    }else {
                        pm25Repo.save(PM25Pollution.builder()
                                .lat(currentLat)
                                .lon(currentLon)
                                .value(response.getData().getForecast().getDaily().getPm25().get(0).getMax())
                                .build());
                    }

                    Optional<PM10Pollution> pm10Pollution = pm10Repo.findById(tmpId);
                    if(pm10Pollution.isPresent()){
                        PM10Pollution pm10Pollution1 = pm10Pollution.get();
                        pm10Pollution1.setValue(response.getData().getForecast().getDaily().getPm10().get(0).getMax());
                        pm10Repo.save(pm10Pollution1);
                    }else{
                        pm10Repo.save(PM10Pollution.builder()
                                .lat(currentLat)
                                .lon(currentLon)
                                .value(response.getData().getForecast().getDaily().getPm10().get(0).getMax())
                                .build());
                    }

                    Optional<O3Pollution> o3Pollution = o3Repo.findById(tmpId);
                    if(o3Pollution.isPresent()){
                        O3Pollution o3Pollution1 = o3Pollution.get();
                        o3Pollution1.setValue(response.getData().getForecast().getDaily().getO3().get(0).getMax());
                        o3Repo.save(o3Pollution1);
                    }else {
                        o3Repo.save(O3Pollution.builder()
                                .lat(currentLat)
                                .lon(currentLon)
                                .value(response.getData().getForecast().getDaily().getO3().get(0).getMax())
                                .build());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void crawlCo() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> requestBody = new HttpEntity<>(null, new HttpHeaders());

        int kt=0;
        Long tmpId = 0L;
        for (int i = 2; i <= 48; i += 2) {
            for (int j = 2; j <= 48; j += 2) {
                tmpId+=1;
                double currentLat = MIN_LAT + (i - 1) * LAT_EDGE + LAT_EDGE / 2;
                double currentLon = MIN_LONG + (j - 1) * LONG_EDGE + LONG_EDGE / 2;
                String currentUrl = crawlDataUrl.replaceAll("%lat%", String.valueOf(currentLat + (float) (i - 2) / 2))
                        .replaceAll("%lon%", String.valueOf(currentLon + (float) (j - 2) / 2));
                ResponseEntity<String> responseString = restTemplate.exchange(currentUrl, HttpMethod.GET, requestBody, String.class);
                TypeReference<CrawlResponse> reference = new TypeReference<CrawlResponse>() { };
                kt++;
                System.out.println(i+" "+j+" "+kt);
                try {
                    CrawlResponse response = MAPPER.readValue(responseString.getBody(), reference);
                    Iaqi iaqi = response.getData().getIaqi();

//                    coRepo.save(COPollution.builder()
//                            .lat(currentLat)
//                            .lon(currentLon)
//                            .value(iaqi.getCo() == null ? 0 : iaqi.getCo().getValue())
//                            .build());
                    Optional<COPollution> coPollution = coRepo.findById(tmpId);
                    if(coPollution.isPresent()){
                        COPollution coPollution1 = coPollution.get();
                        coPollution1.setValue(iaqi.getCo() == null ? 0 : iaqi.getCo().getValue());
                        coRepo.save(coPollution1);
                    }else {
                        coRepo.save(COPollution.builder()
                                .lat(currentLat)
                                .lon(currentLon)
                                .value(iaqi.getCo() == null ? 0 : iaqi.getCo().getValue())
                                .build());
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
    public void crawlSo2() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> requestBody = new HttpEntity<>(null, new HttpHeaders());
        int kt=0;
        long tmpId =0;
        for (int i = 2; i <= 48; i += 2) {
            for (int j = 2; j <= 48; j += 2) {
                tmpId+=1;
                double currentLat = MIN_LAT + (i - 1) * LAT_EDGE + LAT_EDGE / 2;
                double currentLon = MIN_LONG + (j - 1) * LONG_EDGE + LONG_EDGE / 2;
                String currentUrl = crawlDataUrl.replaceAll("%lat%", String.valueOf(currentLat + (float) (i - 2) / 2))
                        .replaceAll("%lon%", String.valueOf(currentLon + (float) (j - 2) / 2));
                ResponseEntity<String> responseString = restTemplate.exchange(currentUrl, HttpMethod.GET, requestBody, String.class);
                TypeReference<CrawlResponse> reference = new TypeReference<CrawlResponse>() { };
                kt++;
                System.out.println(i+" "+j+" "+kt);
                try {
                    CrawlResponse response = MAPPER.readValue(responseString.getBody(), reference);
                    Iaqi iaqi = response.getData().getIaqi();
                    Optional<SO2Pollution> so2Pollution = so2Repo.findById(tmpId);
                    if(so2Pollution.isPresent()){
                        SO2Pollution so2Pollution1 = so2Pollution.get();
                        so2Pollution1.setValue(iaqi.getSo2() == null ? 0 : iaqi.getSo2().getValue());
                        so2Repo.save(so2Pollution1);
                    }else {
                        so2Repo.save(SO2Pollution.builder()
                                .lat(currentLat)
                                .lon(currentLon)
                                .value(iaqi.getSo2() == null ? 0 : iaqi.getSo2().getValue())
                                .build());
                    }


                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    @Override
    public void crawlTmp() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> requestBody = new HttpEntity<>(null, new HttpHeaders());
        int kt=0;
        Long tmpId=0L;
        for (int i = 2; i <= 48; i += 2) {
            for (int j = 2; j <= 48; j += 2) {
                tmpId+=1;
                double currentLat = MIN_LAT + (i - 1) * LAT_EDGE + LAT_EDGE / 2;
                double currentLon = MIN_LONG + (j - 1) * LONG_EDGE + LONG_EDGE / 2;
                String currentUrl = crawlDataUrl.replaceAll("%lat%", String.valueOf(currentLat + (float) (i - 2) / 2))
                        .replaceAll("%lon%", String.valueOf(currentLon + (float) (j - 2) / 2));
                ResponseEntity<String> responseString = restTemplate.exchange(currentUrl, HttpMethod.GET, requestBody, String.class);
                TypeReference<CrawlResponse> reference = new TypeReference<CrawlResponse>() { };
                kt++;
                System.out.println(i+" "+j+" "+kt);
                try {
                    CrawlResponse response = MAPPER.readValue(responseString.getBody(), reference);
                    Iaqi iaqi = response.getData().getIaqi();
                    Optional<Tmp> tmp = tmpRepo.findById(tmpId);
                    if(tmp.isPresent()){
                            Tmp tmp1 = tmp.get();
                            tmp1.setValue(iaqi.getSo2() == null ? 0 : iaqi.getSo2().getValue());
                            tmpRepo.save(tmp1);
                    }else {
                        tmpRepo.save(Tmp.builder()
                            .lat(currentLat)
                            .lon(currentLon)
                            .value(iaqi.getSo2() == null ? 0 : iaqi.getSo2().getValue())
                            .build());
                    }
//                    tmpRepo.save(Tmp.builder()
//                            .lat(currentLat)
//                            .lon(currentLon)
//                            .value(iaqi.getSo2() == null ? 0 : iaqi.getSo2().getValue())
//                            .build());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}