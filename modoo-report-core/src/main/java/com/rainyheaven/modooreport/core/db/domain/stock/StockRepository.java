package com.rainyheaven.modooreport.core.db.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByIsinCode(String isinCode);

}
