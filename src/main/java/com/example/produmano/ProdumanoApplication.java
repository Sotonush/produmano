package com.example.produmano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProdumanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdumanoApplication.class, args);
	}
}
/*единственный момент добавить в GET запросы
 на листы фильтры поиск например и сортировку по датам по названию
 */