package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
/**
 *
 * @author Aymeric
 */

public class ServeurRMI{
    public static void main(String[] args) {
        try {
                GestionUtilisateurs gu = new GestionUtilisateurs();

                // Enregistrement sur le Registry
                Naming.rebind("GestionUtilisateurs", gu);
        } catch(RemoteException e) {
                System.err.println("Erreur lors de l'enregistrement : " + e);
                System.exit(-1);
        } catch(MalformedURLException e) {
                System.err.println("URL mal formee : " + e);
                System.exit(-1);
        }
    }
}
