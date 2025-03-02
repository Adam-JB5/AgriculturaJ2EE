<%@ page import="java.util.List" %>
<%@ page import="Modelo.Trabajo" %>
<%@ page import="Modelo.Parcela" %>
<%@ page import="Modelo.Usuario" %>
<%@ page import="Modelo.Maquina" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Trabajos</title>
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
    <h2>GESTIÓN DE TRABAJOS</h2>
    <h3>FINALIZAR TRABAJOS</h3>

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
    List<Trabajo> trabajos = (List<Trabajo>) request.getAttribute("trabajos");
    List<Parcela> parcelas = (List<Parcela>) request.getAttribute("parcelas");
    List<Usuario> maquinistas = (List<Usuario>) request.getAttribute("maquinistas");
    List<Maquina> maquinas = (List<Maquina>) request.getAttribute("maquinas");
    
    if (trabajos != null) {
        for (Trabajo trabajo : trabajos) {
%>
        <form method="POST" action="Trabajos">
            <tr>
                <td>
                    <input type="hidden" name="id" value="<%= trabajo.getId() %>">
                    <%= trabajo.getId() %>
                </td>
                <td>
                    <select name="IDparcela" required disabled>
                        <% if (parcelas != null) { %>
                            <% for (Parcela parcela : parcelas) { %>
                                <option value="<%= parcela.getId() %>" <%= (parcela.getId() == trabajo.getIdParcela()) ? "selected" : "" %> >
                                    <%= parcela.getId() %>
                                </option>
                            <% } %>
                        <% } %>
                    </select>
                </td>
                <td>
                    <select name="IDmaquinista" required disabled>
                        <% if (maquinistas != null) { %>
                            <% for (Usuario maquinista : maquinistas) { %>
                                <option value="<%= maquinista.getId() %>" <%= (maquinista.getId() == trabajo.getIdMaquinista()) ? "selected" : "" %> >
                                    <%= maquinista.getId() %>
                                </option>
                            <% } %>
                        <% } %>
                    </select>
                </td>
                <td>
                    <select name="IDmaquina" required disabled>
                        <% if (maquinas != null) { %>
                            <% for (Maquina maquina : maquinas) { %>
                                <option value="<%= maquina.getId() %>" <%= (maquina.getId() == trabajo.getIdMaquina()) ? "selected" : "" %> >
                                    <%= maquina.getId() %>
                                </option>
                            <% } %>
                        <% } %>
                    </select>
                </td>
                <td>
                    <%= trabajo.getTipo() %>
                </td>
                <td>
                    <select name="estado" required class="estadoSelect" id="estado_<%= trabajo.getId() %>">
                        <option value="Iniciado" <%= "Iniciado".equals(trabajo.getEstado()) ? "selected" : "" %>>Iniciado</option>
                        <option value="Finalizado" <%= "Finalizado".equals(trabajo.getEstado()) ? "selected" : "" %>>Finalizado</option>
                    </select>
                </td>
                <td>
                    <input type="date" name="fechaInicio" id="fechaInput_<%= trabajo.getId() %>" value="<%= trabajo.getFechaInicio() %>" required disabled>
                </td>
                <td>
                    <input type="date" name="fechaFin" id="fechaFin_<%= trabajo.getId() %>" required <%= "Finalizado".equals(trabajo.getEstado()) ? "" : "disabled" %>>
                </td>
                <td>
                    <input type="hidden" name="todo" value="finalizarTrabajo">
                    <button type="submit">Finalizar Trabajo</button>
                </td>
            </tr>
        </form>
<%
        }
    }
%>
</table>

<script>
    document.querySelectorAll('.estadoSelect').forEach(select => {
        select.addEventListener('change', function() {
            let idTrabajo = this.id.split('_')[1];  // Extrae el ID del select
            let fechaInicioInput = document.getElementById('fechaInput_' + idTrabajo);
            let fechaFinInput = document.getElementById('fechaFin_' + idTrabajo);
            
            fechaInicioInput.disabled = this.value !== 'Iniciado';
            fechaFinInput.disabled = this.value !== 'Finalizado';
        });
    });
</script>

</body>
</html>
