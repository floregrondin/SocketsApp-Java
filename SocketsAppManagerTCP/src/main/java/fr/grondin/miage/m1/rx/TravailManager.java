/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Représente l'action choisie par un client Manager TCP pour un couple login/pwd
 *
 * @author Flo
 */
public class TravailManager {
    
    private int port;

    /**
     * Constructeur qui permet d'assigner un port au Manager TCP
     * @param port
     */
    public TravailManager(int port) {
        this.port = port;
    }
    
    /**
     * Permet au Manager de choisir une action à effectuer sur un couple login/pwd
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
                	System.out.println("Voici les actions disponibles : ");
                	
                	//"Privilèges" du client manager -> possible de choisir entre plusieurs actions
                	System.out.println("ADD - Créer une paire");
                	System.out.println("CHK - Tester une paire");
                	System.out.println("MOD - Mettre à  jour une paire");
                	System.out.println("DEL - Supprimer une paire");   
                	System.out.println();
                	
                	System.out.println("Veuillez saisir la commande choisie (3 char)");
                	String cmd = sc.next();
                	
                	System.out.println("Veuillez saisir votre login");
                	String login = sc.next();
                	
                	System.out.println("Veuillez saisir votre mot de passe");
                	String pwd = sc.next();
                	
                	sortieSocket.println(cmd + " " + login + " " + pwd);
                	
                	String rep = entreeSocket.readLine();
                	System.out.println("Réponse : " + rep);
                	
                	in.readLine();
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(TravailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
