package com.sahil.stock.portfolio.dto.getMonthlyTs;

import java.math.BigDecimal;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMonthlyTsResponse {
    private Map<String, TimeSeries> timeSeries;

    @Data
    public static class TimeSeries {
        private BigDecimal open;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal close;
        private BigDecimal adjustedClose;
        private BigDecimal volume;
        private BigDecimal dividendAmount;
    }
}
