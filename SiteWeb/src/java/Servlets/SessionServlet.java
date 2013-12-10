/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aymeric
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private ArrayList<ArrayList<String>> listeProduits= new ArrayList<ArrayList<String>>();
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
         String identifiant = (String) request.getSession().getAttribute("identifiant");
         if (identifiant != null) {
            String idProduit= request.getParameter("id");
            String nomProduit= request.getParameter("nom");
            String prix= request.getParameter("prix");
            String nomBoutique= request.getParameter("boutique");
            
            ArrayList<String> caracProduit= new ArrayList<String>(); 
            
            caracProduit.add(idProduit);
            caracProduit.add(nomProduit);
            caracProduit.add(prix);
            caracProduit.add(nomBoutique);
            
            this.listeProduits.add(caracProduit);
            
            request.getSession().setAttribute("listeProduits", this.listeProduits);
            response.sendRedirect("ConsulterProduits.jsp?listeDesBoutiques="+nomBoutique+ "&validerB=Valider");
         }
         else
         {
             System.out.println("le produit ne peut etre ajouté");
             response.sendRedirect("ErreurServlet.jsp");
         }
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
    }
}
