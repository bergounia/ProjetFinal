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
        
      public static void main(String[] args) throws CloneNotSupportedException, InterruptedException{
        
        Boutique boutique1=new Boutique("Carrefour");
        //GestionBoutique.sauvegarderXML(boutique);
       //GestionBoutique.chargerXML(bout);
        
        //bout.ajouterProduit(new Produit("toto",15));
        //System.out.println(bout.getId()); 
        boutique1.runServeurProduits(5005);
        boutique1.runServeurCommandes(5006);
    }   
}