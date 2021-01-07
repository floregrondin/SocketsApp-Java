package fr.grondin.miage.m1.rx.socketsappsrvmultiprotocol;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.JsonLogger;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.ListeAuth;


public class LancementSrv {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int portC = 28414;
		int portM = 28415;
		
		ListeAuth auth = new ListeAuth("./bd");
		
		CheckerTCP ct = new CheckerTCP(portC, auth);
		Thread tct = new Thread(ct);
		tct.start();
		
		ManagerTCP mt = new ManagerTCP(portM, auth);
		Thread tmt = new Thread(mt);
		tmt.start();
		
		CheckerUDP cu = new CheckerUDP(portC, auth);
		Thread tcu = new Thread(cu);
		tcu.start();
		

	}

}
