package com.sahil.stock.portfolio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalValue = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer totalNumberOfCompanies = 0;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalNumberOfStocks = BigDecimal.ZERO;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<Stock> stocksInOrder;
}
