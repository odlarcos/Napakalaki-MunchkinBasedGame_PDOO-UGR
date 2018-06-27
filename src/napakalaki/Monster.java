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
public class Monster {
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence bc;
    private int levelChangeAgainstCultistPlayer;
    
    Monster(String name, int level, Prize prize, BadConsequence bc, int IC ){
        this.name = name;
        this.combatLevel = level;
        this.bc = bc;
        this.prize = prize;
        levelChangeAgainstCultistPlayer = IC;
    }
    
    Monster(String name, int level, Prize prize, BadConsequence bc){
        this.name = name;
        this.combatLevel = level;
        this.bc = bc;
        this.prize = prize;
        levelChangeAgainstCultistPlayer = 0;
    }
    
    public int getCombatLevelAgainstCultistPlayer(){
        return (combatLevel + levelChangeAgainstCultistPlayer);
    }
    
    public String getName(){
        return name;
    }
    public int getCombatLevel(){
        return combatLevel;
    }
    
    public int getLevelsGained(){
        return prize.getLevel();
    }
    
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    
    public Prize getPrize(){
        return prize;
    }
    public BadConsequence getBc(){
        return bc;
    }
    
    public String toString(){
        String description = "\nName = " + name + "\nCombat level = " + Integer.toString(combatLevel) + 
                "\nPrize: " + prize.toString() + "\nBad Consequence = " + bc.toString() + "\n"; 
        return description;
        // CAMBIAR. No creo que sea getPrize y getBc, más bien será prize.getTreasures y bc.getText
    }
}
