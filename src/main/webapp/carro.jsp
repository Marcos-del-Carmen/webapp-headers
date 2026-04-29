<%@page contentType="text/html" pageEncoding="UTF-8" import="models.*"%>

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
            <table>
                <tr>
                    <td>id</td>
                    <td>nombre</td>
                    <td>precio</td>
                    <td>cantidad</td>
                    <td>total</td>

                </tr>

                <%for(ItemCarro item: carro.getItems()) { %>
                    <tr>
                        <td><%=item.getProducto().getId()%></td>
                        <td><%=item.getProducto().getNombre()%></td>
                        <td><%=item.getProducto().getPrecio()%></td>
                        <td><%=item.getCantidad()%></td>
                        <td><%=item.getImporte()%></td>
                    </tr>
                <%}%>
                <tr>
                    <td colspan="4" style="text-aling: right;">Total</td>
                    <td><%=carro.getTotal()%></td>
                </tr>
            </table>
       <% } %>
       <p><a href="<%=request.getContextPath()%>/productos">seguir comprando</a></p>
       <p><a href="<%=request.getContextPath()%>/index.jsp"></a></p>
</body>
</html>