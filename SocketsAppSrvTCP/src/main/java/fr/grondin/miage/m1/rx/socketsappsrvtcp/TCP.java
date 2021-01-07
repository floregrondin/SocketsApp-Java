/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx.socketsappsrvtcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.Comprehension;

/**
 * 
 * @author Flo
 */
public class TCP implements Runnable {
    
    private int port;
    private Comprehension compre;

    /**
     * Constructeur qui permet d'assigner un port et d'interpréter une commande via un objet Comprehension
     * @param port
     * @param compre
     */
    public TCP(int port, Comprehension compre) {
        this.port = port;
        this.compre = compre;
    }
   
    
    /**
     * Méthode qui permet lors de la connexion au serveur TCP de mettre en place un Thread sur un objet GererClient
     */
    public void travail(){
        
    	ServerSocket se;
    	
        try {
       
           se = new ServerSocket(port);
           
           try {
        	   
        	   while(true) {
        		   Socket ss = se.accept();
        	   
		    	   //travail Thread
        		   GererClient gc = new GererClient(ss, compre);
        		   Thread t = new Thread(gc);
        		   t.start();
        		   
        	   }
           } catch(IOException ex) {
        	   ex.printStackTrace();
           }
           
           se.close(); 
            
        } catch (IOException ex) {
            Logger.getLogger(TCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * Permet d'implémenter la classe TCP sous forme de Thread
     */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		travail();
	}

}
