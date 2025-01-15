package com.sahil.stock.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahil.stock.portfolio.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    public Optional<Portfolio> findByName(String name);

    public boolean existsByName(String name);
}
