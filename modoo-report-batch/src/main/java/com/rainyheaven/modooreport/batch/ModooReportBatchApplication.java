package com.rainyheaven.modooreport.batch;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ModooReportBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModooReportBatchApplication.class, args);
    }
}
