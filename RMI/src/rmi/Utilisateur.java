package rmi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Aymeric
 */
public class Utilisateur {

    private static int incr;
    private String identifiant;
    private String nom;
    private String prenom;
    private String motDePasse;
    private String groupe;
  
    public Utilisateur()
    {
        this.incr++;
        this.nom="";
        this.prenom="";
        this.motDePasse="";
        this.identifiant= String.valueOf(this.incr);
    }
    
    public Utilisateur(String n, String p, String mdp, String g)
    {
        this.incr++;
        this.nom= n;
        this.prenom= p;
        this.motDePasse= encode(mdp);
        this.identifiant= this.nom.substring(0, 4)+ this.prenom.substring(0, 4)+ this.incr;
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

    public String getGroupe() {
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

    public void setGroupe(String groupe) {
        this.groupe = groupe;
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
