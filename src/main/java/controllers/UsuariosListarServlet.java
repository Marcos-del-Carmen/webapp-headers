package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuarios;
import services.UsuarioService;
import services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/usuarios/listar")
public class UsuariosListarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        List<Usuarios> usuarios = service.listar();

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("title", "Lista de usuarios");
        getServletContext().getRequestDispatcher("/listar-usuarios.jsp").forward(req, resp);
    }
}
