package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import services.ProductService;
import services.ProductoServicesImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService service = new ProductoServicesImp();
        String nombre = req.getParameter("nombre");
        Optional<Producto> encontrado = service.buscar(nombre);

        if(encontrado.isPresent()) {
            resp.setContentType("text/html; charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Producto encontrado</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Producto encontrado</h1>");
                out.println("       <h3>Producto encontrado " + encontrado.get().getNombre() +" con precio de $"+encontrado.get().getPrecio()+"</h3>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Lo siento pero no se encuentra el producto que esta buscando. ");
        }
    }
}
