package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.LoginService;
import services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> sessionOptional = auth.getUsername(req);

        if (sessionOptional.isPresent()) {
            HttpSession session = req.getSession();
            session.invalidate(); // borron y cuenta nueva, pero borra todo lo que esta en la sección no solo una parte
            // session.removeAttribute("username");
        }
        resp.sendRedirect(req.getContextPath() + "/login.html"); // redirecciona
    }
}
