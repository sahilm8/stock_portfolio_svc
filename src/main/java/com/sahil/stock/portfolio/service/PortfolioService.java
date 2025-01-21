package com.sahil.stock.portfolio.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sahil.stock.portfolio.dto.addPortfolio.AddPortfolioRequest;
import com.sahil.stock.portfolio.dto.addPortfolio.AddPortfolioResponse;
import com.sahil.stock.portfolio.dto.deletePortfolio.DeletePortfolioRequest;
import com.sahil.stock.portfolio.dto.deletePortfolio.DeletePortfolioResponse;
import com.sahil.stock.portfolio.dto.getPortfolio.GetPortfolioRequest;
import com.sahil.stock.portfolio.dto.getPortfolio.GetPortfolioResponse;
import com.sahil.stock.portfolio.exception.PortfolioAlreadyExistsException;
import com.sahil.stock.portfolio.exception.PortfolioNotFoundException;
import com.sahil.stock.portfolio.model.Portfolio;
import com.sahil.stock.portfolio.repository.PortfolioRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public AddPortfolioResponse addPortfolio(AddPortfolioRequest addPortfolioRequest) {
        if (!portfolioRepository.existsByName(addPortfolioRequest.getName())) {
            Portfolio portfolio = Portfolio.builder()
                    .name(addPortfolioRequest.getName())
                    .description(addPortfolioRequest.getDescription())
                    .currency(addPortfolioRequest.getCurrency())
                    .amount(java.math.BigDecimal.ZERO)
                    .stocks(new ArrayList<>())
                    .transactions(new ArrayList<>())
                    .build();
            Portfolio savedPortfolio = portfolioRepository.save(portfolio);
            return AddPortfolioResponse.builder().portfolio(savedPortfolio).build();
        }
        throw new PortfolioAlreadyExistsException("Portfolio already exists");
    }

    public GetPortfolioResponse getPortfolio(GetPortfolioRequest getPortfolioRequest) {
        if (portfolioRepository.existsByName(getPortfolioRequest.getName())) {
            Portfolio portfolio = portfolioRepository.findByName(getPortfolioRequest.getName()).get();
            return GetPortfolioResponse.builder().portfolio(portfolio).build();
        }
        throw new PortfolioNotFoundException("Portfolio not found");
    }

    public DeletePortfolioResponse deletePortfolio(DeletePortfolioRequest deletePortfolioRequest) {
        if (portfolioRepository.existsByName(deletePortfolioRequest.getName())) {
            Portfolio portfolio = portfolioRepository.findByName(deletePortfolioRequest.getName()).get();
            portfolioRepository.delete(portfolio);
            return DeletePortfolioResponse.builder().status("Portfolio deleted successfully").build();
        }
        throw new PortfolioNotFoundException("Portfolio not found");
    }
}
