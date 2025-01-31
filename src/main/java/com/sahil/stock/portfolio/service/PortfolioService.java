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
import com.sahil.stock.portfolio.model.User;
import com.sahil.stock.portfolio.repository.PortfolioRepository;
import com.sahil.stock.portfolio.repository.UserRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;
    private final JwtService jwtService;

    public AddPortfolioResponse addPortfolio(AddPortfolioRequest addPortfolioRequest) {
        if (!portfolioRepository.existsByName(addPortfolioRequest.getName())) {
            String authHeader = httpServletRequest.getHeader("Authorization");
            String token = authHeader.substring(7);
            String email = jwtService.extractClaim(token, Claims::getSubject);

            User user = userRepository.findByEmail(email).get();

            Portfolio portfolio = Portfolio.builder()
                    .name(addPortfolioRequest.getName())
                    .description(addPortfolioRequest.getDescription())
                    .currency(addPortfolioRequest.getCurrency())
                    .amount(java.math.BigDecimal.ZERO)
                    .user(user)
                    .stocks(new ArrayList<>())
                    .transactions(new ArrayList<>())
                    .build();

            Portfolio savedPortfolio = portfolioRepository.save(portfolio);
            return AddPortfolioResponse
                    .builder()
                    .id(savedPortfolio.getId())
                    .createdAt(savedPortfolio.getCreatedAt())
                    .name(savedPortfolio.getName())
                    .description(savedPortfolio.getDescription())
                    .currency(savedPortfolio.getCurrency())
                    .amount(savedPortfolio.getAmount())
                    .user(savedPortfolio.getUser())
                    .stocks(savedPortfolio.getStocks())
                    .transactions(savedPortfolio.getTransactions())
                    .build();
        }
        throw new PortfolioAlreadyExistsException("Portfolio already exists");
    }

    public GetPortfolioResponse getPortfolio(GetPortfolioRequest getPortfolioRequest) {
        if (portfolioRepository.existsByName(getPortfolioRequest.getName())) {
            Portfolio portfolio = portfolioRepository.findByName(getPortfolioRequest.getName()).get();
            return GetPortfolioResponse
                    .builder()
                    .id(portfolio.getId())
                    .createdAt(portfolio.getCreatedAt())
                    .name(portfolio.getName())
                    .description(portfolio.getDescription())
                    .currency(portfolio.getCurrency())
                    .amount(portfolio.getAmount())
                    .user(portfolio.getUser())
                    .stocks(portfolio.getStocks())
                    .transactions(portfolio.getTransactions())
                    .build();
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
