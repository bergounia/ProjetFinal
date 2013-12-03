package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Aymeric
 */
public interface IGestionBoutiques extends Remote{
    public void ajouterBoutique(String nom, String adresse, String port) throws RemoteException;
    public void afficherListeBoutiques() throws RemoteException;
    public ArrayList<String> getListeDesBoutiques() throws RemoteException;
}
