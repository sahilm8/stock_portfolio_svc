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

    public Portfolio addPortfolio(String name, String desc, String currency) {
        if (portfolioRepository.findByName(name).isEmpty()) {
            Portfolio portfolio = new Portfolio();
            portfolio.setName(name);
            portfolio.setDescription(desc);
            portfolio.setCurrency(currency);
            portfolioRepository.save(portfolio);
            log.info("Portfolio created: " + portfolio.toString());
            return portfolio; 
        }
        Portfolio portfolio = portfolioRepository.findByName(name).get();
        log.info("Portfolio already exists: " + portfolio.toString());
        return null;
    }

    public Portfolio getPortfolio(String name) {
        if (portfolioRepository.findByName(name).isPresent()) {
            Portfolio portfolio = portfolioRepository.findByName(name).get();
            log.info("Portfolio found: " + portfolio.toString());
            return portfolio;
        }
        log.info("Portfolio not found: " + name);
        return null;
    }

    public boolean deletePortfolio(String name) {
        if (portfolioRepository.findByName(name).isPresent()) {
            Portfolio portfolio = portfolioRepository.findByName(name).get();
            portfolioRepository.delete(portfolio);
            log.info("Portfolio deleted: " + name);
            return true;
        }
        log.info("Portfolio not found: " + name);
        return false;
    }
}
