/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import connexions.ConnexionThreadClientCommandes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aymeric
 */
@WebServlet(name = "Commandes", urlPatterns = {"/Commandes"})
public class Commandes extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Commandes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Commandes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("validation panier");
        System.out.println(request.getSession().getAttribute("listeProduits"));
        
        ArrayList<ArrayList<String>> listeDesProduits = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("listeProduits");
        
        ArrayList<String> boutiques = new ArrayList<String>();
        ArrayList<String> tempProduits = new ArrayList<String>();

        for (ArrayList<String> al : listeDesProduits) {
            if (!boutiques.contains(al.get(3))) {
                boutiques.add(al.get(3));
            }
        }
        
        System.out.println(boutiques);

        for (String bout : boutiques) {

            tempProduits = new ArrayList<String>();

            for (ArrayList<String> al : listeDesProduits) {
                if (al.get(3).equals(bout)) {
                    tempProduits.add(al.get(0));
                }
            }
            System.out.println("liste"+tempProduits);
            
            ConnexionThreadClientCommandes.envoyerCommande(bout, (String) request.getSession().getAttribute("identifiant"), tempProduits);

        }
        response.sendRedirect("Panier.jsp");
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

    public boolean contenir(ArrayList<String> liste,String b){
        for(String a:liste){
            if(a.equals(b))
                return true;
        }
        return false;
    }
}
