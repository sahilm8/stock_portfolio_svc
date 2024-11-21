package com.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    public Stock findByStockName(String stockName);

    public Stock findByStockTicker(String stockTicker);
}
