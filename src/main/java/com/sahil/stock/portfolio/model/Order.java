package com.sahil.stock.portfolio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.sahil.stock.portfolio.util.OrderStatus;
import com.sahil.stock.portfolio.util.Currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
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
    private String status = OrderStatus.CREATED.getValue();

    @Column(nullable = false)
    private String currency = Currency.USD.getValue();

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalValue = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalNumberOfStocks = BigDecimal.ZERO;

    @ManyToMany
    @JoinTable(
        name = "order_stocks",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private Set<Stock> stocks = new HashSet<>();
}
