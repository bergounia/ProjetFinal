<%-- 
    Document   : EspaceAdministrateur
    Created on : 1 déc. 2013, 23:15:08
    Author     : Aymeric
--%>
    
       <%@ include file="./EnTete.jsp" %>
       <div class="corpsPage">
           <h3>Espace administrateur</h3>
           
           <div class="corps">
               <p>Ajouter une boutique
                   
               <form name="AjouterUneBoutique" methode="POST" onsubmit=
                     <% 
                        String nom= request.getParameter("nomNouvelleBoutique");
                        String ip= request.getParameter("ipNouvelleBoutique");
                        String portProduits= request.getParameter("portProduitsNouvelleBoutique");
                        String portCommandes= request.getParameter("portCommandesNouvelleBoutique");
                        
                        if(nom != null && ip != null && portProduits != null)
                         gb.ajouterBoutique(nom, ip, portProduits, portCommandes);   
                     %> 
               >
                   <table class="tableau">
                       <tr><td>Nom: <td><input type="text" name="nomNouvelleBoutique" value="" placeholder="nom de la boutique"><br>
                       <tr><td>Adresse IP:  <td><input type="text" name="ipNouvelleBoutique" value="" placeholder="192.68.1.1"><br>
                       <tr><td>Port produits:  <td><input type="text" name="portProduitsNouvelleBoutique" value="" placeholder="4632"><br>
                       <tr><td>Port commandes:  <td><input type="text" name="portCommandesNouvelleBoutique" value="" placeholder="3698"><br>
                   </table>
                   
                   <input type="Submit" name="ValiderBoutique" value="Valider">
                   <input type="reset" name="EffacerBoutique" value="Effacer">
               </form>
               
              
           </div>
           
           <div class="corps">
               <p>Utilisateurs <br>
               
               <form name="boutonsRadio" action="" method="GET" onsubmit=
                     <%
                        if(request.getParameter("chk") != null)
                        {
                            String nomU= request.getParameter("nomNouvelUtilisateur");
                            String prenomU= request.getParameter("prenomNouvelUtilisateur");
                            String mdpU= request.getParameter("mdpNouvelUtilisateur");
                            String groupeU= request.getParameter("groupeNouvelUtilisateur");
                            
                            if(request.getParameter("chk").equals( "1"))
                            {
                                if(nomU != null && prenomU != null && mdpU != null && groupeU != null)
                                    gu.ajouterUtilisateur(nomU, prenomU, mdpU, groupeU);
                            }
                            else if(request.getParameter("chk").equals( "2"))
                            {
                                if(nomU != null && prenomU != null)
                                    gu.supprimerUtilisateur(nomU, prenomU);
                            }
                        }
                     %>
               >
                   <input type="radio" name="chk" id="chk1" value="1"><label for="chk1">Ajouter</label>
                   <input type="radio" name="chk" id="chk2" value="2"><label for="chk2">Supprimer</label>
                   
                   <table class="tableau">
                       <tr><td>Nom: <td><input type="text" name="nomNouvelUtilisateur" value="" placeholder="nom utilisateur"><br>
                       <tr><td>Prenom: <td><input type="text" name="prenomNouvelUtilisateur" value="" placeholder="prenom utilisateur"><br>
                       <tr><td>Mot de passe:  <td><input type="password" name="mdpNouvelUtilisateur" value=""><br>
                       <tr><td>Groupe:  <td><input type="text" name="groupeNouvelUtilisateur" value="" placeholder="1 ou 2"><br>
                   </table>
                   
                   <input type="Submit" name="ValiderUtilisateur" value="Valider">
                   <input type="reset" name="EffacerUtilisateur" value="Effacer">
               </form>
               
           </div>
       </div>  
        
    </body>
</html>
