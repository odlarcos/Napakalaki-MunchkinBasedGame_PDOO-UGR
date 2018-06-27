/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;
import java.util.ArrayList;
/**
 *
 * @author Oscar
 */
public class Player {
    
    private static final int MAXLEVEL = 10; // Creo que así se declara una constante
    private String name;
    private int level;
    private boolean dead;
    private boolean canISteal;
    private BadConsequence pendingBadConsequence;
    protected Player enemy;
    protected ArrayList <Treasure> visibleTreasures;
    private ArrayList <Treasure> hiddenTreasures;
            
    public Player(String n){
        name = n;
        level = 1;
        dead = true;
        canISteal = true;
        pendingBadConsequence = null;
        enemy = null;
        visibleTreasures = new ArrayList();
        hiddenTreasures = new ArrayList();
        
    }
    public Player(Player p){
        name = p.name;
        level = p.level;
        dead = p.dead;
        canISteal = p.canISteal;
        pendingBadConsequence = p.pendingBadConsequence;
        enemy = p.enemy;
        visibleTreasures = p.visibleTreasures;
        hiddenTreasures = p.hiddenTreasures;
    }
    public String getName(){
        return name;
    }
    
    public Player getEnemy(){
        return enemy;
    }
    
    private void bringToLife(){
        dead = false;
    }
    
    protected int getCombatLevel(){
        int bonus=0;
        for(Treasure t: visibleTreasures)
            bonus += t.getBonus();
        
        int l = level + bonus;
        
        return l;
    }
    
    protected int getOponentLevel(Monster m){
        return m.getCombatLevel();
    }
    
    protected boolean shouldConvert(){
        Dice dice = Dice.getInstance();
        int number = dice.nextNumber();
        
        if(number == 1)
            return true;
        else
            return false;
    }
    
    private int incrementLevels(int l){
        level = level + l;
        
        if(level > MAXLEVEL)
            level = 10;
        
        return level;
    }
    
    private void decrementLevels(int l){
        level = level - l;
        
        if(level < 1)
            level = 1;
    }
    
    private void setPendingBadConsequence(BadConsequence b){
        this.pendingBadConsequence = b;
    }
    
    private void applyPrize(Monster m){
        incrementLevels(m.getPrize().getLevel());
        int nTreasures = m.getPrize().getTreasures();
        int n_Levels = m.getLevelsGained();
        
        if(nTreasures > 0){
            CardDealer dealer = CardDealer.getInstance();
            
            for (int i = 0; i < nTreasures; i++){
                Treasure treasure = dealer.nextTreasure();
                hiddenTreasures.add(treasure);
            }
        }
    }

    public String toString(){
        return name + ", nivel " + Integer.toString(level);
    }
    
    private void applyBadConsequence(Monster m){
        BadConsequence badConsequence = m.getBc();
        int nlevels = badConsequence.getLevels();
        decrementLevels(nlevels);
        
        BadConsequence pendingBad = badConsequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        
        setPendingBadConsequence(pendingBad);
    }

    
    public boolean canMakeTreasureVisible(Treasure t) {

        boolean result = false;

        //Comprobamos que no tenga ya 4 tesoros visibles
        if (this.visibleTreasures.size() < 4) {
            TreasureKind type = t.getType();
            switch (type) {

                case ONEHAND: //En el caso de los de una mano hay que comprar algunas cosas

                    //Si está equipado con dos manos no puede agregar un tesoro de una mano
                    if (isTreasureKindInUse(TreasureKind.BOTHHANDS)) {
                        result = false;
                    } else {

                        //Recorremos los tesoros visibles para ver si ya tiene alguno de una mano (0, 1 o 2)
                        int i = 0;
                        for (Treasure tv : this.visibleTreasures) {
                            if (tv.getType().equals(TreasureKind.ONEHAND)) {
                                i++;
                            }
                        }

                        if (i == 2) {
                            //Si están las dos manos ocupadas no puede
                            result = false;
                        } else {
                            //En caso contrario si que puede
                            result = true;
                        }
                    }
                    break;

                default: //El resto de casos, si esta en uso false, si no true
                    result = !isTreasureKindInUse(type);
                    break;

            }

        }

        return result;
    }

    //Este método lo he hecho personalmente para no duplicar codigo en el método anterior
    private boolean isTreasureKindInUse(TreasureKind type) {
        boolean result = false;
        for (Treasure tv : this.visibleTreasures) {

            if (type.equals(tv.getType())) {

                result = true;
                break;

            }

        }
        return result;
    }

    
    private int howManyVisibleTreasures(TreasureKind tKind){
        int contador = 0;
        for(Treasure t: visibleTreasures)
            if(t.getType() == tKind)
                contador = contador + 1;
        return contador;
    }
    
