package br.edu.utfpr.td.tsi.ecommerce.produtos.api.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String FILA_ESTOQUE = "fila.estoque";

    @Bean
    public Queue filaEstoque() {
        return new Queue(FILA_ESTOQUE, false);
    }
}
