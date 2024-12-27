package com.sahil.stock.portfolio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahil.stock.portfolio.model.Order;
import com.sahil.stock.portfolio.model.Portfolio;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPortfolioOrderByCreatedAtDesc(Portfolio portfolio);
}
