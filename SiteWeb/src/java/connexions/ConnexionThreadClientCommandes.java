package connexions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import rmi.IGestionBoutiques;
//import thread.ConnexionThreadCommandes;

/**
 *
 * @author Benjamin
 */
public class ConnexionThreadClientCommandes extends Thread {
    
    private static IGestionBoutiques gbu;
    
    public static org.jdom2.Document recevoirCommande(String nom) throws IOException {
        org.jdom2.Document res=null;
        try {
            gbu = (IGestionBoutiques) Naming.lookup("rmi://localhost/GestionBoutiques");
        } catch (NotBoundException ex) {
            Logger.getLogger(ConnexionThreadClientCommandes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConnexionThreadClientCommandes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ConnexionThreadClientCommandes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String port = gbu.getPortCommandesBoutique(nom);
        String ip = gbu.getPortIPBoutique(nom);
        
        Element racine = new Element("connexion");
        org.jdom2.Document doc = new Document(racine);

        racine.setAttribute("action", "recevoirCommande");
        
        //envoie XML
        XMLOutputter sortie = new XMLOutputter(Format.getCompactFormat());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        sortie.output(doc, baos);

        byte[] tampon = baos.toByteArray();
        DatagramSocket socket = null;

        // Creation du socket
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
            System.err.println("Erreur lors de la creation du socket");
            System.exit(-1);
        }

        // Creation du message
        DatagramPacket msg = null;
        try {
            InetAddress adresse = InetAddress.getByName(ip);
            msg = new DatagramPacket(tampon, tampon.length, adresse, Integer.valueOf(port));

        } catch (Exception e) {
            System.err.println("Erreur lors de la creation du message");
            System.exit(-1);
        }

        // Envoi du message
        try {
            socket.send(msg);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du message");
            System.exit(-1);
        }
        
        //reception de la réponse
        socket.receive(msg);
        String texte = new String(msg.getData(), 0, msg.getLength());
              ByteArrayInputStream bais = new ByteArrayInputStream(texte.getBytes());
                SAXBuilder sxb = new SAXBuilder();
        try {
            res=sxb.build(bais);
        } catch (JDOMException ex) {
            Logger.getLogger(ConnexionThreadClientCommandes.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Fermeture du socket
        try {
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de la fermeture du socket");
            System.exit(-1);
        }
        return res;
    }

    public static void envoyerCommande(String nom, ArrayList<String> listeIdProduits) throws IOException {

        String port = gbu.getPortCommandesBoutique(nom);
        String ip = gbu.getPortIPBoutique(nom);
        
        Element racine = new Element("connexion");
        org.jdom2.Document doc = new Document(racine);

        racine.setAttribute("action", "envoyerCommande");

        Element commande = new Element("commande");
        racine.addContent(commande);

        Element date = new Element("date");
        commande.addContent(date);
        date.setText(String.valueOf(new Date().getTime()));

        Element produits = new Element("produits");
        commande.addContent(produits);

        for (String i : listeIdProduits) {

            Element produit = new Element("produit");
            produits.addContent(produit);
            produit.setAttribute("id", i);
        }

        //envoie XML
        XMLOutputter sortie = new XMLOutputter(Format.getCompactFormat());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        sortie.output(doc, baos);

        byte[] tampon = baos.toByteArray();
        DatagramSocket socket = null;

        // Creation du socket
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
            System.err.println("Erreur lors de la creation du socket");
            System.exit(-1);
        }

        // Creation du message
        DatagramPacket msg = null;
        try {
            InetAddress adresse = InetAddress.getByName(ip);
            msg = new DatagramPacket(tampon, tampon.length, adresse, Integer.valueOf(port));

        } catch (Exception e) {
            System.err.println("Erreur lors de la creation du message");
            System.exit(-1);
        }

        // Envoi du message
        try {
            socket.send(msg);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du message");
            System.exit(-1);
        }

        // Fermeture du socket
        try {
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de la fermeture du socket");
            System.exit(-1);
        }
    }

    public static void validerCommande(String nom, int i) throws RemoteException {
        
        String port = gbu.getPortCommandesBoutique(nom);
        String ip = gbu.getPortIPBoutique(nom);

        Element racine = new Element("connexion");
        org.jdom2.Document doc = new Document(racine);

        racine.setAttribute("action", "validerCommande");

        Element commande = new Element("commande");
        racine.addContent(commande);
        commande.setAttribute("id", String.valueOf(i));

        //envoie XML
        XMLOutputter sortie = new XMLOutputter(Format.getCompactFormat());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            sortie.output(doc, baos);
        } catch (IOException ex) {
            Logger.getLogger(ConnexionThreadClientCommandes.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] tampon = baos.toByteArray();
        DatagramSocket socket = null;

        // Creation du socket
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
            System.err.println("Erreur lors de la creation du socket");
            System.exit(-1);
        }

        // Creation du message
        DatagramPacket msg = null;
        try {
            InetAddress adresse = InetAddress.getByName(ip);
            msg = new DatagramPacket(tampon, tampon.length, adresse, Integer.valueOf(port));

        } catch (Exception e) {
            System.err.println("Erreur lors de la creation du message");
            System.exit(-1);
        }

        // Envoi du message
        try {
            socket.send(msg);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du message");
            System.exit(-1);
        }

        // Fermeture du socket
        try {
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de la fermeture du socket");
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws IOException {
        
        ConnexionThreadClientCommandes.recevoirCommande("Carrefour");

    }
}
