<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, models.*"%>

<%
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Curso curso = (Curso) request.getAttribute("curso");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de cursos</title>
</head>
<body>
    <h1>Formulario de cursos</h1>
    <form action="<%=request.getContextPath()%>/cursos/form" method="POST">
        <div>
            <div>
                <label for="nombre">Nombre</label>
            </div>
            <input type="text" name="nombre" id="nombre" value="<%=curso.getNombre() != null ? curso.getNombre() : ""%>">
            <% if (errores!=null && errores.containsKey("nombre")) {%>
                <p style="color: red;"><%=errores.get("nombre")%></p>
            <% } %>
        </div>
        <div>
            <div>
                <label for="descripcion">Descripcion</label>
            </div>
            <input type="text" name="descripcion" id="descripcion" value="<%=curso.getDescripcion() != null ? curso.getDescripcion() : ""%>">
            <% if (errores!=null && errores.containsKey("descripcion")) {%>
                <p style="color: red;"><%=errores.get("descripcion")%></p>
            <% } %>
        </div>
        <div>
            <div>
                <label for="instructor">Instructor</label>
            </div>
            <input type="text" name="instructor" id="instructor" value="<%=curso.getInstructor() != null ? curso.getInstructor() : ""%>">
            <% if (errores!=null && errores.containsKey("instructor")) {%>
                <p style="color: red;"><%=errores.get("instructor")%></p>
            <% } %>
        </div>
        <div>
            <div>
                <label for="duracion">Duracion</label>
            </div>
            <input type="text" name="duracion" id="duracion" value="<%=curso.getDuracion() > 0 ? curso.getDuracion() : ""%>">
            <% if (errores!=null && errores.containsKey("duracion")) {%>
                <p style="color: red;"><%=errores.get("duracion")%></p>
            <% } %>
        </div>
        <div>
            <input type="submit" value="<%=(curso.getId() != null && curso.getId() > 0) ? "Editar" : "Crear"%>">
            <input type="hidden" name="id" id="id" value="<%=curso.getId()%>">
        </div>
    </form>
</body>
</html>