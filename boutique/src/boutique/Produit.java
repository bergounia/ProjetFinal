/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique;

/**
 *
 * @author Benjamin
 */
public class Produit {
    
    private String nom;
    private String id;
    private static int incr;
    private long prix;

    public Produit(String nom, long prix) {
        this.incr++;
        this.nom = nom;
        this.prix = prix;
        this.id=Integer.toString(incr);
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public long getPrix() {
        return prix;
    }
    
     public static int getIncr() {
        return incr;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(long prix) {
        this.prix = prix;
    }
    
    public static void setIncr(int incr) {
        Produit.incr = incr;
    }
    
    
}
