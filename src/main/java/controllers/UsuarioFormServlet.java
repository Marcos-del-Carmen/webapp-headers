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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        Usuarios usuario = new Usuarios();
        Long id;

        try {
            id = Long.parseLong(req.getParameter("idUsuario"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Usuarios> o = service.porId(id);
            if (o.isPresent()) {
                usuario = o.get();
            }
        }

        req.setAttribute("usuario", usuario);
        req.setAttribute("title", "Formulario de usuario");

        getServletContext().getRequestDispatcher("/form-usuarios.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        Usuarios u = new Usuarios();

        Map<String, String> errores = new HashMap<>();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Long id;


        if (username==null || username.isEmpty()) {
            errores.put("username", "El nombre de usuario es requerido.");
        }

        if (password == null || password.isEmpty()) {
            errores.put("password","La contraña es requerida.");
        }

        if (email==null || email.isEmpty()) {
            errores.put("email","El correo electronico es requerido");
        }
        try {
            id = Long.parseLong(req.getParameter("idUsuario"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        u.setIdUsuario(id);
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);

        if (errores.isEmpty()) {
            service.guardar(u);
            resp.sendRedirect(req.getContextPath() + "/usuarios/listar");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("usuario", u);
            getServletContext().getRequestDispatcher("/form-usuarios.jsp").forward(req, resp);
        }
    }
}
