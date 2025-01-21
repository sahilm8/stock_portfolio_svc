package com.sahil.stock.portfolio.dto.getStock;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStockResponse {
    private String symbol;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal price;
    private BigDecimal volume;
    private String latestTradingDay;
    private BigDecimal previousClose;
    private BigDecimal change;
    private BigDecimal changePercent;
}
