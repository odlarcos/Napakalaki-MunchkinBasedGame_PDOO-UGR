/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author jaime
 */
public class Napakalaki {

    /**
     * @param args the command line arguments
     */
    private static Napakalaki instance = null;
    private ArrayList <Player> players;
    private Player currentPlayer;
    private CardDealer dealer = CardDealer.getInstance();
    private Monster currentMonster;
    
   
    public static Napakalaki getInstance() {
        if (instance == null) {
            instance = new Napakalaki();
        }
        return instance;
    }
    
    private Napakalaki(){
        players = new ArrayList();
        currentPlayer = null;
        currentMonster = null;
    }
    
    
    
    private void initPlayers(ArrayList <String> names){
        for (String s: names)
                players.add(new Player(s));
    } 
    
    private Player nextPlayer(){
        int siguiente;
        if(currentPlayer == null)
            siguiente = (int) (Math.random()* players.size());
        else{
            siguiente = (players.indexOf(currentPlayer) +1) % players.size();
        }
            currentPlayer = players.get(siguiente);
            
            return currentPlayer;
    }
    
    
    
    private boolean nextTurnAllowed(){
        boolean correcto;
        if(currentPlayer == null)
            correcto = true;
        else
            correcto = currentPlayer.validState();
        
        return correcto;
    }
    
    private void setEnemies(){
        
        int pos_enemigo = (int) (Math.random()* players.size());
        int pos_jugador;
        
        for(Player e: players){
            pos_jugador = players.indexOf(e);
            while(pos_enemigo == pos_jugador)
                pos_enemigo = (int) (Math.random()* players.size());
        
            players.get(pos_jugador).setEnemy(players.get(pos_enemigo));
        }
        
        
    }
    
    public CombatResult developCombat(){
        CombatResult resultado = currentPlayer.combat(currentMonster);
        dealer.giveMonsterBack(currentMonster);
        if(resultado == CombatResult.LOSEANDCONVERT){
            Cultist c = dealer.NextCultist();
            CultistPlayer p = new CultistPlayer(currentPlayer,c);
            int pos = players.indexOf(currentPlayer); // pos del currentPlayer
            players.set(pos, p); // Lo cambio por p
            
            for(Player x: players)
                if(x.getEnemy() == currentPlayer) // Si el enemigo es currentPlayer
                    x.setEnemy(p);      // El enemigo pasa a ser p
            
            currentPlayer = p;            
        }
        return resultado;
    }
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        
        for(Treasure t: treasures){
            currentPlayer.discardVisibleTreasure(t);
            dealer.GiveTreasureBack(t);
        }
        
    }
    
    public void discardHiddenTreasures(ArrayList<Treasure> treasures){
        for(Treasure t: treasures){
            currentPlayer.discardHiddenTreasure(t);
            dealer.GiveTreasureBack(t);
        }
    }
    
    public void makeTreasuresVisible(ArrayList<Treasure> treasures){
        for(Treasure t: treasures){
            currentPlayer.makeTreasureVisible(t);
        }
    }
    
    public void initGame(ArrayList<String> players){
        initPlayers(players);
        setEnemies();
        dealer.initCards();
        nextTurn();           
    }
    
    public Player getCurrentPlayer(){
        
        return currentPlayer;
        
    }
    
    public Monster getCurrentMonster(){
        
        return currentMonster;
    }
    
    public boolean nextTurn(){
        boolean stateOK = nextTurnAllowed();
        
        if(stateOK){
            currentMonster = dealer.nextMonster(); // Falta
            currentPlayer = nextPlayer();
            if(currentPlayer.isDead())
                currentPlayer.initTreasures(); // Falta
        }
        return stateOK;
        
        // mirar erseco para un else???
    }
    
    public boolean endOfGame(CombatResult result){
        boolean bool = false;
        if ( result == CombatResult.WINGAME)
            bool = true;
        
        return bool;
    }
    
}
