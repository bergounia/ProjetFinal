package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aymeric
 */
public interface IGestionBoutiques extends Remote{
    public void ajouterBoutique(Boutique b) throws RemoteException;
    public void afficherListeBoutiques() throws RemoteException;
}
