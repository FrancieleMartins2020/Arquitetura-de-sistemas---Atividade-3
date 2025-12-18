package br.edu.utfpr.td.tsi.ecommerce.pagamento.service;


import br.edu.utfpr.td.tsi.ecommerce.pagamento.service.model.PedidoAtualizacaoEstoque;
import br.edu.utfpr.td.tsi.ecommerce.pagamento.service.model.PedidoProcessamentoPagamento;
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
        amqpAdmin.declareQueue(new Queue("fila.entrega"));
        amqpAdmin.declareQueue(new Queue("fila.fiscal"));
    }
    // Geração de nota fiscal
    public void publicarFiscal(String mensagem) {
        String json = gson.toJson(mensagem);
        rabbitTemplate.convertAndSend("fila.fiscal", json);
    }
    // Liberação para entrega
    public void publicarEntrega(String mensagem) {
        String json = gson.toJson(mensagem);
        rabbitTemplate.convertAndSend("fila.entrega", json);
    }
    
    // Liberação para estoque
    public void publicarEstoque(String estoque) {
        String json = gson.toJson(estoque);
        rabbitTemplate.convertAndSend("fila.estoque", json);
    }
}