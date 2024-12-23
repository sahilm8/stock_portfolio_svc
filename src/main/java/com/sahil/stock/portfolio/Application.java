package com.sahil.stock.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("*** APPLICATION SERVICE ACTIVATED ***");
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("*** APPLICATION SERVICE TERMINATED ***");
        }));
	}
}
