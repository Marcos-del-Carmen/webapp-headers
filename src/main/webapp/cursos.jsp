<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, models.*"%>
<%@ page import="java.util.List" %>

<%
    List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de cursos</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/cursos/buscar" method="POST">
        <label for="nombre">Nombre del curso </label><br>
        <input type="text" name="nombre" id="nombre">
        <input type="submit" value="Buscar">
    </form>

    <% if(!cursos.isEmpty()) { %>
        <table>
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Nombre</td>
                        <td>Instructor</td>
                        <td>Descripcion</td>
                        <td>Duracion</td>
                    </tr>
                </thead>

                <tbody>
                    <%for(Curso c : cursos) { %>
                        <tr>
                            <td><%=c.getId()%></td>
                            <td><%=c.getNombre()%></td>
                            <td><%=c.getDescripcion()%></td>
                            <td><%=c.getInstructor()%></td>
                            <td><%=c.getDuracion()%></td>
                        </tr>
                    <% } %>
                </tbody>
        </table>
    <% } else { %>
        <p>No hay cursos disponibles...</p>
    <% } %>

</body>
</html>