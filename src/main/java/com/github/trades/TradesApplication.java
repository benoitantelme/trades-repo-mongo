package com.github.trades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TradesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradesApplication.class, args);
	}

}
