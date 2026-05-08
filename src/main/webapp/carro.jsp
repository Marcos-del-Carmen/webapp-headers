<%@ page contentType="text/html" pageEncoding="UTF-8" import="models.*"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    Carro carro = (Carro) session.getAttribute("carro");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro compras</title>
</head>
<body>
    <h1>Carro compras</h1>
       <c:choose>
           <c:when test="${sessionScope.carro==null || sessionScope.carro.items.isEmpty()}">
                <p>Lo siento pero no hay productos en el carrito de compras</p>
           </c:when>
           <c:otherwise>
               <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="POST">
                    <table>
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
                    <a href="javascript:document.formcarro.submit()">Actualizar</a>
               </form>
           </c:otherwise>
       </c:choose>
       <p><a href="${pageContext.request.contextPath}/productos">seguir comprando</a></p>
       <p><a href="${pageContext.request.contextPath}/index.jsp"></a></p>
</body>
</html>