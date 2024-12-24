package com.sahil.stock.portfolio.util;

public enum OrderStatus {
    CREATED("CREATED"),
    PENDING("PENDING§"),
    COMPLETED("COMPLETED");
    
    private final String value;
    
    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
