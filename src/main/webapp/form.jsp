<%@page contentType="text/html" pageEncoding="UTF-8"  import="java.time.format.*" %>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de producto</title>
</head>
    <body>
        <h1>Formulario de producto</h1>
        <form action="${pageContext.request.contextPath}/producto/form" method="POST">
            <div>
                <label for="nombre">Nombre</label>
                <div>
                    <input type="text" name="nombre" id="nombre" value="${producto.nombre}">
                </div>
                <c:if test="${errores != null && errores.containsKey('nombre')}">
                    <div style="color: red;">${errores.nombre}</div>
                </c:if>
            </div>
            <div>
                <label for="sku">Sku</label>
                <div>
                    <input type="text" name="sku" id="sku" value="${producto.sku}">
                </div>
                <c:if test="${errores != null && errores.containsKey('sku')}">
                    <div style="color: red;">${errores.sku}</div>
                </c:if>
            </div>
            <div>
                <label for="precio">Precio</label>
                <div>
                    <input type="number" name="precio" id="precio" value="${producto.precio}">
                </div>
                <c:if test="${errores != null && errores.containsKey('precio')}">
                    <div style="color: red;">${errores.precio}</div>
                </c:if>
            </div>
            <div>
                <label for="fecha_registro">Fecha de registro</label>
                <div>
                    <input type="date" name="fecha_registro" id="fecha_registro" value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern('yyyy-MM-dd')) : ''}">
                </div>
                <c:if test="${errores != null && errores.containsKey('fecha_registro')}">
                    <div style="color: red;">${errores.fecha_registro}</div>
                </c:if>
            </div>
            <div>
                <label for="categoria">Categoria</label>
                <div>
                    <select name="categoria" id="categoria">
                        <option value="">-- selecciona la categoria --</option>
                        <c:forEach items="${categorias}" var="c">
                            <option value="${c.id}" ${c.id.equals(producto.categoria.id) ? "selected" : ""}>${c.nombre}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${errores != null && errores.containsKey('categoria')}">
                        <div style="color: red;">${errores.categoria}</div>
                    </c:if>
                </div>
            </div>
            <div>
                <input type="submit" value="${producto.id !=null && producto.id > 0 ? 'Editar': 'Crear' }">
                <input type="hidden" value="${producto.id}" name="id">
            </div>
        </form>
    </body>
</html>