package com.finance.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "portfolios")
@Data
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String portfolioName;

    @Column(nullable = false)
    private String portfolioDescription;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    @JoinTable(name = "portfolio_stocks", joinColumns = @JoinColumn(name = "portfolio_id"), inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<Stock> stocks;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal portfolioValue;
}
