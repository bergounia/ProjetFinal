<%-- 
    Document   : ConsulterCommandes
    Created on : 2 déc. 2013, 09:48:23
    Author     : Aymeric
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
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
                    <% 
                        if(cookieId!= null && gu.estAdmin(espaceAdmin)) 
                            out.print("<tr><td>Id commande <td>Id client <td>Date <td>Status <td>Validation");
                        else
                            out.print("<tr><td>Id <td>Date <td>Status");
                    
                            String image= "";
                            Date dateCMD;
                            String dateAffiche;
                            
                            Element message = ConnexionThreadClientCommandes.recevoirCommande(nomBoutique).getRootElement();

                            List commandes = message.getChildren("commande");

                            Iterator cmd = commandes.iterator();
                            

                            while(cmd.hasNext()){
                                Element crt = (Element) cmd.next();
                                   if(crt.getChildText("valide").equals("true"))
                                      image= "<img src='tick.png' width= '25'/>";
                                   else
                                      image= "<img src='croix.png' width= '20'/>";
                                   
                                   dateCMD=new Date(Long.parseLong(crt.getChildText("date")));
                                   DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
                                   dateAffiche=shortDateFormat.format(dateCMD);
                                 
                                   if(cookieId!= null && gu.estAdmin(espaceAdmin))
                                        out.print("<tr><td>"+crt.getChildText("id")+"<td>"+crt.getChildText("idCli")+"<td>"+dateAffiche+" <td>"+ image +" <td><a href=ValiderCommande?nomBoutique="+ nomBoutique+ "&idCommande="+ crt.getChildText("id") +">Valider</a>");
                                   else
                                       out.print("<tr><td>"+crt.getChildText("id") +"<td>"+dateAffiche+" <td>"+ image);
                            }
                          
                           
                     %>
                    </table>
                </div>
         </div>   
    </body>
</html>
