package br.edu.utfpr.td.tsi.ecommerce.fiscal.service;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitMqDemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RabbitMqDemoApplication.class, args);
	}

	@Bean
	public Queue myQueue() {
		return new Queue("fila.fiscal");
	}
}
