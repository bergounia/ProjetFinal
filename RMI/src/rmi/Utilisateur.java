package rmi;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Aymeric
 */
public class Utilisateur implements Serializable{

    private static int incr;
    private String identifiant;
    private String nom;
    private String prenom;
    private String motDePasse;
    private int groupe;
  
    public Utilisateur()
    {
        Utilisateur.incr++;
        this.nom="";
        this.prenom="";
        this.motDePasse="";
        this.identifiant= String.valueOf(this.incr);
        this.groupe=1;
    }
    
    public Utilisateur(String n, String p, String mdp, int g)
    {
        Utilisateur.incr++;
        this.nom= n;
        this.prenom= p;
        this.motDePasse= encode(mdp);
        this.identifiant= this.nom.substring(0, 3)+ this.prenom.substring(0, 3)+ this.incr;
        this. groupe= g;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getGroupe() {
        return groupe;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGroupe(int groupe) {
        this.groupe = groupe;
    }
    
    @Override
    public String toString()
    {
        return "Utilisateur: "+ this.getNom()+" "+ this.getPrenom()+ "\n";
    }
    
    public boolean equals(Object o)
    {
        if(this.nom.equals(((Utilisateur)o).getNom()) && this.prenom.equals(((Utilisateur)o).getPrenom()))
            return true;
        else
            return false;
    }
    
    public static String encode(String password)
    {
        byte[] uniqueKey = password.getBytes();
        byte[] hash      = null;

        try
        {
                hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        }
        catch (NoSuchAlgorithmException e)
        {
                throw new Error("No MD5 support in this VM."+ e);
        }

        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; i++)
        {
                String hex = Integer.toHexString(hash[i]);
                if (hex.length() == 1)
                {
                        hashString.append('0');
                        hashString.append(hex.charAt(hex.length() - 1));
                }
                else
                        hashString.append(hex.substring(hex.length() - 2));
        }
        return hashString.toString();
    }
}
