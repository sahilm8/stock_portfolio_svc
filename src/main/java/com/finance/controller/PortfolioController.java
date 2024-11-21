package com.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.model.Portfolio;
import com.finance.service.PortfolioService;

@RestController
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        return String.format(
                "Stock Portfolio Service%n%n" +
                        "Welcome to the portfolios endpoint, you can make the following requests:%n" +
                        "- POST /new-portfolio%n");
    }

    @PostMapping(value = "/new-portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPortfolio(
            @RequestBody Portfolio portfolio) {
        return portfolioService.createPortfolio(portfolio);
    }
}
