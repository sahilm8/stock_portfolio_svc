package com.finance.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.model.Portfolio;
import com.finance.repository.PortfolioRepository;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    public String createPortfolio(Portfolio portfolio) {
        if (!portfolioRepository.findByPortfolioName(portfolio.getPortfolioName()).isPresent()) {
            portfolio.setPortfolioValue(
                    portfolio.getStocks().stream()
                            .map(stock -> stock.getStockPrice().multiply(stock.getStockQuantity()))
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
            portfolioRepository.save(portfolio);
            return "Portfolio created successfully.";
        }
        return "Portfolio with the given name already exists.";
    }
}
