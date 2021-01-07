/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.grondin.miage.m1.rx;

/**
 *
 * @author Flo
 */
public class MainManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int port = 28415;
        
        TravailManager tc = new TravailManager(port);
        
        tc.travail();
        
        
    }
    
}
