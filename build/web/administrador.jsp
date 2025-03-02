<%-- 
    Document   : administrador.jsp
    Created on : 01-mar-2025, 15:37:55
    Author     : adamj
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Usuario"%>
<%@page import="ConexionABD.UsuarioDML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/styleMenu.css">
    </head>
    <body>
        <header>
            <h1>¡BIENVENIDO <%=session.getAttribute("nombre")%>!</h1>
            <div id="rolImagen">
                <span>ROL:<%=session.getAttribute("tipo")%></span>
                <img id="imagen" src="media/img/<%=session.getAttribute("tipo")%>.png" alt="">
                <form action="Controlador" method="POST">
                    <input type="hidden" name="todo" value="cerrarSesion">
                    <button type="submit">CERRAR SESIÓN</button>
                </form>
            </div>
        </header>
        
        <form action="Parcelas" method="POST">
            <input type="hidden" name="todo" value="gestionParcelas">
            <button type="submit">GESTIÓN PARCELAS</button>
        </form>
        
        <form action="Maquinas" method="POST">
            <input type="hidden" name="todo" value="gestionMaquinas">
            <button type="submit">GESTIÓN MÁQUINAS</button>
        </form>
                
        <form action="Trabajos" method="POST">
            <input type="hidden" name="todo" value="gestionTrabajos">
            <button type="submit">GESTIÓN TRABAJOS</button>
        </form>
                
        <form action="Facturas" method="POST">
            <input type="hidden" name="todo" value="gestionFacturas">
            <button type="submit">GESTIÓN FACTURAS</button>
        </form>
                
        <h2>USUARIOS</h2>
        
        <table border="1">
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>APELLIDOS</th>
            <th>EMAIL</th>
            <th>CONTRASEÑA</th>
            <th>TIPO</th>
            <th>ACTUALIZAR</th>
            <th>ELIMINAR</th>
        </tr>

    <%
        List<Usuario> usuarios = UsuarioDML.listar(ConexionABD.Conexion.getConexion());
        
        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
    %>
        <tr>
            <form method="POST" action="Usuarios">
                <td><input type="hidden" name="id" value="<%= usuario.getId() %>"> <%= usuario.getId() %></td>
                <td><input type="text" name="nombre" value="<%= usuario.getNombre() %>"></td>
                <td><input type="text" name="apellidos" value="<%= usuario.getApellidos() %>"></td>
                <td><input type="email" name="email" value="<%= usuario.getEmail() %>"></td>
                <td><input type="password" name="contrasenna" value="<%= usuario.getContrasenna() %>"></td>
                <td>
                    <select name="tipo">
                        <option value="No asignado" <%= usuario.getTipo() == null ? "selected" : "" %>>No asignado</option>
                        <option value="Agricultor" <%= (usuario.getTipo() != null && usuario.getTipo().equals("Agricultor")) ? "selected" : "" %>>Agricultor</option>
                        <option value="Maquinista" <%= (usuario.getTipo() != null && usuario.getTipo().equals("Maquinista")) ? "selected" : "" %>>Maquinista</option>
                        <option value="Administrador" <%= (usuario.getTipo() != null && usuario.getTipo().equals("Administrador")) ? "selected" : "" %>>Administrador</option>
                    </select>
                </td>
                <td>
                    <input type="hidden" name="todo" value="actualizarUsuario">
                    <button type="submit" name="action" value="actualizar">Actualizar</button>
                </td>
            </form>

            <td>
                <form method="POST" action="Usuarios" onsubmit="return confirm('¿Estás seguro de eliminar este usuario?');">
                    <input type="hidden" name="id" value="<%= usuario.getId() %>">
                    <input type="hidden" name="todo" value="eliminarUsuario">
                    <button type="submit" name="action" value="eliminar" style="background-color: red; color: white;">Eliminar</button>
                </form>
            </td>
        </tr>
    <%
            }
        }
    %>
    </table>
    

    </body>
</html>
