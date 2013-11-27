package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aymeric
 */
public interface IGestionUtilisateurs extends Remote {
    
    public void ajouterUtilisateur(Utilisateur u) throws RemoteException;
    public void supprimerUtilisateur(Utilisateur u) throws RemoteException;
    public boolean chercherUtilisateur(Utilisateur u) throws RemoteException;
    public void afficherListeUtilisateurs() throws RemoteException;
}
