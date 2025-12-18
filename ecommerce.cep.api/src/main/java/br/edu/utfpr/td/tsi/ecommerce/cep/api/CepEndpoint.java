package br.edu.utfpr.td.tsi.ecommerce.cep.api;

import java.io.InputStream;
import java.util.List;
import java.util.Random;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.utfpr.td.tsi.ecommerce.cep.api.model.Endereco;

@RestController
@CrossOrigin
@RequestMapping("/cep")
public class CepEndpoint {

    @GetMapping(
        value = { "", "/", "/{cep}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> buscarCep(
            @PathVariable(required = false) String cep) {

        try {
            List<Endereco> enderecos = carregarEnderecosDoJson();

            Endereco enderecoAleatorio =
                    enderecos.get(new Random().nextInt(enderecos.size()));

            return ResponseEntity.ok(enderecoAleatorio);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao carregar endereços");
        }
    }

    private List<Endereco> carregarEnderecosDoJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        ClassPathResource resource =
                new ClassPathResource("enderecos.json");

        try (InputStream inputStream = resource.getInputStream()) {
            return mapper.readValue(
                    inputStream,
                    new TypeReference<List<Endereco>>() {}
            );
        }
    }
}