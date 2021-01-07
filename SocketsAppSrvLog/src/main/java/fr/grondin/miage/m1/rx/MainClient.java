package fr.grondin.miage.m1.rx;

import fr.grondin.miage.m1.rx.socketsappclienttcp.TravailClient;

public class MainClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		/**
		 * travail client tcp normal
		 */
		
		int portC = 28414;
		int portM = 28415;
		
//		TravailClient travailCheckerTCP = new TravailClient(portC);
//		travailCheckerTCP.travail();
//		
		TravailManager travailManagerTCP = new TravailManager(portM);
		travailManagerTCP.travail();
		
		
//		JsonLogger log = null;
//		log.log(host, port, proto, type, login, result);

		
	}

}
