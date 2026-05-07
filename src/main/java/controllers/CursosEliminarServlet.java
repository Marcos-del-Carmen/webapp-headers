package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Curso;
import models.Producto;
import services.CursoService;
import services.CursoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/cursos/eliminar")
public class CursosEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CursoService servie = new CursoServiceJdbcImpl(conn);

        Long id;

        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {

            Optional<Curso> o = servie.porId(id);
            if (o.isPresent()) {
                servie.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/cursos/listar");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "no existe el curso a eliminar...");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "no se esta enviando bien el curso a eliminar...");
        }
    }
}
