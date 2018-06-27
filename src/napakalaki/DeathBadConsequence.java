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
public class DeathBadConsequence extends NumericBadConsequence{
    // Player player;
    private boolean death;
    
    public DeathBadConsequence(String text, boolean death){
        super(text,10, MAXTREASURES, MAXTREASURES);
        this.death = death;
    }
    
    public boolean getDeath(){
        return death;
    }
}
