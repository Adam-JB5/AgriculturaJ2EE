/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ConexionABD.UsuarioDML;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class Controlador extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        Connection conexion = ConexionABD.Conexion.getConexion();
        String nextPage = "";
        String todo = request.getParameter("todo");
        
        
        
        
        if (todo.equals("inicioSesion")) {
            String email = request.getParameter("email");
            String contra = request.getParameter("contra");
            
            Usuario usuario = UsuarioDML.obtenerUsuario(conexion, email, contra);
            
            if (usuario != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("id", usuario.getId());
                session.setAttribute("nombre", usuario.getNombre());
                session.setAttribute("email", usuario.getEmail());
                session.setAttribute("contrasenna", usuario.getContrasenna());
                session.setAttribute("tipo", usuario.getTipo());
                
                // Redirigir según el tipo de usuario
                switch (usuario.getTipo()) {
                    case "Administrador":
                        nextPage = "/administrador.jsp";
                        break;
                    case "Agricultor":
                        nextPage = "/agricultor.jsp";
                        break;
                    case "Maquinista":
                        nextPage = "/maquinista.jsp";
                        break;
                    default:
                        nextPage = "/sinAlta.jsp";
                }
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                nextPage = "/login.jsp";
            }
            
            
        } else if (todo.equals("registroUsuario")) {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String contrasenna = request.getParameter("contra");
            
            UsuarioDML.insertar(conexion, nombre, apellidos, email, contrasenna);
            nextPage = "/login.jsp";
        } else if (todo.equals("")) {
            
        } else if (todo.equals("")) {
            
        } else if (todo.equals("")) {
            
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
