<%-- 
    Document   : gestionTrabajos
    Created on : 02-mar-2025, 15:11:44
    Author     : adamj
--%>

<%@page import="Modelo.Parcela"%>
<%@page import="Modelo.Maquina"%>
<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Trabajo"%>
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
                
        <h2>TRABAJOS</h2>
        <h3>CREAR TRABAJOS</h3>
        <form method="POST" action="Trabajos">
            <table border="1">
                <tr>
                    <th>TIPO DE TRABAJO</th>
                    <th>ESTADO</th>
                    <th>Acción</th>
                </tr>
                <tr>
                    <td>
                        <select name="tipo" required>
                            <option value="Siembra">Siembra</option>
                            <option value="Fumigacion">Fumigación</option>
                            <option value="Cosecha">Cosecha</option>
                        </select>
                    </td>
                    <td>
                        <select name="estado" required>
                            <option value="No asignado">No asignado</option>
                        </select>
                    </td>
                    <td><input type="hidden" name="todo" value="crearTrabajo"><button type="submit">Crear Trabajo</button></td>
                </tr>
            </table>
        </form>
        <h3>ASIGNAR TRABAJOS</h3>
        <table border="1">
            <tr>
                <th>ID TRABAJO</th>
                <th>ID PARCELA</th>
                <th>ID MAQUINISTA</th>
                <th>ID MÁQUINA</th>
                <th>TIPO DE TRABAJO</th>
                <th>ESTADO</th>
                <th>Acción</th>
            </tr>
            <%
                List<Trabajo> trabajosNoAsignados = (List<Trabajo>) request.getAttribute("trabajosNoAsignados");
                List<Parcela> parcelas = (List<Parcela>) request.getAttribute("parcelas");
                List<Usuario> maquinistas = (List<Usuario>) request.getAttribute("maquinistas");
                List<Maquina> maquinas = (List<Maquina>) request.getAttribute("maquinas");

                for (Trabajo trabajo : trabajosNoAsignados) {
            %>
            <tr>
                <form method="POST" action="Trabajos">
                    <td><input type="hidden" name="id" value="<%= trabajo.getId() %>"><%= trabajo.getId() %></td>

                    <td>
                        <select name="IDparcela" required>
                            <% for (Parcela parcela : parcelas) { %>
                            <option value="<%= parcela.getId() %>" <%= trabajo.getIdParcela() == parcela.getId() ? "selected" : "" %>><%= parcela.getId() %></option>
                            <% } %>
                        </select>
                    </td>

                    <td>
                        <select name="IDmaquinista" required>
                            <% for (Usuario maquinista : maquinistas) { %>
                            <option value="<%= maquinista.getId() %>" <%= trabajo.getIdMaquinista() == maquinista.getId() ? "selected" : "" %>><%= maquinista.getId() %></option>
                            <% } %>
                        </select>
                    </td>

                    <td>
                        <select name="IDmaquina" required>
                            <% for (Maquina maquina : maquinas) { %>
                            <option value="<%= maquina.getId() %>" <%= trabajo.getIdMaquina() == maquina.getId() ? "selected" : "" %>><%= maquina.getId() %></option>
                            <% } %>
                        </select>
                    </td>

                    <td>
                        <select name="tipo" required>
                            <option value="Siembra" <%= "Siembra".equals(trabajo.getTipo()) ? "selected" : "" %>>Siembra</option>
                            <option value="Fumigacion" <%= "Fumigacion".equals(trabajo.getTipo()) ? "selected" : "" %>>Fumigación</option>
                            <option value="Cosecha" <%= "Cosecha".equals(trabajo.getTipo()) ? "selected" : "" %>>Cosecha</option>
                        </select>
                    </td>

                    <td>
                        <select name="estado" required>
                            <option value="No asignado" <%= "No asignado".equals(trabajo.getEstado()) ? "selected" : "" %>>No asignado</option>
                            <option value="Asignado" <%= "Asignado".equals(trabajo.getEstado()) ? "selected" : "" %>>Asignado</option>
                        </select>
                    </td>

                    <td><input type="hidden" name="todo" value="asignarTrabajo"><button type="submit">Asignar Trabajo</button></td>
                </form>
            </tr>
            <% } %>
        </table>
        <h3>LISTADO DE TRABAJOS</h3>
        <table border="1">
            <tr>
                <th>ID TRABAJO</th>
                <th>ID PARCELA</th>
                <th>ID MAQUINISTA</th>
                <th>ID MÁQUINA</th>
                <th>TIPO DE TRABAJO</th>
                <th>ESTADO</th>
            </tr>
            <%
            List<Trabajo> trabajos = (List<Trabajo>) request.getAttribute("trabajos");
            
            for (Trabajo trabajo : trabajos) {
            %>
            <tr>
                <td><%= trabajo.getId()%></td>
                <td><%= trabajo.getIdParcela() == 0 ? "Sin asignar" : trabajo.getIdParcela() %></td>
                <td><%= trabajo.getIdMaquinista() == 0 ? "Sin asignar" : trabajo.getIdMaquinista() %></td>
                <td><%= trabajo.getIdMaquina() == 0 ? "Sin asignar" : trabajo.getIdMaquina() %></td>
                <td><%= trabajo.getTipo()%></td>
                <td><%= trabajo.getEstado()%></td>
            </tr>
            <%
            }
            %>
    </body>
</html>
