package com.sahil.stock.portfolio.dto.getStock;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStockRequest {
    @NotBlank(message = "Symbol is required")
    private String symbol;
}
