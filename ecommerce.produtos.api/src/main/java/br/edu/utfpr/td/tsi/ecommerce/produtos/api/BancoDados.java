package br.edu.utfpr.td.tsi.ecommerce.produtos.api;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.utfpr.td.tsi.ecommerce.produtos.api.model.Produto;


@Component
public class BancoDados {

	public List<Produto> produtos= null;
	
	public List<Produto> listarTodosProdutos() throws Exception {
		if(produtos == null) {
			produtos = carregarProdutosDoJson();
		}
		return produtos;
	}

	public void atualizarEstoque(String id) {

		for (Produto produto : produtos) {
			if(produto.getId().equals(id)) {
				int quantidadeEmEstoque = produto.getQuantidadeEmEstoque();
				int novaQuantidade = 14;
				produto.setQuantidadeEmEstoque(novaQuantidade);
			}
		}
	}
	
	 private List<Produto> carregarProdutosDoJson() throws Exception {
	        ObjectMapper mapper = new ObjectMapper();

	        ClassPathResource resource = new ClassPathResource("produtos.json");
	        InputStream inputStream = resource.getInputStream();

	        return mapper.readValue(
	                inputStream,
	                new TypeReference<List<Produto>>() {}
	        );
	    }
}
