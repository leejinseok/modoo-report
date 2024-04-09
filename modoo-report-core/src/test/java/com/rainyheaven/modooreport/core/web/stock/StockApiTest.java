package com.rainyheaven.modooreport.core.web.stock;

import com.rainyheaven.modooreport.core.web.config.CoreWebConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CoreWebConfig.class})
@TestPropertySource(locations = {"classpath:/application-core-web.yml"}, properties = {"fsc.service-key=2odLNTxW1kr0Q1CzQ0h3vITUowIGxiVsdBQntIx2JkP8Rl7Urig5yh3XBiUvM5cdLaSs2plzadKFbPUHhcLT+w=="})
class StockApiTest {

    @Autowired
    private StockApi stockApi;

    @DisplayName("주식 조회 테스트")
    @Test
    void fetchStock() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String baseDt = currentDate.format(formatter);

        StockResponseDto responseDto = stockApi.fetchStock("에이프로", 100, baseDt);
        System.out.println(responseDto);
    }

}