package com.sahil.stock.portfolio.dto.deletePortfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeletePortfolioResponse {
    private String status;
}