    private void dieIfNoTreasures(){
        if(visibleTreasures.isEmpty() && hiddenTreasures.isEmpty())
            dead=true;
    }
    
    public boolean isDead(){
        return dead;
    }
    
    public ArrayList <Treasure> getHiddenTreasures(){
        return hiddenTreasures;
    }
    
    public ArrayList <Treasure> getVisibleTreasures(){
        return visibleTreasures;
    }
    
    public CombatResult combat(Monster m){
        
        int myLevel = getCombatLevel(); //1.1.1
        int monsterLevel = getOponentLevel(m); // LO HE CAMBIADO
        if(!canISteal()){
            Dice dice = Dice.getInstance();
            int number = dice.nextNumber();
            if(number < 3)
               monsterLevel += enemy.getCombatLevel();
        }
       CombatResult resultado;
       
        if(myLevel >= monsterLevel){
            applyPrize(m); // Falta
            if(getLevels() >= MAXLEVEL)
              resultado = CombatResult.WINGAME;
            else
                resultado = CombatResult.WIN;
        }
        else{
            applyBadConsequence(m); // Falta
            if (shouldConvert())
                resultado = CombatResult.LOSEANDCONVERT;
            else
                resultado = CombatResult.LOSE;
        }
           
        return resultado;
    }
    
    public void makeTreasureVisible(Treasure t){
        boolean canI = canMakeTreasureVisible(t);
        if(canI){
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        }
    }
    
    public void discardVisibleTreasure(Treasure t){
        visibleTreasures.remove(t);
        if(pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
                pendingBadConsequence.substractVisibleTreasure(t); //FALTA
        dieIfNoTreasures();
    }
    
    public void discardHiddenTreasure(Treasure t){
        hiddenTreasures.remove(t);
        if(pendingBadConsequence != null && !pendingBadConsequence.isEmpty())
                pendingBadConsequence.substractHiddenTreasure(t); //FALTA
        dieIfNoTreasures();
    }
    
    
    public boolean validState(){
        return (( pendingBadConsequence == null || pendingBadConsequence.isEmpty()) && hiddenTreasures.size() <= 4);
    }
    
    public void initTreasures(){
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        bringToLife();
        Treasure treasure = dealer.nextTreasure();
        hiddenTreasures.add(treasure);
        int number = dice.nextNumber();
        
        if(number > 1){
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
        
        if(number == 6){
            treasure = dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
    }

    
    public int getLevels(){
        return level;
    }
    
    public Treasure stealTreasure(){
        boolean canI = canISteal();
        
        if(canI){
            boolean canYou = enemy.canYouGiveMeATreasure();
            if(canYou){
                Treasure treasure = enemy.giveMeATreasure();
                hiddenTreasures.add(treasure);
                haveStolen();
                return treasure;
            }
        }
        
        return null;
    }

    
    public void setEnemy (Player enemy){
        this.enemy = enemy;
    }
    
    protected Treasure giveMeATreasure(){
        Treasure t;
        int indice_aleatorio = (int) (Math.random() * hiddenTreasures.size());
        t = hiddenTreasures.get(indice_aleatorio);
        hiddenTreasures.remove(indice_aleatorio);
        return t;
    }
    
    public boolean canISteal(){
        
        return canISteal;
    }
    
    protected boolean canYouGiveMeATreasure(){ // MAL??
        boolean bool = true;
        
        if(hiddenTreasures.isEmpty()) //hidden? antes ponía visible :/
            bool = false;
        
        return bool;
    }
    
    private void haveStolen() {
        if(!hiddenTreasures.isEmpty())
        {
            canISteal = false;
        }
        else
            canISteal = true;
    }
    
    public void discardAllTreasures(){
        
        ArrayList<Treasure> vcopia = new ArrayList();
        
        for(Treasure th: visibleTreasures)
            vcopia.add(th);
        
        for(Treasure tv: vcopia)
            discardVisibleTreasure(tv);
        
        ArrayList<Treasure> hcopia = new ArrayList();//= hiddenTreasures;
        
        for(Treasure th: hiddenTreasures)
            hcopia.add(th);
        
        for(Treasure th: hcopia)
            discardHiddenTreasure(th);
        
        
        
    }   
    
    
}
