/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
        incr.setText(String.valueOf(Commande.getIncr()));
        
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
            date.setText(cmd.getDateCmd().toString());
            
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
    
    public void chargerXML(){
        
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
