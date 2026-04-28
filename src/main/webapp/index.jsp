<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Session HTTP</title>
    </head>
    <body>
        <h1>Tarea 5: Session HTTP</h1>
        <p>Hola <%=session.getAttribute("nombre") != null? session.getAttribute("nombre"): "anónimo"%>, bienvenido a la tarea5.</p>

        <form action="/webapp-headers/guardar-session" method="POST">
            <div>
                <label for="nombre">Ingresa tú nombre</label> <br>
                <input id="nombre" name="nombre" type="text">
            </div>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
