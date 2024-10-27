package com.markfy;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MarkyfyMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkyfyMvcApplication.class, args);
	}

}
