package rmi;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Aymeric
 */

public class GestionUtilisateurs extends UnicastRemoteObject implements IGestionUtilisateurs {
    
    private static Element racine= new Element("utilisateurs");
    public static org.jdom2.Document document = new Document(racine);
    private static ArrayList<Utilisateur> lUtilisateurs = new ArrayList<Utilisateur>();
        
    public GestionUtilisateurs() throws RemoteException {
	ParserXML();	
    }
    
    public static void ParserXML()
    {
       //On crée une instance de SAXBuilder
       SAXBuilder sxb = new SAXBuilder();
       try
       {
          //On crée un nouveau document JDOM avec en argument le fichier XML
          //Le parsing est terminé ;)
          document = sxb.build(new File("utilisateurs.xml"));
       }
       catch(Exception e){}

       //On initialise un nouvel élément racine avec l'élément racine du document.
       racine = document.getRootElement();

    }
    
    /*Pour l'appartenance à un groupe: 1 pour les administrateurs et 2 pour les utilisateurs*/
    public void ajouterUtilisateur(Utilisateur u) throws RemoteException
    {
        GestionUtilisateurs.lUtilisateurs.add(u);
        GestionUtilisateurs.sauvegarderXML();
    }
    
    public void supprimerUtilisateur(Utilisateur u) throws RemoteException
    {
        if(GestionUtilisateurs.lUtilisateurs.contains(u))
            GestionUtilisateurs.lUtilisateurs.remove(u);
        else
            System.out.println("Utilisateur inexistant !");
        
        GestionUtilisateurs.sauvegarderXML();
    }
    
    public boolean chercherUtilisateur(Utilisateur u) throws RemoteException
    {
        if(GestionUtilisateurs.lUtilisateurs.contains(u))
            return true;
        else
            return false;
    }
    
    public static void sauvegarderXML()
    {
        for(Utilisateur u : GestionUtilisateurs.lUtilisateurs)
        {
            Element utilisateur= new Element("utilisateur");
            racine.addContent(utilisateur);

            Element id= new Element("id");
            utilisateur.addContent(id);
            id.setText(u.getIdentifiant());

            Element nom= new Element("nom");
            utilisateur.addContent(nom);
            nom.setText(u.getNom());

            Element prenom= new Element("prenom");
            utilisateur.addContent(prenom);
            prenom.setText(u.getPrenom());

            Element mdp= new Element("mdp");
            utilisateur.addContent(mdp);
            mdp.setText(u.getMotDePasse());

            Element groupe= new Element("groupe");
            utilisateur.addContent(groupe);
            groupe.setText(u.getGroupe());
        }
        
        //Enregistrement dans le fichier
        try
        {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream("utilisateurs.xml"));
        }
        catch (java.io.IOException e){}
    }
}
