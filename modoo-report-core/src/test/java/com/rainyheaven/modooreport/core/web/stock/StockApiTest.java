package com.rainyheaven.modooreport.core.web.stock;

import com.rainyheaven.modooreport.core.web.config.WebConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfig.class})
@TestPropertySource(locations = {"classpath:/application-core-web.yml"}, properties = {"seibro.service-key=2odLNTxW1kr0Q1CzQ0h3vITUowIGxiVsdBQntIx2JkP8Rl7Urig5yh3XBiUvM5cdLaSs2plzadKFbPUHhcLT+w=="})
class StockApiTest {

    @Autowired
    private StockApi stockApi;

    @DisplayName("주식 조회 테스트")
    @Test
    void fetchStock() {
        Object o = stockApi.fetchStock("삼성전자");
        System.out.println(o);
    }
}