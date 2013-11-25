/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thread;

import boutique.Boutique;
import boutique.Commande;
import boutique.Produit;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import static thread.ConnexionThreadProduits.affiche;

/**
 *
 * @author Benjamin
 */
public class ConnexionThreadCommandes extends Thread{
    
    private static DatagramPacket DP;
    private static Boutique bout;
    
    public ConnexionThreadCommandes(DatagramPacket msg, Boutique bout){
        
        this.bout=bout;

        String texte = new String(msg.getData(), 0, msg.getLength());
        System.out.println(texte);

        ByteArrayInputStream bais = new ByteArrayInputStream(texte.getBytes());
        SAXBuilder sxb = new SAXBuilder();
        
        try {
            lireMessage(sxb.build(bais));
        } catch (JDOMException ex) {
            Logger.getLogger(ConnexionThreadCommandes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnexionThreadCommandes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void lireMessage(org.jdom2.Document document){
		
        affiche(document);
        Element message = document.getRootElement();

        if(message.getAttributeValue("action").equals("envoyerCommande"))
            passerCommande(message.getChild("commande"));
        if(message.getAttributeValue("action").equals("validerCommande"))
            validerCommande(message);

        System.out.println(message.getAttributeValue("action"));					
    }
    
    public static void passerCommande(Element message){        
        
        List produits = message.getChild("produits").getChildren("produit");
        
        ArrayList<Produit> listeProduits = new ArrayList<Produit>();
        
        Iterator pro = produits.iterator();
            
        while(pro.hasNext()){
            Element produit = (Element)pro.next();
            listeProduits.add(bout.rechercherProduit(produit.getAttributeValue("id")));
        }
        
        //ajout de la commande
        bout.ajouterCommande(new Commande(new Date(Long.parseLong(message.getChild("date").getText())), (ArrayList<Produit>) listeProduits.clone()));
    }
         
    public static void validerCommande(Element message){
        
    }
}
