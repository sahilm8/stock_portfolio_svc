package com.sahil.stock.portfolio.dto;

import com.sahil.stock.portfolio.model.Portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPortfolioResponse {
    private Portfolio portfolio;
}
