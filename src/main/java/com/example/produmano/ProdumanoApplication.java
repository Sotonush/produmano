package com.example.produmano;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.example.produmano.telegram.TelegramNotificationService;
import org.springframework.context.annotation.Bean;

@EnableScheduling
@SpringBootApplication
public class ProdumanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdumanoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(TelegramNotificationService telegramNotificationService) {
		return args -> {
			telegramNotificationService.start();
		};
	}
}
