package com.example.da_ari_pollution.schedule;

import com.example.da_ari_pollution.common.AESUtil;
import com.example.da_ari_pollution.common.JsonMapper;
import com.example.da_ari_pollution.entity.Token;
import com.example.da_ari_pollution.service.TokenService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Log4j2
@Component
public class UpdateArcGisTokenJob {

    @Value("${arcgis.getToken.param.value.clientId}")
    private String clientIdValue;

    @Value("${arcgis.getToken.param.value.clientSecret}")
    private String clientSecretValue;

    @Value("${arcgis.getToken.param.value.grantType}")
    private String grantTypeValue;

    @Value("${arcgis.getToken.param.key.clientId}")
    private String clientIdKey;

    @Value("${arcgis.getToken.param.key.clientSecret}")
    private String clientSecretKey;

    @Value("${arcgis.getToken.param.key.grantType}")
    private String grantTypeKey;

    @Value("${arcgis.getToken.Url}")
    private String url;

    @Value("${aes.secretKey}")
    private String aesKey;

    private static final String accessTokenKey = "access_token";

    @Autowired
    private TokenService tokenService;

    @Scheduled(fixedRate = 90 * 1000 * 60)
    public void updateArcGisToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        MultiValueMap<String, String> addRequestInformation = new LinkedMultiValueMap<>();
        addRequestInformation.add(clientIdKey, clientIdValue);
        addRequestInformation.add(clientSecretKey, clientSecretValue);
        addRequestInformation.add(grantTypeKey, grantTypeValue);
        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<>(addRequestInformation, httpHeaders);
        //get new token
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestBody, String.class);
        LinkedHashMap<String, Object> responseMap = JsonMapper.convertJsonToObject(responseEntity.getBody(),
                new TypeReference<LinkedHashMap<String, Object>>() {});
        String newToken = (String) responseMap.get(accessTokenKey);
        //encrypt save new token
        String newTokenEncrypted = AESUtil.encrypt(newToken, aesKey);
        List<Token> tokens = tokenService.findAll();
        if (ObjectUtils.isEmpty(tokens)) {
            Token token = Token.builder().value(newTokenEncrypted).build();
            tokenService.save(token);
        } else {
//            tokens.get(0).setValue(newTokenEncrypted);
//            tokenService.saveAll(tokens);
              Token token = tokens.get(0);
              token.setValue(newTokenEncrypted);
              tokenService.save(token);
        }

//        log.info("update arc gis token");
//        log.info("updated new arGis token");
    }
}
