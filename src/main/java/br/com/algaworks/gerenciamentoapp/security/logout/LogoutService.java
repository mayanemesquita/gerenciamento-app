package br.com.algaworks.gerenciamentoapp.security.logout;

import br.com.algaworks.gerenciamentoapp.security.login.user.ProfileSecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LogoutService {

    ProfileSecurityProperty profileSecurityProperty;

    @Autowired
    public LogoutService(ProfileSecurityProperty profileSecurityProperty) {
        this.profileSecurityProperty = profileSecurityProperty;
    }

    public void revoke(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(profileSecurityProperty.isEnableHttps());
        cookie.setPath(servletRequest.getRequestURI());
        cookie.setMaxAge(0);

        servletResponse.addCookie(cookie);
        servletResponse.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
