package fr.grondin.miage.m1.rx.socketsappsrvmultiprotocol;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.Comprehension;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.ListeAuth;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.Metier;
import fr.grondin.miage.m1.rx.socketsappsrvtcp.TCP;

/**
 * Classe qui permet de simuler la mise en place d'un serveur TCP sur le port d'un Checker
 * 
 * @author Flo
 *
 */
public class CheckerTCP implements Runnable {

	private int port; 
	private ListeAuth auth;
	private Metier m;
	private Comprehension compre;
	
	/**
	 * Constructeur qui permet  d'assigner un port et une base de données
	 * @param port
	 * @param auth
	 */
	public CheckerTCP(int port, ListeAuth auth) {
		this.port = this.port;
		this.auth = this.auth;

	}
	
	
	/**
	 * Permet de démarrer un serveur TCP
	 */
	public void travail() {
		//pour initialiser comprehension 
		this.m = new Metier(auth);
		this.compre = new Comprehension(m, port);
		
		//lancement srv tcp       
		TCP tcp = new TCP(port, compre);
		tcp.travail();
		
		//lancement client checker tcp
//		TravailClient tc = new TravailClient(port);
//		tc.travail();
		
	}
	
	
	/**
	 * Permet d'implémenter la classe CheckerTCP sous forme de Thread
	 */
	public void run() {
		travail();
	}
	
}
