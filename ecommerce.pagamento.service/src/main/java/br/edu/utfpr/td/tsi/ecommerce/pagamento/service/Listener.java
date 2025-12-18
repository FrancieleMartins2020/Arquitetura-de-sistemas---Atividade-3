package br.edu.utfpr.td.tsi.ecommerce.pagamento.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.utfpr.td.tsi.ecommerce.pagamento.service.model.PedidoAtualizacaoEstoque;
import br.edu.utfpr.td.tsi.ecommerce.pagamento.service.model.PedidoProcessamentoPagamento;


@Component
public class Listener {

    @Autowired
    private Publisher publisher;

    @Autowired
    private EstoqueService estoqueService;

    private final Gson gson = new Gson();

    @RabbitListener(queues = "fila.pagamento")
    public void listen(String mensagem) {
        
        System.out.println("Mensagem recebida da fila.pagamento: . Produto: " + mensagem);

        // Simula pagamento aprovado
        boolean pagamentoAprovado = true;

        if (pagamentoAprovado) {

            // Baixa estoque
            publisher.publicarEstoque(mensagem);

            // Api Fiscal
            publisher.publicarFiscal("Gerar nota fiscal. Produto: " + mensagem);

            // Api Entrega
            publisher.publicarEntrega("Pedido liberado para entrega. Produto: " + mensagem);

        }
    }
}