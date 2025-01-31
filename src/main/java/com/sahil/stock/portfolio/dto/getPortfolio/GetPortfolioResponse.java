package com.sahil.stock.portfolio.dto.getPortfolio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sahil.stock.portfolio.model.Stock;
import com.sahil.stock.portfolio.model.Transaction;
import com.sahil.stock.portfolio.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPortfolioResponse {
    private Long id;
    private Date createdAt;
    private String name;
    private String description;
    private String currency;
    private BigDecimal amount;
    private User user;
    private List<Stock> stocks;
    private List<Transaction> transactions;
}
