package br.edu.utfpr.td.tsi.monitorador.loja.web.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoConverter;

@Configuration
public class RabbitConfig {

    // ======== FILAS ========

    @Bean
    public Queue pagamentoQueue() {
        return new Queue("fila.pagamento", true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("fila.email", true);
    }
}

