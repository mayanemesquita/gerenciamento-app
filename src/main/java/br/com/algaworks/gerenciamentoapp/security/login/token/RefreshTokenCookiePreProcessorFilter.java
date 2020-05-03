package br.com.algaworks.gerenciamentoapp.security.login.token;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if ("/oauth/token".equalsIgnoreCase(request.getRequestURI()) &&
                "refresh_token".equalsIgnoreCase(request.getParameter("grant_type")) && request.getCookies() != null) {

            String cookieToken = Stream.of(Objects.requireNonNull(request.getCookies()))
                    .filter(cookie -> cookie.getName().equalsIgnoreCase("refreshToken"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);

            request = new MyServletRequestWrapper(request, cookieToken);

        }
        filterChain.doFilter(request, servletResponse);
    }

    static class MyServletRequestWrapper extends HttpServletRequestWrapper {
        private final String refreshToken;

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[]{refreshToken});
            map.setLocked(true);
            return map;
        }
    }
}
