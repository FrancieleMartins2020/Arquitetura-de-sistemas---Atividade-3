package br.edu.utfpr.td.tsi.ecommerce.produtos.api;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.edu.utfpr.td.tsi.ecommerce.produtos.api.config.RabbitConfig;
import br.edu.utfpr.td.tsi.ecommerce.produtos.api.model.PedidoAtualizacaoEstoque;


@Component
public class Listener {
	
	@Autowired
	private BancoDados bancoDados;
	
	@RabbitListener(queues = RabbitConfig.FILA_ESTOQUE)
	public void listen(String in) {

		try {
		 String atualizacaoEstoque= new Gson().fromJson(in, String.class);
		 bancoDados.atualizarEstoque(atualizacaoEstoque);	    
		 System.out.println("Estoque atualizado. Produto: " + atualizacaoEstoque);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}		
}


