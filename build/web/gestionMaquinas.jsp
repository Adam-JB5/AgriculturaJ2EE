<%-- 
    Document   : gestionMaquinas
    Created on : 02-mar-2025, 15:11:54
    Author     : adamj
--%>

<%@page import="Modelo.Maquina"%>
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
                
        <h2>MÁQUINAS</h2>
        <h3>MODIFICAR Y ELIMINAR MÁQUINAS</h3>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>TIPO</th>
                <th>ESTADO</th>
                <th>Acciones</th>
            </tr>
            <%
                List<Maquina> maquinas = (List<Maquina>) request.getAttribute("maquinas");
                for (Maquina maquina : maquinas) {
            %>
            <tr>
                <form method="POST" action="Maquinas">
                    <td><input type="hidden" name="id" value="<%= maquina.getId() %>"><%= maquina.getId() %></td>
                    <td>
                        <select name="tipo">
                            <option value="Siembra" <%= "Siembra".equals(maquina.getTipo()) ? "selected" : "" %>>Sembradora</option>
                            <option value="Fumigacion" <%= "Fumigacion".equals(maquina.getTipo()) ? "selected" : "" %>>Fumigadora</option>
                            <option value="Cosecha" <%= "Cosecha".equals(maquina.getTipo()) ? "selected" : "" %>>Cosechadora</option>
                        </select>
                    </td>
                    <td>
                        <select name="estado">
                            <option value="Libre" <%= "Libre".equals(maquina.getEstado()) ? "selected" : "" %>>Libre</option>
                            <option value="Ocupada" <%= "Ocupada".equals(maquina.getEstado()) ? "selected" : "" %>>Ocupada</option>
                        </select>
                    </td>
                    <td>
                        <input type="hidden" name="todo" value="actualizarMaquina">
                        <button type="submit">Actualizar</button>
                    </td>
                </form>
                <td>
                    <form method="POST" action="Maquinas" onsubmit="return confirm('¿Estás seguro de que deseas eliminar esta máquina?');">
                        <input type="hidden" name="id" value="<%= maquina.getId() %>">
                        <input type="hidden" name="todo" value="eliminarMaquina">
                        <button type="submit" style="background-color: red; color: white; border: none; padding: 5px 10px; cursor: pointer;">Eliminar</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <h3>CREAR MÁQUINAS</h3>
        <form method="POST" action="Maquinas">
            <table border="1">
                <tr>
                    <th>TIPO DE MÁQUINA</th>
                    <th>ESTADO</th>
                    <th>Acción</th>
                </tr>
                <tr>
                    <td>
                        <select name="tipo" required>
                            <option value="Siembra">Sembradora</option>
                            <option value="Fumigacion">Fumigadora</option>
                            <option value="Cosecha">Cosechadora</option>
                        </select>
                    </td>
                    <td>
                        <select name="estado" required>
                            <option value="Libre">Libre</option>
                            <option value="Ocupada">Ocupada</option>
                        </select>
                    </td>
                    <td><input type="hidden" name="todo" value="crearMaquina"><button type="submit">Crear</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
