package br.com.algaworks.gerenciamentoapp.integrations.cep.client;

import br.com.algaworks.gerenciamentoapp.integrations.cep.contracts.CepResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepClient {

    public ResponseEntity<CepResponse> buscarCep(String cep) {
        String fooResourceUrl = "https://viacep.com.br/ws/" + cep + "/json/";
        return new RestTemplate().getForEntity(fooResourceUrl, CepResponse.class);
    }
}
