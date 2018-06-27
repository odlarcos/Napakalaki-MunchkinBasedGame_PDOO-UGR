/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author Oscar
 */
public class Dice {
    
    private static final Dice instance = new Dice();
         // El constructor privado asegura que no se puede instanciar
        // desde otras clases
    
    private Dice() { 
       
    }
    public static Dice getInstance() { 
        return instance;
    }
    
    public int nextNumber(){
        int numeroAleatorio = (int) (Math.random()*6+1);
        
        return numeroAleatorio;
    }
}
