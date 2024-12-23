package com.sahil.stock.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahil.stock.portfolio.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    public Optional<Stock> findByName(String name);
}
