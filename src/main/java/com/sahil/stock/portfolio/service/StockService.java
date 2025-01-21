package com.sahil.stock.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.stock.portfolio.dto.getDailyTs.GetDailyTsRequest;
import com.sahil.stock.portfolio.dto.getDailyTs.GetDailyTsResponse;
import com.sahil.stock.portfolio.dto.getIntradayTs.GetIntradayTsRequest;
import com.sahil.stock.portfolio.dto.getIntradayTs.GetIntradayTsResponse;
import com.sahil.stock.portfolio.dto.getMonthlyTs.GetMonthlyTsRequest;
import com.sahil.stock.portfolio.dto.getMonthlyTs.GetMonthlyTsResponse;
import com.sahil.stock.portfolio.dto.getStock.GetStockRequest;
import com.sahil.stock.portfolio.dto.getStock.GetStockResponse;
import com.sahil.stock.portfolio.dto.getWeeklyTs.GetWeeklyTsRequest;
import com.sahil.stock.portfolio.dto.getWeeklyTs.GetWeeklyTsResponse;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class StockService {
        @Value("${stock.info-url}")
        private String apiUrl;

        private WebClient webClient;

        @PostConstruct
        public void init() {
                ExchangeStrategies strategies = ExchangeStrategies
                                .builder()
                                .codecs(configurer -> configurer
                                                .defaultCodecs()
                                                .maxInMemorySize(1024 * 1024 * 10) // 10MB
                                )
                                .build();

                webClient = WebClient
                                .builder()
                                .baseUrl(apiUrl)
                                .exchangeStrategies(strategies)
                                .build();
        }

        public Mono<GetStockResponse> getStock(GetStockRequest getStockRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-stock")
                                                .queryParam("symbol", getStockRequest.getSymbol())
                                                .build())
                                .retrieve()
                                .bodyToMono(GetStockResponse.class);
        }

        public Mono<GetIntradayTsResponse> getIntradayTs(GetIntradayTsRequest getIntradayTsRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-intraday-ts")
                                                .queryParam("symbol", getIntradayTsRequest.getSymbol())
                                                .queryParam("interval", getIntradayTsRequest.getInterval())
                                                .build())
                                .retrieve()
                                .bodyToMono(GetIntradayTsResponse.class);
        }

        public Mono<GetDailyTsResponse> getDailyTs(GetDailyTsRequest getDailyTsRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-daily-ts")
                                                .queryParam("symbol", getDailyTsRequest.getSymbol())
                                                .build())
                                .retrieve()
                                .bodyToMono(GetDailyTsResponse.class);
        }

        public Mono<GetWeeklyTsResponse> getWeeklyTs(GetWeeklyTsRequest getWeeklyTsRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-weekly-ts")
                                                .queryParam("symbol", getWeeklyTsRequest.getSymbol())
                                                .build())
                                .retrieve()
                                .bodyToMono(GetWeeklyTsResponse.class);
        }

        public Mono<GetMonthlyTsResponse> getMonthlyTs(GetMonthlyTsRequest getMonthlyTsRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-monthly-ts")
                                                .queryParam("symbol", getMonthlyTsRequest.getSymbol())
                                                .build())
                                .retrieve()
                                .bodyToMono(GetMonthlyTsResponse.class);
        }
}
