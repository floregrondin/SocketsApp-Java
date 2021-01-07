/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx.socketsappsrvcommon;

/**
 * Classe qui permet de faire appel aux méthodes de la bd
 * @author Flo
 */
public class Metier {
    
    public ListeAuth auth;
    
    /**
     * 
     * @param auth
     */
    public Metier(ListeAuth auth) {
		super();
		this.auth = auth;
	}

    /**
     * Test en bd d'un couple login/pwd
     * @param login : le login
     * @param pwd : le mot de passe
     * @return GOOD si ça s'est bien passé
     */
	public boolean tester (String login, String pwd){
        return auth.tester(login, pwd);
    }
    
	/**
	 * Création en bd d'un couple login/pwd
     * @param login : le login
     * @param pwd : le mot de passe
     * @return GOOD si ça s'est bien passé
	 */
    public boolean creer (String login, String pwd){
        return auth.creer(login, pwd);
    }
    
    /**
     * Modification en bd d'un couple login/pwd
     * @param login : le login
     * @param pwd : le mot de passe
     * @return GOOD si ça s'est bien passé
     */
    public boolean modifier (String login, String pwd){
        return auth.mettreAJour(login, pwd);
    }
    
    /**
     * Suppression en bd d'un couple login/pwd
     * @param login : le login
     * @param pwd : le mot de passe
     * @return GOOD si ça s'est bien passé
     */
    public boolean supprimer (String login, String pwd){
        return auth.supprimer(login, pwd);
    }
    
}
