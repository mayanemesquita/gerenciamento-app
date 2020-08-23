//package br.com.algaworks.gerenciamentoapp.controller;
//
//import br.com.algaworks.gerenciamentoapp.security.logout.LogoutService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//@RequestMapping("/logout")
//public class LogoutController {
//
//    private final LogoutService logoutService;
//
//    @Autowired
//    public LogoutController(LogoutService logoutService) {
//        this.logoutService = logoutService;
//    }
//
//    @DeleteMapping("/revoke")
//    public void revoke(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        logoutService.revoke(httpServletRequest, httpServletResponse);
//    }
//}
