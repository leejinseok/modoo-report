package com.rainyheaven.modooreport.core.web.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class StockApi {

    private final RestTemplate restTemplate;
    private final String serviceKey;

    public StockApi(
            final RestTemplate restTemplate,
            @Value("${fsc.service-key}") final String serviceKey
    ) {
        this.restTemplate = restTemplate;
        this.serviceKey = serviceKey;
    }

    public StockResponseDto fetchStock(final String query) {
        String queryEncoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String serviceKeyEncoded = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder("https://apis.data.go.kr/1160100/service/GetStocIssuInfoService_V2/getItemBasiInfo_V2");
        builder.append("?serviceKey=").append(serviceKeyEncoded);
        builder.append("&pageNo=1");
        builder.append("&numOfRow=1");
        builder.append("&resultType=json");
        builder.append("&stckIssuCmpyNm=").append(queryEncoded);
        try {
            ResponseEntity<StockResponseDto> forEntity = restTemplate.getForEntity(builder.toString(), StockResponseDto.class);
            return forEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
