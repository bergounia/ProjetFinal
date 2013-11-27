package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aymeric
 */

public class ServeurRMI{
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            Logger.getLogger(ServeurRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                GestionUtilisateurs gu = new GestionUtilisateurs();
                GestionBoutiques gb= new GestionBoutiques();
                
                // Enregistrement sur le Registry
                Naming.rebind("GestionUtilisateurs", gu);
                Naming.rebind("GestionBoutiques", gb);
        } catch(RemoteException e) {
                System.err.println("Erreur lors de l'enregistrement : " + e);
                System.exit(-1);
        } catch(MalformedURLException e) {
                System.err.println("URL mal formee : " + e);
                System.exit(-1);
        }
    }
}
