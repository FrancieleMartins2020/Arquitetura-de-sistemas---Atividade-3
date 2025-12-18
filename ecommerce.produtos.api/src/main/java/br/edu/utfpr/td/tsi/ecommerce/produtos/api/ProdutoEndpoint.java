package br.edu.utfpr.td.tsi.ecommerce.produtos.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.td.tsi.ecommerce.produtos.api.model.Produto;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoEndpoint {

	@Autowired
	private BancoDados bancoDados;

	@GetMapping(value = "/catalogo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> carregarCatalogo() throws Exception{
		List<Produto> produtos = bancoDados.listarTodosProdutos();
		return ResponseEntity.status(HttpStatus.OK).body(produtos);
	}	

}
