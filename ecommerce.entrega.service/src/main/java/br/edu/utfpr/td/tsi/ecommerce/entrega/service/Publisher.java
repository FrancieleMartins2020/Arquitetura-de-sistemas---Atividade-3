package br.edu.utfpr.td.tsi.ecommerce.entrega.service;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.annotation.PostConstruct;

@Component
public class Publisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PostConstruct
    public void configurarFilas() {
        amqpAdmin.declareQueue(new Queue("fila.email"));
    }

    // Email entrega aprovado
    public void publicarEmailEntrega(String entrega) {
        String msg = "Email: Entrega aprovada para o produto: "
                + entrega;

        rabbitTemplate.convertAndSend("fila.email", msg);
    }
}