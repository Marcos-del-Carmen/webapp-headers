<%@ page contentType="text/html" pageEncoding="UTF-8" import="models.*"%>
<%@ page isELIgnored="false" %>
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
       <%if (carro==null || carro.getItems().isEmpty()) { %>
            <p>Lo siento pero no hay productos en el carrito de compras</p>
       <% } else { %>
           <form name="formcarro" action="/webapp-headers/actualizar-carro" method="POST">
                <table>
                    <tr>
                        <td>id</td>
                        <td>nombre</td>
                        <td>precio</td>
                        <td>cantidad</td>
                        <td>total</td>
                        <td>borrar</td>
                    </tr>

                    <% for(ItemCarro item: carro.getItems()) { %>
                        <tr>
                            <td><%=item.getProducto().getId()%></td>
                            <td><%=item.getProducto().getNombre()%></td>
                            <td><%=item.getProducto().getPrecio()%></td>
                            <td><input type="text" value="<%=item.getCantidad()%>" name="cant_<%=item.getProducto().getId()%>" size="4"></td>
                            <td><%=item.getImporte()%></td>
                            <td><input type="checkbox" name="deleteProductos" value="<%=item.getProducto().getId()%>"></td>
                        </tr>
                    <%}%>
                    <tr>
                        <td colspan="4" style="text-aling: right;">Total</td>
                        <td><%=carro.getTotal()%></td>
                    </tr>
                </table>
                <a href="javascript:document.formcarro.submit()">Actualizar</a>
           </form>
       <% } %>
       <p><a href="<%=request.getContextPath()%>/productos">seguir comprando</a></p>
       <p><a href="<%=request.getContextPath()%>/index.jsp"></a></p>
</body>
</html>