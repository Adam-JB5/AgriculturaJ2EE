/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ConexionABD.MaquinaDML;
import ConexionABD.ParcelaDML;
import ConexionABD.TrabajoDML;
import ConexionABD.UsuarioDML;
import Modelo.Maquina;
import Modelo.Parcela;
import Modelo.Trabajo;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamj
 */
public class Trabajos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection conexion = ConexionABD.Conexion.getConexion();
        String nextPage = "";
        String todo = request.getParameter("todo");
        
        if (todo.equals("iniciarTrabajoPagina")) {
            // Obtener listas desde los DAOs
            HttpSession session = request.getSession();
            int idMaquinista = (int) session.getAttribute("id");
            List<Trabajo> trabajos = TrabajoDML.obtenerTrabajosAsignados(conexion, idMaquinista);
            List<Parcela> parcelas = ParcelaDML.listar(conexion);
            List<Usuario> usuarios = UsuarioDML.listar(conexion);
            List<Maquina> maquinas = MaquinaDML.listar(conexion);
            
            // Pasar listas como atributos del request
            request.setAttribute("trabajos", trabajos);
            request.setAttribute("parcelas", parcelas);
            request.setAttribute("maquinistas", usuarios);
            request.setAttribute("maquinas", maquinas);
            nextPage = "/iniciarTrabajos.jsp";
            
        } else if (todo.equals("finalizarTrabajoPagina")) {
            // Obtener listas desde los DAOs
            HttpSession session = request.getSession();
            int idMaquinista = (int) session.getAttribute("id");
            List<Trabajo> trabajos = TrabajoDML.obtenerTrabajosIniciados(conexion, idMaquinista);
            List<Parcela> parcelas = ParcelaDML.listar(conexion);
            List<Usuario> usuarios = UsuarioDML.listar(conexion);
            List<Maquina> maquinas = MaquinaDML.listar(conexion);
            
            // Pasar listas como atributos del request
            request.setAttribute("trabajos", trabajos);
            request.setAttribute("parcelas", parcelas);
            request.setAttribute("maquinistas", usuarios);
            request.setAttribute("maquinas", maquinas);
            nextPage = "/finalizarTrabajos.jsp";
            
        } else if (todo.equals("iniciarTrabajo")) {
            String estado = request.getParameter("estado");
            String fechaInicio = request.getParameter("fechaInicio");
            int id = Integer.parseInt(request.getParameter("id"));
            TrabajoDML.iniciarTrabajo(conexion,estado, fechaInicio, id);
            nextPage = "/maquinista.jsp";
            
        } else if (todo.equals("finalizarTrabajo")) {
            String estado = request.getParameter("estado");
            String fechaFin = request.getParameter("fechaFin");
            int id = Integer.parseInt(request.getParameter("id"));
            TrabajoDML.finalizarTrabajo(conexion,estado, fechaFin, id);
            nextPage = "/maquinista.jsp";
        }
        
        
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
        requestDispatcher.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
