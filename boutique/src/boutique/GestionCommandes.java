/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

//Revoir la gestion des incréments, est-il possible de passer par un static

/**
 *
 * @author Benjamin
 */
public class GestionCommandes {
    
    //mise à disposition du document et de l'élément racine
    private static Element racine= new Element("commandes");
    public static org.jdom2.Document document = new Document(racine);
    
    
    public static void sauvegarderXML(Boutique boutique){
        
        //on ajoute l'élément incr à l'élément commandes
        Element incr= new Element("incr");
        racine.addContent(incr);
//        incr.setText(String.valueOf(Commande.getIncr()));
        
        //parcourt des commandes de la boutique passée en paramétre
        for(Commande cmd:boutique.getListeCommandes()){
            
            //on ajoute l'élément commande à l'élément commandes
            Element commande= new Element("commande");
            racine.addContent(commande);
            
            //on ajoute l'élément id à l'élément commande
            Element id= new Element("id");
            commande.addContent(id);
            id.setText(cmd.getId());

            //on ajoute l'élément date à l'élément commande
            Element date= new Element("date");
            commande.addContent(date);
            date.setText(String.valueOf(cmd.getDateCmd().getTime()));
            
            //on ajoute l'élément produits à l'élément commande
            Element produits= new Element("produits");
            commande.addContent(produits);
            
            //on parcourt les produits de la commande
            for(Produit prdt:cmd.getListeProduits()){
                
                //on ajoute l'élément produit à l'élément produits
                Element produit= new Element("produit");
                produits.addContent(produit);

                //on ajoute l'élément id à l'élément produit
                Element idPrdt= new Element("id");
                produit.addContent(idPrdt);
                idPrdt.setText(prdt.getId());
            }  
        }
        
        //on demande l'écriture du fichier .xml
        writeFile(boutique.getId());

    }
    
    public static void chargerXML(Boutique boutique){
        ParserXML(boutique.getId());
        
        //On commence par mettre à jour l'incrément des commandes
       // Commande.setIncr(Integer.parseInt(racine.getChild("incr").getText()));
        
        //On créer une liste des Element Jdom de type commande
        List commandes = racine.getChildren("commande");
        
        //On la parcourt afin de recréer nos objets
        Iterator cmd = commandes.iterator();
            
        while(cmd.hasNext()){
            Element commande = (Element)cmd.next();
            
            List produits = commande.getChild("produits").getChildren("produit");
            ArrayList<Produit> listeProduits=new ArrayList<Produit>();
            
           Iterator prdt = commandes.iterator();
            
            while(prdt.hasNext()){
                Element produit = (Element)prdt.next();
                
                if(boutique.rechercherProduit(produit.getChild("id").getText())!=null)
                    listeProduits.add(boutique.rechercherProduit(produit.getChild("id").getText()));
            }
            System.out.println(listeProduits);
           boutique.ajouterCommande(new Commande(new Date(Long.parseLong(commande.getChild("date").getText())), (ArrayList<Produit>)listeProduits.clone()));            
        }
        
    }
    
    public static void ParserXML(String name)
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

        File fichier=new File(dir, "commandes.xml");*/
          
         document = sxb.build("XML-Boutiques/Boutique"+name+"/commandes.xml");
      }
      catch(Exception e){}

      //On initialise un nouvel élément racine avec l'élément racine du document.
      racine = document.getRootElement();

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
                    
                    File fichier=new File(dir, "commandes.xml");
                    sortie.output(document, new FileOutputStream(fichier));
            }
            catch (java.io.IOException e){}
    }
    
}