package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

/**
 *
 * @author Aymeric
 */

public class ClientRMI {
    public static void main(String[] args) {
	IGestionUtilisateurs gestionUtilisateurs = null;
        IGestionBoutiques gestionBoutiques= null;
        
        //IGestionBoutiques gestionBoutiques= null;
 
	// Recuperation de la personne distante
	try {
	    gestionUtilisateurs = (IGestionUtilisateurs)Naming.lookup("rmi://localhost/GestionUtilisateurs");
            gestionBoutiques = (IGestionBoutiques)Naming.lookup("rmi://localhost/GestionBoutiques");
            //gestionBoutiques = (IGestionBoutiques)Naming.lookup("rmi://localhost/GestionBoutiques");
	} catch(NotBoundException e) {
	    System.err.println("Pas possible d'acceder à l'objet distant : " + e);
	    System.exit(-1);
	} catch(MalformedURLException e) {
	    System.err.println("URL mal formee : " + e);
	    System.exit(-1);
	} catch(RemoteException e) {
	    System.err.println("Pas possible d'acceder à l'objet distant : " + e);
	    System.exit(-1);
	}
 
        Utilisateur u1= new Utilisateur("BRIDE", "Aymeric", "aymeric", 1);
        Utilisateur u2= new Utilisateur("KAMINSKI", "Benjamin", "benjamin", 2);
        
        Boutique b1= new Boutique("Amazon", "192.68.1.1", 3131, 3232);
        Boutique b2= new Boutique("Cdiscount", "192.10.0.2", 4141, 4242);
        
        try {
            //gestionUtilisateurs.ajouterUtilisateur("BRIDE", "Aymeric", "aymeric", "1");
            //gestionUtilisateurs.ajouterUtilisateur(u2);
            
            //System.out.println(gestionUtilisateurs.chercherUtilisateur(u1));
            //gestionUtilisateurs.supprimerUtilisateur("BRIDE", "Aymeric");
            //gestionUtilisateurs.supprimerUtilisateur(u2);
            
            //Affiche la liste des utilisateurs créés
            //gestionUtilisateurs.afficherListeUtilisateurs();
            
            
            gestionBoutiques.ajouterBoutique("Amazon", "192.68.1.1", "3131", "3232");
            gestionBoutiques.ajouterBoutique("Cdiscount", "192.10.0.2", "4141", "4242");
            
            //gestionBoutiques.afficherListeBoutiques();
            
            
        } catch(RemoteException e) {
            System.err.println("Erreur lors de l'acces aux methodes : " + e);
            System.exit(-1);
        }
    }
}
