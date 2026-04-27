package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.LoginService;
import services.LoginServiceImpl;
import services.ProductoServicesImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {

    static public final String USERNAME="admin";
    static public final String PASSWORD="12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        if(cookieOptional.isPresent()) {
            resp.setContentType("text/html; charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Hola "+cookieOptional.get()+"</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Hola "+cookieOptional.get()+" has iniciado sesión anteriormente!</h1>");
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

            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo siento pero no esta autorizado.");
        }
    }
}
