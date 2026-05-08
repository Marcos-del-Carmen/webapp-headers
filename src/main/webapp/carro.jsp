<%@ page contentType="text/html" pageEncoding="UTF-8" import="models.*"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    Carro carro = (Carro) session.getAttribute("carro");
%>
<%@include file="layout/header.jsp" %>
           <h3>${title}</h3>
           <c:choose>
               <c:when test="${sessionScope.carro==null || sessionScope.carro.items.isEmpty()}">
                    <div class="alert alert-warning">Lo siento pero no hay productos en el carrito de compras</div>
               </c:when>
               <c:otherwise>
                   <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="POST">
                        <table class="table table-hover table-striped">
                            <tr>
                                <td>id</td>
                                <td>nombre</td>
                                <td>precio</td>
                                <td>cantidad</td>
                                <td>total</td>
                                <td>borrar</td>
                            </tr>

                            <c:forEach items="${sessionScope.carro.items}" var="item">
                                <tr>
                                    <td>${item.producto.id}</td>
                                    <td>${item.producto.nombre}</td>
                                    <td>${item.producto.precio}</td>
                                    <td><input type="text" value="${item.cantidad}" name="cant_${item.producto.id}" size="4"></td>
                                    <td>${item.importe}</td>
                                    <td><input type="checkbox" name="deleteProductos" value="${item.producto.id}"></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="4" style="text-aling: right;">Total</td>
                                <td>${carro.total}</td>
                            </tr>
                        </table>
                        <a class="btn btn-warning" href="javascript:document.formcarro.submit()">Actualizar</a>
                   </form>
               </c:otherwise>
           </c:choose>
           <div><a class="btn btn-primary" href="${pageContext.request.contextPath}/productos">Seguir comprando</a>
           <a class="btn btn-danger" href="${pageContext.request.contextPath}/index.jsp">Volver</a></div>
<%@include file="layout/footer.jsp" %>