package com.sahil.stock.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahil.stock.portfolio.model.Portfolio;
import com.sahil.stock.portfolio.repository.PortfolioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    public Portfolio addPortfolio(Portfolio portfolio) {
        if (portfolioRepository.findByName(portfolio.getName()).isEmpty()) {
            portfolioRepository.save(portfolio);
            log.info("Portfolio created: " + portfolio.toString());
            return portfolio;
        }
        log.info("Portfolio already exists: " + portfolio.toString());
        return null;
    }
}
