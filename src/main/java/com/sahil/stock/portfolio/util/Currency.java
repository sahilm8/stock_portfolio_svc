package com.sahil.stock.portfolio.util;

public enum Currency {
    USD("USD"),
    GBP("GBP"),
    CAD("CAD"),
    AUD("AUD"),
    EUR("EUR"),
    INR("INR");
    
    private final String value;
    
    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
