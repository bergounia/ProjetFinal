package rmi;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
    
    private static Element racine= new Element("boutiques");
    public static org.jdom2.Document document = new Document(racine);
    
    public GestionBoutiques() throws RemoteException {
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
          document = sxb.build(new File("boutiques.xml"));
       }
       catch(Exception e){}

       //On initialise un nouvel élément racine avec l'élément racine du document.
       racine = document.getRootElement();

    }
    
    public static void affiche()
    {
        try
        {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
        }
        catch (java.io.IOException e){}
    }

    public static void enregistre(String fichier)
    {
        try
        {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream(fichier));
        }
        catch (java.io.IOException e){}
    }
}
