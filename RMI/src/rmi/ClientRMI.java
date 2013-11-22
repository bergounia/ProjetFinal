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
 
    /**
     * Methode principale.
     * @param args inutilise
     */
    public static void main(String[] args) {
	IGestionUtilisateurs gestion = null;
 
	// Recuperation de la personne distante
	try {
	    gestion = (IGestionUtilisateurs)Naming.lookup("rmi://localhost/GestionUtilisateurs");
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
 
	try {
            Utilisateur u1= new Utilisateur("BRIDE", "Aymeric", "aymeric", 1);
            gestion.ajouterUtilisateur(u1);
        } catch(RemoteException e) {
            System.err.println("Erreur lors de l'acces aux methodes : " + e);
            System.exit(-1);
        }
    }
 
}
