package com.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    public Stock findByStockName(String stockName);

    public Stock findByStockTicker(String stockTicker);
}
