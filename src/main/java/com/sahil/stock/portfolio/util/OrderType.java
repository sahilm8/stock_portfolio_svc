package com.sahil.stock.portfolio.util;

public enum OrderType {
    BUY("BUY"),
    SELL("SELL");
    
    private final String value;
    
    OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
