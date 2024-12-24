package com.sahil.stock.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.stock.portfolio.model.Portfolio;
import com.sahil.stock.portfolio.service.PortfolioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/portfolio")
@Slf4j
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        log.info("Received request to GET /.");
        return String.format(
                "Stock Portfolio API%n%n" +
                        "Welcome to the portfolio endpoint, you can make the following requests:%n" +
                        "- POST /add-portfolio%n" +
                        "- GET /get-portfolio%n" +
                        "- DELETE /delete-portfolio%n");
    }

    @PostMapping(value = "/add-portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Portfolio> addPortfolio(@RequestParam String name, @RequestParam String desc) {
        log.info("Received request to POST /add-portfolio with arguments: " + name.trim() + ", " + desc.trim());
        Portfolio response = portfolioService.addPortfolio(name.trim(), desc.trim());
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/get-portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Portfolio> getPortfolio(@RequestParam String name) {
        log.info("Received request to GET /get-portfolio with argument: " + name.trim());
        Portfolio portfolio = portfolioService.getPortfolio(name.trim());
        if (portfolio != null) {
            return ResponseEntity.ok(portfolio);            
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value = "/delete-portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePortfolio(@RequestParam String name) {
        log.info("Received request to DELETE /delete-portfolio with argument: " + name.trim());
        boolean response = portfolioService.deletePortfolio(name.trim());
        if (response) {
            return ResponseEntity.ok("Portfolio deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
