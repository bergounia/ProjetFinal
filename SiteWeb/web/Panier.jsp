<%-- 
    Document   : Panier
    Created on : 2 déc. 2013, 10:29:06
    Author     : Aymeric
--%>

         <%@page import="connexions.ConnexionThreadClientCommandes"%>
<%@page import="Servlets.SessionServlet"%>
<%@page import="connexions.ConnexionThreadClientProduits"%>
<%@page import="org.jdom2.Element"%>
<%@ include file="./EnTete.jsp" %>
         
         <div class="corpsPage">
             <h3>Votre panier</h3>
             
             <div class="corps">
                 <h3>Liste des produits</h3>
                 <%
                    ArrayList<ArrayList<String>> listeDesProduits= null;
                    if(session.getAttribute("listeProduits") != null) 
                    {
                 %>
                <table class="tableau">
                    <tr><td>Id <td>Nom <td>Prix <td>Boutique
                <%
 
                           listeDesProduits= (ArrayList<ArrayList<String>>) session.getAttribute("listeProduits");

                           for(ArrayList<String> al : listeDesProduits)
                           {
                               out.print("<tr><td>"+al.get(0)+ "<td>"+al.get(1)+"<td>"+al.get(2)+"<td>"+al.get(3));
                           }
                 %>
                </table>
                <form name="validerCommande" method="POST" action="Commandes">
                    <input type="hidden" name="champCache" value="envoyerCommande">
                    <input type="Submit" name="valider" value="Valider la commande">
                </form>
                <%
                    }
                    else
                        out.print("<h3>Panier vide !</h3>");
                %>
                    
             </div>
         </div> 
         
    </body>
</html>
