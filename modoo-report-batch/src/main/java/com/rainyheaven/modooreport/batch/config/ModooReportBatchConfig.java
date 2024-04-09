package com.rainyheaven.modooreport.batch.config;

import com.rainyheaven.modooreport.core.db.config.CoreDbConfig;
import com.rainyheaven.modooreport.core.web.config.CoreWebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreWebConfig.class, CoreDbConfig.class})
public class ModooReportBatchConfig {
}
