package br.edu.utfpr.td.tsi.ecommerce.loja.web;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.edu.utfpr.td.tsi.ecommerce.loja.web.modelo.Endereco;
import br.edu.utfpr.td.tsi.ecommerce.loja.web.modelo.Produto;

@Component
public class CepCliente {

    @Value("${cep.api.server}")
    private String cepApiServer;

    private final Gson gson = new Gson();

    public Endereco obterEnderecoAleatorio() {
        try {
            URL url = new URL(cepApiServer + "/cep/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int status = con.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Falha ao obter endereco. HTTP status: " + status);
            }

            try (InputStreamReader reader = new InputStreamReader(con.getInputStream())) {
                return gson.fromJson(reader, Endereco.class);
            } finally {
                con.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace(); // pode trocar por logger
            throw new RuntimeException("erro ao consultar endereço na API de CEP");
        }
    }
}
