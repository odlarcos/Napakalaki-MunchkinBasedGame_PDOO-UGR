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
public class CultistPlayer extends Player{
    
    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c){
        super(p);
        totalCultistPlayers++;
        myCultistCard = c;
        
    }
    
    @Override
    protected int getCombatLevel(){
        int trunc = (int) (super.getCombatLevel() + 0.7 * super.getCombatLevel() + myCultistCard.getGainedLevels()*totalCultistPlayers);
        return trunc;
    }
    
    @Override
    protected int getOponentLevel(Monster m){
        return m.getCombatLevelAgainstCultistPlayer();
    }
    
    @Override
    protected boolean shouldConvert(){
        return false;
    }
    
    @Override
    protected Treasure giveMeATreasure(){
        Treasure t;
        int indice_aleatorio = (int) (Math.random() * visibleTreasures.size());
        t = visibleTreasures.get(indice_aleatorio);
        visibleTreasures.remove(indice_aleatorio);
        return t;
    }
    
    @Override
    protected boolean canYouGiveMeATreasure(){
        boolean bool = true;
        
        if(getVisibleTreasures().isEmpty())
            bool = false;
        
        return bool;
    }
    
    public int getTotalCultistPlayers(){
        return totalCultistPlayers;
    }
    
    
}
