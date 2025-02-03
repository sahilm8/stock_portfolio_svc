package com.sahil.stock.portfolio.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
                    .user(
                        AddPortfolioResponse.User.builder()
                        .id(savedPortfolio.getUser().getId())
                        .firstName(savedPortfolio.getUser().getFirstName())
                        .lastName(savedPortfolio.getUser().getLastName())
                        .email(savedPortfolio.getUser().getEmail())
                        .createdAt(savedPortfolio.getUser().getCreatedAt().toString())
                        .updatedAt(savedPortfolio.getUser().getUpdatedAt().toString())
                        .build()
                    )
                    .stocks(
                        savedPortfolio.getStocks()
                        .stream()
                        .map(stock -> {
                            return AddPortfolioResponse.Stock.builder()
                            .id(stock.getId())
                            .createdAt(stock.getCreatedAt().toString())
                            .symbol(stock.getSymbol())
                            .currency(stock.getCurrency())
                            .price(stock.getPrice().toString())
                            .open(stock.getOpen().toString())
                            .high(stock.getHigh().toString())
                            .low(stock.getLow().toString())
                            .close(stock.getClose().toString())
                            .volume(stock.getVolume().toString())
                            .build();
                        })
                        .collect(Collectors.toList())
                    )
                    .transactions(
                        savedPortfolio.getTransactions()
                        .stream()
                        .map(transaction -> {
                            return AddPortfolioResponse.Transaction.builder()
                            .id(transaction.getId())
                            .createdAt(transaction.getCreatedAt().toString())
                            .currency(transaction.getCurrency())
                            .amount(transaction.getAmount().toString())
                            .build();
                        })
                        .collect(Collectors.toList())
                    )
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
                    .user(
                        GetPortfolioResponse.User.builder()
                        .id(portfolio.getUser().getId())
                        .firstName(portfolio.getUser().getFirstName())
                        .lastName(portfolio.getUser().getLastName())
                        .email(portfolio.getUser().getEmail())
                        .createdAt(portfolio.getUser().getCreatedAt().toString())
                        .updatedAt(portfolio.getUser().getUpdatedAt().toString())
                        .build()
                    )
                    .stocks(
                        portfolio.getStocks()
                        .stream()
                        .map(stock -> {
                            return GetPortfolioResponse.Stock.builder()
                            .id(stock.getId())
                            .createdAt(stock.getCreatedAt().toString())
                            .symbol(stock.getSymbol())
                            .currency(stock.getCurrency())
                            .price(stock.getPrice().toString())
                            .open(stock.getOpen().toString())
                            .high(stock.getHigh().toString())
                            .low(stock.getLow().toString())
                            .close(stock.getClose().toString())
                            .volume(stock.getVolume().toString())
                            .build();
                        })
                        .collect(Collectors.toList())
                    )
                    .transactions(
                        portfolio.getTransactions()
                        .stream()
                        .map(transaction -> {
                            return GetPortfolioResponse.Transaction.builder()
                            .id(transaction.getId())
                            .createdAt(transaction.getCreatedAt().toString())
                            .currency(transaction.getCurrency())
                            .amount(transaction.getAmount().toString())
                            .build();
                        })
                        .collect(Collectors.toList())
                    )
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
