/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Benjamin
 */
public class Commande {
    
    private String id;
    private static int incr;
    private Date dateCmd;
    private ArrayList<Produit> listeProduits = new ArrayList<Produit>();

    public Commande(Date dateCmd, ArrayList<Produit> listeProduits) {
        this.incr++;
        this.dateCmd = dateCmd;
        this.listeProduits = listeProduits;
        this.id=Integer.toString(incr);
    }
    
        public Commande(Date dateCmd) {
        this.incr++;
        this.dateCmd = dateCmd;
        this.listeProduits = listeProduits;
        this.id=Integer.toString(incr);
    }
    
     public static int getIncr() {
        return incr;
    }
    
    public void setListeProduits(ArrayList<Produit> listeProduits) {
        this.listeProduits = listeProduits;
    }
    
    public static void setIncr(int incr) {
        Commande.incr = incr;
    }
   
    public String getId() {
        return id;
    }

    public Date getDateCmd() {
        return dateCmd;
    }

    public ArrayList<Produit> getListeProduits() {
        return listeProduits;
    }
    
    public void ajouterProduit(Produit prdt){
        this.listeProduits.add(prdt);
    }
}
