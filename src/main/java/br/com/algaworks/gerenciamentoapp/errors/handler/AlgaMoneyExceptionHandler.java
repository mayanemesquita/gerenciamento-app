package br.com.algaworks.gerenciamentoapp.errors.handler;

import br.com.algaworks.gerenciamentoapp.errors.exceptions.PessoaInexistenteOuInativaException;
import br.com.algaworks.gerenciamentoapp.errors.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class AlgaMoneyExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public AlgaMoneyExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(PessoaInexistenteOuInativaException.class)
    public ResponseEntity<?> handlerPessoaInexistenteOuInativoException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente.inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<AlgamoneyHandlerResponse.Erro> erros = Collections.singletonList(new AlgamoneyHandlerResponse.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {

        String mensagemUsuario = ex.getMessage();
        String mensagemDesenvolvedor = ex.toString();
        List<AlgamoneyHandlerResponse.Erro> erros = Arrays.asList(new AlgamoneyHandlerResponse.Erro(mensagemUsuario, mensagemDesenvolvedor));

        return new ResponseEntity<>(erros, HttpStatus.NOT_FOUND);
    }

}
