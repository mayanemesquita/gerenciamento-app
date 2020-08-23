package br.com.algaworks.gerenciamentoapp.security;

import java.util.Date;

public class SecurityConstants {
    public static final String SECRET_KEY = "Q2xpJm4hQW5ndWxAciMk";
    public static final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
