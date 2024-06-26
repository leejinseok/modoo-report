package com.rainyheaven.modooreport.api.application.config;

import com.rainyheaven.modooreport.core.db.config.CoreDbConfig;
import com.rainyheaven.modooreport.core.db.config.CoreQueryDslConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreDbConfig.class, CoreQueryDslConfig.class})
public class ApiDbConfig {
}
