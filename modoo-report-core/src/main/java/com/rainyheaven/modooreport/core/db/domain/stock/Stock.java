package com.rainyheaven.modooreport.core.db.domain.stock;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String stockIssueCompanyName;

    @Column(nullable = false)
    private String isinCodeName;

    @Column(nullable = false, unique = true)
    private String isinCode;

    public static Stock create(final String stockIssueCompanyName, final String isinCodeName, final String isinCode) {
        Stock stock = new Stock();
        stock.stockIssueCompanyName = stockIssueCompanyName;
        stock.isinCodeName = isinCodeName;
        stock.isinCode = isinCode;
        return stock;
    }


}
