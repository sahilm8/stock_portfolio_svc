package com.sahil.stock.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

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

        public Mono<Object> getStock(Object getStockRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-stock")
                                                .queryParam("", getStockRequest)
                                                .build())
                                .retrieve()
                                .bodyToMono(Object.class);
        }

        public Mono<Object> getIntradayTs(Object getIntradayTsRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-intraday-ts")
                                                .queryParam("", getIntradayTsRequest)
                                                .build())
                                .retrieve()
                                .bodyToMono(Object.class);
        }

        public Mono<Object> getDailyTs(Object getDailyTsRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-daily-ts")
                                                .queryParam("", getDailyTsRequest)
                                                .build())
                                .retrieve()
                                .bodyToMono(Object.class);
        }

        public Mono<Object> getWeeklyTs(Object getWeeklyTsRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-weekly-ts")
                                                .queryParam("", getWeeklyTsRequest)
                                                .build())
                                .retrieve()
                                .bodyToMono(Object.class);
        }

        public Mono<Object> getMonthlyTs(Object getMonthlyTsRequest) {
                return webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/get-monthly-ts")
                                                .queryParam("", getMonthlyTsRequest)
                                                .build())
                                .retrieve()
                                .bodyToMono(Object.class);
        }
}
