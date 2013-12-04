package rmi;

import java.io.Serializable;

/**
 *
 * @author Aymeric
 */
public class Boutique implements Serializable{
    private String nom;
    private String adresseIP;
    private int portProduits;
    private int portCommandes;
    
    public Boutique()
    {
        this.nom= "";
        this.adresseIP="";
        this.portProduits= 0;
        this.portCommandes= 0;
    }
    
    public Boutique(String n, String adr, int pP, int pC)
    {
        this.nom= n;
        this.adresseIP= adr;
        this.portProduits= pP;
        this.portCommandes= pC;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public int getPortProduits() {
        return this.portProduits;
    }

    public void setPortProduits(int port) {
        this.portProduits = port;
    }
    
    public int getPortCommandes() {
        return this.portCommandes;
    }

    public void setPortCommandes(int port) {
        this.portCommandes = port;
    }
    
    @Override
    public String toString()
    {
        return "Boutique: "+ this.getNom()+" "+ this.getAdresseIP()+ " "+ this.getPortProduits()+ " "+this.getPortCommandes() +"\n";
    }
}


