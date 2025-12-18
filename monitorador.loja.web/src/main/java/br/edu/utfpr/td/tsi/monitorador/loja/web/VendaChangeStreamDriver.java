package br.edu.utfpr.td.tsi.monitorador.loja.web;

import org.bson.Document;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.FullDocument;

import br.edu.utfpr.td.tsi.monitorador.loja.web.model.PedidoProcessamentoPagamento;
import jakarta.annotation.PostConstruct;

@Component
public class VendaChangeStreamDriver {

	@Value("${spring.data.mongodb.uri}")
	private String uriServer;

	@Value("${spring.data.mongodb.database}")
	private String nomeDb;

	@Value("${spring.data.mongodb.collection}")
	private String colecao;
	private final RabbitTemplate rabbitTemplate;

    public VendaChangeStreamDriver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	@PostConstruct
	public void init() {
		MongoClient client = MongoClients.create(uriServer);
		MongoDatabase db = client.getDatabase(nomeDb);
		MongoCollection<Document> collection = db.getCollection(colecao);

		System.out.println("Ouvindo MongoDB...");

		collection.watch().fullDocument(FullDocument.UPDATE_LOOKUP).forEach(event -> {
			Document doc = event.getFullDocument();
			String operacao = event.getOperationType().getValue();

            System.out.println("===== Change Event Detectado =====");
            System.out.println("Documento: " + doc.get("_id"));
            
            if (doc != null) {

                // Envia evento de pagamento
                rabbitTemplate.convertAndSend(
                    "fila.pagamento",
                    doc.get("_id")
                );

                // Envia evento de e-mail
                rabbitTemplate.convertAndSend(
                    "fila.email",
                    "Email: Sua compra foi concluída. Estamos processando seu pedido: " + doc.get("_id")
                );
            }

            System.out.println("==================================");
        });
	}
}
