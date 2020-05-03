package br.com.algaworks.gerenciamentoapp.security.login.token;

import br.com.algaworks.gerenciamentoapp.security.login.user.ProfileSecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Configuration
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

    private final ProfileSecurityProperty profileSecurityProperty;

    @Autowired
    public RefreshTokenPostProcessor(ProfileSecurityProperty profileSecurityProperty) {
        this.profileSecurityProperty = profileSecurityProperty;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.requireNonNull(returnType.getMethod()).getName().equals("postAccessToken");
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
                                             MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                             ServerHttpRequest request, ServerHttpResponse response) {

        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;

        assert body != null;
        String refreshToken = token.getRefreshToken().getValue();
        adicionarRefreshTokenNoCookie(refreshToken, req, resp);
        removerRefreshTokenDoBody(token);

        return body;
    }

    private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }

    private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(profileSecurityProperty.isEnableHttps());
        refreshTokenCookie.setPath(req.getRequestURI());
        refreshTokenCookie.setMaxAge(2592000);
        resp.addCookie(refreshTokenCookie);
    }

}
