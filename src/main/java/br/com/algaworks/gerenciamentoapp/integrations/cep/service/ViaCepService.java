package br.com.algaworks.gerenciamentoapp.integrations.cep.service;

import br.com.algaworks.gerenciamentoapp.integrations.cep.client.ViaCepClient;
import br.com.algaworks.gerenciamentoapp.integrations.cep.contracts.CepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    @Autowired
    public ViaCepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public CepResponse buscarCep(String cep) {
        return viaCepClient.buscarCep(cep).getBody();
    }

    private CepResponse buscarViaBancoDados(String cep) {
        System.out.println("Via Banco de Dados" + cep);
        return null;
    }
}