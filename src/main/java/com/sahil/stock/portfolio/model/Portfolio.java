package com.sahil.stock.portfolio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Table(name = "portfolios")
@Data
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalValue = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer totalNumberOfCompanies = 0;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalNumberOfStocks = BigDecimal.ZERO;

    @ManyToMany
    @JoinTable(
        name = "portfolio_stocks",
        joinColumns = @JoinColumn(name = "portfolio_id"),
        inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private Set<Stock> stocks = new HashSet<>();
}
