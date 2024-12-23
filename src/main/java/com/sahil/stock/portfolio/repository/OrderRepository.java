package com.sahil.stock.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahil.stock.portfolio.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Optional<Order> findByName(String name);
}
