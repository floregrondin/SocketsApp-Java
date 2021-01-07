package fr.grondin.miage.m1.rx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MainClient {
	
	public static void main(String[] args) {	

		try {	
			
			//port pour requête udp 
			int port = 28414;
	    	byte buffer[] = new byte[1024];
			DatagramSocket s = new DatagramSocket();
			
			Scanner sc = new Scanner(System.in);
		    System.out.println("Veuillez saisir votre login");
		    String login = sc.next();
		    System.out.println("Veuillez saisir votre mot de passe");
		    String pwd = sc.next();
		    
		    System.out.println("CHK " + login + " " + pwd);
		    String req = "CHK + " + login + " " + pwd;
		   
	
			DatagramPacket dp = new DatagramPacket(req.getBytes(), req.length(), InetAddress.getLocalHost(), port);
			
			s.send(dp);
			dp.setData(buffer);
			s.receive(dp);
		
		    //réponse envoyée après avoir check login + pwd
		    String rep = new String(dp.getData(), 0, dp.getLength());
		    System.out.println("Réponse : " + rep);
		    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
 
	}
}
