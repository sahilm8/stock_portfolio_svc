package com.finance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    public Optional<Portfolio> findByPortfolioName(String portfolioName);
}
