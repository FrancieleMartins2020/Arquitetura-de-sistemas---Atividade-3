package br.edu.utfpr.td.tsi.ecommerce.produtos.api;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
@SpringBootApplication
@PropertySource({ "file:./application.properties" })
@EnableRabbit
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
