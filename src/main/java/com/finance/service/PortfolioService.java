package com.finance.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finance.model.Portfolio;
import com.finance.repository.PortfolioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
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
            log.info("Portfolio created: " + portfolio.toString());
            return "Portfolio created successfully.";
        }
        log.info("Portfolio already exists: " + portfolio.toString());
        return "Portfolio with the given name already exists.";
    }
}
