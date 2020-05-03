package br.com.algaworks.gerenciamentoapp.security.login.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("algamoney")
public class ProfileSecurityProperty {
    private boolean enableHttps;

    public boolean isEnableHttps() {
        return enableHttps;
    }

    public void setEnableHttps(boolean enableHttps) {
        this.enableHttps = enableHttps;
    }
}
