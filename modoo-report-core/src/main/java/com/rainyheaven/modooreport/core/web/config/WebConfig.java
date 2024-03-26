package com.rainyheaven.modooreport.core.web.config;

import com.rainyheaven.modooreport.core.web.stock.StockApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({StockApi.class})
public class WebConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
