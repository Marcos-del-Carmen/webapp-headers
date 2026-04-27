<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Iniciar seción</title>
</head>
<body>
    <h1>Iniciar sesión</h1>
    <form action="/webapp-headers/login" method="POST">
        <div>
            <input name="username" id="username" type="text">
            <label for="username">Username</label>
        </div>
        <div>
            <input name="password" id="password" type="password">
            <label for="password">Password</label>
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>
</body>
</html>