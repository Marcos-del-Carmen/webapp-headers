<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<%@include file="layout/header.jsp" %>
        <h3>${title}</h3>
        <div class="my-2">
            <a href="${pageContext.request.contextPath}/cursos/form" class="btn btn-primary">Crear [+]</a>
        </div>
        <form action="${pageContext.request.contextPath}/cursos/buscar" method="POST">
            <label class="form-label" for="nombre">Nombre del curso </label><br>
            <input class="form-control" type="text" name="nombre" id="nombre">
            <input class="btn btn-primary" type="submit" value="Buscar">
        </form>
        <c:choose>
            <c:when test="${cursos.isEmpty()}">
                <p class="alert alert-danger">No hay cursos disponibles...</p>
            </c:when>
            <c:otherwise>
                <table class="table table-primary table-hover table-striped my-2">
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
                                        <a class="btn btn-warning" href="${pageContext.request.contextPath}/cursos/form?id=${c.id}">
                                            Editar
                                        </a>
                                    </td>
                                    <td>
                                        <a class="btn btn-danger" onclick="return confirm('estas seguro de eliminar el curso?');" href="${pageContext.request.contextPath}/cursos/eliminar?id=${c.id}">
                                            Eliminar
                                        </a>
                                    </td>
                                </tr>
                           </c:forEach>

                        </tbody>
                </table>
            </c:otherwise>
        </c:choose>
<%@include file="layout/footer.jsp" %>