/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx.socketsappclienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Représente l'action d'un client checker TCP qui CHK son couple login/pwd
 * 
 * @author Flo
 */
public class TravailClient {
    
    private int port;

    /**
     * Constructeur qui permet d'assigner un port au Checker TCP
     * @param port
     */
    public TravailClient(int port) {
        this.port = port;
    }
    
    /**
     * Permet au Checker de saisir son couple login/pwd pour une action de CHK
     */
    public void travail(){
        
        try {
            
            Socket s = new Socket("localhost", port);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            try {
                BufferedReader entreeSocket = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintStream sortieSocket = new PrintStream(s.getOutputStream());
                
                while (true) {
                	Scanner sc = new Scanner(System.in);
	                
                	System.out.println("Veuillez saisir votre login");
	                String login = sc.next();
	                
	                System.out.println("Veuillez saisir votre mot de passe");
	                String pwd = sc.next();
	                
	                //Ce client n'a seulement le droit que de checker
	                sortieSocket.println("CHK " + login + " " + pwd);

	                String rep = entreeSocket.readLine();
	                System.out.println("Réponse : " + rep);
	                
	                in.readLine();
                }  
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(TravailClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
