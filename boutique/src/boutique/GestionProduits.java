/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique;

import static boutique.GestionCommandes.document;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Benjamin
 */
public class GestionProduits {
    
   private static Element racine= new Element("produits");
   public static org.jdom2.Document document = new Document(racine);
        
    public static void sauvegarderXML(Boutique boutique){
        
        //on ajoute l'élément incr à l'élément produits
        Element incr= new Element("incr");
        racine.addContent(incr);
        //incr.setText(String.valueOf(Produit.getIncr()));
        
        //parcourt des commandes de la boutique passée en paramétre
        for(Produit pdrt:boutique.getListeProduits()){
            
            //on ajoute l'élément commande à l'élément commandes
            Element produit= new Element("produit");
            racine.addContent(produit);
            
            //on ajoute l'élément id à l'élément commande
            Element id= new Element("id");
            produit.addContent(id);
            id.setText(pdrt.getId());

            //on ajoute l'élément date à l'élément commande
            Element nom= new Element("nom");
            produit.addContent(nom);
            nom.setText(pdrt.getNom());
            
              //on ajoute l'élément date à l'élément commande
            Element prix= new Element("prix");
            produit.addContent(prix);
            prix.setText(String.valueOf(pdrt.getPrix()));
        }
        
        //on demande l'écriture du fichier .xml
        writeFile(boutique.getId());

    }
    
    public static void chargerXML(Boutique boutique){
        Element racine2=ParserXML(boutique.getId());
        
        //On commence par mettre à jour l'incrément des commandes
        boutique.setIncrProduits(Integer.parseInt(racine2.getChild("incr").getText()));

        //On créer une liste des Element Jdom de type produit
        List produits = racine2.getChildren("produit");
        
         //On la parcourt afin de recréer nos objets
        Iterator prdt = produits.iterator();
            
        while(prdt.hasNext()){
            Element produit = (Element)prdt.next();
            
            boutique.ajouterProduit(new Produit(produit.getChild("nom").getText(),produit.getChild("id").getText(),Long.parseLong(produit.getChild("prix").getText())));
        }
    }
    
    public static Element ParserXML(String name)
   {
      //On crée une instance de SAXBuilder
      SAXBuilder sxb = new SAXBuilder();
      try
      {
         //On crée un nouveau document JDOM avec en argument le fichier XML
         //Le parsing est terminé ;)
        /*File global = new File("XML-Boutiques");
        boolean isGlobalCreated = global.mkdirs();

        File dir=new File(global, "Boutique"+name);
        boolean isDirCreated = dir.mkdirs();

        File fichier=new File(dir, "produits.xml");*/
          
         document = sxb.build("XML-Boutiques/Boutique"+name+"/produits.xml");
      }
      catch(Exception e){}

      //On initialise un nouvel élément racine avec l'élément racine du document.
      return document.getRootElement();

   }
    
    public static void writeFile(String name)
    {
            try
            {
                    //On utilise ici un affichage classique avec getPrettyFormat()
                    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                    //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
                    //avec en argument le nom du fichier pour effectuer la sérialisation.
                    File global = new File("XML-Boutiques");
                    boolean isGlobalCreated = global.mkdirs();
                    
                    File dir=new File(global, "Boutique"+name);
                    boolean isDirCreated = dir.mkdirs();
                    
                    File fichier=new File(dir, "produits.xml");
                    sortie.output(document, new FileOutputStream(fichier));
            }
            catch (java.io.IOException e){}
    }
    
}