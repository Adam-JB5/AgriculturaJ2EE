<%-- 
    Document   : registro
    Created on : 28-feb-2025, 16:02:32
    Author     : adamj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/styleRegistro.css">
    </head>
    <body>
        <div id="pantalla">

            <div id="cuadroForm">

                <h1>AGRIWARE<img src="media/img/Administrador.png" alt="" width="30" height="30"></h1>

                <form action="Controlador" method="post"><br>
                    <label for="nombre">INTRODUCE TU NOMBRE:</label><br>
                    <input type="text" name="nombre" id="nombre" required><br><br>

                    <label for="apellidos">INTRODUCE TUS APELLIDOS:</label><br>
                    <input type="text" name="apellidos" id="apellidos" required><br><br>

                    <label for="email">INTRODUCE TU EMAIL:</label><br>
                    <input type="email" name="email" id="email" required><br><br>

                    <label for="contra">INTRODUCE TU CONTRASEÑA:</label><br>
                    <input type="password" name="contra" id="contra" required><br><br>
                    
                    <input type="hidden" name="todo" value="registroUsuario">

                    <input type="submit" name="enviarRegistro" value="ENVIAR">
                </form>

            </div>
        </div>
    </body>
</html>
