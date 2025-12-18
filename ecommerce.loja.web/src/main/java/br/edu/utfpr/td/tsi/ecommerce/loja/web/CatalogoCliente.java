package br.edu.utfpr.td.tsi.ecommerce.loja.web;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.edu.utfpr.td.tsi.ecommerce.loja.web.modelo.Produto;

@Component
public class CatalogoCliente {

    @Value("${produto.api.server}")
    private String produtoApiServer;

    private final RestTemplate rest = new RestTemplate();
    private final String URL = "http://localhost:8082/catalogo";

    public List<Produto> listarProdutos() {
      return Arrays.asList(
        rest.getForObject(URL, Produto[].class));
    }
    
    public Produto buscarPorId(String id) {
        return listarProdutos().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }
}
