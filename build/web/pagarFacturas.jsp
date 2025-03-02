<%-- 
    Document   : pagarFacturas.jsp
    Created on : 01-mar-2025, 15:39:36
    Author     : adamj
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Factura"%>
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
            
    <h2>GESTIÓN DE FACTURAS</h2>
    <h3>FACTURAS POR PAGAR</h3>
            
    <table border="1">
        <tr>
            <th>ID FACTURA</th>
            <th>ID TRABAJO</th>
            <th>DINERO (€)</th>
            <th>ESTADO</th>
            <th>FECHA EMISIÓN</th>
            <th>FECHA PAGO</th>
        </tr>

        <%
        // Obtener las facturas por pagar desde el request
        List<Factura> facturasPendientes = (List<Factura>) request.getAttribute("facturasPorPagar");

        // Comprobar si hay facturas pendientes
        if (facturasPendientes != null) {
            for (Factura factura : facturasPendientes) {
        %>
        <form method="POST" action="Facturas">
            <tr>
                <td>
                    <input type="hidden" name="id" value="<%= factura.getId() %>">
                    <%= factura.getId() %>
                </td>
                <td>
                    <input type="text" value="<%= factura.getIdTrabajo() %>" disabled>
                </td>
                <td>
                    <input type="text" value="<%= factura.getDinero() %>" disabled>
                </td>
                <td>
                    <input type="text" value="<%= factura.getEstado() %>" disabled>
                </td>
                <td>
                    <input type="date" value="<%= factura.getFechaEmision() %>" disabled>
                </td>
                <td>
                    <input type="date" value="<%= factura.getFechaPago() %>" disabled>
                </td>
                <td>
                    <input type="hidden" name="todo" value="pagoFactura">
                    <button type="submit">PAGAR FACTURA</button>
                </td>
            </tr>
        </form>
        <%
            }
        } else {
        %>
            <tr><td colspan="7">No hay facturas pendientes de pago.</td></tr>
        <%
        }
        %>
    </table>

    <h3>FACTURAS PAGADAS</h3>

    <table border="1">
        <tr>
            <th>ID FACTURA</th>
            <th>ID TRABAJO</th>
            <th>DINERO (€)</th>
            <th>ESTADO</th>
            <th>FECHA EMISIÓN</th>
            <th>FECHA PAGO</th>
        </tr>

        <%
        // Obtener las facturas pagadas desde el request
        List<Factura> facturasPagadas = (List<Factura>) request.getAttribute("facturasPagadas");

        // Comprobar si hay facturas pagadas
        if (facturasPagadas != null) {
            for (Factura factura : facturasPagadas) {
        %>
        <form method="POST" action="">
            <tr>
                <td>
                    <input type="hidden" name="id" value="<%= factura.getId() %>">
                    <%= factura.getId() %>
                </td>
                <td>
                    <input type="text" value="<%= factura.getIdTrabajo() %>" disabled>
                </td>
                <td>
                    <input type="text" value="<%= factura.getDinero() %>" disabled>
                </td>
                <td>
                    <input type="text" value="<%= factura.getEstado() %>" disabled>
                </td>
                <td>
                    <input type="date" value="<%= factura.getFechaEmision() %>" disabled>
                </td>
                <td>
                    <input type="date" value="<%= factura.getFechaPago() %>" disabled>
                </td>
            </tr>
        </form>
        <%
            }
        } else {
        %>
            <tr><td colspan="7">No hay facturas pagadas.</td></tr>
        <%
        }
        %>
    </table>

</body>
</html>