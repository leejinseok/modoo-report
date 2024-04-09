package com.rainyheaven.modooreport.batch.domain.stock;

import com.rainyheaven.modooreport.core.db.domain.stock.Stock;
import com.rainyheaven.modooreport.core.web.stock.StockResponseDto;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StockJobConfiguration {


    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final EntityManagerFactory entityManagerFactory;
    private final StockItemReader stockItemReader;
    private final StockItemProcessor stockItemProcessor;

    @Bean
    public Job fetchStockJob() {
        return new JobBuilder("fetchStockJob", jobRepository)
                .start(fetchStockTaskletStep())
                .build();
    }

    @Bean
    public Step fetchStockTaskletStep() {
        return new StepBuilder("fetchStockTaskletStep", jobRepository)
                .<StockResponseDto.ItemDto, Stock>chunk(10, platformTransactionManager)
                .reader(stockItemReader)
                .processor(stockItemProcessor)
                .writer(fetchStockJpaItemWriter())
                .build();
    }

    @Bean
    public JpaItemWriter<Stock> fetchStockJpaItemWriter() {
        return new JpaItemWriterBuilder<Stock>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

}
