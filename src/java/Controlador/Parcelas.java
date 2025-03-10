/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ConexionABD.FacturaDML;
import ConexionABD.ParcelaDML;
import ConexionABD.UsuarioDML;
import Modelo.Factura;
import Modelo.Parcela;
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
public class Parcelas extends HttpServlet {

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
        
        if (todo.equals("gestionParcelas")) {
            List<Parcela> parcelas = ParcelaDML.listar(conexion);
            List<Usuario> agricultores = UsuarioDML.obtenerAgricultores(conexion);
            
            request.setAttribute("parcelas", parcelas);
            request.setAttribute("agricultores", agricultores);
            nextPage = "/gestionParcelas.jsp";
        } else if (todo.equals("actualizarParcela")) {
            int idAgricultor = Integer.parseInt(request.getParameter("IDagricultor"));
            String ubicacion = request.getParameter("ubicacion");
            double superficie = Double.parseDouble(request.getParameter("superficie"));
            String ccaa = request.getParameter("comunidadAutonoma");
            int idParcela = Integer.parseInt(request.getParameter("id"));
            
            ParcelaDML.actualizarParcela(conexion, idAgricultor, ubicacion, superficie, ccaa, idParcela);
            nextPage = "/administrador.jsp";
        } else if (todo.equals("crearParcela")) {
            int idAgricultor = Integer.parseInt(request.getParameter("IDagricultor"));
            String ubicacion = request.getParameter("ubicacion");
            double superficie = Double.parseDouble(request.getParameter("superficie"));
            String ccaa = request.getParameter("comunidadAutonoma");
            
            ParcelaDML.insertar(conexion, idAgricultor, ubicacion, superficie, ccaa);
            nextPage = "/administrador.jsp";
        } else if (todo.equals("eliminarParcela")) {
            int idParcela = Integer.parseInt(request.getParameter("id"));
            
            ParcelaDML.eliminar(conexion, idParcela);
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
