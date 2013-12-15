package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aymeric
 */
public interface IGestionUtilisateurs extends Remote {
    
    public void ajouterUtilisateur(String nom, String prenom, String mdp, String groupe) throws RemoteException;
    public void supprimerUtilisateur(String nom, String prenom) throws RemoteException;
    public boolean chercherUtilisateur(String identifiant, String motDePasse) throws RemoteException;
    public boolean estAdmin(String identifiant) throws RemoteException;
    public void afficherListeUtilisateurs() throws RemoteException;
}
