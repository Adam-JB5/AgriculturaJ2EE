<%-- 
    Document   : agricultor.jsp
    Created on : 01-mar-2025, 15:39:36
    Author     : adamj
--%>

<%@page import="Modelo.Parcela"%>
<%@page import="ConexionABD.ParcelaDML"%>
<%@page import="java.util.List"%>
<%@page import="ConexionABD.TrabajoDML"%>
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
            
    <form action="Facturas" method="POST">
            <input type="hidden" name="todo" value="pagoFacturaPagina">
            <button type="submit">PAGO DE FACTURAS</button>
    </form>
            
            <h2>PARCELAS</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>ID AGRICULTOR</th>
                    <th>UBICACION</th>
                    <th>SUPERFICIE</th>
                    <th>COMUNIDAD AUTÓNOMA</th>
                </tr>
            <%
            int idAgricultor = (int) session.getAttribute("id");
            List<Parcela> parcelas = ParcelaDML.obtenerParcelasAgricultor(ConexionABD.Conexion.getConexion(), idAgricultor);
            if (parcelas != null) {
                    for (Parcela parcela : parcelas) {
            %>
                <tr>
                    <td><%= parcela.getId()%></td>
                    <td><%= parcela.getIdAgricultor()%></td>
                    <td><%= parcela.getUbicacion()%></td>
                    <td><%= parcela.getSuperficie()%></td>
                    <td><%= parcela.getComunidadAutonoma()%></td>
                </tr>
            <%
                }
            }
            %>
            </table>
            <h2>TRABAJOS</h2>
            <h3>TRABAJOS INICIADOS</h3>
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
                    
                List<Trabajo> trabajosIniciados = TrabajoDML.obtenerTrabajosIniciadosAgricultor(ConexionABD.Conexion.getConexion(), idAgricultor);
                if (trabajosIniciados != null) {
                    for (Trabajo trabajo : trabajosIniciados) {
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
            <h3>TRABAJOS FINALIZADOS</h3>
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

                List<Trabajo> trabajosFinalizados = TrabajoDML.obtenerTrabajosFinalizadosAgricultor(ConexionABD.Conexion.getConexion(), idAgricultor);
                if (trabajosFinalizados != null) {
                    for (Trabajo trabajo : trabajosFinalizados) {
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