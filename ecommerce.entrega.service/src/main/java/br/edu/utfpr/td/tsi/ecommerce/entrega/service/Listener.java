package br.edu.utfpr.td.tsi.ecommerce.entrega.service;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;


@Component
public class Listener {

    @Autowired
    private Publisher publisher;

    private final Gson gson = new Gson();

    @RabbitListener(queues = "fila.entrega")
    public void listen(String mensagem) {

        System.out.println("Mensagem recebida da fila.entrega:");
        System.out.println(mensagem);

        //Email de entrega aprovado
        publisher.publicarEmailEntrega(mensagem);
    }
}
