package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Categoria;
import models.Producto;
import services.ProductService;
import services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/producto/form")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductService service = new ProductoServiceJdbcImpl(conn);

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Producto producto = new Producto();
        producto.setCategoria(new Categoria());

        if(id > 0) {
            Optional<Producto> o = service.porId(id);
            if(o.isPresent()) {
                producto = o.get();
            }
        }

        req.setAttribute("categorias", service.listaCategoria());
        req.setAttribute("producto", producto);

        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errores = new HashMap<>();
        Connection conn = (Connection) req.getAttribute("conn");
        ProductService service = new ProductoServiceJdbcImpl(conn);
        String nombre = req.getParameter("nombre");

        Integer precio;

        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");
        Long idCategoria;
        try {
            idCategoria = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e){
            idCategoria = 0L;
        }

        if (nombre==null || nombre.isEmpty()) {
            errores.put("nombre", "el nombre es requerido");
        }

        if (sku==null || sku.isEmpty()) {
            errores.put("sku", "el sku es requerido");
        } else if(sku.length() > 10) {
            errores.put("sku", "el sku deve ser menor a 10 carácteres");
        }

        if ( fechaStr == null || fechaStr.isEmpty()) {
            errores.put("fecha_registro", "la fecha de registro es requerida");
        }

        if (precio.equals(0)) {
            errores.put("precio", "el precio es requerido");
        }

        if (idCategoria.equals(0L)) {
            errores.put("categoria", "la categoria es requerida");
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (NullPointerException e) {
            fecha = null;
        }

        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);

        Categoria categoria = new Categoria();
        categoria.setId(idCategoria);
        producto.setCategoria(categoria);

        if (errores.isEmpty()) {
            service.guardar(producto); // lugar donde seesta utilizando que es el servlet
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listaCategoria());
            req.setAttribute("producto", producto);

            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }

    }
}
