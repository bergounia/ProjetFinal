<%-- 
    Document   : BenEtAymeric
    Created on : 27 nov. 2013, 22:09:39
    Author     : Aymeric
--%>

<%@page import="java.rmi.Naming"%>
<%@page import="rmi.IGestionBoutiques"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ChezBenEtAymeric</title>
        <link href="./BenEtAymericCss.css" rel="stylesheet" type="text/css">
    </head>
    <body>
       
        <%@ include file="./EnTete.jsp" %>
        
       
       <div class="corpsPage">
            <p>Choississez une boutique:<br>
            <select name="listeDesBoutiques">
                <option value="selectionner" selected>Boutiques...
                <%
                    IGestionBoutiques gb= (IGestionBoutiques)Naming.lookup("rmi://localhost/GestionBoutiques");
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
