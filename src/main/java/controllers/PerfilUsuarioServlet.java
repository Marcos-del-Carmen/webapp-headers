package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guardar-session")
public class PerfilUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        HttpSession session = req.getSession();
        session.setAttribute("nombre", nombre);

        if(nombre!=null || !nombre.isEmpty()) { // estoy validando que el cliente haya ingresa el nombre xd
           resp.setContentType("text/html; charset=UTF-8");
           try(PrintWriter out = resp.getWriter()) {
           
               out.println("<!DOCTYPE html>");
               out.println("   <head>");
               out.println("       <meta charset=\"UTF-8\">");
               out.println("       <title>Perfil usuario</title>");
               out.println("   </head>");
               out.println("   <body>");
               out.println("       <h1>Perfil usuario " + nombre + "</h1>");
               out.println("       <p>Username: " + nombre + "</p>");
               out.println("       <a href=\"/webapp-headers/index.jsp\">Volver</a>");
               out.println("   </body>");
               out.println("</html>");
           } 
        } else {
            resp.sendError(HttpServletResponse.SC_NO_CONTENT, "Debes de ingresar tu nombre en la entrada de texto");
        }
        
    }
}
