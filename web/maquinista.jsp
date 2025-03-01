<%-- 
    Document   : maquinista.jsp
    Created on : 01-mar-2025, 15:38:17
    Author     : adamj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>GESTIÓN DE TRABAJOS</h2>

            <h3>INICIAR TRABAJOS</h3>
                <table>
                    <tr>
                        <th>ID TRABAJO</th>
                        <th>ID PARCELA</th>
                        <th>ID MAQUINISTA</th>
                        <th>ID MÁQUINA</th>
                        <th>TIPO DE TRABAJO</th>
                        <th>ESTADO</th>
                        <th>FECHA INICIO</th>
                    </tr>

                    <?php

                    // Consulta para obtener los trabajos existentes
                    $sql = "SELECT * FROM trabajos WHERE Estado = 'Asignado' AND ID_Maquinista = '".$_SESSION['id']."'";
                    $resultado = $conexion->query($sql);

                    // Comprobar si se encontraron trabajos
                    if ($resultado->num_rows > 0) {
                        while ($fila = $resultado->fetch_assoc()) {
                            $idTrabajo = $fila['ID'];
                            echo "<tr>";
                            echo "<form method='POST' action='iniciarTrabajo.php'>";

                            echo "<td><input type='hidden' name='id' value='{$fila['ID']}'>{$fila['ID']}</td>";
                            
                            // ID Parcela: Lista desplegable
                            echo "<td><select name='IDparcela' required disabled>";
                            $parcelas = $conexion->query("SELECT ID FROM parcelas");
                            while ($parcela = $parcelas->fetch_assoc()) {
                                $selected = ($fila['ID_Parcela'] == $parcela['ID']) ? 'selected' : '';
                                echo "<option value='{$parcela['ID']}' $selected>{$parcela['ID']}</option>";
                            }
                            echo "</select></td>";


                            // ID Maquinista: Lista desplegable
                            echo "<td><select name='IDmaquinista' required disabled>";
                            $maquinistas = $conexion->query("SELECT ID FROM usuarios WHERE Tipo='Maquinista'");
                            while ($maquinista = $maquinistas->fetch_assoc()) {
                                $selected = ($fila['ID_Maquinista'] == $maquinista['ID']) ? 'selected' : '';
                                echo "<option value='{$maquinista['ID']}' $selected>{$maquinista['ID']}</option>";
                            }
                            echo "</select></td>";


                            // ID Maquina: Lista desplegable
                            echo "<td><select name='IDmaquina' required disabled>";
                            $maquinas = $conexion->query("SELECT ID FROM maquinas");
                            while ($maquina = $maquinas->fetch_assoc()) {
                                $selected = ($fila['ID_Maquina'] == $maquina['ID']) ? 'selected' : '';
                                echo "<option value='{$maquina['ID']}' $selected>{$maquina['ID']}</option>";
                            }
                            echo "</select></td>";


                            // Tipo de Trabajo: Lista desplegable
                            echo "<td><select name='Tipo' required disabled>";
                            $tiposTrabajo = ['Siembra', 'Fumigacion', 'Cosecha'];
                            foreach ($tiposTrabajo as $tipo) {
                                $selected = ($fila['Tipo'] == $tipo) ? 'selected' : '';
                                echo "<option value='$tipo' $selected>$tipo</option>";
                            }
                            echo "</select></td>";


                            // Estado: Lista desplegable con ID único
                            echo "<td><select name='Estado' id='estadoSelect_{$idTrabajo}' class='estadoSelect' required>";
                            $estados = ['Asignado', 'Iniciado'];
                            foreach ($estados as $estado) {
                                $selected = ($fila['Estado'] == $estado) ? 'selected' : '';
                                echo "<option value='$estado' $selected>$estado</option>";
                            }
                            echo "</select></td>";

                            // Fecha: Input con ID único
                            $fechaDisabled = ($fila['Estado'] == 'Iniciado') ? '' : 'disabled';
                            echo "<td><input type='date' name='FechaInicio' id='fechaInput_{$idTrabajo}' class='fechaInput' $fechaDisabled required></td>";

                            // Botón para actualizar
                            echo "<td><button type='submit'>Asignar Trabajo</button></td>";
                            echo "</form>";


                            echo "</tr>";
                        }
                    }
                    ?>
                </table>
            <script>
                document.querySelectorAll('.estadoSelect').forEach(select => {
                    select.addEventListener('change', function() {
                        let idTrabajo = this.id.split('_')[1];  // Extrae el ID del select
                        let fechaInput = document.getElementById('fechaInput_' + idTrabajo);
                        fechaInput.disabled = this.value !== 'Iniciado';
                    });
                });
            </script>


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

                    <?php

                    // Consulta para obtener los trabajos existentes
                    $sql = "SELECT * FROM trabajos WHERE Estado = 'Iniciado' AND ID_Maquinista = '".$_SESSION['id']."'";
                    $resultado = $conexion->query($sql);

                    // Comprobar si se encontraron trabajos
                    if ($resultado->num_rows > 0) {
                        while ($fila = $resultado->fetch_assoc()) {
                            $idTrabajo = $fila['ID'];
                            echo "<tr>";
                            echo "<form method='POST' action='finalizarTrabajo.php'>";

                            echo "<td><input type='hidden' name='id' value='{$fila['ID']}'>{$fila['ID']}</td>";
                            
                            // ID Parcela: Lista desplegable
                            echo "<td><select name='IDparcela' required disabled>";
                            $parcelas = $conexion->query("SELECT ID FROM parcelas");
                            while ($parcela = $parcelas->fetch_assoc()) {
                                $selected = ($fila['ID_Parcela'] == $parcela['ID']) ? 'selected' : '';
                                echo "<option value='{$parcela['ID']}' $selected>{$parcela['ID']}</option>";
                            }
                            echo "</select></td>";


                            // ID Maquinista: Lista desplegable
                            echo "<td><select name='IDmaquinista' required disabled>";
                            $maquinistas = $conexion->query("SELECT ID FROM usuarios WHERE Tipo='Maquinista'");
                            while ($maquinista = $maquinistas->fetch_assoc()) {
                                $selected = ($fila['ID_Maquinista'] == $maquinista['ID']) ? 'selected' : '';
                                echo "<option value='{$maquinista['ID']}' $selected>{$maquinista['ID']}</option>";
                            }
                            echo "</select></td>";


                            // ID Maquina: Lista desplegable
                            echo "<td><select name='IDmaquina' required disabled>";
                            $maquinas = $conexion->query("SELECT ID FROM maquinas");
                            while ($maquina = $maquinas->fetch_assoc()) {
                                $selected = ($fila['ID_Maquina'] == $maquina['ID']) ? 'selected' : '';
                                echo "<option value='{$maquina['ID']}' $selected>{$maquina['ID']}</option>";
                            }
                            echo "</select></td>";


                            // Tipo de Trabajo: Lista desplegable
                            echo "<td><select name='Tipo' required disabled>";
                            $tiposTrabajo = ['Siembra', 'Fumigacion', 'Cosecha'];
                            foreach ($tiposTrabajo as $tipo) {
                                $selected = ($fila['Tipo'] == $tipo) ? 'selected' : '';
                                echo "<option value='$tipo' $selected>$tipo</option>";
                            }
                            echo "</select></td>";


                            // Estado: Lista desplegable con ID único
                            echo "<td><select name='Estado' id='estadoFinalizar_{$idTrabajo}' class='estadoFinalizar' required>";
                            $estados = ['Iniciado', 'Finalizado'];
                            foreach ($estados as $estado) {
                                $selected = ($fila['Estado'] == $estado) ? 'selected' : '';
                                echo "<option value='$estado' $selected>$estado</option>";
                            }
                            echo "</select></td>";

                            // Fecha de inicio (deshabilitada para evitar edición)
                            echo "<td><input type='date' name='FechaInicio' value='{$fila['Fecha_Inicio']}' disabled></td>";

                            // Fecha de fin (habilitar solo si está en "Finalizado")
                            $fechaFinDisabled = ($fila['Estado'] == 'Finalizado') ? '' : 'disabled';
                            echo "<td><input type='date' name='FechaFin' id='fechaFin_{$idTrabajo}' class='fechaFin' $fechaFinDisabled required></td>";

                            // Botón para finalizar trabajo
                            echo "<td><button type='submit'>Finalizar Trabajo</button></td>";

                            echo "</form>";
                            echo "</tr>";
                        }
                    }
                    ?>
                </table>

                <script>
                    document.querySelectorAll('.estadoFinalizar').forEach(select => {
                        select.addEventListener('change', function() {
                            let idTrabajo = this.id.split('_')[1]; // Extraer ID del trabajo
                            let fechaFinInput = document.getElementById('fechaFin_' + idTrabajo);
                            fechaFinInput.disabled = this.value !== 'Finalizado';
                        });
                    });
                </script>


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

                    <?php

                    // Consulta para obtener los trabajos existentes
                    $sql = "SELECT * FROM trabajos WHERE Estado = 'Finalizado' AND ID_Maquinista = '".$_SESSION['id']."'";
                    $resultado = $conexion->query($sql);

                    // Comprobar si se encontraron trabajos
                    if ($resultado->num_rows > 0) {
                        while ($fila = $resultado->fetch_assoc()) {
                            $idTrabajo = $fila['ID'];
                            echo "<tr>";
                            echo "<form method='POST' action='finalizarTrabajo.php'>";

                            echo "<td><input type='hidden' name='id' value='{$fila['ID']}'>{$fila['ID']}</td>";
                            
                            // ID Parcela: Lista desplegable
                            echo "<td><select name='IDparcela' required disabled>";
                            $parcelas = $conexion->query("SELECT ID FROM parcelas");
                            while ($parcela = $parcelas->fetch_assoc()) {
                                $selected = ($fila['ID_Parcela'] == $parcela['ID']) ? 'selected' : '';
                                echo "<option value='{$parcela['ID']}' $selected>{$parcela['ID']}</option>";
                            }
                            echo "</select></td>";


                            // ID Maquinista: Lista desplegable
                            echo "<td><select name='IDmaquinista' required disabled>";
                            $maquinistas = $conexion->query("SELECT ID FROM usuarios WHERE Tipo='Maquinista'");
                            while ($maquinista = $maquinistas->fetch_assoc()) {
                                $selected = ($fila['ID_Maquinista'] == $maquinista['ID']) ? 'selected' : '';
                                echo "<option value='{$maquinista['ID']}' $selected>{$maquinista['ID']}</option>";
                            }
                            echo "</select></td>";


                            // ID Maquina: Lista desplegable
                            echo "<td><select name='IDmaquina' required disabled>";
                            $maquinas = $conexion->query("SELECT ID FROM maquinas");
                            while ($maquina = $maquinas->fetch_assoc()) {
                                $selected = ($fila['ID_Maquina'] == $maquina['ID']) ? 'selected' : '';
                                echo "<option value='{$maquina['ID']}' $selected>{$maquina['ID']}</option>";
                            }
                            echo "</select></td>";


                            // Tipo de Trabajo: Lista desplegable
                            echo "<td><select name='Tipo' required disabled>";
                            $tiposTrabajo = ['Siembra', 'Fumigacion', 'Cosecha'];
                            foreach ($tiposTrabajo as $tipo) {
                                $selected = ($fila['Tipo'] == $tipo) ? 'selected' : '';
                                echo "<option value='$tipo' $selected>$tipo</option>";
                            }
                            echo "</select></td>";


                            // Estado: Lista desplegable con ID único
                            echo "<td><select name='Estado' id='estadoFinalizar_{$idTrabajo}' class='estadoFinalizar' disabled>";
                            $estados = ['Iniciado', 'Finalizado'];
                            foreach ($estados as $estado) {
                                $selected = ($fila['Estado'] == $estado) ? 'selected' : '';
                                echo "<option value='$estado' $selected>$estado</option>";
                            }
                            echo "</select></td>";

                            // Fecha de inicio (deshabilitada para evitar edición)
                            echo "<td><input type='date' name='FechaInicio' value='{$fila['Fecha_Inicio']}' disabled></td>";


                            echo "<td><input type='date' name='FechaFin' id='fechaFin_{$idTrabajo}' class='fechaFin' value='{$fila['Fecha_Fin']}' disabled></td>";

                            

                            echo "</form>";
                            echo "</tr>";
                        }
                    }
                    ?>
                </table>
    </body>
</html>
