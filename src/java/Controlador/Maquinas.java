/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ConexionABD.FacturaDML;
import ConexionABD.MaquinaDML;
import Modelo.Factura;
import Modelo.Maquina;
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
public class Maquinas extends HttpServlet {

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
        
        if (todo.equals("gestionMaquinas")) {
            List<Maquina> maquinas = MaquinaDML.listar(conexion);
            
            request.setAttribute("maquinas", maquinas);
            nextPage = "/gestionMaquinas.jsp";
        } else if (todo.equals("actualizarMaquina")) {
            String tipo = request.getParameter("tipo");
            String estado = request.getParameter("estado");
            int idMaquina = Integer.parseInt(request.getParameter("id"));
            
            MaquinaDML.actualizarMaquina(conexion, tipo, estado, idMaquina);
            nextPage = "/administrador.jsp";
        } else if (todo.equals("crearMaquina")) {
            String tipo = request.getParameter("tipo");
            String estado = request.getParameter("estado");
            
            MaquinaDML.insertar(conexion, tipo, estado);
            nextPage = "/administrador.jsp";
        } else if (todo.equals("eliminarMaquina")) {
            int idMaquina = Integer.parseInt(request.getParameter("id"));
            
            MaquinaDML.eliminar(conexion, idMaquina);
            nextPage = "/administrador.jsp";
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
