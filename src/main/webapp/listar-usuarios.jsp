<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="layout/header.jsp" %>
    <div class="my-2">
        <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/usuarios/form">Crear [+]</a>
    </div>
    <table class="table table-primary table-hover table-spinner">
        <thead>
            <tr>
                <td>Id usuario</td>
                <td>Nombre de usuario</td>
                <td>Contraseña</td>
                <td>Correo electronico</td>
                <td colspan="2" class="text-center">Acciones</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${usuarios}" var="u">
                <tr>
                        <td>${u.idUsuario}</td>
                        <td>${u.username}</td>
                        <td>${u.password}</td>
                        <td>${u.email}</td>
                        <td>
                            <a
                                class="btn btn-sm btn-warning"
                                href="${pageContext.request.contextPath}/usuarios/form?idUsuario=${u.idUsuario}">
                                Editar
                            </a>
                        </td>
                        <td>
                            <a
                                class="btn btn-sm btn-danger"
                                href="${pageContext.request.contextPath}/usuarios/eliminar?idUsuario=${u.idUsuario}"
                                onclick="return confirm('Estas seguro de eliminar este producto?')">
                                Eliminar
                            </a>
                        </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<%@include file="layout/footer.jsp" %>
