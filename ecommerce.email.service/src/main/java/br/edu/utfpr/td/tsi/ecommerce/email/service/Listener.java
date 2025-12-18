package br.edu.utfpr.td.tsi.ecommerce.email.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
	
	@RabbitListener(queues = "fila.email")
	public void listen(String in) {
	    System.out.println("Mensagem recebida da fila : " + in);
	}		
}


