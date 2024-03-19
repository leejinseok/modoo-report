package com.rainyheaven.modooreport.core.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.rainyheaven.modooreport.core.db.domain")
@EnableJpaAuditing
@EntityScan(basePackages = "com.rainyheaven.modooreport.core.db.domain")
public class CoreJpaConfig {

}
