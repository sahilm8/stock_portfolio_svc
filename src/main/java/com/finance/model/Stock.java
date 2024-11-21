package com.finance.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stocks")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false)
    private String stockTicker;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal stockPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal stockQuantity;

    @ManyToMany(mappedBy = "stocks")
    private List<Portfolio> portfolios;
}
