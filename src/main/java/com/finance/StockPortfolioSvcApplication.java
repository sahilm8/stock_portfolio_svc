package com.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class StockPortfolioSvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockPortfolioSvcApplication.class, args);
		log.info("*** Service Activated ***");

	}
}
