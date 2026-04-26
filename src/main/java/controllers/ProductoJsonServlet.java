package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import services.ProductoServicesImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(jsonStream, Producto.class);
        
        resp.setContentType("text/html; charset=UTF-8");
        try(PrintWriter out = resp.getWriter()) {
        
            out.println("<!DOCTYPE html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Detalle del producto</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Detalle del producto</h1>");
            out.println("           <ul>");
            out.println("               <li>"+producto.getId()+"</li>");
            out.println("               <li>"+producto.getNombre()+"</li>");
            out.println("               <li>"+producto.getTipo()+"</li>");
            out.println("               <li>"+producto.getPrecio()+"</li>");
            out.println("           </ul>");
            out.println("   </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoServicesImp service = new ProductoServicesImp();
        List<Producto> productos = service.listar();
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(productos);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
