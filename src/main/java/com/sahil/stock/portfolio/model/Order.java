package com.sahil.stock.portfolio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import com.sahil.stock.portfolio.util.Currency;
import com.sahil.stock.portfolio.util.OrderStatus;
import com.sahil.stock.portfolio.util.OrderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String orderType = OrderType.BUY.getValue();

    @Column(nullable = false)
    private String currency = Currency.USD.getValue();

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalValue = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal totalNumberOfStocks = BigDecimal.ZERO;
}
