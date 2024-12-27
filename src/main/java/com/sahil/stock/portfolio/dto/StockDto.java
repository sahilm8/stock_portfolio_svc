package com.sahil.stock.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockDto {
    /*
    API Response:
    {
        "symbol": "AAPL",
        "open": 255.4900,
        "high": 258.2100,
        "low": 255.2900,
        "price": 258.2000,
        "volume": 23234705,
        "latestTradingDay": "2024-12-24",
        "prevClose": 255.2700,
        "change": 2.9300,
        "changePercent": 1.1478
    }
    */
    
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("open")
    private String open;

    @JsonProperty("high")
    private String high;

    @JsonProperty("low")
    private String low;

    @JsonProperty("price")
    private String price;

    @JsonProperty("volume")
    private String volume;

    @JsonProperty("latestTradingDay")
    private String latestTradingDay;

    @JsonProperty("prevClose")
    private String prevClose;

    @JsonProperty("change")
    private String change;

    @JsonProperty("changePercent")
    private String changePercent;
}
