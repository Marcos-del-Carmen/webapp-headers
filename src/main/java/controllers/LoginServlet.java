package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.LoginService;
import services.LoginServiceCookieImpl;
import services.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {

    static public final String USERNAME="admin";
    static public final String PASSWORD="12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> sessionOptional = auth.getUsername(req);

        if(sessionOptional.isPresent()) {
            resp.setContentType("text/html; charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Hola "+sessionOptional.get()+"</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Hola "+sessionOptional.get()+" has iniciado sesión anteriormente!</h1>");
                out.println("       <a href=\"/webapp-headers/index.jsp\">Volver</a>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username.equals(USERNAME) && password.equals(PASSWORD)) {

            HttpSession usernameSession = req.getSession();
            usernameSession.setAttribute("username", username);
            req.setAttribute("title", "Inicio de sessión");

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo siento pero no esta autorizado.");
        }
    }
}
