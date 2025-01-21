package com.sahil.stock.portfolio.dto.addPortfolio;

import com.sahil.stock.portfolio.model.Portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPortfolioResponse {
    private Portfolio portfolio;
}
