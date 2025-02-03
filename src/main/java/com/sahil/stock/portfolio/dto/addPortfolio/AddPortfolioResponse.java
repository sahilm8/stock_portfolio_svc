package com.sahil.stock.portfolio.dto.addPortfolio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPortfolioResponse {
    private Long id;
    private Date createdAt;
    private String name;
    private String description;
    private String currency;
    private BigDecimal amount;
    private User user;
    private List<Stock> stocks;
    private List<Transaction> transactions;

    @Data
    @Builder
    public static class User {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String createdAt;
        private String updatedAt;
    }

    @Data
    @Builder
    public static class Stock {
        private Long id;
        private String createdAt;
        private String symbol;
        private String currency;
        private String price;
        private String open;
        private String high;
        private String low;
        private String close;
        private String volume;
    }

    @Data
    @Builder
    public static class Transaction {
        private Long id;
        private String createdAt;
        private String currency;
        private String amount;
    }
}
