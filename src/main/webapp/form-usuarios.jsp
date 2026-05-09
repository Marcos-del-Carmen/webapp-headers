
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="layout/header.jsp" %>
    <form action="${pageContext.request.contextPath}/usuarios/form" method="POST">
        <div>
            <div>
                <label class="form-label" for="username">Nombre de usuario</label>
            </div>
            <input class="form-control" type="text" name="username" id="username" value="${usuario.username}">
            <c:if test="${errores != null && errores.containsKey('username')}">
                <div style="color: red;">${errores.username}</div>
            </c:if>
        </div>
        <div>
            <div>
                <label class="form-label" for="password">Contraseña</label>
            </div>
            <input class="form-control" type="password" name="password" id="password" value="${usuario.password}">
            <c:if test="${errores != null && errores.containsKey('password')}">
                <div style="color: red;">${errores.password}</div>
            </c:if>
        </div>
        <div>
            <div>
                <label class="form-label" for="email">Correo electronico</label>
            </div>
            <input class="form-control" class="form-control" type="email" name="email" id="email" value="${usuario.email}">
            <c:if test="${errores != null && errores.containsKey('email')}">
                <div style="color: red;">${errores.email}</div>
            </c:if>
        </div>
        <div>
            <input class="btn btn-primary btn-sm" type="submit" value="${usuario.idUsuario != null && usuario.idUsuario > 0 ? 'Editar' : 'Crear'}">
            <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">
        </div>
    </form>
<%@include file="layout/footer.jsp" %>