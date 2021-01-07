package fr.grondin.miage.m1.rx.socketsappsrvtcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.Comprehension;

/**
 * Classe qui permet de faire appel à la méthode de traitement de la req d'un client TCP
 * 
 * @author Flo
 *
 */
public class GererClient implements Runnable {

	private Socket ss;
	private Comprehension compre;
	
	/**
	 * Constructeur qui récupère un socket et l'interprétation d'une commande via un objet Comprehension
	 * @param ss
	 * @param compre
	 */
	public GererClient(Socket ss, Comprehension compre) {
		super();
		this.ss = ss;
		this.compre = compre;
	}
	
	/**
	 * Permet d'implémenter la classe GérerClient sous forme de Thread
	 */
	@Override
	public void run() {
				
		try {
			
			BufferedReader entreeSocket = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            PrintStream sortieSocket = new PrintStream(ss.getOutputStream());
            
			try {
	            while(true) {
		            String req = entreeSocket.readLine();
		            
		            //Decodage + metier
		            String rep = compre.traiter(req, "TCP");
		            sortieSocket.println(rep);
	            }
		
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
            ss.close();
        
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

}
