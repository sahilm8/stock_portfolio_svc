package com.sahil.stock.portfolio.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.stock.portfolio.model.Order;
import com.sahil.stock.portfolio.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        log.info("Received request to GET /.");
        return String.format(
                "Stock Portfolio API%n%n" +
                        "Welcome to the order endpoint, you can make the following requests:%n" +
                        "- POST /add-order%n" +
                        "- GET /get-last-order%n");
    }

    @PostMapping(value = "/add-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> addOrder(
        @RequestParam String portfolioName,
        @RequestParam String orderType,
        @RequestParam String symbol,
        @RequestParam String currency,
        @RequestParam String totalValue
    ) {
        if (
            portfolioName.isEmpty() ||
            orderType.isEmpty() ||
            symbol.isEmpty() ||
            currency.isEmpty() ||
            new BigDecimal(totalValue.trim()).compareTo(BigDecimal.ZERO) <= 0
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        log.info(
            "Received request to POST /add-order with arguments: " +
            portfolioName.trim() + ", " +
            orderType.trim() + ", " +
            symbol.trim() + ", " +
            currency.trim() + ", " +
            totalValue.trim()
        );
        
        Order response = orderService.addOrder(
            portfolioName.trim(),
            orderType.trim().toUpperCase(),
            symbol.trim().toUpperCase(),
            currency.trim().toUpperCase(),
            totalValue.trim()
            );
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();    
    }

    @GetMapping(value = "/get-last-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getLastOrder(@RequestParam String portfolioName) {
        if (portfolioName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        log.info("Received request to GET /get-last-order with argument: " + portfolioName.trim());
        Order order = orderService.getLastOrder(portfolioName.trim());
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
