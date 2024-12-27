package com.sahil.stock.portfolio.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.sahil.stock.portfolio.dto.StockDto;
import com.sahil.stock.portfolio.model.Order;
import com.sahil.stock.portfolio.model.Portfolio;
import com.sahil.stock.portfolio.model.Stock;
import com.sahil.stock.portfolio.repository.OrderRepository;
import com.sahil.stock.portfolio.util.Currency;
import com.sahil.stock.portfolio.util.OrderStatus;
import com.sahil.stock.portfolio.util.OrderType;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PortfolioService portfolioService;

    @Value("${stock.info_url}")
    private String infoUrl;
    
    private WebClient webClient;

    @PostConstruct
    public void init() {
        ExchangeStrategies strategies = ExchangeStrategies
        .builder()
        .codecs(configurer -> 
            configurer
            .defaultCodecs()
            .maxInMemorySize(1024 * 1024 * 10)  // 10MB
        )
        .build();

        webClient = WebClient
        .builder()
        .baseUrl(infoUrl)
        .exchangeStrategies(strategies)
        .build();
    }

    public Order addOrder(
        String portfolioName,
        String orderType,
        String symbol,
        String currency,
        String totalValue
        ) {
        Order order = new Order();

        order.setStatus(OrderStatus.CREATED.getValue());
        order.setOrderType(
            orderType.equals("BUY")
            ? OrderType.BUY.getValue()
            : OrderType.SELL.getValue()
        );
        order.setCurrency(
            !currency.equals("USD")
            ? currency
            : Currency.USD.getValue()
        );
        order.setTotalValue(new BigDecimal(totalValue));

        Portfolio portfolio = portfolioService.getPortfolio(portfolioName);
        if (portfolio == null) {
            return null;
        }
        order.setPortfolio(portfolio);

        webClient.get()
        .uri(uriBuilder -> uriBuilder
        .path("/get-global-quote")
        .queryParam("symbol", symbol)
        .build())
        .retrieve()
        .bodyToMono(StockDto.class)
        .map(dto -> {
            log.info("dto: " + dto);
            Stock stock = new Stock();
            stock.setSymbol(dto.getSymbol() != null ? dto.getSymbol() : symbol);
            stock.setCurrency(currency);
            stock.setPrice(dto.getPrice() != null ? new BigDecimal(dto.getPrice()) : BigDecimal.ZERO);
            stock.setOpen(dto.getOpen() != null ? new BigDecimal(dto.getOpen()) : BigDecimal.ZERO);
            stock.setHigh(dto.getHigh() != null ? new BigDecimal(dto.getHigh()) : BigDecimal.ZERO);
            stock.setLow(dto.getLow() != null ? new BigDecimal(dto.getLow()) : BigDecimal.ZERO);
            stock.setClose(dto.getPrevClose() != null ? new BigDecimal(dto.getPrevClose()) : BigDecimal.ZERO);
            stock.setVolume(dto.getVolume() != null ? new BigDecimal(dto.getVolume()) : BigDecimal.ZERO);
            order.setStock(stock);
            order.setTotalNumberOfStocks(new BigDecimal(totalValue).divide(stock.getPrice()));
            return stock;
        });

        return order;
    }

    public Order getLastOrder(String portfolioName) {
        Portfolio portfolio = portfolioService.getPortfolio(portfolioName);
        if (portfolio == null) {
            return null;
        }
        
        List<Order> orders = orderRepository.findByPortfolioOrderByCreatedAtDesc(portfolio);
        if (!orders.isEmpty()) {
            Order lastOrder = orders.get(0);
            log.info("Last order found: " + lastOrder);
            return lastOrder;
        }
        
        log.info("Last order not found: " + portfolioName);
        return null;
    }   
}
