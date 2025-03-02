<%-- 
    Document   : login
    Created on : 28-feb-2025, 16:01:25
    Author     : adamj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesión</title>
    <link rel="stylesheet" href="css/styleLogin.css">
    <link rel="preload" href="media/img/campo.jpg" as="image">
</head>
<body>

   
    <div id="pantalla">
        <% if (request.getAttribute("error") != null) { %>
            <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>
        <div id="cuadroForm">

            <h1>AGRIWARE<img src="media/img/Agricultor.png" alt="" width="30" height="30"></h1>
            <p style="color:rgb(167, 167, 167);">¿Todavía no tienes usuario?&nbsp;&nbsp;&nbsp;<a style="text-decoration: none;" href="./registro.jsp">REGÍSTRATE</a></p><br>

            <form action="Controlador" method="post">

                    <label for="email">INTRODUCE TU EMAIL:</label><br>
                    <input type="email" name="email" id="email" required><br><br><br><br>

                    <label for="contra">INTRODUCE TU CONTRASEÑA:</label><br>
                    <input type="password" name="contra" id="contra" required><br><br>
                    
                    <input type="hidden" name="todo" value="inicioSesion">

                    <input type="submit" name="enviarLogin" value="ENVIAR">

            </form>
            
            
        </div>
    </div>

</body>
</html>
