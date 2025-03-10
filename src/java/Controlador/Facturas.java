/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ConexionABD.FacturaDML;
import ConexionABD.ParcelaDML;
import ConexionABD.TrabajoDML;
import Modelo.Factura;
import Modelo.Trabajo;
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
public class Facturas extends HttpServlet {

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
        
        if (todo.equals("pagoFacturaPagina")) {
            HttpSession session = request.getSession();
            int idAgricultor = (int) session.getAttribute("id");
            List<Factura> facturas = FacturaDML.listarFacturasPorPagar(conexion, idAgricultor);
            List<Factura> facturasPagadas = FacturaDML.listarFacturasPagadas(conexion, idAgricultor);
            
            request.setAttribute("facturasPorPagar", facturas);
            request.setAttribute("facturasPagadas", facturasPagadas);
            nextPage = "/pagarFacturas.jsp";
        } else if (todo.equals("pagoFactura")) {
            int idFactura = Integer.parseInt(request.getParameter("id"));
            FacturaDML.pagarFactura(conexion, idFactura);
            nextPage = "/agricultor.jsp";
        } else if (todo.equals("gestionFacturas")) {
            List<Factura> facturas = FacturaDML.listar(conexion);
            List<Trabajo> trabajosFinalizados = TrabajoDML.obtenerTodosTrabajosFinalizados(conexion);
            
            request.setAttribute("facturas", facturas);
            request.setAttribute("trabajosFinalizados", trabajosFinalizados);
            nextPage = "/gestionFacturas.jsp";
        } else if (todo.equals("generarFactura")) {
            int idTrabajo = Integer.parseInt(request.getParameter("id"));
            int idParcela = Integer.parseInt(request.getParameter("IDparcela"));
            String tipo = request.getParameter("tipo");
            
            
            double superficie = ParcelaDML.obtenerSuperficieParcela(conexion, idParcela);
            double precioPorHectarea = ParcelaDML.obtenerPrecioHectarea(conexion, tipo);
            
            double total = superficie * precioPorHectarea;
            
            String fecha = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            
            FacturaDML.insertar(conexion, idTrabajo, total, "Pendiente de pago", fecha);
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
