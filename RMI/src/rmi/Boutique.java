package rmi;

import java.io.Serializable;

/**
 *
 * @author Aymeric
 */
public class Boutique implements Serializable{
    private String nom;
    private String adresseIP;
    private int port;
    
    public Boutique()
    {
        this.nom= "";
        this.adresseIP="";
        this.port= 0;
    }
    
    public Boutique(String n, String adr, int p)
    {
        this.nom= n;
        this.adresseIP= adr;
        this.port= p;
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    @Override
    public String toString()
    {
        return "Boutique: "+ this.getNom()+" "+ this.getAdresseIP()+ " "+ this.getPort()+"\n";
    }
}


