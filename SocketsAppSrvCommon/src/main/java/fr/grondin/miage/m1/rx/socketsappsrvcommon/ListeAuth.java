/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx.socketsappsrvcommon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Fichier mis à disposition par les enseignants
 * Répercutent les actions d'un client sur la base de données
 * 
 * @author Flo
 */
public class ListeAuth {
    
    /**
	 *  Connection Ã  la BD via JDBC
	 */
	private Connection conn;
	
	/**
	 * RequÃªte SQL pour tester
	 */
	private static final String requeteCheck = "select login from AUTH where login = ? AND passwd = ?";
	/**
	 * RequÃªte SQL pour crÃ©ation
	 */
	private static final String requeteInsert = "insert into AUTH values (?, ?)";
	/**
	 * RequÃªte SQL pour modification
	 */
	private static final String requeteUpdate = "update AUTH set passwd = ? where login = ?";
	/**
	 * RequÃªte SQL pour suppression
	 */
	private static final String requeteDelete = "delete from AUTH where login = ? AND passwd = ?";
	
	/**
	 * Prepared Statement pour tester
	 */
	private PreparedStatement requeteCheckSt = null;
	/**
	 * Prepared Statement pour crÃ©ation
	 */
	private PreparedStatement requeteInsertSt = null;
	/**
	 * Prepared Statement pour modification
	 */
	private PreparedStatement requeteUpdateSt = null;
	/**
	 * Prepared Statement pour suppression
	 */
	private PreparedStatement requeteDeleteSt = null;
	
	/**
	 * constructeur
	 * @param nomBD le nom de la bd
	 */
	public ListeAuth(String nomBD) {
		try {
			// rÃ©cupÃ©ration du driver
		    Class.forName("org.h2.Driver");
		    // crÃ©ation d'une connexion
		    conn = DriverManager.getConnection("jdbc:h2:"+nomBD+";IGNORECASE=TRUE", "sa", "");
	        // On regarde si la table existe deja
	        try {
	        	// construction du prepared statement
	        	requeteCheckSt = conn.prepareStatement(requeteCheck);
	        } catch(Exception e) {
	        	// sinon on la cree
	        	Statement s = conn.createStatement();
	        	s.execute("create table AUTH  ( " +
	        			" login VARCHAR( 256 ) NOT NULL PRIMARY KEY, " +
	        			" passwd VARCHAR( 256 ) )");
	        	// on ajoute des entrees de test
	        	s.executeUpdate("insert into AUTH values ('Toto', 'Toto')");
	        	s.executeUpdate("insert into AUTH values ('Titi', 'Titi')");
	        	s.executeUpdate("insert into AUTH values ('Tata', 'Tata')");
	        	s.executeUpdate("insert into AUTH values ('Tutu', 'Tutu')");
	        	
	        	// on retente la construction qui devrait desormais marcher
	        	requeteCheckSt = conn.prepareStatement(requeteCheck);
	        }
	        // construction des autres prepared statement
		    requeteInsertSt = conn.prepareStatement(requeteInsert);
		    requeteUpdateSt = conn.prepareStatement(requeteUpdate);
		    requeteDeleteSt = conn.prepareStatement(requeteDelete);
		} catch(Exception e) {
			// il y a eu une erreur
			e.printStackTrace();
		}
	}
	
