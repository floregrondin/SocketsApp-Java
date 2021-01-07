package fr.grondin.miage.m1.rx;

import java.net.DatagramPacket;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.Comprehension;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.ListeAuth;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.Metier;

public class MainSrv {

	public static void main(String[] args) {
		
		int port = 28414;
    	byte buffer[] = new byte[1024];

		
		ListeAuth auth = new ListeAuth("./bd");
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		Metier metier = new Metier(auth);
		Comprehension compre = new Comprehension(metier, port);
		UDP sudp = new UDP(dp, port, compre);
		
		sudp.travail();
		
	}

}
