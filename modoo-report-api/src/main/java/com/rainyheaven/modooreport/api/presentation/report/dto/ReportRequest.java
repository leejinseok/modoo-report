package com.rainyheaven.modooreport.api.presentation.report.dto;


import com.rainyheaven.modooreport.core.db.domain.report.Recommended;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
public class ReportRequest {

    private String title;
    private String content;
    private BigDecimal targetPrice;
    private Recommended recommended;

}
