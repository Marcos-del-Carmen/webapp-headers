<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<%@include file="layout/header.jsp" %>
        <h3>${title}</h3>
        <form action="${pageContext.request.contextPath}/cursos/form" method="POST">
            <div>
                <div>
                    <label class="form-label" for="nombre">Nombre</label>
                </div>
                <input class="form-control" type="text" name="nombre" id="nombre" value="${curso.nombre}">
    
                <c:if test="${errores != null && errores.containsKey('nombre')}">
                    <div style="color: red;">${errores.nombre}</div>
                </c:if>
            </div>
            <div>
                <div>
                    <label class="form-label" for="descripcion">Descripcion</label>
                </div>
                <input class="form-control" type="text" name="descripcion" id="descripcion" value="${curso.descripcion}">
                <c:if test="${errores != null && errores.containsKey('descripcion')}">
                    <div style="color: red;">${errores.descripcion}</div>
                </c:if>
            </div>
            <div>
                <div>
                    <label class="form-label" for="instructor">Instructor</label>
                </div>
                <input class="form-control" type="text" name="instructor" id="instructor" value="${curso.instructor}">
                <c:if test="${errores != null && errores.containsKey('instructor')}">
                    <div style="color: red;">${errores.instructor}</div>
                </c:if>
            </div>
            <div>
                <div>
                    <label class="form-label" for="duracion">Duracion</label>
                </div>
                <input class="form-control" type="text" name="duracion" id="duracion" value="${curso.duracion}">
                <c:if test="${errores != null && errores.containsKey('duracion')}">
                    <div style="color: red;">${errores.duracion}</div>
                </c:if>
            </div>
            <div>
                <input class="btn btn-primary" type="submit" value="${curso.id != null && curso.id > 0 ? 'Editar' : 'Crear'}">
                <input type="hidden" name="id" id="id" value="${curso.id}">
            </div>
        </form>
<%@include file="layout/footer.jsp" %>