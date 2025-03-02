<%-- 
    Document   : maquinista.jsp
    Created on : 01-mar-2025, 15:38:17
    Author     : adamj
--%>

<%@page import="ConexionABD.TrabajoDML"%>
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
        
        <form action="Trabajos" method="POST">
            <input type="hidden" name="todo" value="iniciarTrabajoPagina">
            <button type="submit">INICIAR TRABAJOS</button>
        </form>

        <form action="Trabajos" method="POST">
            <input type="hidden" name="todo" value="finalizarTrabajoPagina">
            <button type="submit">FINALIZAR TRABAJOS</button>
        </form>
                
        <h2>TRABAJOS FINALIZADOS</h2>
        
        <table>
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
            int idMaquinista = (int) session.getAttribute("id");
            
            List<Trabajo> trabajos = TrabajoDML.obtenerTrabajosFinalizados(ConexionABD.Conexion.getConexion(), idMaquinista);
            if (trabajos != null) {
                for (Trabajo trabajo : trabajos) {
            %>
            <tr>
                <td><%= trabajo.getId() %></td>
                <td><%= trabajo.getIdParcela()%></td>
                <td><%= trabajo.getIdMaquinista()%></td>
                <td><%= trabajo.getIdMaquina()%></td>
                <td><%= trabajo.getTipo() %></td>
                <td><%= trabajo.getEstado() %></td>
                <td><%= trabajo.getFechaInicio() %></td>
                <td><%= trabajo.getFechaFin() %></td>
            </tr>
            <%
                }
            }
            %>
            
            
        </table>
            
    </body>
</html>
