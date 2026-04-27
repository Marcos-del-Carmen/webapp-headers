<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manejo de cookies HTTP </title>
    </head>
    <body>
        <h3 style="color: ${cookie.color.getValue()}">Tarea 4: cambiar el color de los textos</h3>
        <p style="color: ${cookie.color.getValue()}">No leemos ni escribos poseisa por que sea romantico, leemos y escribimos poseisa por que somos parte de la humanidad,<b>
        y la humanidad rebosa pasión.</b></p>
        <form action="/webapp-headers/cambiar-color" method="GET">
          <select name="color" id="color">
            <option value="blue">Azul</option>
            <option value="red">Rojo</option>
            <option value="green">Verde</option>
            <option value="aqua">Aqua</option>
            <option value="BlueViolet">Violeta</option>
            <option value="Gray">Gris</option>
            <option value="Cyan">Cyan</option>
          </select>
          <input type="submit" value="cambiar">
        </form>
    </body>
</html>
