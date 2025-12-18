package br.edu.utfpr.td.tsi.ecommerce.fiscal.service;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;


@Component
public class Listener {

    @Autowired
    private Publisher publisher;

    @RabbitListener(queues = "fila.fiscal")
    public void listen(String mensagem) {

        System.out.println("Mensagem recebida da fila.fiscal:");
        System.out.println(mensagem);

        // Email de envio nota fiscal
        publisher.publicarEmailNotaFiscal(mensagem);
    }
}
