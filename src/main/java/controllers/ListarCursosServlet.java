package controllers;

import filters.ConexionFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Curso;
import repositories.CursoRepositorioImpl;
import services.CursoService;
import services.CursoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/cursos/listar")
public class ListarCursosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CursoService service = new CursoServiceJdbcImpl(conn);

        List<Curso> cursos = service.listar();

        req.setAttribute("cursos", cursos);

        getServletContext().getRequestDispatcher("/cursos.jsp").forward(req, resp);
    }
}
