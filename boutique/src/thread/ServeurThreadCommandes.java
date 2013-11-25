/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thread;

import boutique.Boutique;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benjamin
 */
public class ServeurThreadCommandes extends Thread{
    
    private static int portEcoute;
    private DatagramSocket socketServeur;
    private Boutique boutique;
    private byte[] tampon = new byte[3000];

    public ServeurThreadCommandes(int port, Boutique boutique) {
        this.portEcoute=port;
        this.socketServeur=null;
        this.boutique=boutique;
        
    }
    
     //** Methode : la première méthode exécutée, elle attend les connections **
  public void run()
  {
      System.out.println("lancement du serveur de commandes");
      
	// Creation du socket
	try {	    
	    socketServeur = new DatagramSocket(portEcoute);
	} catch(Exception e) {
	    System.err.println("Erreur lors de la creation du socket");
	    System.exit(-1);
	}
        
        // Creation du message
	DatagramPacket msg = new DatagramPacket(tampon, tampon.length);
        
        while(true){
          try {
              socketServeur.receive(msg);
              System.out.println("connexion recu");
              Thread t = new Thread(new ConnexionThreadCommandes(msg,this.boutique));
              t.start();
          } catch (IOException ex) {
              Logger.getLogger(ServeurThreadCommandes.class.getName()).log(Level.SEVERE, null, ex);
          }
            //Thread t = new Thread(new ConnexionThreadCommandes(this.socketServeur,this.boutique));
            //t.start();
        }
    }
}
