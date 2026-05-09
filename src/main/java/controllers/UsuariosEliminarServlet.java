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
import java.util.Optional;

@WebServlet("/usuarios/eliminar")
public class UsuariosEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =  (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        Long idUsuario;

        try {
            idUsuario = Long.parseLong(req.getParameter("idUsuario"));
        } catch (NumberFormatException e) {
            idUsuario = 0L;
        }

        if (idUsuario > 0) {
            Optional<Usuarios> optionalUsuario = service.porId(idUsuario);

            if (optionalUsuario.isPresent()) {
                service.eliminar(idUsuario);
                getServletContext().getRequestDispatcher("/usuarios/listar").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El usuario a eliminar no existe en la base de datos...");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error al eliminar el usuario...");
        }
    }
}
