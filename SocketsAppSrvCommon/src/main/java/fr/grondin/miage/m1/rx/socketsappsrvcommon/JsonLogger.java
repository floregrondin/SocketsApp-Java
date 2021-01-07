package fr.grondin.miage.m1.rx.socketsappsrvcommon;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


/**
 * Classe Singleton qui permet de logger des requ�tes vers un serveur de log sur le port 3244 de la machine locale
 * 
 * @author torguet
 *
 */
public class JsonLogger {
	
	private static JsonLogger log;
	private static String LOGGER_CMD_CLIENT_SRVAS;
	private static Socket s;
	private int port = 3244;
	
	/**
	 * Constructeur prot�g� qui permet de construire un logger pour un syst�me
	 * @param log objet logger à initialiser
	 * @param LOGGER_CMD_CLIENT_SRVAS nom du logger
	 */
	private JsonLogger(JsonLogger log, String LOGGER_CMD_CLIENT_SRVAS, Socket s) {
		JsonLogger.log = getLogger();
		try {
			this.s = new Socket("localhost", port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Transforme une requ�te en Json
	 * 
	 * @param host machine client
	 * @param port port sur la machine client
	 * @param proto protocole de transport utilis�
	 * @param type type de la requ�te
	 * @param login login utilis�
	 * @param result r�sultat de l'op�ration
	 * @return un objet Json correspondant � la requ�te
	 */
	private JsonObject reqToJson(String host, int port, String proto, String type, String login, String result) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("host", host)
		   	   .add("port", port)
		   	   .add("proto", proto)
			   .add("type", type)
			   .add("login", login)
			   .add("result", result)
			   .add("date", new Date().toString());

		return builder.build();
	}
	
	/**
	 *  singleton
	 */
	private static JsonLogger logger = null;
	
	/**
	 * r�cup�ration du logger qui est cr�� si n�cessaire
	 * 
	 * @return le logger
	 */
	private static JsonLogger getLogger() {
		if (logger == null) {
			logger = new JsonLogger(log, LOGGER_CMD_CLIENT_SRVAS, s);
		}
		return logger;
	}
	
	/**
	 * m�thode pour logger
	 * 
	 * @param host machine client
	 * @param port port sur la machine client
	 * @param proto protocole de transport utilis�
	 * @param type type de la requ�te
	 * @param login login utilis�
	 * @param result r�sultat de l'op�ration
	 */
	public static void log(String host, int port, String proto, String type, String login, String result) {
		getLogger();
		logger.reqToJson(host, port, proto, type, login, result);
		
		try {
			while (true) {
				try {
					Writer fichierLog = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/log.txt"), "utf-8"));
					fichierLog.write(logger.toString());
					fichierLog.close();
				} catch (IOException e) {
					System.out.println("ERREUR : Pas d'insertion dans le fichier de log.");
					e.printStackTrace();
				}
				s.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


}