	/**
	 * crÃ©ation d'un couple (login, mot de passe)
	 * @param login : le login
	 * @param passwd : le mot de passe
	 * @return true si ça c'est bien passé
	 */
	public synchronized boolean creer(String login, String passwd) {
		try {
			requeteInsertSt.setString(1, login);
	        requeteInsertSt.setString(2, passwd);
	      
	        if (requeteInsertSt.executeUpdate()==1)
				return true;
        	else
        		return false;
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 *  mise Ã  jour d'un couple (login, mot de passe)
	 * @param login : le login
	 * @param passwd : le mot de passe
	 * @return true si ça c'est bien passé
	 */
	public synchronized boolean mettreAJour(String login, String passwd) {	
		try {
			requeteUpdateSt.setString(1, passwd);
			requeteUpdateSt.setString(2, login);
        	if (requeteUpdateSt.executeUpdate()==1)
				return true;
        	else
        		return false;
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}	
	}
	
	/**
	 *  suppression d'un couple (login, mot de passe)
	 * @param login : le login
	 * @param passwd : le mot de passe
	 * @return true si ça c'est bien passé
	 */

	public synchronized boolean supprimer(String login, String passwd) {
		try {
			requeteDeleteSt.setString(1, login);
			requeteDeleteSt.setString(2, passwd);
        	if (requeteDeleteSt.executeUpdate()==1)
				return true;
        	else
        		return false;   
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}	
	}

	/**
	 *  test d'un couple (login, mot de passe)
	 * @param login : le login
	 * @param passwd : le mot de passe
	 * @return true si ça c'est bien passé
	 */
	public synchronized boolean tester(String login, String passwd) {
		try {
			requeteCheckSt.setString(1, login);
			requeteCheckSt.setString(2, passwd);
			ResultSet rs = requeteCheckSt.executeQuery();
        	if (rs.next())
				return true;
        	else
        		return false;   
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}	
	}

	/**
	 * fermeture de la connexion JDBC
	 * @throws Exception s'il y a eu un problÃ¨me
	 */
	public void fermer() throws Exception {		
		try {
			conn.close();
		} catch(Exception ex) {
			// il y a eu une erreur
			ex.printStackTrace();
		}
	}
	
	/**
	 * Programme de test
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ListeAuth la = new ListeAuth("authentif");
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int choix;
			String login;
			String passwd;
		
			System.out.println("+---------------------------------+");
			System.out.println("| 1 - creer une paire             |");
			System.out.println("| 2 - tester une paire            |");
			System.out.println("| 3 - mettre Ã  jour une paire     |");
			System.out.println("| 4 - supprimer une paire         |");
			System.out.println("| 0 - arreter                     |");
			System.out.println("+---------------------------------+");
		
			choix = sc.nextInt();
			sc.nextLine(); // saute le retour Ã  la ligne
			
			switch (choix) {
			case 0:
				la.fermer();
				sc.close();
				System.exit(0);
			case 1:
				System.out.println("Tapez le login");
				login = sc.next();
				sc.nextLine(); // saute le retour Ã  la ligne
				System.out.println("Tapez le mot de passe");
				passwd = sc.next();
				sc.nextLine(); // saute le retour Ã  la ligne
				if (!la.creer(login, passwd))
					System.out.println("La paire existe deja!");
				else
					System.out.println("Creation effectuee.");
				break;
			case 2:
				System.out.println("Tapez le login");
				login = sc.next();
				sc.nextLine(); // saute le retour Ã  la ligne
				System.out.println("Tapez le mot de passe");
				passwd = sc.next();
				if (la.tester(login, passwd))
					System.out.println("ValidÃ©");
				else
					System.out.println("Erreur d'authentification");
				break;
			case 3:
				System.out.println("Tapez le login");
				login = sc.next();
				sc.nextLine(); // saute le retour Ã  la ligne
				System.out.println("Tapez le mot de passe");
				passwd = sc.next();
				if(!la.mettreAJour(login, passwd))
					System.out.println("La paire n'existe pas!");
				else
					System.out.println("MAJ effectue.");
				break;
			case 4:
				System.out.println("Tapez le login");
				login = sc.next();
				sc.nextLine(); // saute le retour Ã  la ligne
				System.out.println("Tapez le mot de passe");
				passwd = sc.next();
				if (!la.supprimer(login, passwd))
					System.out.println("La paire n'existe pas!");
				else
					System.out.println("Retrait effectue.");
				break;
			}
		}
	}
        
}
