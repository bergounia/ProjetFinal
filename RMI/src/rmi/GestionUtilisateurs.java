package rmi;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
    
    private static Element racine;
    public static org.jdom2.Document document;
    private static ArrayList<Utilisateur> lUtilisateurs = new ArrayList<Utilisateur>();

    //Permet d'écraser le fichier pour tout réécrire sinon création de doublons
    public static void reset()
    {
        racine= new Element("utilisateurs");
        document = new Document(racine);
    }
    
    public GestionUtilisateurs() throws RemoteException {
	ParserXML();	
    }
    
    public static void LectureXML()
    {
        List listeUtilisateurs= racine.getChildren("utilisateur");

        //On crée un Iterator sur notre liste
        Iterator i = listeUtilisateurs.iterator();
        while(i.hasNext())
        {
            Element courant= (Element)i.next();
            String nom= courant.getChild("nom").getText();
            String prenom= courant.getChild("prenom").getText();
            String mdp= courant.getChild("mdp").getText();
            int groupe= Integer.valueOf(courant.getChild("groupe").getText());
            
            GestionUtilisateurs.lUtilisateurs.add(new Utilisateur(nom, prenom, mdp, groupe));
        }
    }
    
    public static void ParserXML()
    {
        reset();
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

       LectureXML();
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
    
    public void afficherListeUtilisateurs() throws RemoteException
    {
        for(Utilisateur u : GestionUtilisateurs.lUtilisateurs)
        {
            System.out.println(u.toString());
        }
    }
    
    public static void sauvegarderXML()
    {       
        reset();
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
            groupe.setText(String.valueOf(u.getGroupe()));
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
