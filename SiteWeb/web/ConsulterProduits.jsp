<%-- 
    Document   : BenEtAymeric
    Created on : 27 nov. 2013, 22:09:39
    Author     : Aymeric
--%>
       
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="connexions.ConnexionThreadClientProduits"%>
<%@page import="org.jdom2.Element"%>
<%@page import="org.jdom2.Element"%>
<%@ include file="./EnTete.jsp" %>
        
       
       <div class="corpsPage">
           <h3>Consulter les produits</h3>
            <p>Choississez une boutique:<br>
               <form name="formu" action="">
               <select name="listeDesBoutiques">
                    <%
                        String nomBoutique= "Carrefour";
                     
                        if(request.getParameter("listeDesBoutiques") != null)
                            nomBoutique= request.getParameter("listeDesBoutiques");
                            
                        ArrayList<String> listeDesBoutiques= gb.getListeDesBoutiques();

                        for(String s: listeDesBoutiques)
                        {
                            if(s.equals(nomBoutique))
                                out.print("<option value='"+s+"' selected>"+s);
                            else
                                out.print("<option value='"+s+"'>"+s);
                        }
                    %>
                </select>
                <input type="submit" name="validerB" value="Valider">
                </form>
            <div class="corps">
                <% 
                     out.print("<h3>"+nomBoutique +"</h3>");
                %>
                <table class="tableau">                  
 
                    <tr><td>Id <td>Nom <td>Prix <td>Commander
                     <%
                     Element message = ConnexionThreadClientProduits.demanderProduits(nomBoutique).getRootElement();
                     
                     List produits = message.getChildren("produit");
                     
                     Iterator pro = produits.iterator();
                     
                     while(pro.hasNext()){
                         Element crt = (Element) pro.next();
                         
                         out.println("<tr><td>"+crt.getChild("id").getText()+"<td>"+crt.getChild("nom").getText()+"<td>"+crt.getChild("prix").getText() + "<td><a href= SessionServlet?id="+ crt.getChild("id").getText() + "&nom="+crt.getChild("nom").getText()+ "&prix="+crt.getChild("prix").getText()+ "&boutique="+ nomBoutique+"><img src='panier.png' width= '25'/></a>");  
                     }

                           
                      %>
                </table>
            </div>
        </div>
    </body>
</html>