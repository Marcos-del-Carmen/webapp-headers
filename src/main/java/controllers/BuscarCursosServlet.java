package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Curso;
import services.CursoService;
import services.CursoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/cursos/buscar")
public class BuscarCursosServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");

        Connection conn = (Connection) req.getAttribute("conn");
        CursoService service = new CursoServiceJdbcImpl(conn);

        List<Curso> cursos = service.buscarNombre(nombre);

        req.setAttribute("cursos", cursos);

        getServletContext().getRequestDispatcher("/cursos.jsp").forward(req, resp);

    }
}
