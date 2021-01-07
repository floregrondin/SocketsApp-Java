package fr.grondin.miage.m1.rx.socketsappsrvmultiprotocol;

import java.net.DatagramPacket;

import fr.grondin.miage.m1.rx.UDP;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.Comprehension;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.ListeAuth;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.Metier;

/**
 * Classe qui permet de simuler la mise en place d'un serveur UDP sur le port d'un Checker
 * 
 * @author Flo
 *
 */
public class CheckerUDP implements Runnable {

	private int port;
	private ListeAuth auth;	
	
	/**
	 * Constructeur qui permet  d'assigner un port et une base de données
	 * @param port
	 * @param auth
	 */
	public CheckerUDP (int port, ListeAuth auth) {	
		this.port = port;
		this.auth = this.auth;
	}
	
	

	/**
	 * Permet de démarrer un serveur UDP
	 */
	public void travail() {
		
		//lancement serveur UDP
		byte buffer[] = new byte[1024];
		Metier metier = new Metier(auth);
		
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		Comprehension compre = new Comprehension(metier, port);
		UDP sudp = new UDP(dp, port, compre);
		sudp.travail();
	
	}
	
	
	/**
	 * Permet d'implémenter la classe CheckerUDP sous forme de Thread
	 */
	public void run() {

		travail();
		
	}
	

	
	
	
	
}
