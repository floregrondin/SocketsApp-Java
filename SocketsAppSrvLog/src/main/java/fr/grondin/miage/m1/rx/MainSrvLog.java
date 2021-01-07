package fr.grondin.miage.m1.rx;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.JsonLogger;

public class MainSrvLog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket se;
		int portC = 28414;
		int portM = 28415;

		try {
			/*
			 * Ne pas modifier le numéro de port qui correspond au Serveur de Log
			 */
			se = new ServerSocket(3244);
			Socket s = se.accept();
			
			JsonLogger log = null;
			
			JsonLogger.log("localhost", portC, "TCP", "ADD", "flotest", "GOOD");
			JsonLogger.log("localhost", portM, "UDP", "CHK", "test", "BAD");

			se.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
