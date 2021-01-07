/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx.socketsappsrvtcp;

import fr.grondin.miage.m1.rx.socketsappsrvcommon.Comprehension;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.ListeAuth;
import fr.grondin.miage.m1.rx.socketsappsrvcommon.Metier;

/**
 *
 * @author Flo
 */
public class MainSrv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    	int port = 28415;
    	
        ListeAuth auth = new ListeAuth("./bd");
        Metier m = new Metier(auth);
        
        Comprehension cmp = new Comprehension(m, port);
        
        TCP stcp = new TCP(port, cmp);
        
        stcp.travail();
        
        
        
    }
    
}
