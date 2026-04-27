package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.LoginService;
import services.LoginServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        Optional<String> optionalCookie = auth.getUsername(req);

        if (optionalCookie.isPresent()) {
            Cookie usernameCookie = new Cookie("username", ""); // se cambia el valor a una cadena vacia
            usernameCookie.setMaxAge(0); // expira inmediatamente
            resp.addCookie(usernameCookie); // se agrega los valores a la cookie obiamente ya sin nada
        }
        resp.sendRedirect(req.getContextPath() + "/login.html"); // redirecciona
    }
}
