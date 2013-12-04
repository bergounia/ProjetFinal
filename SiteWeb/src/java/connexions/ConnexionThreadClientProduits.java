package connexions;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import rmi.IGestionBoutiques;

public class ConnexionThreadClientProduits {

    private static IGestionBoutiques gbu;

    public static void ajouterProduit(String address, int port, String nomProduit, long prix) {

        Element racine = new Element("produit");
        org.jdom2.Document doc = new Document(racine);

        Element nom = new Element("nom");
        racine.addContent(nom);
        nom.setText(nomProduit);

        Element prixp = new Element("prix");
        racine.addContent(prixp);
        prixp.setText(String.valueOf(prix));

        racine.setAttribute(new Attribute("action", "ajoutProduit"));

        try {
            Socket s = new Socket(address, port);
            java.io.OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(doc);
            oos.flush();
            s.close();

        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }

    }

    public static org.jdom2.Document demanderProduits(String nom) throws ClassNotFoundException, UnknownHostException, IOException {

        org.jdom2.Document res = null;

        try {
            gbu = (IGestionBoutiques) Naming.lookup("rmi://localhost/GestionBoutiques");
        } catch (NotBoundException ex) {
            Logger.getLogger(ConnexionThreadClientProduits.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConnexionThreadClientProduits.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ConnexionThreadClientProduits.class.getName()).log(Level.SEVERE, null, ex);
        }

        String port = gbu.getPortProduitsBoutique(nom);
        String ip = gbu.getPortIPBoutique(nom);

        if (port != null && ip != null) {

            Element racine = new Element("demande");
            org.jdom2.Document doc = new Document(racine);
            Socket s = new Socket(ip, Integer.valueOf(port));

            racine.setAttribute(new Attribute("action", "recevoirProduits"));

            //envoyer la demande
            try {
                java.io.OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(doc);
                oos.flush();

            } catch (Exception e) {
                System.err.println("Erreur : " + e);
            }

            //reception de la demande
            InputStream is;
            try {
                is = s.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                res = (org.jdom2.Document) ois.readObject();
                affiche(res);
                s.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return res;
        }

        return null;
    }

    static void affiche(org.jdom2.Document document) {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
        } catch (java.io.IOException e) {
        }
    }

    public static void main(String arg[]){
     try {
     ConnexionThreadClientProduits.demanderProduits("Carrefour");
     } catch (ClassNotFoundException ex) {
     Logger.getLogger(ConnexionThreadClientProduits.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
     Logger.getLogger(ConnexionThreadClientProduits.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
}
