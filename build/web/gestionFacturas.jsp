<%-- 
    Document   : gestionFacturas
    Created on : 02-mar-2025, 15:11:36
    Author     : adamj
--%>

<%@page import="Modelo.Trabajo"%>
<%@page import="Modelo.Factura"%>
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
                
        <h2>FACTURAS</h2>
        <h3>GENERACIÓN DE FACTURAS</h3>
        <table border="1">
            <tr>
                <th>ID TRABAJO</th>
                <th>ID PARCELA</th>
                <th>ID MAQUINISTA</th>
                <th>ID MÁQUINA</th>
                <th>TIPO DE TRABAJO</th>
                <th>ESTADO</th>
                <th>FECHA INICIO</th>
                <th>FECHA FIN</th>
            </tr>

            <% 
                List<Trabajo> trabajos = (List<Trabajo>) request.getAttribute("trabajosFinalizados");
                if (trabajos != null && !trabajos.isEmpty()) {
                    for (Trabajo trabajo : trabajos) {
            %>
            <form method="POST" action="Facturas">
                <tr>
                    <td><input type="hidden" name="id" value="<%= trabajo.getId() %>"><%= trabajo.getId()%></td>

                    <!-- ID Parcela: Lista desplegable (suponiendo que tienes datos de parcelas en el servlet) -->
                    <td>
                        <select name="IDparcela" required disabled>
                            <option value="<%= trabajo.getIdParcela() %>" selected><%= trabajo.getIdParcela() %></option>
                        </select>
                        <input type="hidden" name="IDparcela" value="<%= trabajo.getIdParcela() %>">
                    </td>

                    <!-- ID Maquinista: Lista desplegable -->
                    <td>
                        <select name="IDmaquinista" required disabled>
                            <option value="<%= trabajo.getIdMaquinista()%>" selected><%= trabajo.getIdMaquinista()%></option>
                        </select>
                    </td>

                    <!-- ID Maquina: Lista desplegable -->
                    <td>
                        <select name="IDmaquina" required disabled>
                            <option value="<%= trabajo.getIdMaquina()%>" selected><%= trabajo.getIdMaquina()%></option>
                        </select>
                    </td>

                    <!-- Tipo de Trabajo: Lista desplegable -->
                    <td>
                        <select name="tipo" required disabled>
                            <option value="Siembra" <%= trabajo.getTipo().equals("Siembra") ? "selected" : "" %>>Siembra</option>
                            <option value="Fumigacion" <%= trabajo.getTipo().equals("Fumigacion") ? "selected" : "" %>>Fumigación</option>
                            <option value="Cosecha" <%= trabajo.getTipo().equals("Cosecha") ? "selected" : "" %>>Cosecha</option>
                        </select>
                        <input type="hidden" name="tipo" value="<%= trabajo.getTipo()%>">    
                    </td>

                    <!-- Estado: Lista desplegable -->
                    <td>
                        <select name="estado" required disabled>
                            <option value="No asignado" <%= trabajo.getEstado().equals("No asignado") ? "selected" : "" %>>No asignado</option>
                            <option value="Asignado" <%= trabajo.getEstado().equals("Asignado") ? "selected" : "" %>>Asignado</option>
                            <option value="Iniciado" <%= trabajo.getEstado().equals("Iniciado") ? "selected" : "" %>>Iniciado</option>
                            <option value="Finalizado" <%= trabajo.getEstado().equals("Finalizado") ? "selected" : "" %>>Finalizado</option>
                        </select>
                    </td>

                    <!-- Fecha de Inicio -->
                    <td><input type="date" name="FechaInicio" value="<%= trabajo.getFechaInicio() %>" disabled></td>

                    <!-- Fecha de Fin -->
                    <td><input type="date" name="FechaFin" value="<%= trabajo.getFechaFin() %>" disabled></td>

                    <td><input type="hidden" name="todo" value="generarFactura"><button type="submit" style="background-color: red; color: white; border: none; padding: 5px 10px; cursor: pointer;">GENERAR FACTURA</button></td>
                </tr>
            </form>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="8">No hay trabajos finalizados.</td>
            </tr>
            <% 
                }
            %>
        </table>
        <h3>LISTAR FACTURAS</h3>
        <table>
            <tr>
                <th>ID FACTURA</th>
                <th>ID TRABAJO</th>
                <th>DINERO (€)</th>
                <th>ESTADO</th>
                <th>FECHA EMISIÓN</th>
                <th>FECHA PAGO</th>
            </tr>
            <% 
            List<Factura> facturas = (List<Factura>) request.getAttribute("facturas");
            if (facturas != null && !facturas.isEmpty()) {
                for (Factura factura : facturas) {
            %>
            <tr>
                <td><input type="hidden" name="id" value="<%= factura.getId() %>"><%= factura.getId() %></td>
                <td><input type="text" value="<%= factura.getIdTrabajo() %>" disabled></td>
                <td><input type="text" value="<%= factura.getDinero() %>" disabled></td>
                <td><input type="text" value="<%= factura.getEstado() %>" disabled></td>
                <td><input type="date" value="<%= factura.getFechaEmision() %>" disabled></td>
                <td><input type="date" value="<%= factura.getFechaPago() %>" disabled></td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="6">No hay facturas registradas.</td>
            </tr>
            <% 
                }
            %>
        </table>
    </body>
</html>
