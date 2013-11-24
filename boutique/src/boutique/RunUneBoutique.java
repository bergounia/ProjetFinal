/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique;

import gestion.GestionBoutique;
import java.util.ArrayList;
import java.util.Date;
import thread.ServeurThreadProduits;

/**
 *
 * @author Benjamin
 */
public class RunUneBoutique {
    
    private Boutique maBoutique;
    private Boutique ParserBoutique;
    
      public static void main(String[] args) throws CloneNotSupportedException {
        Produit P1 = new Produit("1", 10);
        Produit P2 = new Produit("2", 10);
        Produit P3 = new Produit("3", 10);
        Produit P4 = new Produit("4", 10);
        Produit P5 = new Produit("5", 10);
        
        Commande C1 = new Commande(new Date(2013, 3, 29));
        Commande C2 = new Commande(new Date(2012, 3, 29));
        Commande C3 = new Commande(new Date(2011, 3, 29));
        
        C1.ajouterProduit(P1);
        C1.ajouterProduit(P2);
        C1.ajouterProduit(P5);
        
        C2.ajouterProduit(P2);
        C2.ajouterProduit(P4);
        C2.ajouterProduit(P5);
        
        C2.ajouterProduit(P1);
        C2.ajouterProduit(P4);
        C2.ajouterProduit(P3);
        
        Boutique boutique=new Boutique("Carrefour");
        //Boutique bout=new Boutique("bridePD");
        
        boutique.ajouterCommande(C1);
        boutique.ajouterCommande(C2);
        boutique.ajouterCommande(C3);
        
        boutique.ajouterProduit(P1);
        boutique.ajouterProduit(P2);
        boutique.ajouterProduit(P3);
        boutique.ajouterProduit(P4);
        boutique.ajouterProduit(P5);
        
        
        //GestionBoutique.sauvegarderXML(boutique);
       //GestionBoutique.chargerXML(bout);
        
        //bout.ajouterProduit(new Produit("toto",15));
        //System.out.println(bout.getId());
          
        boutique.runServeurProduits(5005);
        
    }
    
    
}
