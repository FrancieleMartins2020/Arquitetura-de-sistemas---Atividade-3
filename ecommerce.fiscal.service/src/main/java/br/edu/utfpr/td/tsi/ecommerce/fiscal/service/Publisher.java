package br.edu.utfpr.td.tsi.ecommerce.fiscal.service;

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

    @PostConstruct
    public void configurarFilas() {
        amqpAdmin.declareQueue(new Queue("fila.email"));
    }

    // Email Nota Fiscal
    public void publicarEmailNotaFiscal(String notaFiscal) {
        String msg = "Email: Nota Fiscal do produto: "
                + notaFiscal;

        rabbitTemplate.convertAndSend("fila.email", msg);
    }
}