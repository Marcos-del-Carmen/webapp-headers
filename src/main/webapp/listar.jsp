<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tabla de productos</title>
</head>
<body>
    <c:if test="${username.isPresent()}" >
        <div>Hola ${username.get()}, bienvenido! </div>
        <p><a href="${pageContext.request.contextPath}/producto/form">Crear[+]</a></p>
    </c:if>
    <h1>Tabla de productos</h1>
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Nombre</td>
            <td>Tipo</td>
            <c:if test="${username.isPresent()}">
                <td>Precio</td>
                <td>Compra producto</td>
                <td>Editar</td>
                <td>Eliminar</td>
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
                            <a href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">
                                Agregar a carro
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/producto/form?id=${p.id}">
                                Editar
                            </a>
                        </td>
                        <td>
                            <a
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
</body>
</html>