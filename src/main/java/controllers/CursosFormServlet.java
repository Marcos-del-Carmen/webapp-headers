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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/cursos/form")
public class CursosFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CursoService service = new CursoServiceJdbcImpl(conn);

        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L;
        }

        Curso curso = new Curso();

        if (id > 0) {
            Optional<Curso> o = service.porId(id);
            if (o.isPresent()) {
                curso = o.get();
            }
        }

        req.setAttribute("curso", curso);
        getServletContext().getRequestDispatcher("/form-cursos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errores = new HashMap<>();

        Connection conn = (Connection) req.getAttribute("conn");
        CursoService service = new CursoServiceJdbcImpl(conn);

        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String instructor = req.getParameter("instructor");
        Double duracion;
        try {
            duracion = Double.valueOf(req.getParameter("duracion"));
        } catch (NumberFormatException e) {
            duracion = 0.0;
        }

        if (nombre == null || nombre.isEmpty()) {
            errores.put("nombre", "el nombre es requerido");
        }

        if (descripcion == null || descripcion.isEmpty()) {
            errores.put("descripcion", "La descripcion es requerida");
        }

        if (instructor == null || instructor.isEmpty()) {
            errores.put("instructor","El instructor es requerido");
        }

        if (duracion.equals(0.0)) {
            errores.put("duracion","La duracion es requerida");
        }
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Curso curso = new Curso();
        curso.setId(id);
        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        curso.setInstructor(instructor);
        curso.setDuracion(duracion);

        if (errores.isEmpty()) {
            service.guardar(curso);
            resp.sendRedirect(req.getContextPath() + "/cursos/listar");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("curso", curso);

            getServletContext().getRequestDispatcher("/form-cursos.jsp").forward(req, resp);
        }
    }
}
