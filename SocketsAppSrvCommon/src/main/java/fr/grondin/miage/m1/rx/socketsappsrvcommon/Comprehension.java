/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx.socketsappsrvcommon;

/**
 * Classe qui permet d'interpréter la commande saisie par un utilisateur
 * @author Flo
 */
public class Comprehension {
    
    private Metier metier;
    private int port;

    /**
     * Constructeur qui permet d'assigner un métier (qui sera initialisée par une BD) et un port pour un client
     * @param metier pour l'appel aux méthodes en bd
     * @param port : port du serveur
     */
    public Comprehension(Metier metier, int port) {
        this.metier = metier;
        this.port = port;
    }

    /**
     * Analyse la requête du client et retourne la réponse en base de données appropriée
     * Prend en compte les droits de l'utilisateur (selon son port)
     * 
     * Si pour une action donnée, le couple login/pwd existe, on retourne GOOD
     * S'il n'existe pas, on retourne BAD
     * Si le port d'un utilisateur n'est pas approprié ou que la commande n'existe pas, on retourne ERROR
     * 
     * @param req
     * @return la réponse appropriée après analyse de req
     */
	public String traiter(String req, String proto) {
		String rep = "ERROR : Couldn't check login/pwd";
        String[] tab = req.split(" ");
        String cmd = tab[0];
        String login = tab[1];
        String pwd = tab[2];
        
        switch (cmd.toUpperCase()){
            case "CHK" : {
                boolean returnmetier = metier.tester(login, pwd);
                if (returnmetier){
                    rep="GOOD";
                    if (this.port == 28415) {
                    	System.out.println("DONE");
                    }
                } else {
                    rep="BAD";
                    if (this.port == 28415) {
                    	System.out.println("ERROR");
                    }
                }
                
                if (port == 28414) {
        	        JsonLogger.log("localhost", 28414, proto, "CHK", login, rep);
                } else if (port == 28415) {
                	JsonLogger.log("localhost", 28415, proto, "CHK", login, rep);			
                }
                
                
                break;
            }

            case "ADD" : {
            	if (this.port == 28415) {
            		boolean returnmetier = metier.creer(login, pwd);
            		if (returnmetier){
            			rep="GOOD";
                    	System.out.println("DONE");
            		} else {
            			rep="BAD";
                    	System.out.println("ERROR");
            		}
            	} else {
            		System.out.println("ERROR : You don't have to right to ask for this command.");
    	        }
            	
                if (port == 28414) {
        	        JsonLogger.log("localhost", 28414, proto, "ADD", login, rep);
                } else if (port == 28415) {
                	JsonLogger.log("localhost", 28415, proto, "ADD", login, rep);			
                }
            	break;	            		
            }
            
            case "DEL" : {
            	if (this.port == 28415) {
            		boolean returnmetier = metier.supprimer(login, pwd);
            		if (returnmetier){
            			rep="GOOD";
            			System.out.println("DONE");
            		} else {
            			rep="BAD";
            			System.out.println("ERROR");
            		}
	            } else {
            		System.out.println("ERROR : You don't have to right to ask for this command.");
	            }
            	
                if (port == 28414) {
        	        JsonLogger.log("localhost", 28414, proto, "DEL", login, rep);
                } else if (port == 28415) {
                	JsonLogger.log("localhost", 28415, proto, "DEL", login, rep);			
                }
            	break;
            }
            
            case "MOD" : {
            	if (this.port == 28415) {
            		boolean returnmetier = metier.modifier(login, pwd);
            		if (returnmetier){
            			rep="GOOD";
            			System.out.println("DONE");
            		} else {
            			rep="BAD";
            			System.out.println("ERROR");
            		}
	            } else {
            		System.out.println("ERROR : You don't have to right to ask for this command.");
	            }
            	
                if (port == 28414) {
        	        JsonLogger.log("localhost", 28414, proto, "MOD", login, rep);
                } else if (port == 28415) {
                	JsonLogger.log("localhost", 28415, proto, "MOD", login, rep);			
                }
            	break;
            }
        }
		return rep;
	}

}
