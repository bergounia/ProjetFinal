/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique;

import gestion.GestionBoutique;
import gestion.GestionProduits;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.ServeurThreadProduits;
import thread.XMLThread;


/*caractérisées par leur nom, l'adresse IP, le port pour la gestion des commandes et le port pour la gestion des produits*/

/**
 *
 * @author Benjamin
 */

//l'increment des commandes et produit est direcetment géré par les methodes ajouter.
public class Boutique implements Cloneable{
    
    private String nom;
    private String id;
    private static int incr;
    private int incrProduits;
    private int incrCommandes;
    private ArrayList<Produit> listeProduits=new ArrayList<Produit>();
    private ArrayList<Commande> listeCommandes=new ArrayList<Commande>();
    private ServeurThreadProduits threadServeurProduit;
    private XMLThread threadSauvegarde;

    public Boutique(String nom, ArrayList<Produit> listeProduits){
        this.incr++;
        
        this.nom = nom;
        this.listeProduits = listeProduits;
        this.id=Integer.toString(incr);
        GestionBoutique.chargerXML(this);
        try {        
            this.runSauvegardeXML();
        } catch (InterruptedException ex) {
            Logger.getLogger(Boutique.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boutique(String nom) throws InterruptedException {
        this.incr++;
        this.nom = nom;
        this.id=Integer.toString(incr);
        GestionBoutique.chargerXML(this);
        this.runSauvegardeXML();
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
        if(Integer.parseInt(prdt.getId())==0)
            prdt.setId(String.valueOf(this.maxIncrProduits()+1));
        this.listeProduits.add(prdt);
    }
    
     public void ajouterCommande(Commande cmd){
        if(Integer.parseInt(cmd.getId())==0)
            cmd.setId(String.valueOf(this.maxIncrCommandes()+1));
        this.listeCommandes.add(cmd);
    }

    public int getIncrProduits() {
        return incrProduits;
    }

    public int getIncrCommandes() {
        return incrCommandes;
    }

    public void setIncrProduits(int incrProduits) {
        this.incrProduits = incrProduits;
    }

    public void setIncrCommandes(int incrCommandes) {
        this.incrCommandes = incrCommandes;
    }

    public void setListeCommandes(ArrayList<Commande> listeCommandes) {
        this.listeCommandes = listeCommandes;
    }
     
     public Produit rechercherProduit(String id){
         
         Iterator prdt = this.listeProduits.iterator();
            
            while(prdt.hasNext()){
                
                Produit o = (Produit) prdt.next();
                
                if(id.equals(o.getId()))
                    return o;    
            }
        return null;
     }

    public int maxIncrProduits(){
        int res=0;

        for(Produit p:this.listeProduits){
            if(Integer.parseInt(p.getId())>res)
                res=Integer.parseInt(p.getId());                   
        }
        return res;
    } 
         
    public int maxIncrCommandes(){
        int res=0;
        
        for(Commande cmd:this.listeCommandes){
            if(Integer.parseInt(cmd.getId())>res)
                res=Integer.parseInt(cmd.getId());                   
        }
        return res;
    }
    
    public void runServeurProduits(int port){
        this.threadServeurProduit=new ServeurThreadProduits(port,this);
        Thread t = new Thread(this.threadServeurProduit);
        t.start();
    }
    
        public void runSauvegardeXML() throws InterruptedException{
        this.threadSauvegarde=new XMLThread(this);
        Thread t = new Thread(this.threadSauvegarde);
        t.start();
    }
}

