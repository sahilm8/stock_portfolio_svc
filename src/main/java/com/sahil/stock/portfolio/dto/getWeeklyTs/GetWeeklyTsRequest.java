package com.sahil.stock.portfolio.dto.getWeeklyTs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWeeklyTsRequest {
    @NotBlank(message = "Symbol is required")
    private String symbol;
}
