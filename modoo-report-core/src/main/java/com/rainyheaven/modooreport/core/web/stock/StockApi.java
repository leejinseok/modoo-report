package com.rainyheaven.modooreport.core.web.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class StockApi {

    private final RestTemplate restTemplate;
    private final String serviceKey;

    public StockApi(
            final RestTemplate restTemplate,
            @Value("${seibro.service-key}") final String serviceKey
    ) {
        this.restTemplate = restTemplate;
        this.serviceKey = serviceKey;
    }


    public Object fetchStock(final String query) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://api.seibro.or.kr/openapi/service/StockSvc/getStkIsinByNmN1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("secnNm", query)
                .queryParam("numOfRows", 10)
                .queryParam("pageNo", 1)
                .build()
                .toUri();
        return restTemplate.getForObject(uri, Object.class);
    }

}
