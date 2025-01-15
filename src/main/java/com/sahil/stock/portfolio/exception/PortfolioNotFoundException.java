package com.sahil.stock.portfolio.exception;

public class PortfolioNotFoundException extends RuntimeException {
    public PortfolioNotFoundException(String message) {
        super(message);
    }
}
