<%-- 
    Document   : gestionParcelas
    Created on : 02-mar-2025, 15:11:26
    Author     : adamj
--%>

<%@page import="Modelo.Parcela"%>
<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
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
             
        <h2>PARCELAS</h2>
        <h3>MODIFICAR Y ELIMINAR PARCELAS</h3>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>ID AGRICULTOR</th>
                    <th>UBICACIÓN</th>
                    <th>SUPERFICIE</th>
                    <th>COMUNIDAD AUTÓNOMA</th>
                    <th>Acciones</th>
                </tr>
                <%
            List<Parcela> parcelas = (List<Parcela>) request.getAttribute("parcelas");
            List<Usuario> agricultores = (List<Usuario>) request.getAttribute("agricultores");
            for (Parcela parcela : parcelas) {
        %>
        <tr>
            <form method="POST" action="Parcelas">
                <td><input type="hidden" name="id" value="<%= parcela.getId() %>"><%= parcela.getId() %></td>
                <td>
                    <select name="IDagricultor">
                        <%
                            for (Usuario agricultor : agricultores) {
                                String selected = (parcela.getIdAgricultor() == agricultor.getId()) ? "selected" : "";
                        %>
                        <option value="<%= agricultor.getId() %>" <%= selected %>><%= agricultor.getId() %></option>
                        <% } %>
                    </select>
                </td>
                <td><input type="text" name="ubicacion" value="<%= parcela.getUbicacion() %>"></td>
                <td><input type="number" name="superficie" value="<%= parcela.getSuperficie() %>"></td>
                <td><input type="text" name="comunidadAutonoma" value="<%= parcela.getComunidadAutonoma() %>"></td>
                <td>
                    <input type="hidden" name="todo" value="actualizarParcela">
                    <button type="submit">Actualizar</button>
                </td>
            </form>
            <td>
                <form method="POST" action="Parcelas" onsubmit="return confirm('¿Estás seguro de que deseas eliminar esta parcela?');">
                    <input type="hidden" name="id" value="<%= parcela.getId() %>">
                    <input type="hidden" name="todo" value="eliminarParcela">
                    <button type="submit" style="background-color: red; color: white; border: none; padding: 5px 10px; cursor: pointer;">Eliminar</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <hr>
        <h3>CREAR PARCELAS</h3>
        <form method="POST" action="Parcelas">
        <table border="1">
            <tr>
                <th>ID AGRICULTOR</th>
                <th>UBICACIÓN</th>
                <th>SUPERFICIE</th>
                <th>COMUNIDAD AUTÓNOMA</th>
                <th>Acción</th>
            </tr>
            <tr>
                <td>
                    <select name="IDagricultor">
                        <%
                            for (Usuario agricultor : agricultores) {
                        %>
                        <option value="<%= agricultor.getId() %>"><%= agricultor.getId() %></option>
                        <% } %>
                    </select>
                </td>
                <td><input type="text" name="ubicacion" required></td>
                <td><input type="text" name="superficie" pattern="^\d{1,8}(\.\d{1,2})?$" required title="Introduce un número con hasta 8 dígitos enteros y 2 decimales (ej. 12345678.12)"></td>
                <td><input type="text" name="comunidadAutonoma" required></td>
                <td><input type="hidden" name="todo" value="crearParcela"><button type="submit">Crear</button></td>
            </tr>
        </table>
    </form>
    </body>
</html>
