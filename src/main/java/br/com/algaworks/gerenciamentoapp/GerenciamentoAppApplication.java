package br.com.algaworks.gerenciamentoapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GerenciamentoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciamentoAppApplication.class, args);
        log.info("GERENCIAMENTO APP STARTED");
    }
}
