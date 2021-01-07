/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.Comprehension;

/**
 * Classe qui permet de simuler l'activité d'un serveur UDP
 * @author Flo
 */
public class UDP implements Runnable {
    
	private DatagramPacket dp;
    private int port;
    private Comprehension compre;

    
    /**
     * Constructeur qui permet de créer un DatagramPacket, d'assigner un port et d'interpréter la commande d'un utilisateur via un objet Comprehension
     * @param dp
     * @param port
     * @param compre
     */
    public UDP(DatagramPacket dp, int port, Comprehension compre) {
        this.dp = dp;
    	this.port = port;
        this.compre = compre;
    }
   
    
    /**
     * Méthode qui permet lors de la connexion au serveur UDP de traiter la requête d'un utilisateur
     * Réinitialise la taille du buffer pour que le datagramme ne soit pas chargé pour une prochaine req
     */
    public void travail(){
        
    	DatagramSocket s;
    	byte buffer[] = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		
		try {
			s = new DatagramSocket(port);
			
			while(true) {
				   
				s.receive(dp);

				String req = new String(dp.getData(), 0, dp.getLength());
			    
			    //Decodage + metier
			    String rep = compre.traiter(req, "UDP");
			    System.out.println(rep);
			    
			    //pour pas que le datagramme soit chargé
			    dp.setData(rep.getBytes()); 
			    
			    s.send(dp);
			    
			    //reinit
			    dp.setData(buffer);
			    
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InetAddress ipemetteur = dp.getAddress();
		int portemetteur = dp.getPort();
        
		
        
    }
    
    
    /**
     * Permet d'implémenter la classe UDP sous forme de Thread
     */
	public void run() {		
		
		travail();
	
	}
}
