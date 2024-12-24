package com.sahil.stock.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahil.stock.portfolio.model.Order;
import com.sahil.stock.portfolio.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order addOrder(Order order) {
        orderRepository.save(order);
        log.info("Order created: " + order.toString());
        return order;
    }

    public Order getLastOrder() {
        Order order = orderRepository.findAll().get(orderRepository.findAll().size() - 1);
        if (order != null) {
            log.info("Order found: " + order.toString());
            return order;
        }
        log.info("Order not found.");
        return null;
    }   
}
