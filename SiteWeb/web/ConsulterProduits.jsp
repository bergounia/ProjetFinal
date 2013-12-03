<%-- 
    Document   : BenEtAymeric
    Created on : 27 nov. 2013, 22:09:39
    Author     : Aymeric
--%>
       
        <%@ include file="./EnTete.jsp" %>
        
       
       <div class="corpsPage">
           <h3>Consulter les produits</h3>
            <p>Choississez une boutique:<br>
            <select name="listeDesBoutiques">
                <option value="selectionner" selected>Boutiques...
                <%
                    ArrayList<String> listeDesBoutiques= gb.getListeDesBoutiques();

                    for(String s: listeDesBoutiques)
                    {
                        out.print("<option value='"+s+"'>"+s);
                    }
                %>
            </select>

        
            <div class="corps">
                <table>
                    <tr><td>Id <td>Nom <td>Prix <td>Commander
                </table>
            </div>
        </div>
    </body>
</html>
