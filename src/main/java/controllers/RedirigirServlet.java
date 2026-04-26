package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirigir")
public class RedirigirServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Manera de redireccionar indicando la ruta y status que va enviar al redireccionar
        // resp.setHeader("Location",req.getContextPath() + "/productos.html");
        // resp.setStatus(HttpServletResponse.SC_FOUND);

        // Manerade redireccionar más corta que la enterior es lo mismo a la anterior pero en una sola línea
        resp.sendRedirect(req.getContextPath() + "/productos.html");
    }
}
