package com.sahil.stock.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeletePortfolioRequest {
    @NotBlank(message = "Name is required")
    private String name;
}
