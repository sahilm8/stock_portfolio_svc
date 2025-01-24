package com.sahil.stock.portfolio.model;

import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "portfolio_stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "price", nullable = false, precision = 10, scale = 4)
    private BigDecimal price;

    @Column(name = "open", nullable = false, precision = 10, scale = 4)
    private BigDecimal open;

    @Column(name = "high", nullable = false, precision = 10, scale = 4)
    private BigDecimal high;

    @Column(name = "low", nullable = false, precision = 10, scale = 4)
    private BigDecimal low;

    @Column(name = "close", nullable = false, precision = 10, scale = 4)
    private BigDecimal close;

    @Column(name = "volume", nullable = false, precision = 10, scale = 4)
    private BigDecimal volume;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;
}
