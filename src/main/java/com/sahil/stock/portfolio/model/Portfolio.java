package com.sahil.stock.portfolio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.sahil.stock.portfolio.util.Currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @Column(nullable = false)
    private String currency = Currency.USD.getValue();

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalValue = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer totalNumberOfCompanies = 0;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalNumberOfStocks = BigDecimal.ZERO;

    @OneToMany(mappedBy = "portfolio")
    private List<Stock> stocks = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio")
    private List<Order> orders = new ArrayList<>();
}
