<%@page import="rmi.IGestionUtilisateurs"%>
<%@page import="java.rmi.Naming"%>
<%@page import="rmi.IGestionBoutiques"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    IGestionUtilisateurs gu= (IGestionUtilisateurs)Naming.lookup("rmi://localhost/GestionUtilisateurs");
    IGestionBoutiques gb= (IGestionBoutiques)Naming.lookup("rmi://localhost/GestionBoutiques");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ChezBenEtAymeric</title>
        <link href="./BenEtAymericCss.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" language="JavaScript">
            function verif(formu)
            {
                formu.action= window.location.hostname + "?boutique="+ formu.listeDesBoutiques[formu.listeDesBoutiques.selectedIndex].value);
            }
            
        </script>
    </head>
    <body>

<form class="formulaireConnexion" name='formulaireConnexion' method='POST' action='ConnexionServlet'>   
    <table>
        <tr><td>Identifiant: <td><input type="text" name='identifiant' value=''><br>
        <tr><td>Mot de passe: <td><input type="password" name='motDePasse' value=''><br>  
    </table>
    <input type="submit" name="connexion" value="Connexion">
    <input type="reset" name="effacerID" value="Effacer">
</form>

<div class="bandeau">
    <h1>Bienvenue chez Ben & Aymeric</h1>
    <h2>Trouvez l'objet de vos envies !</h2>
</div>

<ul>
    <li class="borduredroite"><a href="./ConsulterProduits.jsp">Consulter les produits</a>
    <li><a href="./ConsulterCommandes.jsp">Consulter les commandes</a>
    <% 
        String espaceAdmin =(String) session.getAttribute("identifiant");
        if(gu.estAdmin(espaceAdmin)) 
            out.print(" <li><a href='./EspaceAdministrateur.jsp'>Espace administrateur</a>");
    %>
    <li><a href="./Panier.jsp">Panier</a>
</ul>
<% 
    if (session.getAttribute("identifiant") == null && session.getAttribute("motDePasse") == null) 
        out.print("<p class='connexion'>Vous n'êtes pas connecté");
    else
        out.print("<div class='connexion'>Bonjour "+
                    session.getAttribute("identifiant") + 
                    " <form name='FormuDeconnexion' action= 'DeconnexionServlet' method='POST'>"
                + "<input class='boutonDeconnexion' type='submit' name='deco' value='Déconnexion'>"
                + "</form></div>"

                
        );
%>

