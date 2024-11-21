package com.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.model.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    public Portfolio findByPortfolioName(String portfolioName);
}
