package fr.grondin.miage.m1.rx.socketsappsrvmultiprotocol;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.Comprehension;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.ListeAuth;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.Metier;
import fr.grondin.miage.m1.rx.socketsappsrvtcp.TCP;


/**
 * Classe qui permet de simuler la mise en place d'un serveur TCP sur le port d'un Manager
 * 
 * @author Flo
 *
 */
public class ManagerTCP implements Runnable {

	private int port;
	private Comprehension compre;
	private Metier m;
	private ListeAuth auth;
	
	/**
	 * Constructeur qui permet  d'assigner un port et une base de données
	 * @param port
	 * @param auth
	 */
	public ManagerTCP(int port, ListeAuth auth) {
		this.port = port;
		this.auth = auth;
	}
	

	/**
	 * Permet de démarrer un serveur TCP
	 */
	public void travail() {
		//pour initialiser comprehension 
		this.m = new Metier(auth);
		this.compre = new Comprehension(m, this.port);
		
		//lancement srv
		TCP tcp = new TCP(port, compre);
		tcp.travail();
		
	}
	
	
	/**
	 * Permet d'implémenter la classe CheckerTCP sous forme de Thread
	 */
	public void run() {
		travail();
	}
	
	
}
