<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de cursos</title>
</head>
<body>
    <h1>Formulario de cursos</h1>
    <form action="${pageContext.request.contextPath}/cursos/form" method="POST">
        <div>
            <div>
                <label for="nombre">Nombre</label>
            </div>
            <input type="text" name="nombre" id="nombre" value="${curso.nombre}">

            <c:if test="${errores != null && errores.containsKey('nombre')}">
                <div style="color: red;">${errores.nombre}</div>
            </c:if>
        </div>
        <div>
            <div>
                <label for="descripcion">Descripcion</label>
            </div>
            <input type="text" name="descripcion" id="descripcion" value="${curso.descripcion}">
            <c:if test="${errores != null && errores.containsKey('descripcion')}">
                <div style="color: red;">${errores.descripcion}</div>
            </c:if>
        </div>
        <div>
            <div>
                <label for="instructor">Instructor</label>
            </div>
            <input type="text" name="instructor" id="instructor" value="${curso.instructor}">
            <c:if test="${errores != null && errores.containsKey('instructor')}">
                <div style="color: red;">${errores.instructor}</div>
            </c:if>
        </div>
        <div>
            <div>
                <label for="duracion">Duracion</label>
            </div>
            <input type="text" name="duracion" id="duracion" value="${curso.duracion}">
            <c:if test="${errores != null && errores.containsKey('duracion')}">
                <div style="color: red;">${errores.duracion}</div>
            </c:if>
        </div>
        <div>
            <input type="submit" value="${curso.id != null && curso.id > 0 ? 'Editar' : 'Crear'}">
            <input type="hidden" name="id" id="id" value="${curso.id}">
        </div>
    </form>
</body>
</html>