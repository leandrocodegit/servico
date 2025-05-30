package br.modelos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class ModelosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModelosApplication.class, args);
	}

}
