/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boutique.gestion;

import boutique.Boutique;
import static boutique.gestion.GestionCommandes.document;
import java.io.File;
import java.io.FileOutputStream;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Benjamin
 */
public class GestionBoutique {
    
    public static void sauvegarderXML(Boutique boutique){
        
        GestionProduits.sauvegarderXML(boutique);
        GestionCommandes.sauvegarderXML(boutique);
        

        
    }
    
    public static void chargerXML(Boutique boutique){
        
        GestionProduits.chargerXML(boutique);
        GestionCommandes.chargerXML(boutique);
    }

    
}
