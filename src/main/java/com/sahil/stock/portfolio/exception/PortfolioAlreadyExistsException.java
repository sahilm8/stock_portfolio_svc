package com.sahil.stock.portfolio.exception;

public class PortfolioAlreadyExistsException extends RuntimeException {
    public PortfolioAlreadyExistsException(String message) {
        super(message);
    }
}
