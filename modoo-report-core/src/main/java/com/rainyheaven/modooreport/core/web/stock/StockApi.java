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

    public StockResponseDto fetchStock(final String query, final int numOfRows, final String baseDt) {
        String queryEncoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String serviceKeyEncoded = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8);
        String builder = "https://apis.data.go.kr/1160100/service/GetStocIssuInfoService_V2/getItemBasiInfo_V2?serviceKey=" + serviceKeyEncoded +
                "&pageNo=1" +
                "&numOfRows=" + numOfRows +
                "&baseDt=" + baseDt +
                "&resultType=json" +
                "&stckIssuCmpyNm=" + queryEncoded;
        ResponseEntity<StockResponseDto> forEntity = restTemplate.getForEntity(
                builder, StockResponseDto.class);
        return forEntity.getBody();
    }

}
