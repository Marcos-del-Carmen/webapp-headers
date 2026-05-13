<%@page contentType="text/html" pageEncoding="UTF-8"  import="java.time.format.*" %>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="layout/header.jsp" %>
            <h3>${title}</h3>
            <form action="${pageContext.request.contextPath}/producto/form" method="POST" class="">
                <div>
                    <label class="form-label" for="nombre">Nombre</label>
                    <div>
                        <input class="form-control" type="text" name="nombre" id="nombre" value="${producto.nombre}">
                    </div>
                    <c:if test="${errores != null && errores.containsKey('nombre')}">
                        <div style="color: red;">${errores.nombre}</div>
                    </c:if>
                </div>
                <div>
                    <label class="form-label" for="sku">Sku</label>
                    <div>
                        <input class="form-control" type="text" name="sku" id="sku" value="${producto.sku}">
                    </div>
                    <c:if test="${errores != null && errores.containsKey('sku')}">
                        <div style="color: red;">${errores.sku}</div>
                    </c:if>
                </div>
                <div>
                    <label class="form-label" for="precio">Precio</label>
                    <div>
                        <input class="form-control" type="number" name="precio" id="precio" value="${producto.precio}">
                    </div>
                    <c:if test="${errores != null && errores.containsKey('precio')}">
                        <div style="color: red;">${errores.precio}</div>
                    </c:if>
                </div>
                <div>
                    <label class="form-label" for="fecha_registro">Fecha de registro</label>
                    <div>
                        <input class="form-control" type="date" name="fecha_registro" id="fecha_registro" value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern('yyyy-MM-dd')) : ''}">
                    </div>
                    <c:if test="${errores != null && errores.containsKey('fecha_registro')}">
                        <div style="color: red;">${errores.fecha_registro}</div>
                    </c:if>
                </div>
                <div>
                    <label class="form-label" for="categoria">Categoria</label>
                    <div>
                        <select name="categoria" id="categoria" class="form-select">
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
                    <input class="btn btn-primary" type="submit" value="${producto.id !=null && producto.id > 0 ? 'Editar': 'Crear' }">
                    <input type="hidden" value="${producto.id}" name="id">
                </div>
            </form>
<%@include file="layout/footer.jsp" %>