package com.sahil.stock.portfolio.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.stock.portfolio.dto.addPortfolio.AddPortfolioRequest;
import com.sahil.stock.portfolio.dto.addPortfolio.AddPortfolioResponse;
import com.sahil.stock.portfolio.dto.deletePortfolio.DeletePortfolioRequest;
import com.sahil.stock.portfolio.dto.deletePortfolio.DeletePortfolioResponse;
import com.sahil.stock.portfolio.dto.getPortfolio.GetPortfolioRequest;
import com.sahil.stock.portfolio.dto.getPortfolio.GetPortfolioResponse;
import com.sahil.stock.portfolio.service.PortfolioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/portfolio")
@RequiredArgsConstructor
@Validated
public class PortfolioController {
    private final PortfolioService portfolioService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> home() {
        return ResponseEntity.ok(String.format(
                "Stock Portfolio API%n%n" +
                        "Welcome to the /portfolio endpoint, you can make the following requests:%n" +
                        "- POST /add-portfolio%n" +
                        "- GET /get-portfolio%n" +
                        "- DELETE /delete-portfolio%n"));
    }

    @PostMapping(value = "/add-portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddPortfolioResponse> addPortfolio(
            @Valid @RequestBody AddPortfolioRequest addPortfolioRequest) {
        return ResponseEntity.ok(portfolioService.addPortfolio(addPortfolioRequest));
    }

    @GetMapping(value = "/get-portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetPortfolioResponse> getPortfolio(
            @Valid @RequestBody GetPortfolioRequest getPortfolioRequest) {
        return ResponseEntity.ok(portfolioService.getPortfolio(getPortfolioRequest));
    }

    @DeleteMapping(value = "/delete-portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeletePortfolioResponse> deletePortfolio(
            @Valid @RequestBody DeletePortfolioRequest deletePortfolioRequest) {
        return ResponseEntity.ok(portfolioService.deletePortfolio(deletePortfolioRequest));
    }

    @GetMapping(value = "/*", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> invalid() {
        return ResponseEntity
                .ok("Invalid request, please refer to the README at https://github.com/sahilm8/stock_portfolio_svc");
    }
}
