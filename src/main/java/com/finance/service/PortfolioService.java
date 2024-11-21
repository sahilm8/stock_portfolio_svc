package com.finance.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finance.model.Portfolio;
import com.finance.repository.PortfolioRepository;

@Service
@Transactional
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    public String createPortfolio(Portfolio portfolio) {
        // TODO: Add current user based on JWT authentication.
        if (portfolioRepository.findByPortfolioName(portfolio.getPortfolioName()).isEmpty()) {
            portfolio.setPortfolioValue(
                    portfolio.getStocks().stream()
                            .map(stock -> stock.getStockPrice())
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
            portfolioRepository.save(portfolio);
            return "Portfolio created successfully.";
        }
        return "Portfolio with the given name already exists.";
    }
}
