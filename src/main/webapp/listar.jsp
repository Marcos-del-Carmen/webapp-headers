<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, models.*"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>

<%
    // Recuperamos los objetos que el Servlet envió al Request
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");

    String mensajeApp = (String) getServletContext().getAttribute("mensajeGlobal");
    String mensajeRequest = (String) getServletContext().getAttribute("mensajeDelMomento");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tabla de productos</title>
</head>
<body>
    <%if (username.isPresent()) { %>
        <div>Hola <%=username%>, bienvenido! </div>
    <%} else { %>
        <div>Hola anonimo, bienvenido! </div>
    <%} %>
    <h1>Tabla de productos</h1>
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Nombre</td>
            <td>Tipo</td>
            <% if (username.isPresent()) { %>
                <td>Precio</td>
                <td>Compra producto</td>
            <% } %>
        </tr>

        <% if (productos != null) {
            for (Producto p : productos) { %>
            <tr>
                <td> <%= p.getId() %> </td>
                <td> <%= p.getNombre() %> </td>
                <td> <%= p.getTipo() %> </td>

                <% if (username.isPresent()) { %>
                    <td> <%= p.getPrecio() %> </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">
                            Agregar a carro
                        </a>
                    </td>
                <% } %>
            </tr>
        <%  }
        } %>
    </table>

    <p>Mensaje app: <%= mensajeApp %></p>
    <p>Mensaje del request: <%= mensajeRequest %></p>
</body>
</html>