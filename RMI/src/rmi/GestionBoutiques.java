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
public class GestionBoutiques extends UnicastRemoteObject implements IGestionBoutiques {
    
    private static Element racine;
    public static org.jdom2.Document document;
    private static ArrayList<Boutique> lBoutiques= new ArrayList<Boutique>();
    
    //Permet d'écraser le fichier pour tout réécrire sinon création de doublons
    public static void reset()
    {
        racine= new Element("boutiques");
        document = new Document(racine);
    }
    
    public GestionBoutiques() throws RemoteException {
	ParserXML();	
    }
    
    public static void LectureXML()
    {
        List listeBoutiques= racine.getChildren("boutique");

        //On crée un Iterator sur notre liste
        Iterator i = listeBoutiques.iterator();
        while(i.hasNext())
        {
            Element courant= (Element)i.next();
            String nom= courant.getChild("nom").getText();
            String adresseIP= courant.getChild("adresseIP").getText();
            int portProduits= Integer.valueOf(courant.getChild("portProduits").getText());
            int portCommandes= Integer.valueOf(courant.getChild("portCommandes").getText());
            
            GestionBoutiques.lBoutiques.add(new Boutique(nom, adresseIP, portProduits, portCommandes));
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
          document = sxb.build(new File("boutiques.xml"));
       }
       catch(Exception e){}

       //On initialise un nouvel élément racine avec l'élément racine du document.
       racine = document.getRootElement();

       LectureXML();
    }
    
    public void ajouterBoutique(String nom, String adresse, String portProduits, String portCommandes) throws RemoteException
    {
        int pP, pC;
        
        if(portProduits != null && portCommandes != null)
        {
           pP= Integer.valueOf(portProduits);
           pC= Integer.valueOf(portCommandes);
        }
        else
        {
           pP= 0;
           pC= 0;
        }
        
        GestionBoutiques.lBoutiques.add(new Boutique(nom, adresse, pP, pC));
        GestionBoutiques.sauvegarderXML();
    }
    
    public void afficherListeBoutiques() throws RemoteException
    {
        for(Boutique b : GestionBoutiques.lBoutiques)
        {
            System.out.println(b.toString());
        }
    }
    
    public static void sauvegarderXML()
    {
        reset();
        
        for(Boutique b : GestionBoutiques.lBoutiques)
        {
            Element boutique= new Element("boutique");
            racine.addContent(boutique);

            Element nom= new Element("nom");
            boutique.addContent(nom);
            nom.setText(b.getNom());
            
            Element adresseIP= new Element("adresseIP");
            boutique.addContent(adresseIP);
            adresseIP.setText(b.getAdresseIP());
            
            Element portProduits= new Element("portProduits");
            boutique.addContent(portProduits);
            portProduits.setText(String.valueOf(b.getPortProduits()));
            
            Element portCommandes= new Element("portCommandes");
            boutique.addContent(portCommandes);
            portCommandes.setText(String.valueOf(b.getPortCommandes()));
        }
        
        //Enregistrement dans le fichier
        try
        {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream("boutiques.xml"));
        }
        catch (java.io.IOException e){}
    }
    
    public ArrayList<String> getListeDesBoutiques() throws RemoteException
    {
        ArrayList<String> nomDesBoutiques= new ArrayList<String>();
        
        for(Boutique b: lBoutiques)
        {
            nomDesBoutiques.add(b.getNom());
        }
        
        return nomDesBoutiques;
    }
    
    public String getPortProduitsBoutique(String nom) throws RemoteException{
        
        for(Boutique b:this.lBoutiques){
            if(b.getNom().equals(nom))
                return String.valueOf(b.getPortProduits());
        }
        return null;
    }
    
    public String getPortCommandesBoutique(String nom) throws RemoteException{
        
        for(Boutique b:this.lBoutiques){
            if(b.getNom().equals(nom))
                return String.valueOf(b.getPortCommandes());
        }   
        return null;
    }
    
    public String getPortIPBoutique(String nom) throws RemoteException{
        
        for(Boutique b:this.lBoutiques){
            if(b.getNom().equals(nom))
                return b.getAdresseIP();
        }   
        return null;
    }
}
