<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de cursos</title>
</head>
<body>
    <p>
        <a href="${pageContext.request.contextPath}/cursos/form">Crear [+]</a>
    </p>
    <form action="${pageContext.request.contextPath}/cursos/buscar" method="POST">
        <label for="nombre">Nombre del curso </label><br>
        <input type="text" name="nombre" id="nombre">
        <input type="submit" value="Buscar">
    </form>
    <c:choose>
        <c:when test="${cursos.isEmpty()}">
            <p>No hay cursos disponibles...</p>
        </c:when>
        <c:otherwise>
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
                        <c:forEach items="${cursos}" var="c">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.nombre}</td>
                                <td>${c.descripcion}</td>
                                <td>${c.instructor}</td>
                                <td>${c.duracion}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/cursos/form?id=${c.id}">Editar</a>
                                </td>
                                <td>
                                    <a onclick="return confirm('estas seguro de eliminar el curso?');" href="${pageContext.request.contextPath}/cursos/eliminar?id=${c.id}">Eliminar</a>
                                </td>
                            </tr>
                       </c:forEach>

                    </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</body>
</html>