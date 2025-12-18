package br.edu.utfpr.td.tsi.ecommerce.pagamento.service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.utfpr.td.tsi.ecommerce.pagamento.service.model.Produto;

@Service
public class EstoqueService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File arquivoProdutos;

    public EstoqueService() throws Exception {
        ClassPathResource resource = new ClassPathResource("produtos.json");
        this.arquivoProdutos = resource.getFile();
    }

    public synchronized Produto baixarEstoque(String produtoId, int quantidade) {
        try {
            List<Produto> produtos =
                    mapper.readValue(arquivoProdutos, new TypeReference<>() {});

            Produto produto = produtos.stream()
                    .filter(p -> p.getId().equals(produtoId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getQuantidadeEmEstoque() < quantidade) {
                throw new RuntimeException("Estoque insuficiente");
            }

            // Baixa estoque
            produto.setQuantidadeEmEstoque(
                    produto.getQuantidadeEmEstoque() - quantidade
            );

            // Grava no JSON
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(arquivoProdutos, produtos);

            System.out.println("Estoque atualizado: "
                    + produto.getNome()
                    + " → " + produto.getQuantidadeEmEstoque());

            return produto;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar estoque", e);
        }
    }
}
