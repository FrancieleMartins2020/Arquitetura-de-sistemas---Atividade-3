package br.edu.utfpr.td.tsi.ecommerce.loja.web.persistencia;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.ecommerce.loja.web.modelo.Venda;

public interface VendaRepository extends MongoRepository<Venda, String>{

}
