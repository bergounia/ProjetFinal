<%-- 
    Document   : ConsulterCommandes
    Created on : 2 déc. 2013, 09:48:23
    Author     : Aymeric
--%>

<%@page import="connexions.ConnexionThreadClientCommandes"%>
<%@page import="connexions.ConnexionThreadClientProduits"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.jdom2.Element"%>
<%@ include file="./EnTete.jsp" %>
         
         <div class="corpsPage">
             <h3>Consulter les commandes</h3>
                <p>Choississez une boutique:<br>
                   <form name="formu" action="">
                   <select name="listeDesBoutiques">
                        <%
                            ArrayList<String> listeDesBoutiques= gb.getListeDesBoutiques();

                            for(String s: listeDesBoutiques)
                            {
                                out.print("<option value='"+s+"'>"+s);
                            }
                        %>
                    </select>
                    <input type="submit" name="validerB" value="Valider">
                    </form>
                <div class="corps">
                    
                    <% 
                     String nomBoutique= "Carrefour";
                     
                     if(request.getParameter("listeDesBoutiques") != null)
                         nomBoutique= request.getParameter("listeDesBoutiques");
                     
                     out.print("<h3>"+nomBoutique +"</h3>");
                    %>
                    
                    <table class="tableau">
                        <tr><td>Id <td>Date <td>Status <td>Valider
                     <%
                            /*Element message = ConnexionThreadClientCommandes.recevoirCommande(nomBoutique).getRootElement();

                            List produits = message.getChildren("produit");

                            Iterator pro = produits.iterator();

                            while(pro.hasNext()){
                                Element crt = (Element) pro.next();

                                out.print("<tr><td>"+crt.getChild("id").getText()+"<td>"+crt.getChild("date").getText()+"<td>"+crt.getChild("status").getText());
                            }
*/
                           
                     %>
                    </table>
                </div>
         </div>   
    </body>
</html>
