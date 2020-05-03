package br.com.algaworks.gerenciamentoapp.errors.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PessoaInexistenteOuInativaException extends RuntimeException {


    public PessoaInexistenteOuInativaException() {
    }

    public PessoaInexistenteOuInativaException(String message) {
        super(message);
    }
}
