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
    <p>
        <a href="<%=request.getContextPath()%>/cursos/form">Crear [+]</a>
    </p>
    <form action="<%=request.getContextPath()%>/cursos/buscar" method="POST">
        <label for="nombre">Nombre del curso </label><br>
        <input type="text" name="nombre" id="nombre">
        <input type="submit" value="Buscar">
    </form>
    <% if (cursos.isEmpty()) { %>
        <p>No hay cursos disponibles...</p>
    <% } else { %>
        <table>
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Nombre</td>
                        <td>Instructor</td>
                        <td>Descripcion</td>
                        <td>Duracion</td>
                        <td colspan="2">Acciones</td>
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
                            <td>
                                <a href="<%=request.getContextPath()%>/cursos/form?id=<%=c.getId()%>">Editar</a>
                            </td>
                            <td>
                                <a onclick="return confirm('estas seguro de eliminar el curso?');" href="<%=request.getContextPath()%>/cursos/eliminar?id=<%=c.getId()%>">Eliminar</a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
        </table>
    <% } %>
</body>
</html>