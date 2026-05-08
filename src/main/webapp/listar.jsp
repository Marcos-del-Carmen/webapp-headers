<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="layout/header.jsp" %>
        <h3>${title}</h3>
        <c:if test="${username.isPresent()}" >
            <div class="alert alert-info">Hola ${username.get()}, bienvenido! </div>
            <div><a class="btn btn-primary" href="${pageContext.request.contextPath}/producto/form">Crear[+]</a></div>
        </c:if>
        <h1>Tabla de productos</h1>
        <table class="table table-primary table-hover table-striped">
            <tr>
                <td>Id</td>
                <td>Nombre</td>
                <td>Tipo</td>
                <c:if test="${username.isPresent()}">
                    <td>Precio</td>
                    <td colspan="3" class="text-center">Acciones</td>
                </c:if>
            </tr>

            <c:if test="${productos != null}">
                <c:forEach items="${productos}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nombre}</td>
                        <td>${p.categoria.nombre}</td>

                        <c:if test="${username.isPresent()}">
                            <td>${p.precio}</td>
                            <td>
                                <a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">
                                    Agregar a carro
                                </a>
                            </td>
                            <td>
                                <a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/producto/form?id=${p.id}">
                                    Editar
                                </a>
                            </td>
                            <td>
                                <a class="btn btn-sm btn-danger"
                                 onclick="return confirm('Estas seguro de eliminar este producto?')"
                                 href="${pageContext.request.contextPath}/producto/eliminar?id=${p.id}">
                                    Eliminar
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
        </table>

        <p>Mensaje app:${applicationScope.mensaje}</p>
        <p>Mensaje del request:${requestScope.mensaje}</p>
<%@include file="layout/footer.jsp" %>