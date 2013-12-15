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
    public void ajouterUtilisateur(String nom, String prenom, String mdp, String groupe) throws RemoteException
    {
        int g;
        
        if(groupe != null)
           g= Integer.valueOf(groupe);
        else
           g= 0;
        
        GestionUtilisateurs.lUtilisateurs.add(new Utilisateur(nom, prenom, mdp, g));
        GestionUtilisateurs.sauvegarderXML();
    }
    
    public void supprimerUtilisateur(String nom, String prenom) throws RemoteException
    {
        boolean b= false;
        
        List listeU= racine.getChildren("utilisateur");
        Iterator i = listeU.iterator();
        
        while(!b && i.hasNext())
        {
            Element courant= (Element)i.next();
            if(courant.getChild("nom").getText().equals(nom) && courant.getChild("prenom").getText().equals(prenom))
            {
                Utilisateur u= new Utilisateur(courant.getChild("nom").getText(),
                                               courant.getChild("prenom").getText(),
                                               courant.getChild("mdp").getText(),
                                               Integer.valueOf(courant.getChild("groupe").getText()));
                GestionUtilisateurs.lUtilisateurs.remove(u);
                              
                b= true;
            }
                
        }
        
        GestionUtilisateurs.sauvegarderXML();
    }
    
    public boolean chercherUtilisateur(String identifiant, String motDePasse) throws RemoteException
    {        
        List listeU= racine.getChildren("utilisateur");
        Iterator i = listeU.iterator();
        
        while(i.hasNext())
        {
            Element courant= (Element)i.next();
           
            if(courant.getChild("id").getText().equals(identifiant) && courant.getChild("mdp").getText().equals(Utilisateur.encode(motDePasse)))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean estAdmin(String identifiant) throws RemoteException
    {
        List listeU= racine.getChildren("utilisateur");
        Iterator i = listeU.iterator();
        
        while(i.hasNext())
        {
            Element courant= (Element)i.next();
            if(courant.getChild("id").getText().equals(identifiant))
            {
                if(courant.getChild("groupe").getText().equals("2"))
                    return true;
            }
        }
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
