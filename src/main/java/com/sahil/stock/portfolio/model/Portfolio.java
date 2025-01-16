package com.sahil.stock.portfolio.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal amount;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
