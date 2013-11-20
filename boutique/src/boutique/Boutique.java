/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique;

import java.util.ArrayList;


/*caractérisées par leur nom, l'adresse IP, le port pour la gestion des commandes et le port pour la gestion des produits*/

/**
 *
 * @author Benjamin
 */
public class Boutique {
    
    private String nom;
    private String id;
    private static int incr;
    private ArrayList<Produit> listeProduits=new ArrayList<Produit>();
    private ArrayList<Commande> listeCommandes=new ArrayList<Commande>();

    public Boutique(String nom, ArrayList<Produit> listeProduits) {
        this.incr++;
        this.nom = nom;
        this.listeProduits = listeProduits;
        this.id=Integer.toString(incr);
    }

    public Boutique(String nom) {
        this.incr++;
        this.nom = nom;
        this.id=Integer.toString(incr);
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }
    
    public static int getIncr() {
        return incr;
    }

    public ArrayList<Commande> getListeCommandes() {
        return listeCommandes;
    }
    

    public ArrayList<Produit> getListeProduits() {
        return listeProduits;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static void setIncr(int incr) {
        Boutique.incr = incr;
    }
    

    public void setListeProduits(ArrayList<Produit> listeProduits) {
        this.listeProduits = listeProduits;
    }
    
    public void ajouterProduit(Produit prdt){
        this.listeProduits.add(prdt);
    }
    
     public void ajouterCommande(Commande cmd){
        this.listeCommandes.add(cmd);
    }

}
