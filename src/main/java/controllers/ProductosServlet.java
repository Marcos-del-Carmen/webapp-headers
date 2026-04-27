package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import services.LoginService;
import services.LoginServiceImpl;
import services.ProductoServicesImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        ProductoServicesImp service = new ProductoServicesImp();
        List<Producto> productos = service.listar();

        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Tabla de productos</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Tabla de productos</h1>");
            out.println("       <table>");
            out.println("           <tr>");
            out.println("               <td>Id</td>");
            out.println("               <td>Nombre</td>");
            out.println("               <td>Tipo</td>");
            if(cookieOptional.isPresent()) {
                out.println("           <td>Precio</td>");
            }
            out.println("           </tr>");
            productos.forEach(producto -> {
                out.println("       <tr>");
                out.println("           <td> " + producto.getId() + " </td>");
                out.println("           <td> " + producto.getNombre() + " </td>");
                out.println("           <td> " + producto.getTipo() + " </td>");
                if(cookieOptional.isPresent()) {
                    out.println("       <td> " + producto.getPrecio() + " </td>");
                }
                out.println("       <tr>");
            });
            out.println("       </table>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
