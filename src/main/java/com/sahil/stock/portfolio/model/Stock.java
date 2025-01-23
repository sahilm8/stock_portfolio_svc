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
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal open;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal high;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal low;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal close;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal volume;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;
}
